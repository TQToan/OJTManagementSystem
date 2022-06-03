<%-- 
    Document   : studentSaveJob
    Created on : May 29, 2022, 1:19:22 PM
    Author     : ThanhTy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student - Save Jobs</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/student.css">
    </head>

    <body>
        <header></header>

        <main class="row">
            <c:set var="student" value="${sessionScope.STUDENT_ROLE}"/>
            <c:set var="page" value="${requestScope.page}"/>
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
                    <div class="main-body-save ">
                        <div class="main-body-save__header">
                            Save Jobs*
                        </div>


                        <form action="SearchSaveJobController" class="main__search-form">
                            <div class="row">
                                <div class="col-4">
                                    <input type="text" name="txtJob" value="${param.txtJob}" id="" placeholder="Job">
                                    <%--                                    <input type="hidden" name="job" value="${tittle_Post}" />--%>
                                </div>
                                <div class="col-4">
                                    <input type="text" name="txtCompany" value="${param.txtCompany}" id="" placeholder="Company">

                                </div>
                                <div class="col-2">
                                    <select id="city" name="nameLocation"   >
                                        <option value="">Location</option>
                                        <option value="TP.HCM" <c:if test="${nameLocation eq 'TP.HCM'}">
                                                selected="selected"
                                            </c:if>>TP.HCM</option>
                                        <option value="Dong Nai" <c:if test="${nameLocation eq 'Dong Nai'}">
                                                selected="selected"
                                            </c:if>>Dong Nai</option>
                                        <option value="Tay Ninh" <c:if test="${nameLocation eq 'Tay Ninh'}">
                                                selected="selected"
                                            </c:if>>Tay Ninh</option>
                                        <option value="Binh Duong" <c:if test="${nameLocation eq 'Binh Duong'}">
                                                selected="selected"
                                            </c:if>>Binh Duong</option>
                                    </select>
                                </div>
                                <div class="col-2">
                                    <input type="submit" value="Search" class="primary-btn">
                                </div>
                            </div>
                        </form>

                        <div class="main-body-save__content">
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:set var="result" value="${requestScope.LIST_SAVED_POSTS_RESULT}"/>
                            <c:if test="${not empty result}">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>Type Job</th>
                                            <th>Company</th>
                                            <th>Location</th>
                                            <th>Creation Date</th>
                                            <th>Expiration Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${result}" var="post" varStatus="counter">
                                            <c:set var="student1" value="${requestScope.STUDENT_CODE}" />
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>
                                                    <a href="HomeShowCompanyDetailController?postID=${post.postID}">${post.getTittle_Post()}</a>
                                                </td>
                                                <td>${post.companyName}</td>
                                                <td>${post.workLocation}</td>
                                                <td>${my:changeDateFormat(post.postingDate)}</td>
                                                <td>${my:changeDateFormat(post.exprirationDate)}</td>

                                                <td>
                                                    <c:url var="urlDeleteSaveJob" value="StudentDeleteSaveJobController">
                                                        <c:param name="postID" value="${post.postID}"/>
                                                    </c:url>
                                                    <a href="${urlDeleteSaveJob}" >Unsave</a>
                                                    <%--                                                    <form action="StudentDeleteSaveJobController" >
                                                                                                            <input type="hidden" name="postID" value="${post.getPostID()}" />
                                                                                                            <input type="hidden" name="studentCode" value="${student1.getStudentCode()}" />
                                                                                                            <input type="submit" value="Delete" class="far fa-heart save-btn save-btn-active" />
                                                                                                        </form>--%>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                    <c:url var="url" value="SearchSaveJobController">
                                        <c:param name="page" value="${i}"/>
                                        <c:param name="txtJob" value="${param.txtJob}"/>
                                        <c:param name="txtCompany" value="${param.txtCompany}"/>
                                        <c:param name="nameLocation" value="${param.nameLocation}"/>
                                    </c:url>
                                    <div class="main__pagination">
                                        <ul class="pagination main_cus__pagination">
                                            <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty result}">
                                <p3>
                                    You have not saved job yet!
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
            <div class="footer__content">
                @copyright 2022
            </div>

        </footer>

    </body>

</html>