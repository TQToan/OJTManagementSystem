/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.utils;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class MyApplicationConstants {

    public class ShowStudentHomeFeature {

        public static final String STUDENT_HOME_PAGE = "StudentHomePage";

    }
    
    public class StudentSaveJobFeature {
        public static final String STUDENT_SAVE_JOB_PAGE = "StudentSaveJobJSP";
        public static final String STUDENT_SAVE_JOB_CONTROLLER = "StudentSaveJobController";
        public static final String STUDENT_SEARCH_SAVE_JOB_CONTROLLER = "SearchSaveJobController";
        public static final String STUDENT_DELETE_SAVE_JOB_CONTROLLER = "StudentDeleteSaveJobController";
        public static final String STUDENT_DASHBOARD_PAGE = "studentDashboardPage";
        public static final String STUDENT_DASHBOARD_CONTROLLER = "studentDashboardController";
    }
    
    public class StudentDasboardFeature {
        public static final String STUDENT_DASHBOARD_PAGE = "studentDashboardPage";
    }

    public class SearchComanyStudentHomeFeature {
        // trang home, xem thông tin company detail (servelet,jsp)
        public static final String HOME_SHOW_COMPANY_DETAIL_JSP = "HomeShowCompanyDetailJSP";
        public static final String HOME_SHOW_COMPANY_DETAIL_CONTROLLER = "HomeShowCompanyDetail";


        public static final String STUDENT_HOME_PAGE = "ShowStudentHomeController";
        public static final String SEARCH_COMPANY_POST_PAGE = "HomeResultPageJSP";

    }

    public class RegisterCompanyFeature {

        public static final String REGISTER_COMPANY_PAGE_1_JSP = "RegisterPage1JSP";
        public static final String REGISTER_COMPANY_PAGE_1 = "RegisterPage1";
        public static final String LOGIN_PAGE = "";
        public static final String REGISTER_COMPANY_PAGE_2_JSP = "RegisterPage2JSP";

    }

    public class Constants {

        public static final String GOOGLE_CLIENT_ID = "206603271676-8up4i9nf258hmlcol6khi7hcfkf7ibcn.apps.googleusercontent.com";

        public static final String GOOGLE_CLIENT_SECRET = "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";

        public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/OJTManagementSystem/LoginGoogleServlet";

        public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

        public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

        public static final String GOOGLE_GRANT_TYPE = "authorization_code";
    }

    public class LoginFeture {

        public static final String COMPANY_DASHBOARD_PAGE = "companyDashboardPage";
        public static final String LOGIN_PAGE = "loginPage";
    }
    public class LogoutFeture {
        public static final String LOGIN_PAGE = "loginPage";
    }
    public class LoginGoogleFeture {

        public static final String ADMIN_DASHBOARD_PAGE = "adminDashboardPage";
        public static final String LOGIN_PAGE = "loginPage";
        public static final String STUDENT_DASHBOARD_PAGE = "studentDashboardController";
        public static final String ADD_STUDENT_CONTROLLER = "addStudentController";
    }

    public class AddStudentFeture {

        public static final String STUDENT_DASHBOARD_PAGE = "studentDashboardPage";
    }
    
    public class ImportStudentExcelFileFeature{
        public static final String ADMIN_STUDENT_MANAGEMENT_PAGE = "AdminStudentManagementPage";
    }
}
