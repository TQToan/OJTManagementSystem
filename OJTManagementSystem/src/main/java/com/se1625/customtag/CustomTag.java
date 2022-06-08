/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.customtag;

import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class CustomTag {

    public static Boolean getStatusSaveJob(List<TblFollowing_PostDTO> listFollowingPost, Integer postID) {
        if (listFollowingPost != null) {
            for (TblFollowing_PostDTO tblFollowing_PostDTO : listFollowingPost) {
                if (tblFollowing_PostDTO.getPostID() == postID) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Boolean getStatusAcceptCompanyPost(List<TblCompany_PostDTO> listCompanyPost, Integer postID) {
        if (listCompanyPost != null) {
            for (TblCompany_PostDTO tblCompany_PostDTO : listCompanyPost) {
                if (tblCompany_PostDTO.getPostID() == postID) {
                    if(tblCompany_PostDTO.getStatusPost() == 2)
                    return true;
                }
            }
        }
        return false;
    }

    public static String changeDateFormat(java.sql.Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);
            return dateString;
        }
        return null;
    }
}
