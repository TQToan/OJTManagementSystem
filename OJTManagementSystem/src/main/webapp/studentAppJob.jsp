<%-- 
    Document   : studentAppJob
    Created on : Jun 2, 2022, 9:27:42 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student - Applied Jobs</title>
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

        <c:set var="student" value="${sessionScope.STUDENT_ROLE}" />
        <main class="row">
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
                    <div class="main-body-appl ">
                        <div class="main-body-appl__header">
                            Applied Jobs*
                        </div>


                        <div class="main-body-appl__search">
                            <form action="SearchStudentAppliedJobController" method="POST">
                                <%--<<<<<<< HEAD
                                                                <table class="table">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td></td>
                                                                            <td>
                                                                                <input type="text" name="nameTypeJob" value="${param.nameTypeJob}" id="" placeholder="Type Job">
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" name="nameCompany"  value="${param.nameCompany}" id="" placeholder="Company">
                                                                            </td>
                                                                            <td>
                                                                                <select id="city" name="nameLocation">
                                =======--%>
                                <div class="row">
                                    <div class="col-3">
                                        <input type="text" name="nameTypeJob" class="student--input" value="${param.nameTypeJob}" id="" placeholder="Type Job">
                                    </div>
                                    <div class="col-3">                             
                                        <input type="text" name="nameCompany" class="student--input"  value="${param.nameCompany}" id="" placeholder="Company">
                                    </div>
                                    <div class="col-2">
                                        <select id="city" name="nameLocation"  class="student--select" >
                                            <!-->>>>>>> 9edfda73613ca59615b350eb609bf0303156bf1c-->
                                            <option value="">Location</option>
                                            <option value="TP.HCM" <c:if test="${param.nameLocation eq 'TP.HCM'}">
                                                    selected="selected"
                                                </c:if>>TP.HCM</option>
                                            <option value="Dong Nai" <c:if test="${param.nameLocation eq 'Dong Nai'}">
                                                    selected="selected"
                                                </c:if>>Dong Nai</option>
                                            <option value="Tay Ninh" <c:if test="${param.nameLocation eq 'Tay Ninh'}">
                                                    selected="selected"
                                                </c:if>>Tay Ninh</option>
                                            <option value="Binh Duong" <c:if test="${param.nameLocation eq 'Binh Duong'}">
                                                    selected="selected"
                                                </c:if>>Binh Duong</option>
                                        </select>
                                    </div>
                                    <div class="col-2">
                                        <select id="city" name="nameStatus"  class="student--select" >
                                            <option value="" selected>Status</option>
                                            <option value="Denied" class="text-danger" <c:if test="${param.nameStatus eq 'Denied'}">
                                                    selected="selected"
                                                </c:if>>Denied</option>
                                            <option value="Waiting" class="text-warning" <c:if test="${param.nameStatus eq 'Waiting'}">
                                                    selected="selected"
                                                </c:if>>Waiting</option>
                                            <option value="Success" class="text-success" <c:if test="${param.nameStatus eq 'Success'}">
                                                    selected="selected"
                                                </c:if>>Success</option>
                                            <option value="Canceled" class="text-success" <c:if test="${param.nameStatus eq 'Canceled'}">
                                                    selected="selected"
                                                </c:if>>Canceled</option>
                                        </select>
                                    </div>
                                    <div class="col-2">
                                        <input type="submit" value="Search" class=" appl-search-btn">
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="main-body-appl__content">
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:if test="${not empty requestScope.LIST_APPLIED_JOB_RESULT}">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>Type Job</th>
                                            <th>Company</th>
                                            <th>Location</th>
                                            <th>Expiration Date</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.LIST_APPLIED_JOB_RESULT}" var="appliedJob" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>
                                                    <a href="HomeShowCompanyDetailController?postID=${appliedJob.companyPost.postID}">${appliedJob.companyPost.title_Post}</a>
                                                </td>
                                                <td>${appliedJob.companyPost.company.account.name}</td>
                                                <td>${appliedJob.companyPost.workLocation}</td>
                                                <td>${my:changeDateFormat(appliedJob.companyPost.expirationDate)}</td>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq -1 and appliedJob.companyConfirm eq -1}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Waiting
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq 1 and appliedJob.companyConfirm eq -1}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Waiting
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq 1 and appliedJob.companyConfirm eq 0}" >
                                                    <td class="text-danger">
                                                        <strong>
                                                            Denied
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq 0}" >
                                                    <td class="text-danger">
                                                        <strong>
                                                            Denied
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq 1 and appliedJob.companyConfirm eq 1}" >
                                                    <td class="text-success">
                                                        <strong>
                                                            Accepted
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq false}">
                                                    <td class="text-truncate">
                                                        <strong>
                                                            Canceled
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq -1 and appliedJob.companyConfirm eq -1}">
                                                    <td>
                                                        <a href="CancleApplyCVController?applicationID=${appliedJob.applicationID}">Cancel</a>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq true and appliedJob.schoolConfirm eq 1 and appliedJob.companyConfirm eq -1}">
                                                    <td>
                                                        <a href="CancleApplyCVController?applicationID=${appliedJob.applicationID}">Cancel</a>
                                                    </td>
                                                </c:if>
                                                <c:if test="${appliedJob.studentConfirm eq false and appliedJob.schoolConfirm ne -1 and appliedJob.companyConfirm ne -1}">
                                                    <td>

                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>

                                </table>
                                <div class="main__pagination">
                                    <ul class="pagination main_cus__pagination">

                                        <!--                                        <li class="page-item">
                                                                                    <a class="page-link" href="#" aria-label="Previous">
                                                                                        <span aria-hidden="true">&laquo;</span>
                                                                                    </a>
                                                                                </li>-->

                                        <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                            <c:url var="url" value="SearchSaveJobController">
                                                <c:param name="page" value="${i}"/>
                                            </c:url>
                                            <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                            </c:forEach>

                                        <!--                                        <li class="page-item">
                                                                                    <a class="page-link" href="#" aria-label="Next">
                                                                                        <span aria-hidden="true">&raquo;</span>
                                                                                    </a>
                                                                                </li>-->
                                    </ul>
                                </div>

                            </c:if>
                            <c:if test="${empty requestScope.LIST_APPLIED_JOB_RESULT}">
                                <p3>
                                    You have not any applied job yet!
                                </p3>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

        </main>
        <footer class="footer">
            <div class="container-fluid">
                <div class="footer__content">
                    <i class="fa-regular fa-copyright"></i> Copyright 2022
                </div>
            </div>
        </footer>
    </body>
</html>
