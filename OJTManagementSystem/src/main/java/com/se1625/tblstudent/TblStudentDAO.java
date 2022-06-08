/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;


import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 *
 * @author ThanhTy
 */
public class TblStudentDAO implements Serializable {

    public TblStudentDTO showStudentInfo(String userName) throws SQLException, NamingException {
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
    

        

    

    public TblStudentDTO getStudent(String username) throws SQLException, NamingException {
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
    
    public TblStudentDTO getStudentInformation(String studentCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO student = null;
        TblAccountDAO accountDAO = new TblAccountDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, major, birthDay, address, gender, phone, is_Intern, numberOfCredit, username "
                        + "FROM tblStudent "
                        + "WHERE studentCode = ? ";
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
    public boolean updateStudent(String studentCode, Date birthday, String address, boolean gender, String number) throws SQLException, NamingException{
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
    
    public boolean updateStudent(String studentCode) throws SQLException, NamingException{
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
}
