/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblaccount;

import com.se1625.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class TblAccountDAO implements Serializable {

    public boolean getAccountCompany(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT acc.username "
                        + "FROM tblAccount AS acc "
                        + "WHERE acc.username = ? and isAdmin = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, 3);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    if (username != null) {
                        return true;
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
        return false;
    }

    public TblAccountDTO getAccountSchool() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblAccountDTO account = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT acc.username, acc.password "
                        + "FROM tblAccount AS acc "
                        + "WHERE acc.isAdmin = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, 1);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");

                    account = new TblAccountDTO();
                    account.setEmail(username);
                    account.setPassword(password);

                    return account;
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

    public boolean addAccountCompany(TblAccountDTO accountCompany) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            if (accountCompany != null) {
                con = DBHelper.makeConnection();
                if (con != null) {
                    String sql = "INSERT INTO tblAccount (username, password, name, avatar, isAdmin) "
                            + "VALUES(?, ?, ?, ?, ?) ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, accountCompany.getEmail());
                    stm.setString(2, accountCompany.getPassword());
                    stm.setNString(3, accountCompany.getName());
                    stm.setString(4, accountCompany.getAvatar());
                    stm.setInt(5, accountCompany.getIs_Admin());
                    
                    int rows = stm.executeUpdate();
                    if (rows > 0) {
                        return true;
                    }
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
