/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;

<<<<<<< HEAD
import com.se1625.tblaccount.TblAccountDTO;
=======
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
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
<<<<<<< HEAD
 * @author ThanhTy
 */
public class TblStudentDAO implements Serializable{
    
    public TblStudentDTO showStudentInfo (String userName) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select birthDay, major, studentCode " +
                             "from tblStudent " +
                             "where username = ?";
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
=======
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class TblStudentDAO implements Serializable{
    
    public TblStudentDTO getStudent(String studentCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStudentDTO student = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT studentCode, major, birthDay, address, gender, phone, is_Intern, numberOfCredit "
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
                    student = new TblStudentDTO(studentCode, birthDay, address, gender, phone, is_Itern, numberOfCredit, major);
                }
                
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
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
<<<<<<< HEAD
        return dto;
=======
        return student;
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    }
}
