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
    </head>
    <body>
        <header></header>
            <c:set var="student" value="${sessionScope.STUDENT_ROLE}" />
        <main class="row">
            <nav class="col-2  nav-fixed">
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
            <div class="main-body  offset-2 col-10">
                <div class="row">
                    <div class="main-body-appl ">
                        <div class="main-body-appl__header">
                            Applied Jobs*
                        </div>


                        <div class="main-body-appl__search">
                            <form action="SearchStudentAppliedJobController" method="POST">
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
                                            </td>
                                            <td>
                                                <select id="city" name="nameStatus"  class="" >
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
                                            </td>
                                            <td>
                                                <input type="submit" value="Search" class=" appl-search-btn">
                                            </td> 
                                        </tr>
                                    </tbody>

                                </table>
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
                                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                    <c:url var="url" value="SearchSaveJobController">
                                        <c:param name="page" value="${i}"/>
                                    </c:url>
                                    <div class="main__pagination">
                                        <ul class="pagination main_cus__pagination">
                                            <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty requestScope.LIST_APPLIED_JOB_RESULT}">
                                <p3>
                                    You have not any applied job yet!
                                </p3>
                            </c:if>
                        </div>


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
            <div class="container-fluid">
                <div class="footer__content">
                    @copyright 2022
                </div>
            </div>
        </footer>
    </body>
</html>
