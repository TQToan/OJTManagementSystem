/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblapplication;

import com.se1625.tblcompany.TblCompanyDAO;
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
                    
                    TblStudentDTO student = studentDAO.getStudentInformation(studentCode);
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
    
    public boolean addApplication(TblApplicationDTO application) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblApplication (attachmentPath, expected_Job, technology, experience, "
                        + "foreign_Language, otherSkills, studentCode, postID, student_Confirm) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, application.getAttachmentPath());
                stm.setNString(2, application.getExpected_job());
                stm.setNString(3, application.getTechnology());
                stm.setNString(4, application.getExperience());
                stm.setNString(5, application.getForeign_Language());
                stm.setNString(6, application.getOtherSkills());
                stm.setString(7, application.getStudent().getStudentCode());
                stm.setInt (8, application.getCompanyPost().getPostID());
                stm.setBoolean(9, true);
                
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
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
                stm.setInt(2, 1);
                stm.setInt(3, 1);
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

    public boolean updateApplication(TblApplicationDTO application) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblApplication (attachmentPath, expected_Job, technology, experience, "
                        + "foreign_Language, otherSkills, studentCode, postID ) "
                        + "SET (?, ?, ?, ?, ?, ?, ?, ?) "
                        + "WHERE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, application.getAttachmentPath());
                stm.setNString(2, application.getExpected_job());
                stm.setNString(3, application.getTechnology());
                stm.setNString(4, application.getExperience());
                stm.setNString(5, application.getForeign_Language());
                stm.setNString(6, application.getOtherSkills());
                stm.setString(7, application.getStudent().getStudentCode());
                stm.setInt (8, application.getCompanyPost().getPostID());
                stm.setInt(9, application.getApplicationID());
                
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<TblApplicationDTO> getApplicationOfAStudent(TblStudentDTO student) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblApplicationDTO> listApplicationOfAStudent = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT applicationID, attachmentPath, expected_Job, "
                        + "technology, experience, foreign_Language, otherSkills, "
                        + "evaluation, grade, is_Pass, student_Confirm, school_Confirm, "
                        + "company_Confirm, studentCode, postID "
                        + "FROM tblApplication "
                        + "WHERE studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, student.getStudentCode());
                
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int applicationID = rs.getInt("applicationID");
                    String attachmentPath = rs.getString("attachmentPath");
                    String expectedJob = rs.getNString("expected_Job");
                    String technology = rs.getNString("technology");
                    String experience = rs.getNString("experience");
                    String foreign_Language = rs.getNString("foreign_Language");
                    String otherSkills = rs.getNString("otherSkills");
                    String evaluation = rs.getNString("evaluation");
                    float grade = rs.getFloat("grade");
                    boolean isPass = rs.getBoolean("is_Pass");
                    boolean studentConfirm = rs.getBoolean("student_Confirm");
                    int schoolConfirm = rs.getInt("school_Confirm");
                    int companyConfirm = rs.getInt("company_Confirm");
                    
                    int postID = rs.getInt("postID");
                    TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                    TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);
                    
                    TblApplicationDTO applicationDTO = new TblApplicationDTO();
                    applicationDTO.setApplicationID(applicationID);
                    applicationDTO.setAttachmentPath(attachmentPath);
                    applicationDTO.setExpected_job(expectedJob);
                    applicationDTO.setTechnology(technology);
                    applicationDTO.setExperience(experience);
                    applicationDTO.setForeign_Language(foreign_Language);
                    applicationDTO.setOtherSkills(otherSkills);
                    applicationDTO.setEvaluation(evaluation);
                    applicationDTO.setGrade(grade);
                    applicationDTO.setIsPass(isPass);
                    applicationDTO.setStudentConfirm(studentConfirm);
                    applicationDTO.setSchoolConfirm(schoolConfirm);
                    applicationDTO.setCompanyConfirm(companyConfirm);
                    applicationDTO.setStudent(student);
                    applicationDTO.setCompanyPost(companyPost);
                    
                    if (listApplicationOfAStudent == null) {
                        listApplicationOfAStudent = new ArrayList<>();
                    }
                    
                    listApplicationOfAStudent.add(applicationDTO);
                }
                return listApplicationOfAStudent;
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
    
    public List<TblApplicationDTO> getListByPage(List<TblApplicationDTO> list, int start, int end) {
        List<TblApplicationDTO> listPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPage.add(list.get(i));
        }
        return listPage;
    }
    
    public boolean updateApplyCV (int applicationID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblApplication "
                        + "SET student_Confirm = ?"
                        + "WHERE applicationID = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setInt(2, applicationID);
                
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public TblApplicationDTO getApplication(int applicationCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDAO studentDAO = new TblStudentDAO();
        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, postID, grade, evaluation, applicationID, is_Pass, "
                        + "student_Confirm, school_Confirm, company_Confirm "
                        + "FROM tblApplication "
                        + "WHERE applicationId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, applicationCode);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    
                    int postID = rs.getInt("postID");
                    float grade = rs.getFloat("grade");
                    String evaluation = rs.getNString("evaluation");
                    int applicationID = rs.getInt("applicationID");
                    boolean isPass = rs.getBoolean("is_Pass");
                    String studentCode = rs.getString("studentCode");
                    boolean studentConfirm = rs.getBoolean("student_Confirm");
                    int schoolConfirm = rs.getInt("school_Confirm");
                    int company_Confirm = rs.getInt("company_Confirm");
                    
                    TblStudentDTO student = studentDAO.getStudent(studentCode);
                    TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);
                    
                    TblApplicationDTO application = new TblApplicationDTO();
                    application.setApplicationID(applicationID);
                    application.setEvaluation(evaluation);
                    application.setIsPass(isPass);
                    application.setCompanyPost(companyPost);
                    application.setStudent(student);
                    application.setGrade(grade);
                    application.setStudentConfirm(studentConfirm);
                    application.setCompanyConfirm(company_Confirm);
                    application.setSchoolConfirm(schoolConfirm);
                    
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
