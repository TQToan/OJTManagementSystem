/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
@WebServlet(name = "SearchCompanyStudentHomeServlet", urlPatterns = {"/SearchCompanyStudentHomeServlet"})
public class SearchCompanyStudentHomeServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        String companyID = request.getParameter("nameCompany");
        String majorID = request.getParameter("nameMajor");
        String nameLocation = request.getParameter("nameLocation");
        //1. get servletContext 
        ServletContext context = this.getServletContext();
        //2. get properties
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = MyApplicationConstants.SearchComanyStudentHomeFeature.STUDENT_HOME_PAGE;
        int page;
        int numberProductPage = 10;
        int start;
        int size;
        int end;
        try {
            if (companyID.isEmpty() == false || majorID.isEmpty() == false
                    || nameLocation.isEmpty() == false) {
                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                companyPostDAO.searchPostByFilter(companyID, majorID, nameLocation);
                List<TblCompany_PostDTO> listCompanyPostByFilter = companyPostDAO.getCompanyPostByFilter();
                size = listCompanyPostByFilter.size();
                String xpage = request.getParameter("page");
                if (xpage == null) {
                        page = 1;
                    } else {
                        page = Integer.parseInt(xpage);
                    }
                    int numberPage = size % numberProductPage;
                    if (numberPage == 0) {
                        numberPage = size / numberProductPage;
                    } else {
                        numberPage = (size / numberProductPage) + 1 ;
                    }
                    start = (page - 1) * numberProductPage;
                    end = Math.min(page * numberProductPage, size);
                    List<TblCompany_PostDTO> companyPostPerPage = companyPostDAO.
                            getListByPage(listCompanyPostByFilter, start, end);
                    
                    request.setAttribute("LIST_RESULT", companyPostPerPage);
                    request.setAttribute("page", page);
                    request.setAttribute("numberPage", numberPage);
                    
                    
                url = properties.getProperty(MyApplicationConstants.
                        SearchComanyStudentHomeFeature.SEARCH_COMPANY_POST_PAGE);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
        } catch (SQLException ex) {
            log("SQLException occurs at SearchCompanyStudentHomeServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException occurs at SearchCompanyStudentHomeServlet " + ex.getMessage());
        } 
        // check session với role là student 
        // check nếu cả companyname và namemajor, nameLocation đều rỗng thì vẫn gọi lại 
        //       servlet showStudenthomeServlet để show lại các thông tin
        // check nếu 1 trong 2 có giá trị thì bắt đầu search có 3 trường hợp:
        // companyname có, namemajor không, nameLocation không
        // name major có, companyname không, nameLocation không
        // cả namemajor và companyname, nameLocation đều có
        // companyname có, namemajor có, namelocation không
        // companyname có, nammajor không, namelocation có
        // khi xuất kết quả thực hiện phân trang 
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
