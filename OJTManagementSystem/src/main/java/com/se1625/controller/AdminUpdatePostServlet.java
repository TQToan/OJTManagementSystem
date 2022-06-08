/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author ThanhTy
 */
public class AdminUpdatePostServlet extends HttpServlet {

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

        //lay postID
        int postID = Integer.parseInt(request.getParameter("postID"));
        String school_confirm = request.getParameter("school_confirm");
        int statusPost = Integer.parseInt(request.getParameter("statusPost"));
        
//        //get param load lai trang
//        String titlePost = request.getParameter("txtTitle");
//        String companyName = request.getParameter("txtCompanyName");
//        String nameStatus = request.getParameter("nameStatus");
        String xpage = request.getParameter("page");
        
        String save = request.getParameter("save");

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = MyApplicationConstants.LoginFeture.LOGIN_PAGE;

        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                //lay info admin
                TblAccountDTO admin = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");

                //DAO Companypost
                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                if (admin != null) {
                    boolean check = companyPostDAO.updateStatusCompanyPostAsAdmin(postID, school_confirm, statusPost);
                    if (check) {
                        if (save.equals("adminViewPostPage")) {
                            url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_VIEW_POST_DETAIL_CONTROLLER)
                                    + "?postID=" + postID + "&page=" + xpage;
//                            request.setAttribute("page", xpage);
                            request.setAttribute("UPDATE_SUSCESS", "Update success");
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else 
                            if (save.equals("adminPostManagePage")) {
                            //url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_SHOW_POST_MANAGE_CONTROLLER);
                            
                            url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_SEARCH_POST_MANAGE_CONTROLLER)
                                    +"?page=" + xpage + "&txtTitle=&txtCompanyName=&nameStatus=";
                            request.setAttribute("UPDATE_SUSCESS", "Update success");
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        }

//                        response.sendRedirect(url);
                    } else {
                        url = MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_VIEW_POST_DETAIL_CONTROLLER
                                + "?postID=" + postID;
                        response.sendRedirect(url);
                    }

                } //if admin exist
                else {
                    response.sendRedirect(url);
                } //if admin not exist
            } //if session exit
            else {
                response.sendRedirect(url);
            } //if session NOT exit
        } catch (NumberFormatException ex) {
            log("NumberFormatException at HomeShowCompanyDetailServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at SearchCompanyStudentHomeServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException at SearchCompanyStudentHomeServlet " + ex.getMessage());
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
