/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblaccount.TblAccountError;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginAccountsServlet", urlPatterns = {"/LoginAccountsServlet"})
public class LoginAccountsServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String remember = request.getParameter("AutoLogin");
        
        //get properties
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAPS");
        
        String url = siteMap.getProperty(
                MyApplicationConstants.LoginFeture.LOGIN_PAGE);
        
        try {
            
            //check format input login
            TblAccountError error = new TblAccountError();
            boolean foundError = false;
            if (username.trim().length() == 0) {
                error.setUserEmailEmpty("Please enter your email");
                foundError = true;
            }
            if (password.trim().length() == 0) {
                error.setUserPasswordEmpty("Please enter your password");
                foundError = true;
            }

            
            if (foundError) {
                request.setAttribute("ERROR", error);
            } else {
                HttpSession session = request.getSession();
                if (session != null) {
                    
                    //check login
                    TblAccountDAO dao = new TblAccountDAO();
                    boolean result = dao.checkLogin(username, password);
                    if (result) {
                        if (remember != null) {
                            //add cookie
                            Cookie cookie = new Cookie(username, password);
                            cookie.setMaxAge(60);
                            response.addCookie(cookie);
                        }
                        
                        // set session
                        TblAccountDTO dto = dao.getAccount(username);
                        session.setAttribute("ACCOUNT", dto);
                        url = siteMap.getProperty(
                                MyApplicationConstants.LoginFeture.COMPANY_DASHBOARD_PAGE);
                    } else {
                        error.setAccountError("Your email or password may be incorrect");
                        request.setAttribute("ERROR", error);
                    }
                }

            }
        } catch (NamingException ex) {
            log("LoginAccountServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginAccountServlet_SQLException " + ex.getMessage());
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
