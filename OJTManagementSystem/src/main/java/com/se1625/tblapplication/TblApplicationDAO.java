/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblapplication;

import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class TblApplicationDAO implements Serializable {

    private List<TblApplicationDTO> listApplication;

    public List<TblApplicationDTO> getListApplication() {
        return listApplication;
    }

    public void getApplication() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDAO studentDAO = new TblStudentDAO();
        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, postID, grade, evaluation, applicationID, is_Pass "
                        + "FROM tblApplication "
                        + "WHERE student_Confirm = ? and school_Confirm = ? and company_Confirm = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, true);
                stm.setBoolean(2, true);
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String studentCode = rs.getString("studentCode");
                    int postID = rs.getInt("postID");
                    float grade = rs.getFloat("grade");
                    String evaluation = rs.getNString("evaluation");
                    int applicationID = rs.getInt("applicationID");
                    boolean isPass = rs.getBoolean("is_Pass");

                    TblStudentDTO student = studentDAO.getStudent(studentCode);
                    TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);

                    TblApplicationDTO application = new TblApplicationDTO();
                    application.setApplicationID(applicationID);
                    application.setGrade(grade);
                    application.setIsPass(isPass);
                    application.setEvaluation(evaluation);
                    application.setCompanyPost(companyPost);
                    application.setStudent(student);

                    if (listApplication == null) {
                        listApplication = new ArrayList<>();
                    }
                    listApplication.add(application);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public TblApplicationDTO getApplication(String studentCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDAO studentDAO = new TblStudentDAO();
        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, postID, grade, evaluation, applicationID, is_Pass "
                        + "FROM tblApplication "
                        + "WHERE student_Confirm = ? and school_Confirm = ? and company_Confirm = ? and studentCode = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, true);
                stm.setBoolean(2, true);
                stm.setBoolean(3, true);
                stm.setString(4, studentCode);
                rs = stm.executeQuery();
                if(rs.next()){
                    
                    int postID = rs.getInt("postID");
                    float grade = rs.getFloat("grade");
                    String evaluation = rs.getNString("evaluation");
                    int applicationID = rs.getInt("applicationID");
                    boolean isPass = rs.getBoolean("is_Pass");
                    
                    TblStudentDTO student = studentDAO.getStudent(studentCode);
                    TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);
                    
                    TblApplicationDTO application = new TblApplicationDTO();
                    application.setApplicationID(applicationID);
                    application.setEvaluation(evaluation);
                    application.setIsPass(isPass);
                    application.setCompanyPost(companyPost);
                    application.setStudent(student);
                    application.setGrade(grade);                   
                    
                    return application;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }


}
