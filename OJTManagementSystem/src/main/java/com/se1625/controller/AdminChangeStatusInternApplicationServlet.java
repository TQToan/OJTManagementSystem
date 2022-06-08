/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Thanh Huy
 */
public class AdminChangeStatusInternApplicationServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = MyApplicationConstants.ShowStudentHomeFeature.LOGIN_PAGE;
        
        int schoolStatus= 7;
        int applID = 0;
        String applicationID = request.getParameter("txtApplicationID");
        String btnAction = request.getParameter("btnAction");
        
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                TblAccountDTO account = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");
                if (account.getIs_Admin() == 1) {
                    if (applicationID != null && !"".equals(applicationID)){
                         applID = Integer.parseInt(applicationID);
                     }
                     if (btnAction != null && !"".equals(btnAction)){
                         schoolStatus = Integer.parseInt(btnAction);
                     }
                    url = properties.getProperty(MyApplicationConstants.AdminInternApplication.ADMIN_SHOW_INTERN_APPLICATION_CONTROLLER);
                    
                   
                    TblApplicationDAO applDAO = new TblApplicationDAO();
                    applDAO.changeStatusSchool(applID, schoolStatus);
                    
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                    
                } else {
                    response.sendRedirect(url);
                }
            } else{
                response.sendRedirect(url);
            }
        } 
        catch (SQLException ex) {
            log("SQLException occurs AdminChangeStatusInternApplicationServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException occurs AdminChangeStatusInternApplicationServlet " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("NumberFormatException occurs AdminChangeStatusInternApplicationServlet " + ex.getMessage());

        }finally{
            
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
