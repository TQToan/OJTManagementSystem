/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.utils.MyApplicationConstants;
import com.se1625.utils.MyApplicationHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
public class AdminUpdateStatusCompanyServlet extends HttpServlet {

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

        String companyID = request.getParameter("companyID");
        String companyStatus = request.getParameter("Status");

        ServletContext context = this.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAPS");

        String url = MyApplicationConstants.AdminUpdateStatusCompanyFeature.LOGIN_PAGE;
        HttpSession session = request.getSession(false);
        try {
            boolean status = false;
            if ("Success".equals(companyStatus)) {
                status = true;
            }
            if (session != null) {
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");
                if (accountDTO != null) {
                    TblCompanyDAO companyDAO = new TblCompanyDAO();
                    boolean result = companyDAO.updateCompanyStatus(companyID, status);
                    if (result) {
                        TblCompanyDTO company = companyDAO.getCompany(companyID);
                        TblAccountDAO accountDAO = new TblAccountDAO();
                        TblAccountDTO systemAccount = accountDAO.GetAccountByRole(4);
                        String subject = "The signing result";
                        String message = "Dear " + company.getAccount().getName() + " company,\n"
                                + "\n"
                                + "The OJT system wants to announce that your company was changed signed status by FPT University."
                                + "You can create new posts to start recruiting interns for your jobs.\n"
                                + "\n"
                                + "Regards,\n"
                                + "The support OJT team";
                        MyApplicationHelper.sendEmail(company.getAccount(), systemAccount, message, subject);
                        url = prop.getProperty(MyApplicationConstants.AdminUpdateStatusCompanyFeature.ADMIN_COMPANY_MANAGER_CONTROLLER);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                } else {
                   response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("AdminUpdateStatusCompanyServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("AdminUpdateStatusCompanyServlet_SQLException " + ex.getMessage());
        } catch (AddressException ex) {
            log("AddressException at CreateNewCompanyPostServlet " + ex.getMessage());
        } catch (MessagingException ex) {
            log("MessagingException at CreateNewCompanyPostServlet " + ex.getMessage());
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
