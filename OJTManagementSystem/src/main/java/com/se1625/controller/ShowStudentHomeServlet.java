/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblmajor.TblMajorDAO;
import com.se1625.tblmajor.TblMajorDTO;
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

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
@WebServlet(name = "ShowStudentHomeServlet", urlPatterns = {"/ShowStudentHomeServlet"})
public class ShowStudentHomeServlet extends HttpServlet {

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
        
        // check session with role is student 
        // lấy 6 bài post công ty có ngày hết hạn lâu nhất show ra
        // lấy các avatar của các công ty đã kí kết với nhà trường
        // lấy name của các công ty để hiển thị trên filter
        // lấy các major để hiển thị lên filter
        
        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
        TblCompanyDAO companyDAO = new TblCompanyDAO();
        TblMajorDAO majorDAO = new TblMajorDAO();
        String url = properties.getProperty(MyApplicationConstants.
                ShowStudentHomeFeature.STUDENT_HOME_PAGE);
        try {
            //lấy 6 bài post
            companyPostDAO.getListPostHome();
            List<TblCompany_PostDTO> listPostHome = companyPostDAO.getCompanyPostListHome();
            request.setAttribute("LIST_POST_HOME", listPostHome);
            
            //get name của các công ty
            companyDAO.getNameCompanies();
            List<TblCompanyDTO> listNameCompany = companyDAO.getListNameCompany();
            request.setAttribute("COMPANY_NAME", listNameCompany);
            
            //get avatar của signed công ty
            companyDAO.getAvatarSignedCompany();
            List<TblCompanyDTO> listAvatarSignedCompany = companyDAO.getListAvatarSignedCompany();
            request.setAttribute("LIST_AVATAR_SIGNED_COMPANY", listAvatarSignedCompany);
            
            //get major 
            majorDAO.getNameMajor();
            List<TblMajorDTO> listNameMajor = majorDAO.getListNameMajor();
            request.setAttribute("LIST_NAME_MAJOR", listNameMajor);
            
        } catch (SQLException ex) {
            log("SQLException at processRequest " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException at processRequest " + ex.getMessage());
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
