/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblfollowing_post.TblFollowing_PostDAO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThanhTy
 */
@WebServlet(name = "StudentDeleteSaveJobServlet", urlPatterns = {"/StudentDeleteSaveJobServlet"})
public class StudentDeleteSaveJobServlet extends HttpServlet {

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

//        String studentCode = request.getParameter("studentCode");
        int postID = Integer.parseInt(request.getParameter("postID"));
        //1. get servletContext 
        ServletContext context = this.getServletContext();
        //2. get properties
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = properties.getProperty(MyApplicationConstants.StudentSaveJobFeature.STUDENT_SAVE_JOB_PAGE);
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                //lay session
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("LOGIN_SUCESS");
                TblFollowing_PostDAO followPostDAO = new TblFollowing_PostDAO();
                //lay studentCode
                TblStudentDAO studentDAO = new TblStudentDAO();
                TblStudentDTO studentDTO = studentDAO.showStudentInfo(accountDTO.getEmail());
                
                request.setAttribute("STUDENT_CODE", studentDTO);
                System.out.println(studentDTO.getStudentCode());
                boolean delete = followPostDAO.deleteFollowingPost(postID, studentDTO.getStudentCode());
                if (delete) {
                    url = MyApplicationConstants.StudentSaveJobFeature.STUDENT_SEARCH_SAVE_JOB_CONTROLLER
                            +"?txtJob=&txtCompany&nameLocation";
                }

            }
        } catch (SQLException ex) {
            log("SQL Exception occurs in process at DeleteServlet", ex.getCause());
        } catch (NamingException ex) {
            log("Naming Exception occurs in process at DeleteServlet", ex.getCause());
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
