/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.customtag;

import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

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

    public static String changeDateFormat(java.sql.Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);
            return dateString;
        }
        return null;
    }

    public static String getError(String Error, String studentCode) {
        if (Error != null || "".equals(Error) == false) {
            StringTokenizer stk = new StringTokenizer(Error, "_");
            if (stk.hasMoreTokens()) {
                String message = stk.nextToken();
                String studentRoll = stk.nextToken();
                if (studentRoll.equals(studentCode)) {
                    return message;
                }
            }
        }
        return null;
    }
}
