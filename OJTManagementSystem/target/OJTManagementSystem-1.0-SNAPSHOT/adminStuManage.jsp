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
    </head>
    <body>
        <header></header>
            <c:set var="Admin" value="${sessionScope.ADMIN_ROLE}"/>
        <main class="row">
            <nav class="col-2  nav-fixed">
                <a href="ShowAdminStudentManagementController" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    <font> ${Admin.name} </font>
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="ShowAdminStudentManagementController" class="nav__item--link">
                            <i class="fas fa-university"></i>
                            Student Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="adminComManage.html" class="nav__item--link">
                            <i class="far fa-building"></i>
                            Company Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="adminPostManage.html" class="nav__item--link">
                            <i class="fas fa-pen"></i>
                            Post Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="adminInterAppl.html" class="nav__item--link">
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

            <div class="main-body  offset-2 col-10">
                <div class="row">
                    <div class="main-body-aStuManage ">
                        <div class="main-body-aStuManage__header">
                            Student Management
                        </div>
                        <div>
                            <form action="ImportStudentExcelFileController" method="POST" enctype="multipart/form-data">
                                <input type="file" name="Import File" />
                                <input id="importFile" type="submit" value="Import File" name="btAction" />
                            </form>
                            <c:if test="${not empty requestScope.ERROR_IMPORT_EXCEL}">
                                ${requestScope.ERROR_IMPORT_EXCEL}
                            </c:if>
                        </div>
                        <div class="main-body-aStuManage__search">
                            <form action="SearchStudentByAdminController" method="POST">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <c:set var="currentSemester" value="${requestScope.CURRENT_SEMESTER}"/>
                                                <select name="semester">
                                                    <c:forEach items="${requestScope.LIST_SEMESTER}" var="semester">
                                                        <option value="${semester.semesterID}" <c:if test="${currentSemester.semesterID eq semester.semesterID}">
                                                                selected="selected"
                                                            </c:if>>${semester.semesterName}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="text" name="txtStudentCode" value="${param.txtStudentCode}" placeholder="ID">
                                            </td>
                                            <td> 
                                                <input type="number" name="txtCredit" min="0" max="100" value="${param.txtCredit}" placeholder="Credits">     
                                            </td>
                                            <td> 
                                                <%--<input type="text" name="txtMajor" value="${param.txtMajor}" placeholder="Major"> --%>    
                                                <select name="txtMajor">
                                                    <option value="">Major</option>
                                                    <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                                        <option value="${major.majorName}"<c:if test="${param.txtMajor eq major.majorName}" >
                                                                selected="selected"
                                                        </c:if>>${major.majorName}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td> 
                                                <select id="city" name="isIntern"  class="" >
                                                    <option value="" selected>Status</option>
                                                    <option value="1" class="text-success" <c:if test="${param.isIntern eq '1'}" >
                                                            selected="selected"
                                                        </c:if>>
                                                        Working
                                                    </option>
                                                    <option value="2" class="text-warning" <c:if test="${param.isIntern eq '2'}" >
                                                            selected="selected"
                                                        </c:if>>
                                                        Finished
                                                    </option>
                                                    <option value="0" class="text-danger" <c:if test="${param.isIntern eq '0'}" >
                                                            selected="selected"
                                                        </c:if>>
                                                        Not Yet
                                                    </option>
                                                </select>                                        
                                            </td>
                                            <td>
                                                <input type="submit" value="Search" class=" aStuManage-search-btn">
                                            </td> 
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>


                        <div class="main-body-aStuManage__content">
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:if test="${not empty requestScope.LIST_APPLIED_JOB_RESULT}" >
                                <table class="table table-bordered ">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>ID</th>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Credits</th>
                                            <th>Major</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.LIST_APPLIED_JOB_RESULT}" var="student" varStatus="counter">
                                            <c:set var="error" value="${my:getError(requestScope.INVALID_CREDIT, student.studentCode)}" />
                                        <form action="UpdateStudentInforMationController" method="POST">
                                            <tr>
                                                <td>
                                                    ${counter.count}
                                                </td>
                                                <td>
                                                    ${student.studentCode}
                                                    <input type="hidden" name="txtStudentCode" value="${student.studentCode}" />
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
                                                        ${student.numberOfCredit}
                                                    </c:if>
                                                </td>
                                                <td>
                                                    ${student.major}
                                                </td>
                                                <c:if test="${student.isIntern eq 0}">
                                                    <td class="text-danger">
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
                                                    <td class="text-warning">
                                                        <strong>
                                                            Finished
                                                        </strong>
                                                    </td>
                                                </c:if>
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
                                                            <input type="submit" value="Update" name="btAction" />
                                                        </c:if>

                                                    </td> 
                                                </c:if>
                                                <c:if test="${student.isIntern eq 1 or student.isIntern eq 2}" >
                                                    <td>
                                                        <input type="submit" value="Update" name="btAction" disabled="disabled" />
                                                    </td> 
                                                </c:if>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </c:if>
                            <c:if test="${empty requestScope.LIST_APPLIED_JOB_RESULT}" >
                                <p3>
                                    Student List does not has any result for you!
                                </p3>
                            </c:if>
                        </div>
                        <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                            <c:url var="url" value="ShowAdminStudentManagementController">
                                <c:param name="page" value="${i}"/>
                            </c:url>
                            <div class="main__pagination">
                                <ul class="pagination main_cus__pagination">
                                    <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                </ul>
                            </div>
                        </c:forEach>

                        <!--                        <div class="main__pagination">
                                                    <ul class="pagination main_cus__pagination">
                        
                                                        <li class="page-item">
                                                            <a class="page-link" href="#" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>
                        
                                                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        
                                                        <li class="page-item">
                                                            <a class="page-link" href="#" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                            </a>
                                                        </li>
                        
                                                    </ul>
                                                </div>-->


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
