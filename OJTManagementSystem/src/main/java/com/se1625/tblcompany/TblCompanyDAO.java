/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblcompany;

import com.se1625.tblaccount.TblAccountDAO;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
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
public class TblCompanyDAO implements Serializable {
    
    private List<TblCompanyDTO> listNameCompany;
    private List<TblCompanyDTO> listAvatarSignedCompany;
    private List<TblCompanyDTO> listAllCompany;
    
    public List<TblCompanyDTO> getListNameCompany() {
        return listNameCompany;
    }
    
    public List<TblCompanyDTO> getListAvatarSignedCompany() {
        return listAvatarSignedCompany;
    }

    public List<TblCompanyDTO> getListAllCompany() {
        return listAllCompany;
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

    // hàm tìm thông tin company bằng companyID 
    //( companyID, address, city, phone, company_Description, is_Signed, tblAccount(name, mail, avatar)
    // nơi dùng : HomeShowCompanyDetailServlet, 
    public TblCompanyDTO searchCompanyByCompanyID(String companyID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT com.address, com.city, com.phone, com.company_Description,com.is_Signed, acc.username,acc.name, acc.avatar "
                        + "FROM tblCompany com JOIN tblAccount acc on com.username = acc.username "
                        + "WHERE com.companyID = ?";
                stm = con.prepareCall(sql);
                stm.setString(1, companyID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String address = rs.getNString("address");
                    String city = rs.getNString("city");
                    String phone = rs.getString("phone");
                    String com_Description = rs.getNString("company_Description");
                    boolean is_Signed = rs.getBoolean("is_Signed");
                    String username = rs.getString("username");
                    String name = rs.getNString("name");
                    String avatar = rs.getString("avatar");
                    TblAccountDTO account = new TblAccountDTO();
                    account.setName(name);
                    account.setAvatar(avatar);
                    account.setEmail(username);
                    TblCompanyDTO company = new TblCompanyDTO(companyID, address, city, phone, com_Description, is_Signed, account);
                    return company;
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
    
    public TblCompanyDTO getCompany(String companyID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblCompanyDTO company = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT companyID, address, city, phone, company_Description, is_Signed, username "
                        + "FROM tblCompany "
                        + "WHERE companyID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, companyID);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    String address = rs.getNString("address");
                    String city = rs.getNString("city");
                    String phone = rs.getString("phone");
                    String company_Description = rs.getNString("company_Description");
                    boolean is_Signed = rs.getBoolean("is_Signed");
                    String username = rs.getString("username");
                    
                    TblAccountDAO accountDAO = new TblAccountDAO();
                    TblAccountDTO companyAccount = accountDAO.getAccount(username);
                    
                    company = new TblCompanyDTO(companyID, address, city, phone,
                            company_Description, is_Signed, companyAccount);
                    
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
        return company;
    }
    
    //get Company by email
    public TblCompanyDTO getCompanyByEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblCompanyDTO company = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT companyID, address, city, phone, company_Description, is_Signed "
                        + "FROM tblCompany "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    String companyID = rs.getString("companyID");
                    String address = rs.getNString("address");
                    String city = rs.getNString("city");
                    String phone = rs.getString("phone");
                    String company_Description = rs.getNString("company_Description");
                    boolean is_Signed = rs.getBoolean("is_Signed");
                    
                    TblAccountDAO accountDAO = new TblAccountDAO();
                    TblAccountDTO companyAccount = accountDAO.getAccount(email);
                    
                    company = new TblCompanyDTO(companyID, address, city, phone,
                            company_Description, is_Signed, companyAccount);              
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
        return company;
    }
    
    public void getAllCompany() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT acc.username, acc.name, com.companyID, com.address, "
                        + "com.city, com.phone, com.company_Description, com.is_Signed "
                        + "FROM tblCompany AS com INNER JOIN tblAccount AS acc "
                        + "ON (com.username = acc.username)";
                
                stm = con.prepareCall(sql);
                
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String companyEmail = rs.getString("username");
                    String companyName = rs.getNString("name");
                    
                    TblAccountDTO account = new TblAccountDTO();
                    account.setEmail(companyEmail);
                    account.setName(companyName);
                    
                    String companyID = rs.getString("companyID");
                    String companyAddress = rs.getString("address");
                    String companyCity = rs.getString("city");
                    String companyPhone = rs.getString("phone");
                    String companyDescription = rs.getString("company_Description");
                    boolean isSigned = rs.getBoolean("is_Signed");
                    
                    TblCompanyDTO company = new TblCompanyDTO();
                    company.setCompanyID(companyID);
                    company.setAddress(companyAddress);
                    company.setCity(companyCity);
                    company.setCompany_Description(companyDescription);
                    company.setIs_Signed(isSigned);
                    company.setPhone(companyPhone);
                    company.setAccount(account);
                    
                    if (listAllCompany == null) {
                        listAllCompany = new ArrayList<>();
                    }
                    
                    listAllCompany.add(company);
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
    
    //Thanh 
    public List<TblCompanyDTO> getListByPage(List<TblCompanyDTO> list, int start, int end) {
        List<TblCompanyDTO> listPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPage.add(list.get(i));
        }
        return listPage;
    }
    
    public boolean updateCompanyStatus(String companyID, boolean status) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblCompany "
                        + "SET is_Signed = ? "
                        + "WHERE companyID = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, companyID);
                
                int effectRows = stm.executeUpdate();
                if(effectRows > 0){
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
