/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblcompany;

import com.se1625.tblaccount.TblAccountDTO;
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
public class TblCompanyDAO implements Serializable {

    private List<TblCompanyDTO> listNameCompany;
    private List<TblCompanyDTO> listAvatarSignedCompany;

    public List<TblCompanyDTO> getListNameCompany() {
        return listNameCompany;
    }

    public List<TblCompanyDTO> getListAvatarSignedCompany() {
        return listAvatarSignedCompany;
    }

    public void getNameCompanies() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT acc.name, com.companyID "
                        + "FROM tblCompany AS com INNER JOIN tblAccount AS acc "
                        + "ON (com.username = acc.username)";
                stm = con.prepareCall(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String name = rs.getNString("name");
                    TblAccountDTO account = new TblAccountDTO();
                    account.setName(name);

                    String companyID = rs.getString("companyID");
                    TblCompanyDTO company = new TblCompanyDTO();
                    company.setCompanyID(companyID);
                    company.setAccount(account);

                    if (listNameCompany == null) {
                        listNameCompany = new ArrayList<>();
                    }

                    listNameCompany.add(company);
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

    public void getAvatarSignedCompany() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT acc.avatar, com.companyID "
                        + "FROM tblAccount AS acc INNER JOIN tblCompany AS com"
                        + " ON (acc.username = com.username) "
                        + "WHERE com.is_Signed = ? ";
                stm = con.prepareCall(sql);
                stm.setBoolean(1, true);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String avatar = rs.getString("avatar");
                    String companyID = rs.getString("companyID");

                    TblAccountDTO account = new TblAccountDTO();
                    account.setAvatar(avatar);

                    TblCompanyDTO dto = new TblCompanyDTO();
                    dto.setCompanyID(companyID);
                    dto.setAccount(account);

                    if (listAvatarSignedCompany == null) {
                        listAvatarSignedCompany = new ArrayList<>();
                    }

                    listAvatarSignedCompany.add(dto);
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

    public String getLastIDCompany() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 companyID "
                        + "FROM tblCompany "
                        + "ORDER BY companyID DESC ";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String companyID = rs.getString("companyID");
                    return companyID;
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

    public boolean AddCompany(TblCompanyDTO companyDetails) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            if (companyDetails != null) {
                con = DBHelper.makeConnection();
                if (con != null) {
                    String sql = "INSERT INTO tblCompany (companyID, address, city, phone, company_Description, is_Signed, username) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, companyDetails.getCompanyID());
                    stm.setNString(2, companyDetails.getAddress());
                    stm.setNString(3, companyDetails.getCity());
                    stm.setString(4, companyDetails.getPhone());
                    stm.setNString(5, companyDetails.getCompany_Description());
                    stm.setBoolean(6, companyDetails.isIs_Signed());
                    stm.setString(7, companyDetails.getAccount().getEmail());

                    int rows = stm.executeUpdate();
                    if (rows > 0) {
                        return true;
                    }
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
