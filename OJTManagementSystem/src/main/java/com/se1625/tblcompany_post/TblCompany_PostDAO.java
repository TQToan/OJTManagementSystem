/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblcompany_post;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany.TblCompanyDAO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblmajor.TblMajorDAO;
import com.se1625.tblmajor.TblMajorDTO;
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
public class TblCompany_PostDAO implements Serializable {

    private List<TblCompany_PostDTO> companyPostListHome;
    private List<TblCompany_PostDTO> companyPostByFilter;

    public List<TblCompany_PostDTO> getCompanyPostListHome() {
        return companyPostListHome;
    }

    public List<TblCompany_PostDTO> getCompanyPostByFilter() {
        return companyPostByFilter;
    }

    public void getListRecomendPost(String majorName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 6 cp.postID, cp.title_Post, "
                        + " cp.postingDate, cp.quantityInterns, "
                        + "cp.expirationDate, cp.school_confirm, cp.statusPost, cp.workLocation, "
                        + "m.majorID, m.majorName, ac.name, ac.avatar "
                        + "FROM tblCompany_Post AS cp INNER JOIN tblMajor AS m ON (cp.majorID = m.majorID) "
                        + " INNER JOIN tblCompany AS com ON (cp.companyID = com.companyID) "
                        + "INNER JOIN tblAccount AS ac ON (com.username = ac.username) "
                        + " WHERE m.majorName = ? and com.is_Signed = ? "
                        + "ORDER BY cp.expirationDate DESC ";
                stm = con.prepareCall(sql);
                stm.setNString(1, majorName);
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("postID");
                    String title_Post = rs.getNString("title_Post");
                    Date postingDate = rs.getDate("postingDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int quantityInterns = rs.getInt("quantityInterns");
                    boolean school_confirm = rs.getBoolean("school_confirm");
                    int statusPost = rs.getInt("statusPost");
                    String workLocation = rs.getNString("workLocation");
                    int majorID = rs.getInt("majorID");
                    String nameMajor = rs.getNString("majorName");
                    String companyName = rs.getNString("name");
                    String avatar = rs.getString("avatar");

                    if (school_confirm == true && statusPost == 1) {
                        TblCompany_PostDTO dto = new TblCompany_PostDTO();
                        dto.setPostID(postID);
                        dto.setTitle_Post(title_Post);
                        dto.setPostingDate(postingDate);
                        dto.setExpirationDate(expirationDate);
                        dto.setQuantityIterns(quantityInterns);
                        dto.setWorkLocation(workLocation);

                        TblMajorDTO major = new TblMajorDTO();
                        major.setMajorID(majorID);
                        major.setMajorName(nameMajor);
                        dto.setMajor(major);

                        TblAccountDTO account = new TblAccountDTO();
                        account.setName(companyName);
                        account.setAvatar(avatar);

                        TblCompanyDTO company = new TblCompanyDTO();
                        company.setAccount(account);
                        dto.setCompany(company);

                        if (companyPostListHome == null) {
                            companyPostListHome = new ArrayList<>();
                        }
                        companyPostListHome.add(dto);
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
    }

    public void getListPostHome() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 8 cp.postID, cp.title_Post, "
                        + " cp.postingDate, cp.quantityInterns, "
                        + "cp.expirationDate, cp.school_confirm, cp.statusPost, cp.workLocation, "
                        + "m.majorName, ac.name, ac.avatar \n"
                        + "FROM tblCompany_Post AS cp INNER JOIN tblMajor AS m ON (cp.majorID = m.majorID) \n"
                        + " INNER JOIN tblCompany AS com ON (cp.companyID = com.companyID) "
                        + "INNER JOIN tblAccount AS ac ON (com.username = ac.username)\n"
                        + "ORDER BY cp.expirationDate DESC";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("postID");
                    String title_Post = rs.getNString("title_Post");
                    Date postingDate = rs.getDate("postingDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int quantityInterns = rs.getInt("quantityInterns");
                    boolean school_confirm = rs.getBoolean("school_confirm");
                    int statusPost = rs.getInt("statusPost");
                    String workLocation = rs.getNString("workLocation");
                    String majorName = rs.getNString("majorName");
                    String companyName = rs.getNString("name");
                    String avatar = rs.getString("avatar");

                    if (school_confirm == true && statusPost == 1) {
                        TblCompany_PostDTO dto = new TblCompany_PostDTO();
                        dto.setPostID(postID);
                        dto.setTitle_Post(title_Post);
                        dto.setPostingDate(postingDate);
                        dto.setExpirationDate(expirationDate);
                        dto.setQuantityIterns(quantityInterns);
                        dto.setWorkLocation(workLocation);
                        dto.setMajorName(majorName);

                        TblAccountDTO account = new TblAccountDTO();
                        account.setName(companyName);
                        account.setAvatar(avatar);

                        TblCompanyDTO company = new TblCompanyDTO();
                        company.setAccount(account);

                        dto.setCompany(company);
                        if (companyPostListHome == null) {
                            companyPostListHome = new ArrayList<>();
                        }
                        companyPostListHome.add(dto);
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
    }

    public void searchPostByFilter(String companyID,
            int MajorID, String nameLocation) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT post.postID, post.title_Post, post.quantityInterns, post.postingDate, "
                        + "post.expirationDate, post.workLocation, major.majorName, acc.name, acc.avatar, "
                        + "post.school_confirm, post.statusPost "
                        + "FROM tblCompany_Post AS post INNER JOIN tblCompany AS cm ON (post.companyID = cm.companyID) "
                        + "INNER JOIN tblAccount AS acc ON (cm.username = acc.username) INNER JOIN tblMajor AS major "
                        + "ON (post.majorID = major.majorID) ";
                if (companyID.isEmpty() == false && MajorID != 0
                        && nameLocation.isEmpty() == false) {
                    sql += " WHERE post.companyID = ? and post.majorID = ? "
                            + "and post.workLocation LIKE ?";
                    stm = con.prepareCall(sql);
                    stm.setString(1, companyID);
                    stm.setInt(2, MajorID);
                    stm.setNString(3, "%" + nameLocation + "%");
                }
                if (companyID.isEmpty() == false && MajorID == 0
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE post.companyID = ? and post.workLocation LIKE ?";
                    stm = con.prepareCall(sql);
                    stm.setString(1, companyID);
                    stm.setNString(2, "%" + nameLocation + "%");
                }
                if (companyID.isEmpty() == false && MajorID != 0
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.companyID = ? and post.majorID = ? ";
                    stm = con.prepareCall(sql);
                    stm.setString(1, companyID);
                    stm.setInt(2, MajorID);
                }
                if (companyID.isEmpty() == false && MajorID == 0
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.companyID = ?  ";
                    stm = con.prepareCall(sql);
                    stm.setString(1, companyID);
                }
                if (companyID.isEmpty() == true && MajorID != 0
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE post.majorID = ? and post.workLocation LIKE ?";
                    stm = con.prepareCall(sql);
                    stm.setInt(1, MajorID);
                    stm.setNString(2, "%" + nameLocation + "%");
                }
                if (companyID.isEmpty() == true && MajorID != 0
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.majorID = ? ";
                    stm = con.prepareCall(sql);
                    stm.setInt(1, MajorID);
                }
                if (companyID.isEmpty() == true && MajorID == 0
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE post.workLocation LIKE ? ";
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + nameLocation + "%");
                }

                if (companyID.isEmpty() == true && MajorID == 0
                        && nameLocation.isEmpty() == true) {
                    stm = con.prepareCall(sql);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("postID");
                    String title_Post = rs.getNString("title_Post");
                    int quanityItens = rs.getInt("quantityInterns");
                    Date postingDate = rs.getDate("postingDate");
                    Date exprirationDate = rs.getDate("expirationDate");
                    boolean school_confirm = rs.getBoolean("school_confirm");
                    int status_Post = rs.getInt("statusPost");
                    String workLocation = rs.getNString("workLocation");
                    String majorName = rs.getNString("majorName");
                    String companyName = rs.getNString("name");
                    String avatar = rs.getString("avatar");
                    if (school_confirm == true && status_Post == 1 && quanityItens > 0) {
                        TblAccountDTO account = new TblAccountDTO();
                        account.setName(companyName);
                        account.setAvatar(avatar);

                        TblCompanyDTO company = new TblCompanyDTO();
                        company.setAccount(account);

                        TblCompany_PostDTO post = new TblCompany_PostDTO();
                        post.setCompany(company);
                        post.setMajorName(majorName);
                        post.setExpirationDate(exprirationDate);
                        post.setPostID(postID);
                        post.setPostingDate(postingDate);
                        post.setWorkLocation(workLocation);
                        post.setQuantityIterns(quanityItens);
                        post.setTitle_Post(title_Post);

                        if (companyPostByFilter == null) {
                            companyPostByFilter = new ArrayList<>();
                        }

                        companyPostByFilter.add(post);
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
    }

    public List<TblCompany_PostDTO> getListByPage(List<TblCompany_PostDTO> list, int start, int end) {
        List<TblCompany_PostDTO> listPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPage.add(list.get(i));
        }
        return listPage;
    }

    // hàm tìm bài post bằng postID, trả ra tất cả fields của tblCompanyPost 
    // (postID, title_Post, job_Description, job_Requirement, remuneration,workLoaction, 
    // quantityInterns, postingDate, expirationDate, school_confirm, statusPost, 
    // tblCompany(companyID), tblMajor(majorID).
    // nơi dùng: HomeShowCompanyDetailServlet
    public TblCompany_PostDTO searchPostByPostID(int postID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT post.title_Post, post.job_Description, post.job_Requirement, post.remuneration,"
                        + "post.workLocation, post.quantityInterns, post.postingDate, post.expirationDate,"
                        + "post.school_confirm, post.statusPost, post.companyID, major.majorName, com.companyID "
                        + "FROM tblCompany_Post post JOIN tblMajor major on post.majorID = major.majorID "
                        + "JOIN tblCompany com on com.companyID = post.companyID "
                        + "WHERE post.postID = ?";
                stm = con.prepareCall(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String title_Post = rs.getNString("title_Post");
                    String job_Description = rs.getNString("job_Description");
                    String job_Requirement = rs.getNString("job_Requirement");
                    String remuneration = rs.getNString("remuneration");
                    int quanityItens = rs.getInt("quantityInterns");
                    Date postingDate = rs.getDate("postingDate");
                    Date exprirationDate = rs.getDate("expirationDate");
                    boolean school_confirm = rs.getBoolean("school_confirm");
                    int status_Post = rs.getInt("statusPost");
                    String workLocation = rs.getNString("workLocation");
                    String majorName = rs.getNString("majorName");
                    String companyID = rs.getString("companyID");

                    TblCompanyDTO company = new TblCompanyDTO();
                    company.setCompanyID(companyID);

                    TblCompany_PostDTO post = new TblCompany_PostDTO(postID, title_Post, job_Description, job_Requirement, remuneration,
                            workLocation, quanityItens, postingDate, exprirationDate, school_confirm, status_Post, company, majorName);
                    return post;
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

    public TblCompany_PostDTO getCompanyPost(int postID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblCompany_PostDTO companyPost = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT postID, title_Post, job_Description, "
                        + "job_Requirement, remuneration, workLocation, "
                        + "quantityInterns, postingDate, expirationDate, "
                        + "school_Confirm, statusPost, companyID, majorID "
                        + "FROM tblCompany_Post "
                        + "WHERE postID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postID);

                rs = stm.executeQuery();

                if (rs.next()) {

                    String title_Post = rs.getNString("title_Post");
                    String job_Description = rs.getNString("job_Description");
                    String job_Requirement = rs.getNString("job_Requirement");
                    String remuneration = rs.getNString("remuneration");

                    String workLocation = rs.getNString("workLocation");
                    int quantityInterns = rs.getInt("quantityInterns");
                    Date postingDate = rs.getDate("postingDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    boolean schoolConfirm = rs.getBoolean("school_Confirm");
                    int statusPost = rs.getInt("statusPost");
                    String companyID = rs.getString("companyID");
                    int majorID = rs.getInt("majorID");

                    TblCompanyDAO companyDAO = new TblCompanyDAO();
                    TblCompanyDTO company = companyDAO.getCompany(companyID);

                    TblMajorDAO majorDAO = new TblMajorDAO();
                    TblMajorDTO major = majorDAO.getMajor(majorID);

                    companyPost = new TblCompany_PostDTO();
                    companyPost.setPostID(postID);
                    companyPost.setTitle_Post(title_Post);
                    companyPost.setJob_Description(job_Description);
                    companyPost.setJob_Requirement(job_Requirement);
                    companyPost.setRemuneration(remuneration);
                    companyPost.setQuantityIterns(quantityInterns);
                    companyPost.setWorkLocation(workLocation);
                    companyPost.setPostingDate(postingDate);
                    companyPost.setExpirationDate(expirationDate);
                    companyPost.setSchool_confirm(schoolConfirm);
                    companyPost.setStatusPost(statusPost);
                    companyPost.setCompany(company);
                    companyPost.setMajor(major);
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
        return companyPost;
    }
}
