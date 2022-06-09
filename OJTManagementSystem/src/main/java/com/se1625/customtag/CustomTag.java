/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.customtag;

import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.tblapplication.TblApplicationDTO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblfollowing_post.TblFollowing_PostDTO;
import com.se1625.tblstudent.TblStudentDTO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

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
    
    public static TblApplicationDTO getApplicationOfStudentByID(TblStudentDTO student, ServletContext context) {
        TblApplicationDAO applicationDAO = new TblApplicationDAO();
        TblApplicationDTO application = null;
        try {
            application = applicationDAO.getApplication(student.getStudentCode(), student.getSemester().getSemesterID());
        } catch (SQLException ex) {
            context.log("SQLException at CustomTag " + ex.getMessage());
        } catch (NamingException ex) {
            context.log("NamingException at CustomTag " + ex.getMessage());
        }
        return application;
    }
}
