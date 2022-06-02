/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentError;
import com.se1625.utils.MyApplicationConstants;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "UpdateStudentProfileServlet", urlPatterns = {"/UpdateStudentProfileServlet"})
public class UpdateStudentProfileServlet extends HttpServlet {

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
        String date = request.getParameter("dateUpdate");
        String stringGender = request.getParameter("genderUpdate");
        String address = request.getParameter("addressUpdate");
        String stringPhone = request.getParameter("phoneUpdate");

        ServletContext context = this.getServletContext();
        Properties prop = (Properties) context.getAttribute("SITE_MAPS");
        String url = prop.getProperty(MyApplicationConstants.UpdateStudentProfileFeature.LOGIN_PAGE);

        TblStudentError error = new TblStudentError();
        boolean checkError = false;

        //get session
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                TblAccountDTO account = (TblAccountDTO) session.getAttribute("ACCOUNT");
                //check session account
                if (account != null) {

                    //get Date Now
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String nowDate = format.format(today);

                    //Check date update
                    if (date.compareTo(nowDate) > 0) {
                        error.setErrorDateInvalid("Invalid date with current date");
                        checkError = true;
                    }

                    //check address input format
                    if (address.trim().length() == 0) {
                        error.setErrorAddressLength("Please enter your address");
                        checkError = true;
                    }

                    //check phone number update
                    if (stringPhone.trim().length() < 10 || stringPhone.trim().length() > 11) {
                        error.setErrorPhoneNumberLength("Please enter 10-11 numbers");
                        checkError = true;
                    }
                    if (checkError) {
                        request.setAttribute("ERROR_UPDATE_STUDENTPROFILE", error);
                        url = prop.getProperty(MyApplicationConstants.UpdateStudentProfileFeature.SHOW_STUDENT_PROFILE_SERVLET);
                    } else {
                        boolean gender;
                        if ("Male".endsWith(stringGender)) {
                            gender = true;
                        } else {
                            gender = false;
                        }
                        TblStudentDAO dao = new TblStudentDAO();
                        Date birthday = Date.valueOf(date);
                        //update
                        boolean result = dao.updateStudent(studentCode, birthday, address, gender, stringPhone);
                        if (result) {
                            url = prop.getProperty(MyApplicationConstants.UpdateStudentProfileFeature.SHOW_STUDENT_PROFILE_SERVLET);
                        }
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            error.setErrorDateEmpty("Please, enter your birthday");
            request.setAttribute("ERROR_UPDATE_STUDENTPROFILE", error);
            url = prop.getProperty(MyApplicationConstants.UpdateStudentProfileFeature.SHOW_STUDENT_PROFILE_SERVLET);
        } catch (NamingException ex) {
            log("UpdateStudentProfileServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateStudentProfileServlet_SQLException " + ex.getMessage());
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
