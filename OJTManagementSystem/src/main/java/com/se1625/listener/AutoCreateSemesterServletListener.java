package com.se1625.listener;


import com.se1625.tblsemester.TblSemesterDAO;
import com.se1625.tblsemester.TblSemesterDTO;
import com.se1625.utils.MyApplicationHelper;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    private Timer t;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        t = new Timer();
        // Delay 1 Minute to first execution
        long initialDelay = 1000;
    try {
            MyApplicationHelper.getSemesterDate(sce.getServletContext());
            final ServletContext context = sce.getServletContext();
            final Properties properties = (Properties) context.getAttribute("SEMESTER_DATE");
            // period the period between successive executions
            long period = Integer.parseInt(properties.getProperty("checkingPeriod").trim());
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    try {

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

                        if (currentDate.equals(springStartDate) || currentDate.equals(summerStartDate)
                                || currentDate.equals(fallStartDate)) {
                            if (semester == null) {
                                //tạo semester dựa vào năm và tháng hiện tại
                                if (today.getMonthValue() == springStartDate.getMonth() + 1) {
                                    //tạo kì summer
                                    newSemester = summerSemester + " " + today.getYear();
                                    startSemester = Date.valueOf(dayFormat.format(summerStartDate));
                                    endSemester = Date.valueOf(dayFormat.format(summerEndDate));
                                } else if (today.getMonthValue() == summerStartDate.getMonth() + 1) {
                                    //tạo kì fall
                                    newSemester = fallSemester + " " + today.getYear();
                                    startSemester = Date.valueOf(dayFormat.format(fallStartDate));
                                    endSemester = Date.valueOf(dayFormat.format(fallEndDate));
                                } else if (today.getMonthValue() == fallStartDate.getMonth() + 1) {
                                    //tạo kì spring
                                    newSemester = springSemester + " " + today.getYear();
                                    startSemester = Date.valueOf(dayFormat.format(springStartDate));
                                    endSemester = Date.valueOf(dayFormat.format(springEndDate));
                                }
                            } else {
                                Date endDate = semester.getEndDate();
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
                            semesterDAO.addNextSemester(newSemester, startSemester, endSemester);
                        }
                        //thời gian của các kì mới là 1/1(31/12): spring, 1/5(30/4): summer, 1/9(31/8): false của năm hiện tại (ngày tạo sẽ trước 1 ngày)
                        //nếu ngày cuối cùng là ngày 31/7(ngày hôm nay) thì sẽ tạo kì mới
                    } catch (SQLException ex) {
                        context.log("SQLException at CreateSemesterServletListener " + ex.getMessage());
                    } catch (NamingException ex) {
                        context.log("NamingException at CreateSemesterServletListener " + ex.getMessage());
                    }

                }

            };
            t.scheduleAtFixedRate(tt, initialDelay, period);
        } catch (IOException ex) {
            sce.getServletContext().log("IOException at CreateSemesterServletListener " + ex.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("SEMESTER_DATE");
        t.cancel();
    }
}
