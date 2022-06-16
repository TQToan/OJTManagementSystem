<%-- 
    Document   : companyPostEdit
    Created on : Jun 15, 2022, 4:12:18 PM
    Author     : ThanhTy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Company - Post</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/company.css">
        <link rel="stylesheet" href="./assets/css/company-responsive.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body>
        <header></header>
            <c:set var="company" value="${sessionScope.COMPANY_ROLE_INFO}"/>
            <c:set var="companyPost" value="${requestScope.COMPANY_POST_DETAIL}"/>
            <c:set var="errors" value="${requestScope.ERROR_UPDATE}"/>
        <div class="navbar navbar-expand-md navbar-sm-cus ">
            <a href="CompanyShowProfileController" class="header__logo ">
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
                        <a href="companyDashboard.html" class="nav__item--link">
                            <i class="fas fa-palette "></i>
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="companyProfile.html" class="nav__item--link">
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
                        <a href="companyInternsManage.html" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Interns Management
                        </a>
                    </li>
                    <li class="nav-item nav__items">
                        <a href="companyApplManage.html" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Internship Application
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav__item--link">
                            <i class="fas fa-power-off"></i>
                            Logout
                        </a>
                    </li>

                </ul>
            </div>
        </div>

        <main class="row">
            <nav class="col-xl-2  nav-fixed col-md-3">
                <a href="CompanyShowProfileController" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="CompanyShowProfileController" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${company.account.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="companyDashboard.html" class="nav__item--link">
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
                        <a href="CompanyShowPostController" class="nav__item--link">
                            <i class="fas fa-pen"></i>
                            My Posts
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="companyInternsManage.html" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Interns Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="companyApplManage.html" class="nav__item--link">
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
            <div class="main-body offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">
                
                <div class="row">
                    <div class="main-body-cPostEdit offset-xl-3 col-xl-6 offset-2 col-8">
                        <div class="main-body-cPostEdit__header">
                            Company Post*
                            
                        </div>
                        <form action="CompanyUpdatePostController" method="POST">
                            <input type="hidden" name="postID" value="${companyPost.postID}" />
                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="titlePost">Title Post</label>
                                <input type="text" class="col-8 cPostEdit--input " name="titlePost" id="titlePost"
                                       value="${companyPost.title_Post}">
                                <h5 class="text-danger offset-4 col-8 text-start ">
                                    <c:if test="${not empty errors}">
                                        ${errors.titlePostLenghtError}
                                    </c:if>
                                </h5>
                            </div>

                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="major">Major</label>
                                <select name="majorID" class="col-8 cPostEdit--input">
                                    <option value="" selected disabled>Major</option>
                                    <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                        <option value="${major.majorID}" <c:if test="${companyPost.major.majorID eq major.majorID}">
                                                selected="selected"
                                            </c:if> >${major.majorName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="quantity">Quantity Interns</label>
                                <input type="text" class="col-8 cPostEdit--input " name="quantityIterns" id="quantity" value="${companyPost.quantityIterns}">
                                <h5 class="text-danger offset-4 col-8 text-start ">
                                    <c:if test="${not empty errors}">
                                        ${errors.quantitytInternsNotEngough}
                                    </c:if>
                                </h5>
                            </div>

                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="date">Expiration Date</label>
                                <input type="text" class="col-8 cPostEdit--input " name="expirationDate" id="date" value="${companyPost.expirationDate}">
                                <h5 class="text-danger offset-4 col-8 text-start ">
                                    <c:if test="${not empty errors}">
                                        <%--${errors.expirationDateError}--%>
                                        ${errors.expirationDateEmptyError}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="city">Work Location</label>
                                <select id="city" name="workLocation" class="col-8 cPostEdit--input ">
                                    <option value="" selected disabled>City</option>
                                    
                                    <option value="${companyPost.workLocation}"<c:if test="${companyPost.workLocation eq 'TP.HCM'}">
                                                selected="selected"
                                            </c:if>>TP.HCM</option>
                                    <option value="${companyPost.workLocation}"<c:if test="${companyPost.workLocation eq 'Dong Nai'}">
                                                selected="selected"
                                            </c:if>>Dong Nai</option>
                                    <option value="${companyPost.workLocation}"<c:if test="${companyPost.workLocation eq 'Tay Ninh'}">
                                                selected="selected"
                                            </c:if>>Tay Ninh</option>
                                    <option value="${companyPost.workLocation}"<c:if test="${companyPost.workLocation eq 'Binh Duong'}">
                                                selected="selected"
                                            </c:if>>Binh Duong</option>
                                </select>
                            </div>
                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="descript">Job Description</label>
                                <textarea name="job_Description" class="col-12 cPostEdit--input cPostEdit--input-textarea" id="descript"
                                          cols="30" rows="4">${companyPost.job_Description}</textarea>
                                <h5 class="text-danger  text-start ">
                                    <c:if test="${not empty errors}">
                                        ${errors.descriptionLenghtError}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="requirements">Job Requirements</label>
                                <textarea name="job_Requirement" class="col-12 cPostEdit--input cPostEdit--input-textarea" id="requirements"
                                          cols="30" rows="4">${companyPost.job_Requirement}</textarea>
                                <h5 class="text-danger  text-start ">
                                    <c:if test="${not empty errors}">
                                        ${errors.requirementLenghtError}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="cPostEdit__input row">
                                <label class="col-4 cPostEdit--label" for="remuneration">Remuneration</label>
                                <textarea name="remuneration" class="col-12 cPostEdit--input cPostEdit--input-textarea" id="remuneration"
                                          cols="30" rows="4">${companyPost.remuneration}</textarea>
                                <h5 class="text-danger text-start ">
                                    <c:if test="${not empty errors}">
                                        ${errors.remunerationLenghtError}
                                    </c:if>
                                </h5>
                            </div>

                            <div class="cPostEdit-edit-btn primary-btn">
                                <i class="fas fa-edit"></i>
                                <input type="submit" class="cPostEdit-edit--input " value="Edit">
                            </div>
                        </form>
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
