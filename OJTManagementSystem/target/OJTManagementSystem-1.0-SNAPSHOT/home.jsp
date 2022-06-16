<%-- 
    Document   : home
    Created on : May 25, 2022, 4:42:15 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
                <div class="header__name">
                    <div class="header__name--show">
                        <c:set var="student" value="${sessionScope.STUDENT_ROLE}"/>
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

        <main class="main">
            <img src="./assets/img/main.png" alt="" class="main__img">
            <div class="main__search">
                <h2>Search Jobs</h2>
                <form action="SearchCompanyStudentHomeController" class="main__search-form">
                    <div class="row">
                        <div class="col-4">
                            <!--                            <input type="text" name="" id="" placeholder="Company">-->
                            <select name="nameCompany">
                                <option value="">Company Name</option>
                                <c:forEach items="${requestScope.COMPANY_NAME}" var="company">
                                    <option value="${company.companyID}">${company.account.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <!--                            <input type="text" name="" id="" placeholder="Job">-->
                            <select name="nameMajor">
                                <option value="">Major</option>
                                <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                    <option value="${major.majorID}">${major.majorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-2 ">
                            <select id="city" name="nameLocation" class="form__select"  >
                                <option value="">Location</option>
                                <option value="TP.HCM">TP.HCM</option>
                                <option value="Đồng Nai">Đồng Nai</option>
                                <option value="Tây Ninh">Tây Ninh</option>
                                <option value="Bình Dương">Bình Dương</option>
                            </select>
                        </div>
                        <div class="col-2">
                            <input type="submit" value="Search" class="primary-btn">
                        </div>
                    </div>
                </form>
            </div>

            <div class="main__company">
                <h2>Company</h2>
                <div class="row row-cols-2 row-cols-lg-3 row-cols-md-3 row-cols-xl-4">
                    <c:forEach items="${requestScope.LIST_POST_HOME}" var="dto">
                        <div class="col">
                            <div class="card-company">
                                <img src="./avatars/${dto.company.account.avatar}" alt="${dto.company.account.avatar}" class="card-company--img">
                                <a href="HomeShowCompanyDetailController?postID=${dto.postID}" class="card-company-header">
                                    ${dto.company.account.name}
                                </a>
                                <div class="card-company-body">
                                    <p>Jobs: ${dto.title_Post}</p>
                                    <p>Quantity: ${dto.quantityIterns}</p>
                                    <p>Location: ${dto.workLocation}</p>
                                    <p>Expiration Date: ${my:changeDateFormat(dto.expirationDate)}</p>
                                </div>
                                <div class="card-company-btn">
                                    <a href="ShowApplyCVController?postID=${dto.postID}" class="primary-btn hApply-btn">Apply Now</a>
                                </div>   
                                    <c:url var="urlSaveJob" value="StudentSaveJobController" >
                                        <c:param name="save" value="homePage" />
                                        <c:param name="postID" value="${dto.postID}" />
                                    </c:url>
                                    <c:url var="urlUnSaveJob" value="StudentDeleteSaveJobController" >
                                        <c:param name="unSave" value="homePage" />
                                        <c:param name="postID" value="${dto.postID}" />
                                    </c:url>
                                    <c:set var="statusFollowing" value="${my:getStatusSaveJob(requestScope.LIST_FOLLOWING_POST, dto.postID)}" />
                                    <c:if test="${statusFollowing eq true}">
                                    <a href="${urlUnSaveJob}">
                                            <i class="far fa-heart card-company-btn-save save-btn save-btn-active "></i>
                                    </a>
                                    </c:if>
                                    <c:if test="${statusFollowing eq false}">
                                        <a href="${urlSaveJob}">
                                            <i class="far fa-heart card-company-btn-save save-btn"></i>
                                        </a>
                                    </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="home_see-more">
                    <a href="#" class="home_see-more--btn ">
                        See More 
                        <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
            </div>
            <
            <div class="main__company-icon">
                <div class="row row-cols-7">
                    <c:forEach items="${requestScope.LIST_AVATAR_SIGNED_COMPANY}" var="avatar">
                        <div class="col company__click">
                            <a href="SearchCompanyStudentHomeController?nameCompany=${avatar.companyID}&nameMajor=&nameLocation=" class="">
                                <img src="./avatars/${avatar.account.avatar}" alt="${avatar.account.avatar}" class="main__company-img ">
                            </a>
                        </div>
                    </c:forEach>
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
