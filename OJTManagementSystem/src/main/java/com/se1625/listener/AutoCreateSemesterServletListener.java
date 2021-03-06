package com.se1625.listener;

import com.se1625.tblsemester.TblSemesterDAO;
import com.se1625.tblsemester.TblSemesterDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.MyApplicationHelper;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class AutoCreateSemesterServletListener implements ServletContextListener {

    private Timer timer;
    private Properties properties;
    private long period;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            MyApplicationHelper.getSemesterDate(context);
            properties = (Properties) context.getAttribute("SEMESTER_DATE");
            // period the period between successive executions
            period = Integer.parseInt(properties.getProperty("checkingPeriod").trim());
            startTimer(context);
        } catch (IOException ex) {
            context.log("IOException at CreateSemesterServletListener " + ex.getMessage());
        }
    }

    private void startTimer(ServletContext servletContext) {
        timer = new Timer();
        // Delay 1 second to first execution
        final long initialDelay = 1000;
        final ServletContext context = servletContext;
        final long oldPeriod = period;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    MyApplicationHelper.getSemesterDate(context);
                    properties = (Properties) context.getAttribute("SEMESTER_DATE");
                    period = Integer.parseInt(properties.getProperty("checkingPeriod").trim());
                    String springStart = properties.getProperty("SpringStart");
                    String springEnd = properties.getProperty("SpringEnd");
                    String summerStart = properties.getProperty("SummerStart");
                    String summerEnd = properties.getProperty("SummerEnd");
                    String fallStart = properties.getProperty("FallStart");
                    String fallEnd = properties.getProperty("FallEnd");

                    String summerSemester = properties.getProperty("SummerSemester");
                    String fallSemester = properties.getProperty("FallSemester");
                    String springSemester = properties.getProperty("SpringSemester");

                    String newSemester = "";
                    Date startSemester = null;
                    Date endSemester = null;

                    TblSemesterDAO semesterDAO = new TblSemesterDAO();
                    TblSemesterDTO semester = semesterDAO.getCurrentSemester();
                    TblStudentDAO studentDAO = new TblStudentDAO();
                    List<TblStudentDTO> listStudentOldSemester = studentDAO.getListStudent(semester.getSemesterID());
                    LocalDate today = LocalDate.now();
                    Date currentDate = Date.valueOf(today);
                    java.util.Date springStartDate = new java.util.Date(today.getYear() + "/" + springStart);
                    java.util.Date summerStartDate = new java.util.Date(today.getYear() + "/" + summerStart);
                    java.util.Date fallStartDate = new java.util.Date(today.getYear() + "/" + fallStart);
                    java.util.Date springEndDate = new java.util.Date(today.getYear() + "/" + springEnd);
                    java.util.Date summerEndDate = new java.util.Date(today.getYear() + "/" + summerEnd);
                    java.util.Date fallEndDate = new java.util.Date(today.getYear() + "/" + fallEnd);

                    SimpleDateFormat dayFormat
                            = new SimpleDateFormat("yyyy-MM-dd");
                    boolean status = false;
                    if (currentDate.equals(springStartDate) || currentDate.equals(summerStartDate)
                            || currentDate.equals(fallStartDate)) {
                        if (semester == null) {
                            //t???o semester d???a v??o n??m v?? th??ng hi???n t???i
                            if (today.getMonthValue() == springStartDate.getMonth() + 1) {
                                //t???o k?? summer
                                newSemester = summerSemester + " " + today.getYear();
                                startSemester = Date.valueOf(dayFormat.format(summerStartDate));
                                endSemester = Date.valueOf(dayFormat.format(summerEndDate));
                            } else if (today.getMonthValue() == summerStartDate.getMonth() + 1) {
                                //t???o k?? fall
                                newSemester = fallSemester + " " + today.getYear();
                                startSemester = Date.valueOf(dayFormat.format(fallStartDate));
                                endSemester = Date.valueOf(dayFormat.format(fallEndDate));
                            } else if (today.getMonthValue() == fallStartDate.getMonth() + 1) {
                                //t???o k?? spring
                                newSemester = springSemester + " " + today.getYear();
                                startSemester = Date.valueOf(dayFormat.format(springStartDate));
                                endSemester = Date.valueOf(dayFormat.format(springEndDate));
                            }
                        } else {
                            Date endDate = semester.getEndDate();
                            if (endDate.equals(currentDate)) {
                                status = true;
                                String semesterName = semester.getSemesterName();
                                StringTokenizer stk = new StringTokenizer(semesterName, " ");
                                String prefix = stk.nextToken();
                                String suffix = stk.nextToken();
                                int year = Integer.parseInt(suffix);
                                int month = endDate.getMonth() + 1;
                                if (prefix.equalsIgnoreCase("Summer")) {
                                    prefix = "Fall";
                                    startSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + fallStart)));
                                    endSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + fallEnd)));
                                } else if (prefix.equalsIgnoreCase("Fall")) {
                                    prefix = "Spring";
                                    startSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + springStart)));
                                    if (month == 12) {
                                        year = year + 1;
                                    }
                                    endSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + springEnd)));
                                } else if (prefix.equalsIgnoreCase("Spring")) {
                                    prefix = "Summer";
                                    startSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + summerStart)));
                                    endSemester = Date.valueOf(dayFormat.format(new java.util.Date(year + "/" + summerEnd)));
                                }
                                newSemester = prefix + " " + year;
                            }
                        }
                        if (status) {
                            semesterDAO.addNextSemester(newSemester, startSemester, endSemester);
                            //change status of all of students in the old semester
                            for (TblStudentDTO tblStudentDTO : listStudentOldSemester) {
                                studentDAO.updateDisableStatusOfStudent(true, tblStudentDTO.getStudentCode());
                            }
                        }
                        if (oldPeriod != period) {
                            this.cancel();
                            startTimer(context);
                        }
                    }
                    //th???i gian c???a c??c k?? m???i l?? 1/1(31/12): spring, 1/5(30/4): summer, 1/9(31/8): false c???a n??m hi???n t???i (ng??y t???o s??? tr?????c 1 ng??y)
                    //n???u ng??y cu???i c??ng l?? ng??y 31/7(ng??y h??m nay) th?? s??? t???o k?? m???i
                } catch (SQLException ex) {
                    context.log("SQLException at CreateSemesterServletListener " + ex.getMessage());
                } catch (NamingException ex) {
                    context.log("NamingException at CreateSemesterServletListener " + ex.getMessage());
                } catch (IOException ex) {
                    context.log("IOException at CreateSemesterServletListener " + ex.getMessage());
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, initialDelay, period);
        //timer.scheduleAtFixedRate(timerTask, initialDelay, period);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("SEMESTER_DATE");
        timer.cancel();
    }
}
