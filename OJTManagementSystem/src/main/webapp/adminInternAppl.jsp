<%-- 
    Document   : adminInterAppl
    Created on : Jun 6, 2022, 11:56:45 PM
    Author     : Thanh Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Application Management</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/admin.css">
        <link rel="stylesheet" href="./assets/css/admin-responsive.css">
    </head>

    <body>
        <header></header>
        <c:set var="Admin" value="${sessionScope.ADMIN_ROLE}"/>
        <div class="navbar navbar-expand-md navbar-dark text-center navbar-sm-cus">
            <div class="container-fluid">
                <a href="ShowAdminStudentManagementController" class="header__logo ">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa-solid fa-bars nav__respo--btn"></i>
                </button>
                <div class="collapse navbar-collapse navbar-collapse-cus" id="navbarSupportedContent">
                    <a href="" class=" nav__infor--link text-truncate text-center">
                        <i class="fas fa-user-circle nav__infor--icon"></i>
                        <font> ${Admin.name} </font>
                    </a>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a href="ShowAdminStudentManagementController" class="nav__item--link">
                                <i class="fas fa-university"></i>
                                Student Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="AdminCompanyManagerController" class="nav__item--link">
                                <i class="far fa-building"></i>
                                Company Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="AdminShowPostManagementController" class="nav__item--link">
                                <i class="fas fa-pen"></i>
                                Post Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="AdminShowInternApplicationController" class="nav__item--link link-active">
                                <i class="fas fa-clipboard-check"></i>
                                Internship Application
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="ShowStudentEvaluationController" class="nav__item--link">
                                <i class="fas fa-poll-h"></i>
                                Evaluation
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
        </div>
        
            
            <c:set var="studentID" value="${param.txtStudentID}"/>
            <c:set var="companyID" value="${param.txtCompanyID}"/>
            <c:set var="schoolStatus" value="${param.txtSchoolStatus}"/>
            <c:set var="titleJob" value="${param.txtTitleJob}" />

        

        <main class="row">
            <nav class="col-xl-2  nav-fixed col-md-3">
                <a href="#" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${Admin.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="ShowAdminStudentManagementController" class="nav__item--link">
                            <i class="fas fa-university"></i>
                            Student Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminCompanyManagerController" class="nav__item--link">
                            <i class="far fa-building"></i>
                            Company Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminShowPostManagementController" class="nav__item--link">
                            <i class="fas fa-pen"></i>
                            Post Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminShowInternApplicationController" class="nav__item--link link-active">
                            <i class="fas fa-clipboard-check"></i>
                            Internship Application
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="ShowStudentEvaluationController" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Evaluation
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
            <div class="main-body  offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">
                <div class="row">
                    <div class="main-body-aInterAppl ">
                        <div class="main-body-aInterAppl__header">
                            Intern Application
                        </div>



                        <div class="main-body-aInterAppl__search">
                            <form action="AdminShowInternApplicationController" method="POST">
                                <div class="row">
                                    <div class="col-2">
                                        <input type="text" name="txtStudentID" id="" placeholder="ID" class="admin--input" value="${studentID}">
                                    </div>
                                    <div class="col-4">
                                        <select name="txtCompanyID" class="admin--select">
                                            <option value="">Company Name</option>
                                            <c:forEach items="${requestScope.COMPANY_NAME}" var="company">
                                                <option value="${company.companyID}" <c:if test="${company.companyID eq companyID}">
                                                        selected="selected"
                                                    </c:if> >${company.account.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>

                                    <div class="col-3">
                                        <input type="text" placeholder="Title Job" name="txtTitleJob" class="admin--input" value="${param.txtTitleJob}">
                                    </div>

                                    <div class="col-2">
                                        <select id="status"  name="txtSchoolStatus"  class="admin--select">
                                            <option value="">
                                                School Status
                                            </option>
                                            <option value="1" class="text-success" <c:if test="${schoolStatus eq '1'}"> 
                                                    selected="selected" </c:if>>
                                                    Accepted 
                                                </option>
                                                <option value="-1" class="text-warning"<c:if test="${schoolStatus eq '0'}">
                                                    selected="selected" </c:if>>
                                                    Waiting
                                                </option>
                                                <option value="0" class="text-danger"<c:if test="${schoolStatus eq '-1'}">
                                                    selected="selected" </c:if>>
                                                    Denied
                                                </option>
                                            </select>           
                                        </div>
                                        <div class="col-1">
                                            <input type="submit" value="Search" class="admin-search-btn">
                                        </div>
                                    </div>
                                </form>
                            </div>

                        <c:set var="sizeOfList" value="${requestScope.SIZE_OF_LIST}" />
                        <div class="main-body-aInterAppl__content">                         
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:set var= "listIntern" value="${requestScope.INTERN_APPLICATION}"/>
                            <c:if test="${not empty listIntern}" >
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>ID</th>
                                            <th>Company Name</th>
                                            <th>Title Job</th>
                                            <th>Student Applied</th>
                                            <th>Company Accepted</th>
                                            <th>School Confirm</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="intern" items="${listIntern}" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${intern.student.studentCode}</td>
                                                <td>${intern.companyPost.company.account.name}</td>
                                                <td>${intern.companyPost.title_Post}</td>

                                                <c:if test="${intern.studentConfirm eq true}">
                                                    <td class="text-success">
                                                        <strong>
                                                            Accepted
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${intern.studentConfirm eq false}">
                                                    <td class="text-danger">
                                                        <strong>
                                                            Cancel
                                                        </strong>
                                                    </td>
                                                </c:if>

                                                <c:if test="${intern.companyConfirm eq -1}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Waiting
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${intern.companyConfirm eq 0}">
                                                    <td class="text-danger">
                                                        <strong>
                                                            Denied
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${intern.companyConfirm eq 1}">
                                                    <td class="text-success">
                                                        <strong>
                                                            Accepted
                                                        </strong>
                                                    </td>
                                                </c:if>

 
                                                <c:if test="${intern.schoolConfirm eq 0}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Waiting
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${intern.schoolConfirm eq -1}">
                                                    <td class="text-danger">
                                                        <strong>
                                                            Denied
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${intern.schoolConfirm eq 1}">
                                                    <td class="text-success">
                                                        <strong>
                                                            Accepted
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                    <c:if test="${intern.schoolConfirm eq '0' and intern.studentConfirm eq true}">
                                                    <td>
                                                        <div class="d-flex justify-content-around">
                                                            <c:url var="urlReject" value="AdminChangeStatusInternApplicationServlet">
                                                                <c:param name="page" value="${i}"/>
                                                                <c:param name="txtStudentID" value="${studentID}"/>
                                                                <c:param name="txtCompanyID" value="${companyID}"/>
                                                                <c:param name="txtSchoolStatus" value="${schoolStatus}"/>
                                                                <c:param name="txtTitleJob" value="${titleJob}"/>
                                                                <c:param name="btnAction" value="-1"/>
                                                                <c:param name="txtApplicationID" value="${intern.applicationID}"/>
                                                            </c:url>
                                                            <a href="${urlReject}" class="text-danger">
                                                                <strong>
                                                                    Reject
                                                                </strong>
                                                            </a>
                                                            <c:url var="urlAccept" value="AdminChangeStatusInternApplicationServlet">
                                                                <c:param name="page" value="${i}"/>
                                                                <c:param name="txtStudentID" value="${studentID}"/>
                                                                <c:param name="txtCompanyID" value="${companyID}"/>
                                                                <c:param name="txtSchoolStatus" value="${schoolStatus}"/>
                                                                <c:param name="txtTitleJob" value="${titleJob}"/>
                                                                <c:param name="btnAction" value="1"/>
                                                                <c:param name="txtApplicationID" value="${intern.applicationID}"/>
                                                            </c:url>
                                                            <a href="${urlAccept}" class="text-success">
                                                                <strong>
                                                                    Accept
                                                                </strong>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </c:if>

                                            </tr>
                                        </c:forEach>      

                                    </tbody>

                                </table>

                            </c:if>
                            <c:if test="${empty listIntern}">
                                <h3 class="text-center" style="margin-top: 20px">
                                    Internship Application List does not has any result!
                                </h3>
                            </c:if>
                        </div>


                        <div  class="main__pagination">
                            <ul class="pagination main_cus__pagination">
                                <!--                                     <li class="page-item">
                                                                        <a class="page-link" href="#" aria-label="Previous">
                                                                             <span aria-hidden="true">&laquo;</span>
                                                                        </a>
                                                                    </li>-->

                                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                    <c:url var="url" value="AdminShowInternApplicationController">
                                        <c:param name="page" value="${i}"/>
                                        <c:param name="txtStudentID" value="${studentID}"/>
                                        <c:param name="txtCompanyID" value="${companyID}"/>
                                        <c:param name="txtSchoolStatus" value="${schoolStatus}"/>
                                        <c:param name="txtTitleJob" value="${titleJob}"/>
                                    </c:url>
                                    <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                    </c:forEach>
                                <!--                                    <li class="page-item">
                                                                        <a class="page-link" href="#" aria-label="Next">
                                                                            <span aria-hidden="true">&raquo;</span>
                                                                         </a>
                                                                    </li>-->
                            </ul>
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
        <script src="./assets/font/bootstrap-5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
