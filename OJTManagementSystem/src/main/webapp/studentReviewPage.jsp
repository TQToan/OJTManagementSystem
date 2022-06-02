<%-- 
    Document   : studentReviewPage
    Created on : May 31, 2022, 8:39:47 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student - Review</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/student.css">
    </head>
    <body>
        <header></header>
            <c:set var="account" value="${sessionScope.ACCOUNT}"/>
            <c:if test="${not empty account}">
            <main class="row">
                <nav class="col-2  nav-fixed">
                    <a href="ShowStudentHomeController" class="nav__logo ">
                        <img src="./assets/img/logo.png" alt="" class="nav--logo">
                    </a>
                    <a href="ShowStudentProfileController" class=" nav__infor--link text-truncate">
                        <i class="fas fa-user-circle nav__infor--icon"></i>
                        ${account.name}
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

                <%--Get Student Profile--%>   
                <c:set var="studentProfile" value="${requestScope.STUDENT_PROFILE}"/>
                <%--Get Student Application--%>  
                <c:set var="studentApp" value="${requestScope.STUDENT_APP}"/>
                <%--Get Company profile --%>  
                <c:set var="companyProfile" value="${requestScope.COMPANY_PROFILE}"/>
                <%--Get Company Post --%>  
                <c:set var="companyPost" value="${requestScope.COMPANY_POST}"/>                     


                <div class="main-body  offset-2 col-10">
                    <div class="row">
                        <div class="main-body-review offset-3 col-6">
                            <c:if test="${not empty studentApp}">
                                <div class="main-body-review__header">

                                    Personal Review*
                                </div>
                                <div class="review__input row">
                                    <div class="col-4 review--label" >Student Code</div>
                                    <div class="col-8 review--input " >${studentProfile.studentCode}</div>
                                </div>
                                <div class="review__input row">
                                    <div class="col-4 review--label" >Full Name</div>
                                    <div class="col-8 review--input " >${studentProfile.account.name}</div>
                                </div>
                                <div class="review__input row">
                                    <div class="col-4 review--label" >Birthday</div>
                                    <div class="col-8 review--input" >${studentProfile.birthDay}</div>
                                </div>
                                <div class="detail_internship" disabled="true">
                                    <div class="review__input row">
                                        <div class="col-4 review--label" >Company Internship</div>
                                        <div class="col-8 review--input ">${companyProfile.account.name}</div>
                                    </div>
                                    <div class="review__input row">
                                        <div class="col-4 review--label" >Type Job</div>
                                        <div class="col-8 review--input ">${companyPost.title_Post}</div>
                                    </div>
                                    <!--                            <div class="review__input row">
                                                                    <div class="col-4 review--label" >Date applied</div>
                                                                    <div class="col-8 review--input " >15-6-2022</div>
                                                                </div>-->
                                    <div class="review__input row ">
                                        <div class="col-4 review--label" >Evaluation</div> <br>
                                        <div class="col-12 review--input review--input--evaluate" >
                                            ${studentApp.evaluation}
                                        </div>
                                    </div>
                                    <div class="review__input row">

                                        <div class="col-4 review--label" >Mark</div>

                                        <div class="col-8 review--input">
                                            <c:if test="${studentApp.grade ne 0}">
                                                ${studentApp.grade}
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                            </c:if>
                        </div>
                    </div>
                    <c:if test="${empty studentApp}">
                        
                        <div class="text-center " style="font-size: 30px; margin: auto;" for="">You are not intern at any company 
                            <!--<br/>    <a style="font-size: 15px;" href="studentDashboardController">Recommend Job</a>-->                             
                        </div>

                        <%--Recomment Jobs--%>
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
                                                    <input type="hidden" name="studentCode" value="${studentProfile.studentCode}" />
                                                    <input type="submit" value="Save Job" class="far fa-heart save-btn save-btn-active" />
                                                </form>                                
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

                    </c:if>


                </div>



            </main>

        </c:if>

        <footer class="footer">
            <div class="footer__content">
                @copyright 2022
            </div>

        </footer>

    </body>
</html>
