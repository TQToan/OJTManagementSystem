/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.tblcompany_post.CompanyPostDetailError;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author ASUS
 */
public class CompanyUpdateStatusIntershipApplicationServlet extends HttpServlet {

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

        String studentCode = request.getParameter("studentCode");
        String companyPostIDString = request.getParameter("companyPostID");
        String companyConfirmString = request.getParameter("action");

        ServletContext context = this.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAPS");
        String url = prop.getProperty(MyApplicationConstants.CompanyUpdateStatusIntershipApplicationFeature.LOGIN_PAGE);

        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("COMPANY_ROLE");
                if (accountDTO != null) {

                    //convert data type
                    int companyPostID = Integer.parseInt(companyPostIDString);
                    int companyConfirm = -2;
                    if (companyConfirmString.equals("Interview")) {
                        companyConfirm = 2;
                    } else if (companyConfirmString.equals("Accept")) {
                        companyConfirm = 1;
                    } else if(companyConfirmString.equals("Reject")){
                        companyConfirm = -1;
                    }
                    //get quantityInterns of company
                    TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                    TblCompany_PostDTO companyPostDTO = companyPostDAO.getCompanyPost(companyPostID);
                    int quantityIterns = companyPostDTO.getQuantityIterns();

                    if (quantityIterns <= 0 && companyConfirm == 1) {
                        CompanyPostDetailError error = new CompanyPostDetailError();
                        error.setQuantitytInternsNotEngough("The number of applications is enough");
                        request.setAttribute("ERROR_QUANTITY_INTERNS", error);
                    } else {
                        TblApplicationDAO applicationDAO = new TblApplicationDAO();
                        boolean result = applicationDAO.updateStatusCompanyConfirm(studentCode, companyPostID, companyConfirm);
                        if (result) {
                            //update quantityInterns if accepted
                            if (companyConfirm == 1) {
                                companyPostDAO.updateQuantityInterns(companyPostID);
                            }
                        }
                    }
                    url = prop.getProperty(
                            MyApplicationConstants.CompanyUpdateStatusIntershipApplicationFeature.COMPANY_SEARCH_INTERNS_CONTROLLER);

                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at CompanyUpdateStatusIntershipApplicationServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at CompanyUpdateStatusIntershipApplicationServlet " + ex.getMessage());
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
