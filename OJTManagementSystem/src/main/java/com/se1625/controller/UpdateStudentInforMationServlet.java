/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.tblfollowing_post.TblFollowing_PostDAO;
import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class UpdateStudentInforMationServlet extends HttpServlet {

    private static final int VALID_CREADIT = 68;// số tín chỉ tối thiểu để thực hiện thực tập

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String studentCode = request.getParameter("txtStudentCode");
        int credit = Integer.parseInt(request.getParameter("txtNumberOfCredit"));
        String button = request.getParameter("btAction");

        // credit khi update bị dưới credit cho phép sẽ gửi thông báo không đủ điều kiện
        // nếu bấm xác nhận thì sẽ xóa tài khoản mà trạng thái isIntern là not yet
        // khỏi hệ thống (xóa luôn các bài mà tài khoản đó theo dõi, tài khoản đó apply job)
        // và sẽ thực hiện gửi tin nhắn thông báo tới tài khoản trước khi thực hiện xóa
        HttpSession session = request.getSession(false);

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = MyApplicationConstants.UpdateStudentInforMationFeature.LOGIN_PAGE;
        try {
            if (session != null) {
                TblAccountDTO admin = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");
                if (admin != null) {
                    if ("Update".equals(button)) {
                        if (credit < VALID_CREADIT) {
                            url = properties.getProperty(MyApplicationConstants.UpdateStudentInforMationFeature.ADMIN_MANAGEMENT_STUDENT_CONTROLLER);
                            request.setAttribute("INVALID_CREDIT", "The minimum number of credits are required 68 credits.<br /> "
                                    + "Therefore, this student is not eligible to use the website."
                                    + "<br /> Do you want to remove this student from the system?<br />_" + studentCode);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            //thực hiện update credit
                            TblStudentDAO studentDAO = new TblStudentDAO();
                            boolean result = studentDAO.updateCreditOfStudent(studentCode, credit);
                            if (result) {
                                url = MyApplicationConstants.UpdateStudentInforMationFeature.ADMIN_MANAGEMENT_STUDENT_CONTROLLER;
                                response.sendRedirect(url);
                            }
                        }
                    } else if ("Appcept".equals(button)) {
                        //thực hiện delete following post, application, student, account
                        //1.delete following Post
                        TblFollowing_PostDAO followingPostDAO = new TblFollowing_PostDAO();
                        followingPostDAO.getFollowingPost(studentCode);
                        List<TblFollowing_PostDTO> listFollowingPost = followingPostDAO.getFollowingPostByFilter();
                        boolean deleteFollowingPost = false;
                        if (listFollowingPost != null) {
                            deleteFollowingPost = followingPostDAO.deleteFollowingPost(studentCode);
                        }
                        //2. delete application
                        TblApplicationDAO applicationDAO = new TblApplicationDAO();
                        List<String> listAttachment = applicationDAO.getAttachmentOfStudent(studentCode);
                        boolean deleteApplication = false;
                        if (listAttachment != null) {
                            deleteApplication = applicationDAO.deleteApplicationOfStudent(studentCode);
                            String realPath = request.getServletContext().getRealPath("/CVs");
                            for (String attachment : listAttachment) {
                                String attachmentPath = realPath + "/" + attachment;
                                Files.deleteIfExists(Paths.get(attachmentPath));
                            }
                        }
                        //3. delete student
                        TblStudentDAO studentDAO = new TblStudentDAO();
                        TblStudentDTO student = studentDAO.getStudentInformation(studentCode);
                        boolean deleteStudent = studentDAO.deleteStudent(studentCode);

                        //4. delete account
                        TblAccountDAO accountDAO = new TblAccountDAO();
                        TblAccountDTO account = accountDAO.getAccount(student.getAccount().getEmail());
                        if (account.getAvatar() != null) {
                            String avatarRealPath = request.getServletContext().getRealPath("/avatars");
                            String avatarName = account.getAvatar();
                            String avatarPath = avatarRealPath + "/" + avatarName;
                            Files.deleteIfExists(Paths.get(avatarPath));
                        }
                        boolean deleteAccount = accountDAO.deleteAccout(student.getAccount().getEmail());
                        url = MyApplicationConstants.UpdateStudentInforMationFeature.ADMIN_MANAGEMENT_STUDENT_CONTROLLER;
                        response.sendRedirect(url);
                    } else if ("Cancel".equals(button)) {
                        url = MyApplicationConstants.UpdateStudentInforMationFeature.ADMIN_MANAGEMENT_STUDENT_CONTROLLER;
                        response.sendRedirect(url);
                    }
                } //if admin is created
                else {
                    response.sendRedirect(url);
                }
            } //if session exist
            else {
                response.sendRedirect(url);
            }
        } catch (SQLException ex) {
            log("SQLException at UpdateStudentInforMationServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException at UpdateStudentInforMationServlet " + ex.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
