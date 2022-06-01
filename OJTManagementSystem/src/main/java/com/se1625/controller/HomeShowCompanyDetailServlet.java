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
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Thanh Huy
 */
@WebServlet(name = "HomeShowCompanyDetailServlet", urlPatterns = {"/HomeShowCompanyDetailServlet"})
public class HomeShowCompanyDetailServlet extends HttpServlet {

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
        String sPostID = request.getParameter("postID");

        //1. get servletContext 
        ServletContext context = this.getServletContext();
        //2. get properties
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = properties.getProperty(MyApplicationConstants.SearchComanyStudentHomeFeature.HOME_SHOW_COMPANY_DETAIL_JSP);

        try {
            if (sPostID.isEmpty()) {
                url = "homeCPostDetail.html"; // đưa tạm lỗi ra trang .html
            } else {
                int nPostID = Integer.parseInt(sPostID);
                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                TblCompanyDAO companyDAO = new TblCompanyDAO();
                TblCompanyDTO companyDTO = new TblCompanyDTO();
                
                TblCompany_PostDTO companyPostDTO = new TblCompany_PostDTO();
                companyPostDTO = companyPostDAO.searchPostByPostID(nPostID);
                
                companyDTO = companyDAO.searchCompanyByCompanyID(companyPostDTO.getCompany().getCompanyID());
                companyPostDTO.setCompany(companyDTO);
                request.setAttribute("POST_DETAIL",companyPostDTO);
                
                
                // get majorID từ majorName;
                // gọi hàm searchPostByFilter;
                TblMajorDAO majorDAO = new TblMajorDAO();
                int majorID = majorDAO.getMajorIDByMajorName(companyPostDTO.getMajorName());
                companyPostDAO.searchPostByFilter("",majorID,"");
                List<TblCompany_PostDTO> listCompanyDTO = companyPostDAO.getCompanyPostByFilter();
                request.setAttribute("LIST_OTHER", listCompanyDTO);
            }
            
        }
        catch (NumberFormatException ex) {
            log("NumberFormatException occurs at HomeShowCompanyDetailServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException occurs at SearchCompanyStudentHomeServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException occurs at SearchCompanyStudentHomeServlet " + ex.getMessage());
        } 
        
        finally {
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
