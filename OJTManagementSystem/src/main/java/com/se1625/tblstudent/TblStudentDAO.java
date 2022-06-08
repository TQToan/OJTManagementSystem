/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;


import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblsemester.TblSemesterDAO;
import com.se1625.tblsemester.TblSemesterDTO;
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
 *
 * @author ThanhTy
 */
public class TblStudentDAO implements Serializable {

    public TblStudentDTO showStudentInfo(String userName) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select birthDay, major, studentCode "
                        + "from tblStudent "
                        + "where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date birthDay = rs.getDate("birthDay");
                    String major = rs.getNString("major");
                    String studentCode = rs.getString("studentCode");
                    dto = new TblStudentDTO();
                    dto.setBirthDay(birthDay);
                    dto.setMajor(major);
                    dto.setStudentCode(studentCode);
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
return dto;
    }
    
    public TblStudentDTO getStudent(String username) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO student = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, major, birthDay, address, gender, phone, is_Intern, numberOfCredit "
                        + "FROM tblStudent "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String studentCode = rs.getString("studentCode");
                    String major = rs.getNString("major");
                    Date birthDay = rs.getDate("birthDay");
                    String address = rs.getNString("address");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    int is_Itern = rs.getInt("is_Intern");
                    int numberOfCredit = rs.getInt("numberOfCredit");
                    
                    TblAccountDAO accountDAO = new TblAccountDAO();
                    TblAccountDTO account = accountDAO.getAccount(username);
                    student = new TblStudentDTO(studentCode, birthDay, address, gender, phone, is_Itern, numberOfCredit, major);
                    student.setAccount(account);
