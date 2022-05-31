<%-- 
    Document   : studentDashboard
    Created on : May 25, 2022, 9:29:06 AM
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
        <title>Student - Dashboard</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/student.css">
    </head>
    <body>
        <header></header>

        <main class="row">
            <nav class="col-2  nav-fixed">
                <a href="home.html" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="studentProfile.html" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    Thái Quốc Toàn
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="#" class="nav__item--link">
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
                            <a href="studentSaveJob.html" class="nav__item__dropdown--link">
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
                        <a href="login.html" class="nav__item--link">
                            <i class="fas fa-power-off"></i>
                            Logout
                        </a>
                    </li>
                </ul>

            </nav>
            <c:set var="account" value="${sessionScope.ACCOUNT}"/>
            <c:if test="${not empty account}">
                <div class="main-body  offset-2 col-10">
                    <div class="row">
                        <div class="dashboard-card offset-3 col-2">
                            <a href="studentSaveJob.html" class="dashboard-card--link">
                                <div class="save-jobs">
                                    100
                                </div>
                                <div class="dashboard-card__content">
                                    Save Jobs
                                </div>
                            </a>

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
                        <div class="card-visit offset-3 col-6">
                            <div class="card-visit__header">
                                Student Profile
                            </div>
                            <div class="card-visit__body row">
                                <div class="card-visit--img offset-1 col-3">
                                    <img src="./assets/img/person.jpg" alt="">
                                </div>
                                <div class="card-vist__content offset-1 col-7">
                                    <h3>Thái Quốc Toàn</h3>
                                    <p>Date of Birth: 19/04/2001</p>
                                    <p>Job: Software Engineering</p>
                                    <p>Email: toantqse151272@fpt.edu.vn</p>
                                </div>

                            </div>
                            <a href="studentProfile.html" class="card-visit-btn primary-btn">
                                <i class="fas fa-edit"></i>
                                Edit
                            </a>

                        </div>
                    </div>


                    <div class="recom-jobs">
                        <div class="recom-header">
                            Recommended Jobs
                        </div>

                        <div class="row row-cols-2">

                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="homeCPostDetail.html">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn save-btn-active"></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn save-btn-active"></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn "></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn save-btn-active"></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn "></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="recom-box row ">
                                    <a href="">
                                        <h3>Thực tập sinh (Lập trình viên BACKEND) FPT Software 
                                            Chi nhánh Công ty Cổ Phần Viễn Thông FPT
                                        </h3>
                                    </a>
                                    <div class="recom-box__img col-4">
                                        <img src="./assets/img/FPTSoftware.jpg" alt="">
                                    </div>
                                    <div class="recom-box-content col-8">

                                        <p>Quantity: 7</p>
                                        <p>TP.HCM</p>
                                        <p>Date: 14/5/2022</p>
                                        <p>
                                            <i class="far fa-heart save-btn "></i>
                                            Save Job</p>
                                    </div>
                                </div>
                            </div>



                        </div>

                        <div class="recom__see-more--btn row">
                            <a href="homeResultPage.html" class="recom--more--btn offset-10 col-2">
                                See More 
                                <i class="fas fa-arrow-right"></i>
                            </a> 
                        </div>

                    </div>
                </div>
            </c:if>
        </main>

        <footer class="footer">
            <div class="footer__content">
                @copyright 2022
            </div>

        </footer>

    </body>
</html>
