<%-- 
    Document   : companyViewPost
    Created on : Jun 16, 2022, 3:06:52 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - View Post</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/admin.css">
        <link rel="stylesheet" href="./assets/css/admin-responsive.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <header></header>
    <c:set var="company" value="${requestScope.COMPANY_INFORMATION}" />
    <div class="navbar navbar-expand-md navbar-sm-cus ">
        <a href="#" class="header__logo ">
            <img src="./assets/img/logo.png" alt="" class="logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <i class="fa-solid fa-bars nav__respo--btn"></i>
        </button>
        <div class="collapse navbar-collapse navbar-collapse-cus" id="collapsibleNavbar">
            <a href="CompanyShowProfileController" class=" nav__infor--link text-truncate text-center">
                <i class="fas fa-user-circle nav__infor--icon"></i>
                ${company.account.name}
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="ShowCompanyDashBoardController" class="nav__item--link">
                        <i class="fas fa-palette "></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a href="CompanyShowProfileController" class="nav__item--link">
                        <i class="fas fa-user-edit"></i>
                        My Profile
                    </a>
                </li>
                <li class="nav-item nav__items">
                    <a href="CompanyShowPostController" class="nav__item--link">
                        <i class="fas fa-pen"></i>
                        My Posts
                    </a>
                </li>
                <li class="nav-item nav__items">
                    <a href="CompanyShowInternsManagermentController" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Interns Management
                    </a>
                </li>
                <li class="nav-item nav__items">
                    <a href="CompanyShowIntershipApplicationController" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Internship Application
                    </a>
                </li>
                <li class="nav-item">
                    <a href="logoutController" class="nav__item--link">
                        <i class="fas fa-power-off"></i>
                        Logout
                    </a>
                </li>

            </ul>
        </div>
    </div>

    <main class="row">
        <nav class="col-xl-2  nav-fixed col-md-3">
            <a href="#" class="nav__logo ">
                <img src="./assets/img/logo.png" alt="" class="nav--logo">
            </a>
            <a href="CompanyShowProfileController" class=" nav__infor--link text-truncate">
                <i class="fas fa-user-circle nav__infor--icon"></i>
                ${company.account.name}
            </a>

            <ul class="nav__content">
                <li class="nav__items">
                    <a href="ShowCompanyDashBoardController" class="nav__item--link">
                        <i class="fas fa-palette "></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav__items">
                    <a href="CompanyShowProfileController" class="nav__item--link">
                        <i class="fas fa-user-edit"></i>
                        My Profile
                    </a>
                </li>
                <li class="nav__items">
                    <a href="ShowCreateNewCompanyPostController" class="nav__item--link">
                        <i class="fas fa-pen"></i>
                        My Posts
                    </a>
                </li>
                <li class="nav__items">
                    <a href="CompanyShowInternsManagermentController" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Interns Management
                    </a>
                </li>
                <li class="nav__items">
                    <a href="CompanyShowIntershipApplicationController" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Internship Application
                    </a>
                </li>
                <li class="nav__items">
                    <a href="logoutController" class="nav__item--link">
                        <i class="fas fa-power-off"></i>
                        Logout
                    </a>
                </li>
            </ul>

        </nav>
        <c:set var="post" value="${requestScope.COMPANY_POST_DETAIL}"/>
        <div class="main-body  offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">
            <div class="main-body-aViewPost">
                <div class="aViewPost__header">
                    ${post.title_Post}
                </div>
                <div class="aViewPost__content">
                    <p><strong>Major:</strong> ${post.major.majorName}</p>
                    <p><strong>Vacancy:</strong> ${post.vacancy}</p>
                    <p><strong>Quantity:</strong> ${post.quantityIterns}</p>
                    <p><strong>Job Description:</strong> 
                        ${post.job_Description}
                    </p>
                    <p><strong>Job requirements:</strong> 
                        ${post.job_Requirement}               
                    </p>
                    <p><strong>Remuneration:</strong>
                        ${post.remuneration}
                    </p>
                    <p><strong>Work location:</strong>
                        ${post.workLocation}
                    </p>
                    <p><strong>Posting Date:</strong>
                        ${my:changeDateFormat(post.postingDate)}
                    </p>
                    <p><strong>Expiration Date:</strong>
                        ${my:changeDateFormat(post.expirationDate)}
                    </p>
                </div>
                 <div class="text-center">
                    <form action="CompanyShowPostDetailsController" method="POST">
                        <div>
                            <input type="hidden" name="postID" value="${post.postID}"/>
                            <input type="submit" value="Edit" name="btAction" class="card-visit-btn primary-btn" />
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </main>

    <footer class="footer">
        <div class="footer__content">
            @copyright 2022
        </div>

    </footer>
</body>
</html>