//                    student = new TblStudentDTO(studentCode, birthDay, address, gender, phone, is_Itern, numberOfCredit, major);
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
        return student;

    }
    
    public TblStudentDTO getStudentInformation(String studentCode) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO student = new TblStudentDTO();
        TblAccountDAO accountDAO = new TblAccountDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT student.studentCode, major, birthDay, address, "
                        + "gender, phone, is_Intern, numberOfCredit, username, "
                        + "semester.semesterID "
                        + "FROM tblStudent AS student INNER JOIN tblSemester_Student AS semester "
                        + "ON (student.studentCode = semester.studentCode) "
                        + "WHERE student.studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentCode);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    String major = rs.getNString("major");
                    Date birthDay = rs.getDate("birthDay");
                    String address = rs.getNString("address");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    int is_Itern = rs.getInt("is_Intern");
                    int numberOfCredit = rs.getInt("numberOfCredit");
                    String username = rs.getString("username");
                    int semesterID = rs.getInt("semesterID");
                    
                    TblSemesterDAO semesterDAO = new TblSemesterDAO();
                    TblSemesterDTO semester = semesterDAO.getSemesterByID(semesterID);
                    student.setSemester(semester);
                    
                    TblAccountDTO account = accountDAO.getAccount(username);
                    student = new TblStudentDTO(studentCode, birthDay, address, gender, phone, is_Itern, numberOfCredit, major);
                    student.setAccount(account);
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
        return student;
    }
    
    public boolean updateStudent(String studentCode, Date birthday, String address,
            boolean gender, String number) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "UPDATE tblStudent "
                        + "SET birthDay = ?, address = ?, gender = ?, phone = ? "
                        + "WHERE studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, birthday);
                stm.setString(2, address);               
                stm.setBoolean(3, gender);
                stm.setString(4, number);
                stm.setString(5, studentCode);
                int effectRows = stm.executeUpdate();
                
                if(effectRows > 0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
    public boolean updateStudent(String studentCode) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "UPDATE tblStudent "
                        + "SET is_Intern = ? "
                        + "WHERE studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, studentCode);
                int effectRows = stm.executeUpdate();
                
                if(effectRows > 0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }

    public List<TblStudentDTO> getListStudent(int currentSemesterID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblAccountDAO accountDAO = new TblAccountDAO();
        List<TblStudentDTO> listStudent = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT student.studentCode, birthDay, address, gender, phone, is_Intern, "
                        + "numberOfCredit, major, username "
                        + "FROM tblStudent AS student "
                        + "INNER JOIN tblSemester_Student AS record "
                        + "ON (student.studentCode = record.studentCode) "
                        + "WHERE record.semesterID = ? and is_Disabled = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, currentSemesterID);
                stm.setBoolean(2, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String studentCode = rs.getString("studentCode");
                    Date birthOfDate = rs.getDate("birthDay");
                    String address = rs.getNString("address");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    int is_Intern = rs.getInt("is_Intern");
                    int numberOdCredit = rs.getInt("numberOfCredit");
                    String major = rs.getNString("major");
                    String username = rs.getString("username");
                    
                    TblAccountDTO account = accountDAO.getAccount(username);
                    TblStudentDTO student = new TblStudentDTO();
                    student.setAccount(account);
                    student.setAddress(address);
                    student.setBirthDay(birthOfDate);
                    student.setGender(gender);
                    student.setIsIntern(is_Intern);
                    student.setMajor(major);
                    student.setNumberOfCredit(numberOdCredit);
                    student.setPhone(phone);
                    student.setStudentCode(studentCode);
                    
                    if (listStudent == null) {
                        listStudent = new ArrayList<>();
                    }
                    
                    listStudent.add(student);
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
        return listStudent;
    }
    
    public List<TblStudentDTO> getListByPage(List<TblStudentDTO> list
            , int start, int end) {
        List<TblStudentDTO> listPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPage.add(list.get(i));
        }
        return listPage;
    }
    
    public List<TblStudentDTO> searchStudentByFilter(String studentCode, 
            String major, int numberOfCredit, int isIntern, int semesterID) 
            throws SQLException, NamingException {
        Connection con  = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblStudentDTO> listStudent = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT student.studentCode, Acc.username, numberOfCredit, "
                        + "major, is_Intern, Acc.name, st.semesterID, semester.semesterName "
                        + "FROM tblStudent AS student "
                        + "INNER JOIN tblAccount AS Acc "
                        + "ON (student.username = Acc.username) "
                        + "INNER JOIN tblSemester_Student AS st "
                        + "ON (student.studentCode = st.studentCode ) "
                        + "INNER JOIN tblSemester AS semester "
                        + "ON (st.semesterID = semester.semesterID )";
                
                if ("".equals(studentCode) == true && "".equals(major) == true 
                        && numberOfCredit == -1 && isIntern == -1) {
                    sql += "WHERE semester.semesterID = ? and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, semesterID);
                    stm.setBoolean(2, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == false 
                        && numberOfCredit != -1 && isIntern != -1) {
                    sql += "WHERE student.studentCode = ? and major LIKE ? "
                            + "and numberOfCredit = ? and is_Intern = ? and semester.semesterID = ? "
                            + "and and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setNString(2, "%" + major + "%");
                    stm.setInt(3, numberOfCredit);
                    stm.setInt(4, isIntern);
                    stm.setInt(5, semesterID);
                    stm.setBoolean(6, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == false 
                        && numberOfCredit != -1 && isIntern == -1) {
                    sql += "WHERE student.studentCode = ? and major LIKE ? "
                            + "and numberOfCredit = ? and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setNString(2, "%" + major + "%");
                    stm.setInt(3, numberOfCredit);
                    stm.setInt(4, semesterID);
                    stm.setBoolean(5, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == false 
                        && numberOfCredit == -1 && isIntern != -1) {
                    sql += "WHERE student.studentCode = ? and major LIKE ? "
                            + " and is_Intern = ? and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setNString(2, "%" + major + "%");
                    stm.setInt(3, isIntern);
                    stm.setInt(4, semesterID);
                    stm.setBoolean(5, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == true 
                        && numberOfCredit != -1 && isIntern != -1) {
                    sql += "WHERE student.studentCode = ? "
                            + "and numberOfCredit = ? and is_Intern = ? and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setInt(2, numberOfCredit);
                    stm.setInt(3, isIntern);
                    stm.setInt(4, semesterID);
                    stm.setBoolean(5, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == false 
                        && numberOfCredit != -1 && isIntern != -1) {
                    sql += "WHERE  major LIKE ? "
                            + "and numberOfCredit = ? and is_Intern = ? and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setNString(1, "%" + major + "%");
                    stm.setInt(2, numberOfCredit);
                    stm.setInt(3, isIntern);
                    stm.setInt(4, semesterID);
                    stm.setBoolean(5, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == false 
                        && numberOfCredit == -1 && isIntern == -1) {
                    sql += "WHERE student.studentCode = ? and major LIKE ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setNString(2, "%" + major + "%");
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == true 
                        && numberOfCredit != -1 && isIntern == -1) {
                    sql += "WHERE student.studentCode = ? "
                            + "and numberOfCredit = ? and semester.semesterID = ? and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setInt(2, numberOfCredit);
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == false 
                        && numberOfCredit != -1 && isIntern == -1) {
                    sql += "WHERE  major LIKE ? "
                            + "and numberOfCredit = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setNString(1, "%" + major + "%");
                    stm.setInt(2, numberOfCredit);
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == true 
                        && numberOfCredit == -1 && isIntern != -1) {
                    sql += "WHERE student.studentCode = ? "
                            + "and is_Intern = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setInt(2, isIntern);
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == false 
                        && numberOfCredit == -1 && isIntern != -1){
                    sql += "WHERE major LIKE ? and is_Intern = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setNString(1, "%" + major + "%");
                    stm.setInt(2, isIntern);
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == true 
                        && numberOfCredit != -1 && isIntern != -1) {
                    sql += "WHERE numberOfCredit = ? and is_Intern = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, numberOfCredit);
                    stm.setInt(2, isIntern);
                    stm.setInt(3, semesterID);
                    stm.setBoolean(4, false);
                }
                
                if ("".equals(studentCode) == false && "".equals(major) == true 
                        && numberOfCredit == -1 && isIntern == -1) {
                    sql += "WHERE student.studentCode = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, studentCode);
                    stm.setInt(2, semesterID);
                    stm.setBoolean(3, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == false 
                        && numberOfCredit == -1 && isIntern == -1) {
                    sql += "WHERE major LIKE ?  and semester.semesterID = ?  "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setNString(1, "%" + major + "%");
                    stm.setInt(2, semesterID);
                    stm.setBoolean(3, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == true 
                        && numberOfCredit != -1 && isIntern == -1) {
                    sql += "WHERE numberOfCredit = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, numberOfCredit);
                    stm.setInt(2, semesterID);
                    stm.setBoolean(3, false);
                }
                
                if ("".equals(studentCode) == true && "".equals(major) == true 
                        && numberOfCredit == -1 && isIntern != -1) {
                    sql += "WHERE  is_Intern = ?  and semester.semesterID = ? "
                            + "and is_Disabled = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, isIntern);
                    stm.setInt(2, semesterID);
                    stm.setBoolean(3, false);
                }
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    String studentRoll = rs.getString("studentCode");
                    String email = rs.getString("username");
                    String name = rs.getNString("name");
                    int credit = rs.getInt("numberOfCredit");
                    int is_Intern = rs.getInt("is_Intern");
                    String majorStudent = rs.getNString("major");
                    String semesterName = rs.getString("semesterName");
                    int studentSemesterID = rs.getInt("semesterID");
                    
                    TblStudentDTO student = new TblStudentDTO();
                    student.setMajor(majorStudent);
                    student.setNumberOfCredit(credit);
                    student.setStudentCode(studentRoll);
                    student.setIsIntern(is_Intern);
                    
                    TblAccountDTO account = new TblAccountDTO();
                    account.setEmail(email);
                    account.setName(name);
                    
                    TblSemesterDTO semester = new TblSemesterDTO();
                    semester.setSemesterID(studentSemesterID);
                    semester.setSemesterName(semesterName);
                    
                    student.setAccount(account);
                    student.setSemester(semester);
                    
                    if (listStudent == null) {
                        listStudent = new ArrayList<>();
                    }
                    
                    listStudent.add(student);
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
        return listStudent;
    }
    
    public boolean updateCreditOfStudent(String studentCode, int credit) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblStudent "
                        + "SET numberOfCredit = ? "
                        + "WHERE studentCode = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, credit);
                stm.setString(2,  studentCode);
                
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean deleteStudent(String studentCode)  
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM tblStudent "
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
}
