/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.customtag;

import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import java.util.List;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class CustomTag {
    public static Boolean getStatusSaveJob(List<TblFollowing_PostDTO> listFollowingPost, Integer postID) {
        for (TblFollowing_PostDTO tblFollowing_PostDTO : listFollowingPost) {
            if (tblFollowing_PostDTO.getPostID() == postID) {
                return true;
            }
        }
        return false;
    }
}
