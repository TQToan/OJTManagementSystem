/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblfollowing_post.TblFollowing_PostDAO;
import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "StudentDashboardServlet", urlPatterns = {"/StudentDashboardServlet"})
public class StudentDashboardServlet extends HttpServlet {

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
        //1. get servletContext 
        ServletContext context = this.getServletContext();
        //2. get properties
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = properties.getProperty(MyApplicationConstants.StudentDasboardFeature.STUDENT_DASHBOARD_PAGE);
        HttpSession session = request.getSession(false);
        int size;
        try {
            if (session != null) {
                //Info student
                
                // Thanh fix name session
                TblAccountDTO dto = (TblAccountDTO) session.getAttribute("ACCOUNT");
//            String mail = (String) session.getAttribute("EMAIL_USER");
                TblStudentDAO dao = new TblStudentDAO();
                TblStudentDTO student = dao.showStudentInfo(dto.getEmail());
//                System.out.println(dto.getEmail());
                request.setAttribute("STUDENT", student);
                TblFollowing_PostDAO followPostDao = new TblFollowing_PostDAO();
                followPostDao.getFollowingPost(student.getStudentCode());
                List<TblFollowing_PostDTO> listFollowingCompanyPostByFilter = followPostDao.getFollowingPostByFilter();
                if (listFollowingCompanyPostByFilter != null) {
                    size = listFollowingCompanyPostByFilter.size();
                    request.setAttribute("SIZE_OF_LIST_DASHBOARD", size);
                } else {
                    size = 0;
                    request.setAttribute("SIZE_OF_LIST_DASHBOARD", size);
                }
                //Recomend post
                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                //lấy 6 bài post
                companyPostDAO.getListRecomendPost(student.getMajor());
                List<TblCompany_PostDTO> listPostHome = companyPostDAO.getCompanyPostListHome();
                request.setAttribute("LIST_POST_HOME", listPostHome);

            }
        } catch (SQLException ex) {
            log("SQL Exception occurs in process at StudentDashboardController", ex.getCause());
        } catch (NamingException ex) {
            log("Naming Exception occurs in process at StudentDashboardController", ex.getCause());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
