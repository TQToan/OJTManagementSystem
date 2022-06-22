<%-- 
    Document   : adminStuManage
    Created on : Jun 5, 2022, 5:01:24 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Student Management</title>
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
                            <a href="ShowAdminStudentManagementController" class="nav__item--link link-active">
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
                            <a href="AdminShowInternApplicationController" class="nav__item--link">
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

        
        <main class="row">
            <nav class="col-xl-2  nav-fixed col-md-3">
                <a href="ShowAdminStudentManagementController" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    <font> ${Admin.name} </font>
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="ShowAdminStudentManagementController" class="nav__item--link link-active">
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
                        <a href="AdminShowInternApplicationController" class="nav__item--link">
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
                    <div class="main-body-aStuManage ">
                        <div class="main-body-aStuManage__header">
                            Student Management
                        </div>

                        <div class="admin__import-file-stu">
                            <form action="ImportStudentExcelFileController" method="POST" enctype="multipart/form-data">
                                <input type="file" name="Import File" />
                                <input id="importFile" type="submit" value="Import File" name="btAction" />
                            </form>
                            <c:if test="${not empty requestScope.ERROR_IMPORT_EXCEL}">
                                ${requestScope.ERROR_IMPORT_EXCEL}
                            </c:if>
                        </div>
                        <div>
                            Download this excel file to input your data!
                            <a href="DownloadFileModelController">File Excel Model</a>
                        </div>
                        <div class="main-body-aStuManage__search">
                            <form action="SearchStudentByAdminController" method="POST">
                                <div class="row">
                                    <div class="col-2">
                                        <c:set var="currentSemester" value="${requestScope.CURRENT_SEMESTER}"/>
                                        <c:set var="nowSemester" value="${requestScope.NOW_SEMESTER}"/>
                                        <select name="semester" class="admin--select">
                                            <c:forEach items="${requestScope.LIST_SEMESTER}" var="semester">
                                                <option value="${semester.semesterID}" <c:if test="${currentSemester.semesterID eq semester.semesterID}">
                                                        selected="selected"
                                                    </c:if>>${semester.semesterName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-2">
                                        <input type="text" name="txtStudentCode" value="${param.txtStudentCode}" placeholder="ID" class="admin--input">
                                    </div>
                                    <div class="col-1">
                                        <input type="number" name="txtCredit" min="0" max="100" value="${param.txtCredit}" placeholder="Credits" class="admin--input">
                                    </div>


                                    <div class="col-4">
                                        <select name="txtMajor" class="admin--select">
                                            <option value="">Major</option>
                                            <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                                <option value="${major.majorName}"<c:if test="${param.txtMajor eq major.majorName}" >
                                                        selected="selected"
                                                    </c:if>>${major.majorName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-2">
                                        <select id="city" name="isIntern"  class="admin--select" >
                                            <option value="" selected>Status</option>
                                            <option value="1" class="text-success" <c:if test="${param.isIntern eq '1'}" >
                                                    selected="selected"
                                                </c:if>>
                                                Working
                                            </option>
                                            <option value="2" class="text-danger" <c:if test="${param.isIntern eq '2'}" >
                                                    selected="selected"
                                                </c:if>>
                                                Finished
                                            </option>
                                            <option value="0" class="text-warning" <c:if test="${param.isIntern eq '0'}" >
                                                    selected="selected"
                                                </c:if>>
                                                Not Yet
                                            </option>
                                        </select>    
                                    </div>
                                    <div class="col-1">
                                        <input type="submit" value="Search" class="admin-search-btn">
                                    </div>
                                </div>
                            </form>
                        </div>


                        <div class="main-body-aStuManage__content">
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:if test="${not empty requestScope.LIST_APPLICATION_RESULT}" >
                                <table class="table table-bordered ">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>ID</th>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Credits</th>
                                            <th>Major</th>
                                            <th>Internship Status</th>
                                            <th>Course Status</th>
                                            <th>Active Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.LIST_APPLICATION_RESULT}" var="student" varStatus="counter">
                                            <c:set var="error" value="${my:getError(requestScope.INVALID_CREDIT, student.studentCode)}" />
                                        <form action="UpdateStudentInforMationController" method="POST">
                                            <tr>
                                                <td>
                                                    ${counter.count}
                                                </td>
                                                <td>
                                                    ${student.studentCode}
                                                    <input type="hidden" name="StudentCode" value="${student.studentCode}" />
                                                </td>
                                                <td>
                                                    ${student.account.name}
                                                </td>
                                                <td>
                                                    ${student.account.email}
                                                </td>
                                                <td>
                                                    <c:if test="${student.isIntern eq 0}">
                                                        <c:if test="${empty error}">
                                                            <input style="width: 60px" type="number" min="0" 
                                                                   max="100" name="txtNumberOfCredit" value="${student.numberOfCredit}" />
                                                        </c:if>
                                                        <c:if test="${not empty error}">
                                                            <input style="width: 60px" type="number" min="0" 
                                                                   max="100" name="txtNumberOfCredit" value="${param.txtNumberOfCredit}" />
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${student.isIntern eq 1 or student.isIntern eq 2}" >
                                                        <input style="width: 60px" type="number" min="0" 
                                                               max="100" name="txtNumberOfCredit" value="${student.numberOfCredit}" disabled="disabled" />
                                                    </c:if>
                                                </td>
                                                <td>
                                                    ${student.major}
                                                </td>
                                                <c:if test="${student.isIntern eq 0}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Not Yet
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${student.isIntern eq 1}">
                                                    <td class="text-success">
                                                        <strong>
                                                            Working
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${student.isIntern eq 2}">
                                                    <td class="text-danger">
                                                        <strong>
                                                            Finished
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:set var="context" value="${requestScope.SERVLET_CONTEXT}"/>
                                                <c:set var="application" value="${my:getApplicationOfStudentByID(student, context)}" />
                                                <td>
                                                    <c:if test="${application.isPass eq -1 and student.isIntern eq 2}">
                                                        <strong class="text-danger">
                                                            Not Pass
                                                        </strong>
                                                    </c:if>
                                                    <c:if test="${application.isPass eq 1 and student.isIntern eq 2}">
                                                        <strong class="text-success">
                                                            Passed
                                                        </strong>
                                                    </c:if>
                                                    <c:if test="${student.isIntern eq 0}">
                                                        <strong class="text-warning">
                                                            Not Yet
                                                        </strong>
                                                    </c:if>
                                                    <c:if test="${student.isIntern eq 1}">
                                                        <strong class="text-warning">
                                                            Not Yet
                                                        </strong>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <input type="hidden" name="page" value="${requestScope.page}" />
                                                    <input type="checkbox" name="isDisabled" value="${student.isDisabled}" <c:if test="${student.isDisabled eq false}" >
                                                           checked="checked"
                                                        </c:if> <c:if test="${student.isIntern eq 2 and application.isPass eq 1 
                                                                              and student.semester.semesterID ne nowSemester.semesterID}">
                                                                      disabled="disabled"
                                                        </c:if> <c:if test="${student.semester.semesterID eq nowSemester.semesterID}" >
                                                            disabled="disabled"
                                                        </c:if> />
                                                </td>

                                                <c:if test="${student.isIntern eq 0}" >
                                                    <td>
                                                        <c:if test="${not empty error}">
                                                            <font style="color: red">
                                                            ${error}
                                                            </font>
                                                            <input type="submit" value="Appcept" name="btAction" />
                                                            <input type="submit" value="Cancel" name="btAction" />
                                                        </c:if>
                                                        <c:if test="${empty error}">
                                                            <input type="submit" value="Update" name="btAction" class="btn-update-green"/>
                                                        </c:if>

                                                    </td> 
                                                </c:if>
                                                <c:if test="${student.isIntern eq 1}" >
                                                    <td>
                                                        <input type="submit" value="Update" name="btAction" disabled="disabled" class="btn-update-green"/>
                                                    </td> 
                                                </c:if>
                                                <c:if test="${student.isIntern eq 2 and application.isPass eq 1}" >
                                                    <td>
                                                        <input type="submit" value="Update" name="btAction" disabled="disabled" class="btn-update-green"/>
                                                    </td> 
                                                </c:if>
                                                <c:if test="${student.isIntern eq 2 and application.isPass eq -1}" >
                                                    <td>
                                                        <input type="submit" value="Update" name="btAction" class="btn-update-green"/>
                                                    </td> 
                                                </c:if>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </c:if>
                            <c:if test="${empty requestScope.LIST_APPLICATION_RESULT}" >
                                <h3 class="text-center" style="margin-top: 20px">
                                    Student List does not has any result for you!
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
                                    <c:url var="url" value="SearchStudentByAdminController">
                                        <c:param name="page" value="${i}"/>
                                        <c:param name="semester" value="${currentSemester.semesterID}"/>
                                        <c:param name="txtCredit" value="${param.txtCredit}"/>
                                        <c:param name="txtMajor" value="${param.txtMajor}"/>
                                        <c:param name="isIntern" value="${param.isIntern}"/>
                                        <c:param name="txtStudentCode" value="${param.txtStudentCode}"/>
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
