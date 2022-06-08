/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblapplication;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
                stm.setInt(8, application.getCompanyPost().getPostID());
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
                if (rs.next()) {

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
                stm.setInt(8, application.getCompanyPost().getPostID());
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

    public boolean updateApplyCV(int applicationID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblApplication "
                        + "SET student_Confirm = ? "
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
                if (rs.next()) {

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

    public List<TblApplicationDTO> getApplicationByFilter(TblStudentDTO student,
            String companyName, String nameTypeJob, String nameLocation, String nameStatus)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblApplicationDTO> listApplicationByFilter = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT app.school_Confirm, app.student_Confirm, app.company_Confirm, app.applicationID, "
                        + "cp.title_Post, cp.workLocation, cp.expirationDate, cp.postID, "
                        + "ac.name "
                        + "FROM tblApplication AS app INNER JOIN tblCompany_Post AS cp ON (app.postID = cp.postID) "
                        + "INNER JOIN tblCompany as com ON (cp.companyID = com.companyID) "
                        + "INNER JOIN tblAccount as ac ON (com.username = ac.username) ";
                if ("".equals(companyName) == false && "".equals(nameTypeJob) == false
                        && "".equals(nameLocation) == false && "".equals(nameStatus) == false) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? and cp.title_Post LIKE ? "
                            + "and cp.workLocation LIKE ? ";
                    if (nameStatus.equals("Denied")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? ) ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setNString(4, "%" + nameLocation + "%");
                        stm.setBoolean(5, true);
                        stm.setInt(6, 0);
                        stm.setInt(7, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setNString(4, "%" + nameLocation + "%");
                        stm.setBoolean(5, true);
                        stm.setInt(6, -1);
                        stm.setInt(7, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "and app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setNString(4, "%" + nameLocation + "%");
                        stm.setBoolean(5, true);
                        stm.setInt(6, 1);
                        stm.setInt(7, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "and app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setNString(4, "%" + nameLocation + "%");
                        stm.setBoolean(5, false);
                    }
                }

                if (companyName.trim().isEmpty() == false && nameTypeJob.trim().isEmpty() == false
                        && nameLocation.trim().isEmpty() == false && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? and cp.title_Post LIKE ? "
                            + "and cp.workLocation LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + companyName + "%");
                    stm.setNString(3, "%" + nameTypeJob + "%");
                    stm.setNString(4, "%" + nameLocation + "%");
                }

                if (companyName.trim().isEmpty() == false && nameTypeJob.trim().isEmpty() == false
                        && "".equals(nameLocation) == true && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? and cp.title_Post LIKE ? ";
                    if (nameStatus.equals("Denied")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 0);
                        stm.setInt(6, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ?) ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, -1);
                        stm.setInt(6, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "and app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 1);
                        stm.setInt(6, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "and app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameTypeJob + "%");
                        stm.setBoolean(4, false);
                    }
                }

                if (companyName.trim().isEmpty() == false && "".equals(nameTypeJob) == true
                        && nameLocation.trim().isEmpty() == false && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? "
                            + "and cp.workLocation LIKE ? ";

                    if (nameStatus.equals("Denied")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ?) ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 0);
                        stm.setInt(6, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "and app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, -1);
                        stm.setInt(6, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "and app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 1);
                        stm.setInt(6, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "and app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, false);
                    }
                }

                if ("".equals(companyName) == true && nameTypeJob.trim().isEmpty() == false
                        && nameLocation.trim().isEmpty() == false && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and cp.title_Post LIKE ? "
                            + "and cp.workLocation LIKE ? and ";

                    if (nameStatus.equals("Denied")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 0);
                        stm.setInt(6, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, -1);
                        stm.setInt(6, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, true);
                        stm.setInt(5, 1);
                        stm.setInt(6, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setNString(3, "%" + nameLocation + "%");
                        stm.setBoolean(4, false);
                    }
                }

                if (companyName.trim().isEmpty() == false && nameTypeJob.trim().isEmpty() == false
                        && "".equals(nameLocation) == true && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? and cp.title_Post LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + companyName + "%");
                    stm.setNString(3, "%" + nameTypeJob + "%");
                }

                if (companyName.trim().isEmpty() == false && "".equals(nameTypeJob) == true
                        && nameLocation.trim().isEmpty() == false && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and cp.workLocation LIKE ? and ac.name LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + nameLocation + "%");
                    stm.setNString(3, "%" + companyName + "%");
                }

                if ("".equals(companyName) == true && nameTypeJob.trim().isEmpty() == false
                        && nameLocation.trim().isEmpty() == false && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and cp.workLocation LIKE ? and cp.title_Post LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + nameLocation + "%");
                    stm.setNString(3, "%" + nameTypeJob + "%");
                }

                if (companyName.trim().isEmpty() == false && "".equals(nameTypeJob) == true
                        && "".equals(nameLocation) == true && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? and ";

                    if (nameStatus.equals("Denied")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 0);
                        stm.setInt(5, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, -1);
                        stm.setInt(5, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 1);
                        stm.setInt(5, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + companyName + "%");
                        stm.setBoolean(3, false);
                    }
                }

                if ("".equals(companyName) == true && nameTypeJob.trim().isEmpty() == false
                        && "".equals(nameLocation) == true && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and cp.title_Post LIKE ? and ";

                    if (nameStatus.equals("Denied")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 0);
                        stm.setInt(5, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, -1);
                        stm.setInt(5, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 1);
                        stm.setInt(5, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameTypeJob + "%");
                        stm.setBoolean(3, false);
                    }
                }

                if ("".equals(companyName) == true && "".equals(nameTypeJob) == true
                        && nameLocation.trim().isEmpty() == false && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and cp.workLocation LIKE ? and ";
                    System.out.println(nameLocation);
                    if (nameStatus.equals("Denied")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ?) ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameLocation + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 0);
                        stm.setInt(5, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ?) ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameLocation + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, -1);
                        stm.setInt(5, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameLocation + "%");
                        stm.setBoolean(3, true);
                        stm.setInt(4, 1);
                        stm.setInt(5, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setNString(2, "%" + nameLocation + "%");
                        stm.setBoolean(3, false);
                    }
                }

                if ("".equals(companyName) == true && "".equals(nameTypeJob) == true
                        && "".equals(nameLocation) == true && nameStatus.trim().isEmpty() == false) {
                    sql += "WHERE app.studentCode = ? and ";

                    if (nameStatus.equals("Denied")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setBoolean(2, true);
                        stm.setInt(3, 0);
                        stm.setInt(4, 0);
                    } else if (nameStatus.equals("Waiting")) {
                        sql += "app.student_Confirm = ? and (app.company_Confirm = ? or "
                                + " app.school_Confirm = ? )";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setBoolean(2, true);
                        stm.setInt(3, -1);
                        stm.setInt(4, -1);
                    } else if (nameStatus.equals("Success")) {
                        sql += "app.student_Confirm = ? and app.company_Confirm = ? and "
                                + " app.school_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setBoolean(2, true);
                        stm.setInt(3, 1);
                        stm.setInt(4, 1);
                    } else if (nameStatus.equals("Canceled")) {
                        sql += "app.student_Confirm = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, student.getStudentCode());
                        stm.setBoolean(2, false);
                    }
                }

                if ("".equals(companyName) == true && "".equals(nameTypeJob) == true
                        && nameLocation.trim().isEmpty() == false && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and cp.workLocation LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + nameLocation + "%");
                }

                if ("".equals(companyName) == true && nameTypeJob.trim().isEmpty() == false
                        && "".equals(nameLocation) == true && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and cp.title_Post LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + nameTypeJob + "%");
                }

                if (companyName.trim().isEmpty() == false && "".equals(nameTypeJob) == true
                        && "".equals(nameLocation) == true && "".equals(nameStatus) == true) {
                    sql += "WHERE app.studentCode = ? and ac.name LIKE ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, student.getStudentCode());
                    stm.setNString(2, "%" + companyName + "%");
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    int schoolConfirm = rs.getInt("school_Confirm");
                    int companyConfirm = rs.getInt("company_Confirm");
                    boolean studentConfirm = rs.getBoolean("student_Confirm");
                    int applicationID = rs.getInt("applicationID");
                    String tiltlePost = rs.getNString("title_Post");
                    String workLocation = rs.getNString("workLocation");
                    Date expirationDate = rs.getDate("expirationDate");
                    int postID = rs.getInt("postID");
                    String nameCompany = rs.getNString("name");

                    if (nameStatus.equals("Waiting") && schoolConfirm == 0 && companyConfirm == -1) {
                        continue;
                    }

                    TblApplicationDTO application = new TblApplicationDTO();
                    application.setApplicationID(applicationID);
                    application.setCompanyConfirm(companyConfirm);
                    application.setStudentConfirm(studentConfirm);
                    application.setSchoolConfirm(schoolConfirm);

                    TblCompany_PostDTO companyPost = new TblCompany_PostDTO();
                    companyPost.setTitle_Post(tiltlePost);
                    companyPost.setExpirationDate(expirationDate);
                    companyPost.setWorkLocation(workLocation);
                    companyPost.setPostID(postID);

                    TblAccountDTO account = new TblAccountDTO();
                    account.setName(nameCompany);

                    TblCompanyDTO company = new TblCompanyDTO();
                    company.setAccount(account);

                    companyPost.setCompany(company);
                    application.setCompanyPost(companyPost);

                    if (listApplicationByFilter == null) {
                        listApplicationByFilter = new ArrayList<>();
                    }
                    listApplicationByFilter.add(application);
                }
                return listApplicationByFilter;
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
        return listApplicationByFilter;
    }

    public boolean deleteApplicationOfStudent(String studentCode)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM tblApplication "
                        + "WHERE studentCode = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentCode);
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

    public List<String> getAttachmentOfStudent(String studentCode)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<String> listAttachment = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT attachmentPath "
                        + "FROM tblApplication "
                        + "WHERE studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentCode);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String attachmentPath = rs.getString("attachmentPath");
                    if (listAttachment == null) {
                        listAttachment = new ArrayList<>();
                    }
                    listAttachment.add(attachmentPath);
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
        return listAttachment;
    }

    public List<TblApplicationDTO> getListStudentApplications(int currentSemesterID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblApplicationDTO> listApplication = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT applicationID, attachmentPath, expected_Job, "
                        + "technology, experience, foreign_Language, otherSkills, "
                        + "evaluation, grade, is_Pass, student_Confirm, "
                        + "school_Confirm, company_Confirm, student.studentCode, postID "
                        + "FROM tblApplication AS app "
                        + "INNER JOIN tblStudent AS student "
                        + "ON (app.studentCode = student.studentCode) "
                        + "INNER JOIN tblSemester_Student AS semester "
                        + "ON (semester.studentCode = student.studentCode) "
                        + "WHERE student.is_Disabled = ? and student_Confirm = ? "
                        + "and school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setBoolean(2, true);
                stm.setInt(3, 1);
                stm.setInt(4, 1);
                stm.setInt(5, currentSemesterID);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int applicationID = rs.getInt("applicationID");
                    String attachmentPath = rs.getString("attachmentPath");
                    String expected_Job = rs.getNString("expected_Job");
                    String technology = rs.getNString("technology");
                    String experience = rs.getNString("experience");
                    String foreign_Language = rs.getNString("foreign_Language");
                    String otherSkills = rs.getNString("otherSkills");
                    String evaluation = rs.getNString("evaluation");
                    float grade = rs.getFloat("grade");
                    boolean is_Pass = rs.getBoolean("is_Pass");
                    boolean student_Confirm = rs.getBoolean("student_Confirm");
                    int school_Confirm = rs.getInt("school_Confirm");
                    int company_Confirm = rs.getInt("company_Confirm");
                    String studentCode = rs.getString("studentCode");
                    int postID = rs.getInt("postID");

                    if (grade >= 0 && evaluation != null) {
                        TblApplicationDTO application = new TblApplicationDTO();
                        application.setApplicationID(applicationID);
                        application.setAttachmentPath(attachmentPath);
                        application.setCompanyConfirm(company_Confirm);
                        application.setEvaluation(evaluation);
                        application.setExpected_job(expected_Job);
                        application.setExperience(experience);
                        application.setForeign_Language(foreign_Language);
                        application.setGrade(grade);
                        application.setIsPass(is_Pass);
                        application.setOtherSkills(otherSkills);
                        application.setSchoolConfirm(school_Confirm);
                        application.setStudentConfirm(student_Confirm);
                        application.setTechnology(technology);

                        TblStudentDAO studentDAO = new TblStudentDAO();
                        TblStudentDTO student = studentDAO.getStudentInformation(studentCode);
                        application.setStudent(student);

                        TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                        TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);
                        application.setCompanyPost(companyPost);

                        if (listApplication == null) {
                            listApplication = new ArrayList<>();
                        }

                        listApplication.add(application);
                    }

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
        return listApplication;
    }

    public List<TblApplicationDTO> searchListStudentApplicationByFilter(int currentSemesterID,
            float grade, String studentCode, String companyID, String isPass)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblApplicationDTO> listApplication = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT applicationID, attachmentPath, expected_Job, "
                        + "technology, experience, foreign_Language, otherSkills, "
                        + "evaluation, grade, is_Pass, student_Confirm, "
                        + "app.school_Confirm, company_Confirm, student.studentCode, app.postID, post.companyID "
                        + "FROM tblApplication AS app "
                        + "INNER JOIN tblStudent AS student "
                        + "ON (app.studentCode = student.studentCode) "
                        + "INNER JOIN tblSemester_Student AS semester "
                        + "ON (semester.studentCode = student.studentCode) "
                        + "INNER JOIN tblCompany_Post AS post "
                        + "ON (app.postID = post.postID) ";

                if (grade != -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == false && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and grade = ? and is_Pass = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    stm.setFloat(7, grade);
                    if (isPass.equals("true")) {
                        stm.setBoolean(8, true);
                    } else {
                        stm.setBoolean(8, false);
                    }
                    stm.setString(9, companyID);
                }

                if (grade == -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == false && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and is_Pass = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    if (isPass.equals("true")) {
                        stm.setBoolean(7, true);
                    } else {
                        stm.setBoolean(7, false);
                    }
                    stm.setString(8, companyID);
                }

                if (grade != -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == false && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + " and grade = ? and is_Pass = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setFloat(6, grade);
                    if (isPass.equals("true")) {
                        stm.setBoolean(7, true);
                    } else {
                        stm.setBoolean(7, false);
                    }
                    stm.setString(8, companyID);
                }

                if (grade != -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == true && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and grade = ? and is_Pass = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    stm.setFloat(7, grade);
                    if (isPass.equals("true")) {
                        stm.setBoolean(8, true);
                    } else {
                        stm.setBoolean(8, false);
                    }
                }

                if (grade != -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == false && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and grade = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    stm.setFloat(7, grade);
                    stm.setString(8, companyID);
                }

                if (grade == -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == false && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + " and is_Pass = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    if (isPass.equals("true")) {
                        stm.setBoolean(6, true);
                    } else {
                        stm.setBoolean(6, false);
                    }
                    stm.setString(7, companyID);
                }

                if (grade == -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == true && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and is_Pass = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    if (isPass.equals("true")) {
                        stm.setBoolean(7, true);
                    } else {
                        stm.setBoolean(7, false);
                    }
                }

                if (grade == -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == false && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    stm.setString(7, companyID);
                }

                if (grade != -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == true && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and grade = ? and is_Pass = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setFloat(6, grade);
                    if (isPass.equals("true")) {
                        stm.setBoolean(7, true);
                    } else {
                        stm.setBoolean(7, false);
                    }
                }

                if (grade != -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == false && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + " and grade = ? and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setFloat(6, grade);
                    stm.setString(7, companyID);
                }

                if (grade != -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == true && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? and grade = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                    stm.setFloat(7, grade);
                }

                if (grade == -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == true && "".equals(isPass) == false) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and is_Pass = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    if (isPass.equals("true")) {
                        stm.setBoolean(6, true);
                    } else {
                        stm.setBoolean(6, false);
                    }
                }

                if (grade == -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == false && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + " and post.companyID = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, companyID);
                }

                if (grade == -1 && "".equals(studentCode) == false
                        && "".equals(companyID) == true && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + "and student.studentCode = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setString(6, studentCode);
                }

                if (grade != -1 && "".equals(studentCode) == true
                        && "".equals(companyID) == true && "".equals(isPass) == true) {
                    sql += "WHERE student.is_Disabled = ? and student_Confirm = ? "
                            + "and app.school_Confirm = ? and company_Confirm = ? and semester.semesterID = ? "
                            + " and grade = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setBoolean(1, false);
                    stm.setBoolean(2, true);
                    stm.setInt(3, 1);
                    stm.setInt(4, 1);
                    stm.setInt(5, currentSemesterID);
                    stm.setFloat(6, grade);
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    int applicationID = rs.getInt("applicationID");
                    String attachmentPath = rs.getString("attachmentPath");
                    String expected_Job = rs.getNString("expected_Job");
                    String technology = rs.getNString("technology");
                    String experience = rs.getNString("experience");
                    String foreign_Language = rs.getNString("foreign_Language");
                    String otherSkills = rs.getNString("otherSkills");
                    String evaluation = rs.getNString("evaluation");
                    float gradeApplication = rs.getFloat("grade");
                    boolean is_Pass = rs.getBoolean("is_Pass");
                    boolean student_Confirm = rs.getBoolean("student_Confirm");
                    int school_Confirm = rs.getInt("school_Confirm");
                    int company_Confirm = rs.getInt("company_Confirm");
                    String student_Code = rs.getString("studentCode");
                    int postID = rs.getInt("postID");
                    String company_ID = rs.getString("companyID");

                    TblApplicationDTO application = new TblApplicationDTO();
                    application.setApplicationID(applicationID);
                    application.setAttachmentPath(attachmentPath);
                    application.setCompanyConfirm(company_Confirm);
                    application.setEvaluation(evaluation);
                    application.setExpected_job(expected_Job);
                    application.setExperience(experience);
                    application.setForeign_Language(foreign_Language);
                    application.setGrade(gradeApplication);
                    application.setIsPass(is_Pass);
                    application.setOtherSkills(otherSkills);
                    application.setSchoolConfirm(school_Confirm);
                    application.setStudentConfirm(student_Confirm);
                    application.setTechnology(technology);

                    TblStudentDAO studentDAO = new TblStudentDAO();
                    TblStudentDTO student = studentDAO.getStudentInformation(student_Code);
                    application.setStudent(student);
                    
                    TblCompanyDAO companyDAO = new TblCompanyDAO();
                    TblCompanyDTO company = companyDAO.getCompany(company_ID);

                    TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                    TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(postID);
                    companyPost.setCompany(company);
                    application.setCompanyPost(companyPost);
                    
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
        return listApplication;
    }
}
