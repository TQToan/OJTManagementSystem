/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblfollowing_post;

import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblcompany.TblCompanyDTO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
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
 * @author ThanhTy
 */
public class TblFollowing_PostDAO implements Serializable {

    private List<TblFollowing_PostDTO> followingPostByFilter;

    public List<TblFollowing_PostDTO> getFollowingPostByFilter() {
        return followingPostByFilter;
    }

    public void getFollowingPost()throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select post.title_Post, acc.name, post.workLocation, post.postingDate, post.expirationDate, stu.studentCode, post.postID "
                        + "FROM tblCompany_Post as post INNER JOIN tblCompany as cm ON (post.companyID = cm.companyID) "
                        + "INNER JOIN tblFollowing_Post as flp ON (flp.postID = post.postID) "
                        + "INNER JOIN tblStudent as stu ON (stu.studentCode = flp.studentCode) "
                        + "INNER JOIN tblAccount as acc ON (acc.username = cm.username) ";
                stm = con.prepareCall(sql);
                System.out.println("Chay o TblFollowing_PostDAO");
                rs = stm.executeQuery();
                while (rs.next()) {

                    int postID = rs.getInt("postID");
                    String title_Post = rs.getNString("title_Post");
                    Date postingDate = rs.getDate("postingDate");
                    Date exprirationDate = rs.getDate("expirationDate");
                    String workLocation = rs.getNString("workLocation");
                    String company_Name = rs.getNString("name");
                    String studentCode = rs.getString("studentCode");
//
//                    TblAccountDTO account = new TblAccountDTO();
//                    account.setName(company_Name);
//
//                    TblCompany_PostDTO comPost = new TblCompany_PostDTO();
//                    comPost.setExpirationDate(exprirationDate);
//                    comPost.setTitle_Post(title_Post);
//                    comPost.setPostingDate(postingDate);
//                    comPost.setWorkLocation(workLocation);
//
//                    TblCompanyDTO company = new TblCompanyDTO();
//                    company.setAccount(account);

                    TblFollowing_PostDTO post = new TblFollowing_PostDTO();
                    post.setStudentID(studentCode);
                    post.setPostID(postID);
                    post.setExprirationDate(exprirationDate);
                    post.setTittle_Post(title_Post);
                    post.setPostingDate(postingDate);
                    post.setWorkLocation(workLocation);
                    post.setCompanyName(company_Name);
                    if (followingPostByFilter == null) {
                        followingPostByFilter = new ArrayList<>();
                    }

                    followingPostByFilter.add(post);

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

    public void searchPostByFilter(String tittlePost,
            String companyName, String nameLocation) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select post.title_Post, acc.name, post.workLocation, post.postingDate, post.expirationDate, stu.studentCode, post.postID "
                        + "FROM tblCompany_Post as post INNER JOIN tblCompany as cm ON (post.companyID = cm.companyID) "
                        + "INNER JOIN tblFollowing_Post as flp ON (flp.postID = post.postID) "
                        + "INNER JOIN tblStudent as stu ON (stu.studentCode = flp.studentCode) "
                        + "INNER JOIN tblAccount as acc ON (acc.username = cm.username) ";
//                String sql = "Select post.postID, post.title_Post, post.quantityInterns, major.majorName, acc.name, post.workLocation, post.postingDate, post.expirationDate, "
//                            + "post.school_confirm, post.statusPost "
//                        + "FROM tblCompany_Post as post INNER JOIN tblCompany as cm ON (post.companyID = cm.companyID) "
//                        + "INNER JOIN tblFollowing_Post as flp ON (flp.postID = post.postID) "
//                        + "INNER JOIN tblStudent as stu ON (stu.studentCode = flp.studentCode) "
//                        + "INNER JOIN tblAccount as acc ON (acc.username = cm.username) ";
                if (tittlePost.isEmpty() == true && companyName.isEmpty() == true
                        && nameLocation.isEmpty() == true) {
                    sql = "Select post.title_Post, acc.name, post.workLocation, post.postingDate, post.expirationDate, stu.studentCode, post.postID "
                            + "FROM tblCompany_Post as post INNER JOIN tblCompany as cm ON (post.companyID = cm.companyID) "
                            + "INNER JOIN tblFollowing_Post as flp ON (flp.postID = post.postID) "
                            + "INNER JOIN tblStudent as stu ON (stu.studentCode = flp.studentCode) "
                            + "INNER JOIN tblAccount as acc ON (acc.username = cm.username) ";
                    stm = con.prepareCall(sql);
                    System.out.println("loi o day 0");
                }

                if (tittlePost.isEmpty() == false && companyName.isEmpty() == false
                        && nameLocation.isEmpty() == false) {
                    sql += " WHERE post.title_Post like ? and acc.name like ? and post.workLocation LIKE ?";
                    stm = con.prepareCall(sql);
                    System.out.println("loi o day 1");
                    stm.setNString(1, "%" + tittlePost + "%");
                    stm.setString(2, "%" + companyName + "%");
                    stm.setNString(3, "%" + nameLocation + "%");
                }
                if (tittlePost.isEmpty() == false && companyName.isEmpty() == true
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE acc.name like ?  and post.workLocation LIKE ?";
                    System.out.println("loi o day 2");
                    stm = con.prepareCall(sql);
                    stm.setString(1, "%" + companyName + "%");
                    stm.setNString(2, "%" + nameLocation + "%");
                }
                if (tittlePost.isEmpty() == false && companyName.isEmpty() == false
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.title_Post like ? and acc.name like ? ";
                    System.out.println("loi o day 3");
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + tittlePost + "%");
                    stm.setString(2, "%" + companyName + "%");
                }
                if (tittlePost.isEmpty() == false && companyName.isEmpty() == true
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.title_Post like ? ";
                    System.out.println("loi o day 4");
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + tittlePost + "%");;
                }
                if (tittlePost.isEmpty() == true && companyName.isEmpty() == false
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE acc.name like ? and post.workLocation LIKE ?";
                    System.out.println("loi o day 5");
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + companyName + "%");
                    stm.setNString(2, "%" + nameLocation + "%");
                }
                if (tittlePost.isEmpty() == true && companyName.isEmpty() == false
                        && nameLocation.isEmpty() == true) {
                    sql += "WHERE post.majorID = ? ";
                    System.out.println("loi o day 6");
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + companyName + "%");
                }
                if (tittlePost.isEmpty() == true && companyName.isEmpty() == true
                        && nameLocation.isEmpty() == false) {
                    sql += "WHERE post.workLocation LIKE ? ";
                    System.out.println("loi o day 7");
                    stm = con.prepareCall(sql);
                    stm.setNString(1, "%" + nameLocation + "%");
                }
                rs = stm.executeQuery();
                while (rs.next()) {

                    int postID = rs.getInt("postID");
                    String title_Post = rs.getNString("title_Post");
                    Date postingDate = rs.getDate("postingDate");
                    Date exprirationDate = rs.getDate("expirationDate");
                    String workLocation = rs.getNString("workLocation");
                    String company_Name = rs.getNString("name");
                    String studentCode = rs.getString("studentCode");
//
//                    TblAccountDTO account = new TblAccountDTO();
//                    account.setName(company_Name);
//
//                    TblCompany_PostDTO comPost = new TblCompany_PostDTO();
//                    comPost.setExpirationDate(exprirationDate);
//                    comPost.setTitle_Post(title_Post);
//                    comPost.setPostingDate(postingDate);
//                    comPost.setWorkLocation(workLocation);
//
//                    TblCompanyDTO company = new TblCompanyDTO();
//                    company.setAccount(account);

                    TblFollowing_PostDTO post = new TblFollowing_PostDTO();
                    post.setStudentID(studentCode);
                    post.setPostID(postID);
                    post.setExprirationDate(exprirationDate);
                    post.setTittle_Post(title_Post);
                    post.setPostingDate(postingDate);
                    post.setWorkLocation(workLocation);
                    post.setCompanyName(company_Name);
                    if (followingPostByFilter == null) {
                        followingPostByFilter = new ArrayList<>();
                    }

                    followingPostByFilter.add(post);

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

    public List<TblFollowing_PostDTO> getListByPage(List<TblFollowing_PostDTO> list, int start, int end) {
        List<TblFollowing_PostDTO> listPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPage.add(list.get(i));
        }
        return listPage;
    }

    public boolean addFollowingPost(int idPost, String studentCode)
            throws SQLException, NamingException {
//        TblFollowing_PostDTO dto = new TblFollowing_PostDTO();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblFollowing_Post (studentCode, postID) "
                        + "VALUES (?, ?) ";
                stm = con.prepareStatement(sql);

                stm.setString(1, studentCode);
                stm.setInt(2, idPost);

                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
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
}
