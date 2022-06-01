<%-- 
    Document   : studentDashboard
    Created on : May 25, 2022, 9:29:06 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    </head>

    <body>
        <header></header>

        <main class="row">
            <%--Thanh fix new name session--%>
            <c:set var="user" value="${sessionScope.ACCOUNT}"/>
            <c:set var="student" value="${requestScope.STUDENT}" />
            <nav class="col-2  nav-fixed">
                <a href="home.html" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                  
                <a href="ShowStudentProfileController" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    <c:if test="${not empty user}">
                        ${user.name}
                    </c:if> 
                </a>

                <ul class="nav__content">
                    <li class="nav__items">

                        <a href="studentDashboardController" class="nav__item--link">
                            <i class="fas fa-palette "></i>
                            Dashboard
                        </a>
                    </li>
                    <li class="nav__items">

                        <c:url var="studentProfile" value="ShowStudentProfileController">                           
                        </c:url>
                        <a href="${studentProfile}" class="nav__item--link">
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

                            <c:url var="urlSaveJob" value="SearchSaveJobController">
                                <c:param name="txtJob" value=""/>
                                <c:param name="txtCompany" value=""/>
                                <c:param name="nameLocation" value=""/>
                                <c:param name="studentCode" value="${student.studentCode}"/>

                            </c:url>
                            <a href="${urlSaveJob}" class="nav__item__dropdown--link">
                                Saved Jobs
                            </a>
                            <a href="studentApplJob.html" class="nav__item__dropdown--link">
                                Applied Jobs
                            </a>
                        </div>
                    </li>
                    <li class="nav__items">
                        <a href="studentReview.html" class="nav__item--link">
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
                    <div class="dashboard-card offset-3 col-2">
                        <c:url var="urlSaveJob" value="SearchSaveJobController">
                            <c:param name="txtJob" value=""/>
                            <c:param name="txtCompany" value=""/>
                            <c:param name="nameLocation" value=""/>
                            <c:param name="studentCode" value="${student.studentCode}"/>
                        </c:url>
                        <a href="${urlSaveJob}" class="dashboard-card--link">
                            <div class="save-jobs">
                                ${requestScope.SIZE_OF_LIST_DASHBOARD}
                            </div>
                            <div class="dashboard-card__content">
                                Save Jobs
                            </div>
                        </a>

                        <!--                        <a href="StudentSaveJobController" class="dashboard-card--link">
                                                    <div class="save-jobs">
                        ${requestScope.SIZE_OF_LIST_DASHBOARD}
                    </div>
                    <div class="dashboard-card__content">
                        Save Jobs
                    </div>
                </a>-->

                    </div>

                    <div class="dashboard-card offset-2 col-2 ">
                        <a href="studentApplJob.html" class="dashboard-card--link">
                            <div class="applied-jobs ">
                                200
                            </div>
                            <div class="dashboard-card__content">
                                Applied Jobs
                            </div>
                    </div>


                </div>

                <div class="row">
                    <form action="studentDashboardController">
                        <div class="card-visit offset-3 col-6">
                            <div class="card-visit__header">
                                Student Profile
                            </div>
                            <div class="card-visit__body row">
                                <div class="card-visit--img offset-1 col-3">

                                    <img src="./avatars/${user.avatar}" alt="${user.avatar}">
                                </div>
                                <div class="card-vist__content offset-1 col-7">
                                    <h3>${user.name}</h3>
                                    <p>Date of birth: ${student.birthDay}</p>
                                    <p>Job: ${student.major}</p>
                                    <p>Email: ${user.email}</p>


                                </div>

                            </div>
                            <a href="studentProfile.html" class="card-visit-btn primary-btn">
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

                    <div class="row row-cols-2">
                        <c:forEach items="${requestScope.LIST_POST_HOME}" var="dto">
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="homeCPostDetail.html">
                                        <h3>${dto.title_Post}</h3>
                                        <h3>${dto.company.account.name}</h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./avatars/${dto.company.account.avatar}" alt="${dto.company.account.avatar}">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: ${dto.quantityIterns}</p>
                                        <p>${dto.workLocation}</p>
                                        <p>Date: ${dto.postingDate}</p>


                                        <form action="StudentSaveJobController" method="POST">
                                            <input type="hidden" name="postID" value="${dto.postID}" />
                                            <input type="hidden" name="studentCode" value="${student.studentCode}" />
                                            <input type="submit" value="Save Job" class="far fa-heart save-btn save-btn-active" />
                                        </form>

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
                            <c:param name="nameCompany" value="${companyID}"/>
                            <c:param name="nameMajor" value="${majorID}"/>
                            <c:param name="nameLocation" value="${nameLocation}"/>
                        </c:url>
                        <a href="${urlSearchHome}" class="recom--more--btn offset-10 col-2">
                            See More 
                            <i class="fas fa-arrow-right"></i>
                        </a> 
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
