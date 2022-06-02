/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.tblapplication.TblApplicationDTO;
import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
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
 * @author ASUS
 */
@WebServlet(name = "ReviewInternShipServlet", urlPatterns = {"/ReviewInternShipServlet"})
public class ReviewInternShipServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAPS");
        String url = prop.getProperty(MyApplicationConstants.ReviewInternShipServletFeature.STUDENT_REVIEW_INTERNSHIP_PAGE);
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                //lấy Account của Student
                TblAccountDTO dtoAccount = (TblAccountDTO) session.getAttribute("ACCOUNT");
                if (dtoAccount != null) {
                    //lấy profile của Student 
                    String userName = dtoAccount.getEmail();
                    TblStudentDAO daoStudent = new TblStudentDAO();
                    TblStudentDTO dtoStudent = daoStudent.showStudentInfo(userName);
                    dtoStudent.setAccount(dtoAccount);
                    //set Attribute
                    request.setAttribute("STUDENT_PROFILE", dtoStudent);
                    
                    //lấy Application của Student apply đã được Company duyệt
                    TblApplicationDAO daoApp = new TblApplicationDAO();
                    TblApplicationDTO dtoApp = daoApp.getApplication(dtoStudent.getStudentCode());

                    if (dtoApp != null) {

                        //set Attribute
                        request.setAttribute("STUDENT_APP", dtoApp);

                        //get bài post của Company mà Student apply
                        TblCompany_PostDAO daoPost = new TblCompany_PostDAO();
                        TblCompany_PostDTO dtoPost = daoPost.searchPostByPostID(dtoApp.getCompanyPost().getPostID());
                        //set Attribute
                        request.setAttribute("COMPANY_POST", dtoPost);

                        if (dtoPost != null) {

                            //get Company đăng bài Post 
                            String companyID = dtoPost.getCompany().getCompanyID();
                            TblCompanyDAO daoCompany = new TblCompanyDAO();
                            TblCompanyDTO dtoCompany = daoCompany.getCompany(companyID);//set Attribute

                            //          get tên Company
                            //          String nameCompany = dtoCompany.getAccount().getName();
                            //          System.out.println(nameCompany);
                            //set attribute
                            request.setAttribute("COMPANY_PROFILE", dtoCompany);
                        }
                    } else {
                        //Recomend post
                        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                        //lấy 6 bài post
                        companyPostDAO.getListRecomendPost(dtoStudent.getMajor());
                        List<TblCompany_PostDTO> listPostHome = companyPostDAO.getCompanyPostListHome();
                        request.setAttribute("LIST_POST_HOME", listPostHome);
                    }

                }
            }
        } catch (NamingException ex) {
            log("ReviewInternShipServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("ReviewInternShipServlet_SQLException " + ex.getMessage());
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
