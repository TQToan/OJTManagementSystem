/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblfollowing_post.TblFollowing_PostDAO;
import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import com.se1625.tblmajor.TblMajorDAO;
import com.se1625.tblmajor.TblMajorDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThanhTy
 */
@WebServlet(name = "SearchSaveJobServlet", urlPatterns = {"/SearchSaveJobServlet"})
public class SearchSaveJobServlet extends HttpServlet {

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

        String job = request.getParameter("txtJob");
        String company = request.getParameter("txtCompany");
        String nameLocation = request.getParameter("nameLocation");

        //1. get servletContext 
        ServletContext context = this.getServletContext();
        //2. get properties
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        String url = properties.getProperty(MyApplicationConstants.StudentSaveJobFeature.STUDENT_SAVE_JOB_PAGE);

        int page;
        int numberProductPage = 10;
        int start;
        int size;
        int end;
        HttpSession session = request.getSession(false);

        try {
            if (session != null) {

                TblStudentDTO student = (TblStudentDTO) session.getAttribute("STUDENT_ROLE");
                
                if (student != null) {
//                request.setAttribute("STUDENT_CODE", studentDTO);
//            if (company.isEmpty() == false || job.isEmpty() == false
//                    || nameLocation.isEmpty() == false) {
                    TblFollowing_PostDAO followPostDao = new TblFollowing_PostDAO();
                    followPostDao.searchFollowingPostByFilter(job, company,
                            nameLocation, student.getStudentCode());

                    List<TblFollowing_PostDTO> listFollowingCompanyPostByFilter
                            = followPostDao.getFollowingPostByFilter();

                    if (listFollowingCompanyPostByFilter != null) {
                        size = listFollowingCompanyPostByFilter.size();
//                    System.out.println(size);
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
                            numberPage = (size / numberProductPage) + 1;
                        }
                        start = (page - 1) * numberProductPage;
                        end = Math.min(page * numberProductPage, size);

                        List<TblFollowing_PostDTO> followingPostPerPage = followPostDao.
                                getListByPage(listFollowingCompanyPostByFilter, start, end);

                        request.setAttribute("LIST_RESULT", followingPostPerPage);
                        request.setAttribute("SIZE_OF_LIST", size);
                        request.setAttribute("page", page);
                        request.setAttribute("numberPage", numberPage);

                    } else {
                        size = 0;
                        request.setAttribute("SIZE_OF_LIST", size);

                    }
                    url = properties.getProperty(MyApplicationConstants.StudentSaveJobFeature.STUDENT_SAVE_JOB_PAGE);

                }
            }
        } catch (SQLException ex) {
            log("SQLException occurs at SearchSaveJobController " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException occurs at SearchSaveJobController " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("NumberFormatException occurs at SearchSaveJobController " + ex.getMessage());
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
