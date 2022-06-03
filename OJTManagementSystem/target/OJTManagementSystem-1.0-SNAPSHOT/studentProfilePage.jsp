<%-- 
    Document   : studentProfilePage
    Created on : May 30, 2022, 10:08:37 AM
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
        <title>Student- Profile</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/student.css">
    </head>
    <body>
        <header></header>

        <main class="row">
            <c:set var="student" value="${sessionScope.STUDENT_ROLE}"/>
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

            <div class="main-body  offset-2 col-10 ">
                <div class="row">
                    <div class="main-body-profile offset-3 col-6">
                        <div class="main-body-profile__header">
                            Personal Profile*
                        </div>

                        <c:set var="studentProfile" value="${requestScope.STUDENT_PROFILE}"/>

                        <form action="UpdateStudentProfileController" method="POST" enctype="multipart/form-data">
                            <c:set var="errorUpdate" value="${requestScope.ERROR_UPDATE_STUDENTPROFILE}" />

                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="studentCode">Student Code</label>
                                <input type="text" readonly class="col-8 profile--input" name="studentCode" id="studentCode" value="${studentProfile.studentCode}">
                                <input type="hidden" name="studentCode" value="${studentProfile.studentCode}" />
                                <!--                                    <h5 class="text-danger offset-4 col-8 text-start">
                                                                        your error
                                                                    </h5>-->
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="fullName">Full Name</label>
                                <input type="text" readonly class="col-8 profile--input " name="fullName" id="fullName" value="${studentProfile.account.name}">
                                <!--                                    <h5 class="text-danger offset-4 col-8 text-start">
                                                                        your error
                                                                    </h5>-->
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="birthday">Birthday</label>
                                <input type="date" class="col-8 profile--input " name="dateUpdate" id="birthday" value="${studentProfile.birthDay}">
                                <h5 class="text-danger offset-4 col-8 text-start">
                                    <c:if test="${not empty errorUpdate}">
                                        ${errorUpdate.errorDateInvalid}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="gender">Gender</label>
                                <select name="genderUpdate" class="col-8 profile--input">
                                    <option <c:if test="${studentProfile.gender eq false}">
                                            selected="selected"
                                        </c:if>>Female</option>
                                    <option <c:if test="${studentProfile.gender eq true}">
                                            selected="selected"
                                        </c:if>>Male</option>
                                </select>
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="email">Email</label>
                                <input type="email" readonly class="col-8 profile--input " name="email" id="email"
                                       value="${studentProfile.account.email}">
                                <!--                                    <h5 class="text-danger offset-4 col-8 text-start">
                                                                        your error
                                                                    </h5>-->
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="address">Address</label>
                                <input type="text" class="col-8 profile--input " name="addressUpdate" id="address" value="${studentProfile.address}">
                                <h5 class="text-danger offset-4 col-8 text-start ">
                                    <c:if test="${not empty errorUpdate}">
                                        ${errorUpdate.errorAddressLength}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="phone">Phone Number</label>
                                <input type="number" class="col-8 profile--input " name="phoneUpdate" id="phone" value="${studentProfile.phone}">
                                <h5 class="text-danger offset-4 col-8 text-start ">
                                    <c:if test="${not empty errorUpdate}">
                                        ${errorUpdate.errorPhoneNumberLength}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="profile__input row">
                                <label class="col-4 profile--label" for="major">Major</label>
                                <input type="text" readonly class="col-8 profile--input " name="" id="major"
                                       value="${studentProfile.major}">
                            </div>
                            <div class="profile__input row"> 
                                <label class="col-4 profile--label" for="avatar">Avatar</label>
                                <input type="file" name="avatar" class="col-8" value="" id="avatar" />
                            </div>
                            <c:if test="${not empty errorUpdate.errorFileLength}">
                                <h5 class="text-danger offset-4 col-8 text-start">
                                    ${errorUpdate.errorFileLength}
                                </h5>
                            </c:if>

                            <div class="profile-edit-btn primary-btn">
                                <i class="fas fa-edit"></i>
                                <input type="submit" class="profile-edit--input" value="Edit">
                            </div>
                        </form>
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
