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
 * @author ThanhTy
 */
public class AdminSearchCompanyPostServlet extends HttpServlet {

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

        //get Paramente
        String titlePost = request.getParameter("txtTitle");
        String companyName = request.getParameter("txtCompanyName");
        String nameStatus = request.getParameter("nameStatus");
        String xpage = request.getParameter("page");
        //Phan trang
        int page;
        int numberRowsPerPage = 10;
        int start;
        int end;
        int sizeOfList;
        //URL
        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = MyApplicationConstants.SearchStudentAppliedJobFeature.LOGIN_PAGE;
        //Lay session
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                TblAccountDTO admin = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");
                //get CompanyoPost
                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                
                if (admin != null) {
//                    if ("".equals(titlePost) && "".equals(companyName) && "".equals(nameStatus)) {
//                        url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_POST_MANAGE_PAGE);
//                        response.sendRedirect(url);
//                    } 
                    //else {
                        url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_POST_MANAGE_PAGE);
                        companyPostDAO.searchPostByFilterAsAdminRole(titlePost, companyName, nameStatus);
                        List<TblCompany_PostDTO> listCompanyPost = companyPostDAO.getCompanyPostListAdminPage();
                        //Phan trang
                        if (listCompanyPost != null) {
                            sizeOfList = listCompanyPost.size();

                            if (xpage == null) {
                                page = 1;
                            } // load page save job 
                            else {
                                page = Integer.parseInt(xpage);
                            } // when choose number of page

                            int numberPage = sizeOfList % numberRowsPerPage;

                            if (numberPage == 0) {
                                numberPage = sizeOfList / numberRowsPerPage;
                            } else {
                                numberPage = (sizeOfList / numberRowsPerPage) + 1;
                            }
                            start = (page - 1) * numberRowsPerPage;
                            end = Math.min(page * numberRowsPerPage, sizeOfList);

                            List<TblCompany_PostDTO> companyPostPerPage = companyPostDAO.
                                    getListByPage(listCompanyPost, start, end);
                            //Set attribute
                            request.setAttribute("COMPANY_POST_LIST", companyPostPerPage);
                            request.setAttribute("page", page);
                            request.setAttribute("numberPage", numberPage);

                        } // if company post list exisst
                        else {
                            url = properties.getProperty(MyApplicationConstants.AdminShowPostManagementFeature.ADMIN_POST_MANAGE_PAGE);
                            sizeOfList = 0;
                        } // if company post list NOT exisst
                        request.setAttribute("SIZE_OF_LIST", sizeOfList);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                   // }
                }//if admin exist
                else {
                    response.sendRedirect(url);
                } // if admin NOT exist
            } //if session exist
            else {
                response.sendRedirect(url);
            }//if session NOT exist
        } catch (SQLException ex) {
            log("SQLException at SearchCompanyPostServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException at SearchCompanyPostServlet " + ex.getMessage());
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
