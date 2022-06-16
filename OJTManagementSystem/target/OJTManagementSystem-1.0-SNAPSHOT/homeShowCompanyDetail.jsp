<%-- 
    Document   : HomeShowCompanyDetail
    Created on : May 30, 2022, 12:23:51 AM
    Author     : Thanh Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home - Post Detail</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/home.css">
        <link rel="stylesheet" href="./assets/css/home-responsive.css">
    </head>
    <body>
        <header class="header ">
            <div class="navbar header__nav_cus">
                <a href="ShowStudentHomeController" class="header__logo">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>
                <c:set var="student" value="${sessionScope.STUDENT_ROLE}" />
                <div class="header__name">
                    <div class="header__name--show">
                        Hi, ${student.account.name}
                        <i class="fas fa-angle-down icon-down"></i>
                    </div>
                    <div class="header__name--hidden">
                        <a href="studentDashboardController" class="header__name--hidden-content">Dashboard</a>
                        <a href="logoutController" class="header__name--hidden-content">Logout</a>
                    </div>
                </div>

            </div>

        </header>
        <c:set var="postDetail" value="${requestScope.POST_DETAIL}"/>

        <main class="main">
            <div class="main__search">
                <h2>Search Jobs</h2>
                <form action="SearchCompanyStudentHomeController" class="main__search-form">
                    <div class="row">
                        <div class="col-4">
                            <!--                            <input type="text" name="nameCompany" value="" id="" placeholder="Company">-->
                            <select name="nameCompany">
                                <option value="">Company Name</option>
                                <c:forEach items="${requestScope.COMPANY_NAME}" var="company">
                                    <option value="${company.companyID}" <c:if test="${company.companyID eq companyID}">
                                            selected="selected"
                                        </c:if> >${company.account.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <!--                            <input type="text" name="nameMajor" value="" id="" placeholder="Job">-->
                            <select name="nameMajor">
                                <option value="">Major</option>
                                <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                    <option value="${major.majorID}" <c:if test="${major.majorID eq majorID}">
                                            selected="selected"
                                        </c:if> >${major.majorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-2">
                            <select id="city" name="nameLocation"  class="main__search_select" >
                                <option value="">Location</option>
                                <option value="TP.HCM">TP.HCM</option>
                                <option value="Dong Nai">Đồng Nai</option>
                                <option value="Tay Ninh">Tây Ninh</option>
                                <option value="Binh Duong">Bình Dương</option>
                            </select>
                        </div>
                        <div class="col-2">
                            <input type="submit" value="Search" class="primary-btn">
                        </div>
                    </div>
                </form>
            </div>


            <div class="hComApplDetail row">
                <div class="col-md-7">
                    <div class="hComApplDetail__left">
                        <div class="hComApplDetail--header">${postDetail.company.account.name}</div>
                        <div class="hComApplDetail__left--header">
                            Application Information:
                        </div>
                        <div class="hComApplDetail__left--content">
                            <p><strong>Job:</strong> ${postDetail.title_Post}</p>
                            <p><strong>Quantity:</strong> ${postDetail.quantityIterns}</p>
                            <p><strong>Job Description:</strong> <br>
                                ${postDetail.job_Description}
                            </p>
                            <p><strong>Job requirements:</strong> <br>
                                ${postDetail.job_Requirement}           
                            </p>
                            <p><strong>Remuneration:</strong> <br>
                                ${postDetail.remuneration}
                            </p>
                            <p><strong>Work location:</strong>
                                ${postDetail.workLocation}
                            </p>
                            <p><strong>Posting Date:</strong>
                                ${my:changeDateFormat(postDetail.postingDate)}
                            </p>
                            <p><strong>Expiration Date:</strong>
                                ${my:changeDateFormat(postDetail.expirationDate)}
                            </p>
                        </div>
                        <c:set var="errorCompanyPost" value="${requestScope.ERROR_COMPANY_POST}" />
                        <c:if test="${empty errorCompanyPost}" >
                            <div class="hComApplDetail-btn">
                                <a href="ShowApplyCVController?postID=${postDetail.postID}" class="primary-btn hComApplDetail-btn--app">Apply Now</a>
                                <c:url var="urlSaveJob" value="StudentSaveJobController" >
                                    <c:param name="save" value="homeShowCompanyDetail" />
                                    <c:param name="postID" value="${postDetail.postID}" />
                                </c:url>
                                <c:url var="urlUnSaveJob" value="StudentDeleteSaveJobController" >
                                    <c:param name="unSave" value="homeShowCompanyDetail" />
                                    <c:param name="postID" value="${postDetail.postID}" />
                                </c:url>
                                <c:set var="statusFollowing" value="${my:getStatusSaveJob(requestScope.LIST_FOLLOWING_POST, postDetail.postID)}" />
                                <c:if test="${statusFollowing eq true}">
                                    <a href="${urlUnSaveJob}">
                                        <i class="far fa-heart hComApplDetail-btn-save save-btn save-btn-active "></i>
                                    </a>
                                </c:if>
                                <c:if test="${statusFollowing eq false}">
                                    <a href="${urlSaveJob}">
                                        <i class="far fa-heart hComApplDetail-btn-save save-btn"></i>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>
                        <c:if test="${not empty errorCompanyPost}" >

                            <c:if test="${not empty errorCompanyPost.quantitytInternsNotEngough}" >
                                <font>
                                ${errorCompanyPost.quantitytInternsNotEngough}
                                </font>
                            </c:if>
                            <c:if test="${not empty errorCompanyPost.expirationDateError}" >
                                <font>
                                ${errorCompanyPost.expirationDateError}
                                </font>
                            </c:if>
                        </c:if>
                    </div>
                </div>
                <c:set var="listOtherCompanies" value="${requestScope.LIST_OTHER_COMPANIES}"/>

                <div class="col-md-5">
                    <div class="hComApplDetail__right">
                        <div class="hComApplDetail__right--header">
                            Other Company
                        </div>
                        <div class="hComApplDetail__right--body row row-cols-1">
                            <c:forEach items="${listOtherCompanies}" var="postOther" varStatus="counter">
                                <c:if test="${postOther.postID ne postDetail.postID}">
                                    <c:url var="linkOther" value="HomeShowCompanyDetailController">
                                        <c:param name="postID" value="${postOther.postID}"/>
                                    </c:url>
                                    <div class="col" style="position: relative;">
                                        <a href="${linkOther}" class="hComApplDetail__right--card">
                                            <div class="row">
                                                <div class="col-4 right-card_img">
                                                    <img src="./avatars/${postOther.company.account.avatar}" class="right--card-img"/>
                                                </div>
                                                <div class="col-8 right-card-content">
                                                    <div class="right--card-header">${postOther.company.account.name}</div>
                                                    <div class="right--card-body">
                                                        <p>Job: ${postOther.title_Post}</p>
                                                        <p>Quantity: ${postOther.quantityIterns}</p>
                                                        <p>Location: ${postOther.workLocation}</p>
                                                        <p>Date: ${my:changeDateFormat(postOther.expirationDate)}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="right--card-save">
                                            <c:url var="urlSaveJob" value="StudentSaveJobController" >
                                                <c:param name="save" value="homeShowCompanyDetail" />
                                                <c:param name="postIDOther" value="${postOther.postID}" />
                                                <c:param name="postID" value="${postDetail.postID}" />
                                            </c:url>
                                            <c:url var="urlUnSaveJob" value="StudentDeleteSaveJobController" >
                                                <c:param name="unSave" value="homeShowCompanyDetail" />
                                                <c:param name="postIDOther" value="${postOther.postID}" />
                                                <c:param name="postID" value="${postDetail.postID}" />
                                            </c:url>
                                            <c:set var="statusFollowing" value="${my:getStatusSaveJob(requestScope.LIST_FOLLOWING_POST, postOther.postID)}" />
                                             <c:if test="${statusFollowing eq true}">
                                            
                                                 <a href="${urlUnSaveJob}">
                                                    <i class="far fa-heart right--card-save save-btn save-btn-active "></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${statusFollowing eq false}">
                                                <a href="${urlSaveJob}">
                                                    <i class="far fa-heart right--card-save  save-btn"></i>
                                                 </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="main__pagination">
                            <ul class="pagination main_cus__pagination">

<!--                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>-->
                               <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                <c:url var="url" value="HomeShowCompanyDetailController">
                                    <c:param name="page" value="${i}"/>
                                    <c:param name="postID" value="${postDetail.postID}" />
                                </c:url>
                                <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                            </c:forEach>    
<!--                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>-->
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="hCompanyInfor">
                <div class="hCompanyInfor__header">
                    Company Information
                </div>
                <div class="hCompanyInfor__body">
                    <div class="row">
                        <div class="col-md-3">
                            <img src="./avatars/${postDetail.company.account.avatar}" alt="" >
                            <h3>${postDetail.company.account.name}</h3>
                        </div>
                        <div class="col-md-9 hCompanyInfor__content">
                            ${postDetail.company.company_Description}    
                        </div>
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
