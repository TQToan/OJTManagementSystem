<%-- 
    Document   : homeAfterckick1
    Created on : May 29, 2022, 7:49:15 AM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home - Student Information</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/home.css">
    </head>
    <body>
        <header class="header ">
            <div class="navbar header__nav_cus">
                <a href="home.html" class="header__logo">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>
                <div class="header__name">
                    <div class="header__name--show">
                        Hi, Toàn
                        <i class="fas fa-angle-down icon-down"></i>
                    </div>
                    <div class="header__name--hidden">
                        <a href="studentDashboard.html" class="header__name--hidden-content">Dashboard</a>
                        <a href="login.html" class="header__name--hidden-content">Logout</a>
                    </div>
                </div>

            </div>

        </header>
        <form action="ApplyCVStudentConTroller" method="POST" enctype="multipart/form-data">
            <main class="main">
                <div class="main-body">
                    <div class="main-body-cViewStu">
                        <c:set var="student" value="${requestScope.STUDENT_INFORMATION}" />
                        <%--<c:set var="portID" value="${param.postID}" />--%>
                        <c:set var="portID" value="${1}" />
                        <input type="hidden" name="postID" value="${portID}" />

                        <div class="main-body-cViewStu__header">
                            Student Information
                        </div>
                        <div class="row">
                            <div class="inforStu-left col-3">
                                <c:if test="${not empty student.account.avatar}">
                                    <img src="./avatars/${student.account.avatar}" alt="" class="inforStu-left--img img-responsive">
                                </c:if>
                                <c:if test="${empty student.account.avatar}"> 
                                    <img src="./assets/img/person.jpg" alt="" class="inforStu-left--img">
                                </c:if>
                                <div class="inforStu-left__content">
                                    <p>Name: ${student.account.name} </p>
                                    <p>Birthday: ${student.birthDay}</p>
                                    <p>Gender: <c:if test="${student.gender eq true}">
                                            Male
                                        </c:if>
                                        <c:if test="${student.gender eq false}">
                                            Female
                                        </c:if>
                                    </p>
                                    <p>Address: ${student.address}</p>
                                    <p>Email: ${student.account.email}</p>    
                                </div>
                            </div>

                            <div class="inforStu-right col-8">
                                <div class="inforStu-right__header">
                                    Career Information
                                </div>
                                <c:set var="application" value="${requestScope.APPLICATION_INFORMATION}" />
                                <div class="inforStu-right__content">
                                    <c:set var="errors" value="${requestScope.ERRORS}"/>
                                    <p>Expected Job: <input type="text" name="txtExpectedJob" value="${application.expected_job}" /> </p>
                                        <c:if test="${not empty errors.expectedJobLengthError}" >
                                            ${errors.expectedJobLengthError}
                                        </c:if>
                                    <p>Technology: <input type="text" name="txtTechnology" value="${application.technology}" /> </p>
                                        <c:if test="${not empty errors.technologyLengthError}" >
                                            ${errors.technologyLengthError}
                                        </c:if>
                                    <p>Experience: <select name="txtExperience">
                                            <option value="Chưa có kinh nghiệm" <c:if test="${application.experience eq 'Chưa có kinh nghiệm'}">
                                                    selected="selected"
                                                </c:if>>Chưa có kinh nghiệm</option>
                                            <option value="1 năm" <c:if test="${application.experience eq '1 năm'}">
                                                    selected="selected"
                                                </c:if>>1 năm</option>
                                            <option value="2 năm" <c:if test="${application.experience eq '2 năm'}">
                                                    selected="selected"
                                                </c:if>>2 năm</option>
                                            <option value="Trên 3 năm" <c:if test="${application.experience eq 'Trên 3 năm'}">
                                                    selected="selected"
                                                </c:if>>Trên 3 năm</option>
                                        </select> </p>
                                    <p>Foreign Language: <input type="text" name="txtForeignLanguage" value="${application.foreign_Language}" /> </p>
                                        <c:if test="${not empty errors.foreignLanguageLengthError}" >
                                            ${errors.foreignLanguageLengthError}
                                        </c:if>
                                    <p>Other Skills: <input type="text" name="txtOtherSkills" value="${application.otherSkills}" /> </p>
                                        <c:if test="${not empty errors.otherSkillsLengthError}" >
                                            ${errors.otherSkillsLengthError}
                                        </c:if>
                                </div>
                            </div>
                        </div>


                    </div>

                </div>

                <div class="fix--btn">
                    <!-- <a href="#" class= " primary-btn edit-btn">Edit</a>-->

                    <input type="submit" value="Apply Now" name="btAction" class="primary-btn upload-btn">

                    <%--<c:if test="${not empty requestScope.EDIT}">
                        <input type="submit" value="Edit" name="btAction" class="primary-btn upload-btn">
                    </c:if>--%>
                    <a href="home.html" class= " primary-btn exit-btn">Exit</a>
                </div>

                <div class="file-input">
                    <label for="myfile" >
                        Your CV: 
                    </label>
                    <input type="file" id="myfile" name="myfile" value="${application.attachmentPath}">
                    <c:if test="${not empty errors.fileUploadError}" >
                        ${errors.fileUploadError}
                    </c:if>
                    <c:if test="${not empty errors.fileUploadTypeError}">
                        ${errors.fileUploadTypeError}
                    </c:if>
                    <c:if test="${not empty errors.fileUploadLengthError}" >
                        ${errors.fileUploadLengthError}
                    </c:if>
                </div>


            </main>
        </form>
        <footer class="footer">
            <div class="footer__content">
                @copyright 2022
            </div>

        </footer>
    </body>
</html>
