/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;

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
}
