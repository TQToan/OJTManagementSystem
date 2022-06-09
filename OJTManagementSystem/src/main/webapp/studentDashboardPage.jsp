<%-- 
    Document   : studentDashboard
    Created on : May 25, 2022, 9:29:06 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student - Dashboard</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/student.css">
        <link rel="stylesheet" href="./assets/css/student-responsive.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  
    </head>

    <body>
        <header></header>

        <div class="navbar navbar-expand-md navbar-sm-cus ">
            <a href="ShowStudentHomeController" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <i class="fa-solid fa-bars nav__respo--btn"></i>
            </button>
            <div class="collapse navbar-collapse navbar-collapse-cus" id="collapsibleNavbar">
                <a href="ShowStudentProfileController" class=" nav__infor--link text-truncate text-center">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                     ${student.account.name}
                </a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="studentDashboardController" class="nav__item--link">
                            <i class="fas fa-palette "></i>
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ShowStudentProfileController" class="nav__item--link">
                            <i class="fas fa-user-edit"></i>
                            My Profile
                        </a>
                    </li>
                    <li class="nav-item nav__items">
                        <div  class="nav__item--link nav__item--dropdown">
                            <i class="fas fa-pen"></i>
                            My Jobs
                            <i class="fas fa-angle-down icon-down"></i>
                        </div>
                        <div class="nav__item__dropdown">
                            <a href="SearchSaveJobController" class="nav__item__dropdown--link">
                                Saved Jobs
                            </a>
                            <a href="ShowStudentAppliedJobController" class="nav__item__dropdown--link">
                                Applied Jobs
                            </a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a href="ReviewInternShipController" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Review Internship
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
            <c:set var="student" value="${sessionScope.STUDENT_ROLE}"/>
            <nav class="col-xl-2  nav-fixed col-md-3">
                <a href="ShowStudentHomeController" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>

                <a href="ShowStudentProfileController" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${student.account.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">

                        <a href="studentDashboardController" class="nav__item--link">
                            <i class="fas fa-palette "></i>
                            Dashboard
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="ShowStudentProfileController" class="nav__item--link">
                            <i class="fas fa-user-edit"></i>
                            My Profile
                        </a> 
                    </li>
                    <li class="nav__items">
                        <div  class="nav__item--link nav__item--dropdown">
                            <i class="fas fa-pen"></i>
                            My Jobs
                            <i class="fas fa-angle-down icon-down"></i>
                        </div>
                        <div class="nav__item__dropdown">
                            <a href="SearchSaveJobController" class="nav__item__dropdown--link">
                                Saved Jobs
                            </a>
                            <a href="ShowStudentAppliedJobController" class="nav__item__dropdown--link">
                                Applied Jobs
                            </a>
                        </div>
                    </li>
                    <li class="nav__items">
                        <a href="ReviewInternShipController" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Review Internship
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

            <div class="main-body offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">

                <div class="row">
                    <div class="dashboard-card offset-xl-3 col-xl-2 offset-md-2 col-md-3 offset-2 col-3">
                        <a href="SearchSaveJobController" class="dashboard-card--link">
                            <div class="save-jobs">
                                ${requestScope.NUMBER_OF_FOLLOWING_POST}
                            </div>
                            <div class="dashboard-card__content">
                                Save Jobs
                            </div>
                        </a>
                    </div>

                    <div class="dashboard-card offset-xl-2 col-xl-2 offset-md-2 col-md-3 offset-2 col-3">
                        <a href="ShowStudentAppliedJobController" class="dashboard-card--link">
                            <div class="applied-jobs ">
                                ${requestScope.NUMBER_OF_APLLIED_JOB}
                            </div>
                            <div class="dashboard-card__content">
                                Applied Jobs
                            </div>
                        </a>
                    </div>
                </div>

                <div class="row">
                    <form action="studentDashboardController">
                        <div class="card-visit offset-xl-3 col-xl-6 offset-md-1 col-md-10 offset-sm-2 col-sm-8 offset-1 col-10">
                            <div class="card-visit__header">
                                Student Profile
                            </div>
                            <div class="card-visit__body row">
                                <div class="card-visit--img  col-4">
                                    <c:if test="${empty student.account.avatar}">
                                        <img class="img-responsive" src="./avatars/person.jpg" alt="">
                                    </c:if>
                                    <c:if test="${not empty student.account.avatar}">
                                        <img class="img-responsive" src="./avatars/${student.account.avatar}" alt="${student.account.avatar}">
                                    </c:if>
                                </div>
                                <div class="card-vist__content offset-1 col-7">
                                    <h3>${student.account.name}</h3>
                                    <p>Date of birth: ${my:changeDateFormat(student.birthDay)}</p>
                                    <p>Major: ${student.major}</p>
                                    <p>Email: ${student.account.email}</p>
                                </div>
                            </div>
                            <a href="ShowStudentProfileController" class="card-visit-btn primary-btn">
                                <i class="fas fa-edit"></i>
                                Edit
                            </a>
                        </div>
                    </form>
                </div>


                <div class="recom-jobs">
                    <div class="recom-header">
                        Recommended Jobs
                    </div>

                    <div class="row row-cols-xl-2 row-cols-1">
                        <c:forEach items="${requestScope.LIST_RECOMMEND_POST}" var="recommendPost">
                            <c:set var="majorID" value="${recommendPost.major.majorID}"/>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="HomeShowCompanyDetailController?postID=${recommendPost.postID}">
                                        <span>
                                            
                                        <h3>${recommendPost.title_Post}</h3>
                                        <h4>${recommendPost.company.account.name}</h4>
                                        </span>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./avatars/${recommendPost.company.account.avatar}" alt="${recommendPost.company.account.avatar}">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: ${recommendPost.quantityIterns}</p>
                                        <p>Location: ${recommendPost.workLocation}</p>
                                        <p>Expiration Date: ${my:changeDateFormat(recommendPost.expirationDate)}</p>
                                        
                                        <c:set var="listFollowingPost" value="${requestScope.LIST_FOLLOWING_POST}"/>
                                        <c:set var="statusFollowingPost" value="${my:getStatusSaveJob(listFollowingPost, recommendPost.postID)}"/>
                                        <p>
                                            <c:if test="${statusFollowingPost eq true}">
<!--                                                <form action="StudentDeleteSaveJobController" method="POST">
                                                    <input type="hidden" name="unSave" value="studentDashboardPage" />
                                                    <input type="hidden" name="postID" value="${recommendPost.postID}" />
                                                    <input type="submit" value="Unsave Job" class="far fa-heart save-btn save-btn-active" />
                                                </form>-->
                                                <c:url var="unsaveJob" value="StudentDeleteSaveJobController">
                                                    <c:param name="postID" value="${recommendPost.postID}"/>
                                                    <c:param name="unSave" value="studentDashboardPage"/>
                                                    
                                                </c:url>
                                                <a href="${unsaveJob}" class="far fa-heart save-btn "></a>
                                            </c:if>
                                            <c:if test="${statusFollowingPost eq false}">
<!--                                                <form action="StudentSaveJobController" method="POST">
                                                    <input type="hidden" name="postID" value="${recommendPost.postID}" />
                                                    <input type="submit" value="Save Job" class="far fa-heart save-btn save-btn-active" />
                                                </form>-->
                                                    <c:url var="saveJob" value="StudentSaveJobController">
                                                        <c:param name="postID" value="${recommendPost.postID}"/>
                                                    </c:url>
                                                    <a href="${saveJob}" class="far fa-heart save-btn save-btn-active"></a>
                                            </c:if>
                                        </p>

                                        <%--

                                            <c:if test="${requestScope.FOLLOWING == true}" var="following">
                                                       hidden="hidden"
                                                   </c:if>
                                                       

                                                <c:url var="urlSaveJob" value="StudentSaveJobController">
                                                <c:param name="postID" value="${dto.postID}"/>
                                                <c:param name="studentCode" value="${student.studentCode}"/>
                                                
                                            </c:url>
                                            <p>
                                                <a href="${urlSaveJob}">
                                                    <i class="far fa-heart save-btn save-btn-active"></i>
                                                    Save Job
                                                </a>
                                            </p>--%>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="recom__see-more--btn row">
                        <c:url var="urlSearchHome" value="SearchCompanyStudentHomeController">
                            <c:param name="nameCompany" value=""/>
                            <c:param name="nameMajor" value="${majorID}"/>
                            <c:param name="nameLocation" value=""/>
                        </c:url>
                        <a href="${urlSearchHome}" class="recom--more--btn text-end">
                            See More 
                            <i class="fas fa-arrow-right"></i>
                        </a> 
                    </div>

                </div>
            </div>  
        </main>

        <footer class="footer">
            <div class="footer__content">
                <i class="fa-regular fa-copyright"></i> Copyright 2022
            </div>

        </footer>

    </body>
</html>
