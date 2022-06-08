<%-- 
    Document   : adminViewPost
    Created on : Jun 4, 2022, 7:39:59 PM
    Author     : ThanhTy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - View Post</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/admin.css">
    </head>

    <body>
        <header></header>

        <main class="row">
            <c:set var="admin" value="${sessionScope.ADMIN_ROLE}" />
            <nav class="col-2  nav-fixed">
                <a href="#" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${admin.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="adminStuManage.html" class="nav__item--link">
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
                        <a href="AdminShowPostManagementController" class="nav__item--link">
                            <input type="hidden" name="page" value="1" />
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
                        <a href="adminEval.html" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Evalution
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
            <c:set var="post" value="${requestScope.COMPANY_POST_DETAIL}"/>
            <div class="main-body  offset-2 col-10">
                <div class="main-body-aViewPost">
                    <div class="aViewPost__header">
                        ${post.title_Post}
                    </div>
                    <div class="aViewPost__content">
                        <p><strong>Job:</strong> ${post.major.majorName}</p>
                        <p><strong>Vacancy:</strong> ${post.vacancy}</p>
                        <p><strong>Quantity:</strong> ${post.quantityIterns}</p>
                        <p><strong>Job Description:</strong> 
                            ${post.job_Description}
                        </p>
                        <p><strong>Job requirements:</strong> 
                            ${post.job_Requirement}               
                        </p>
                        <p><strong>Remuneration:</strong>
                            ${post.remuneration}
                        </p>
                        <p><strong>Work location:</strong>
                            ${post.workLocation}
                        </p>
                        <p><strong>Posting Date:</strong>
                            ${post.postingDate}
                        </p>
                        <p><strong>Expiration Date:</strong>
                            ${post.expirationDate}
                        </p>
                    </div>

                    <div class="aViewPost-btn">
                        <form action="AdminUpdatePostController" method="POST">
                            <div>
                                <input type="hidden" name="save" value="adminViewPostPage" />
                                <input type="hidden" name="school_confirm" value="true" />
                                <input type="hidden" name="statusPost" value="2"/>
                                <input type="hidden" name="postID" value="${post.postID}" />
                                <input type="hidden" name="page" value="${requestScope.page}" />
                                <input type="hidden" name="txtTitle" value="${param.txtTitle}"/>
                                <input type="hidden" name="txtCompanyName" value="${param.txtCompanyName}"/>
                                <input type="hidden" name="nameStatus" value="${param.nameStatus}"/>
                                <input type="submit" value="Accept" class="primary-btn accept-btn" 
                                       <c:if test="${not empty requestScope.UPDATE_SUSCESS or post.statusPost eq 2 or post.statusPost eq 3}">
                                           autocomplete="off" hidden 
                                       </c:if> />


                            </div>

                        </form>
                        <form action="AdminUpdatePostController" method="POST">    
                            <div>
                                <input type="hidden" name="save" value="adminViewPostPage" />
                                <input type="hidden" name="school_confirm" value="false" />
                                <input type="hidden" name="statusPost" value="0"/>
                                <input type="hidden" name="postID" value="${post.postID}" />
                                <input type="hidden" name="page" value="${requestScope.page}" />
                                <input type="hidden" name="txtTitle" value="${param.txtTitle}"/>
                                <input type="hidden" name="txtCompanyName" value="${param.txtCompanyName}"/>
                                <input type="hidden" name="nameStatus" value="${param.nameStatus}"/>
                                <input type="submit" value="Reject" class="primary-btn reject-btn"
                                       <c:if test="${not empty requestScope.UPDATE_SUSCESS or post.statusPost eq 0 or post.statusPost eq 3}">
                                           autocomplete="off" hidden 
                                       </c:if> />
                            </div>


                        </form> 

                        <c:if test="${not empty requestScope.UPDATE_SUSCESS}">
                            <font style="color: green"> 
                            <div class="text-success">${requestScope.UPDATE_SUSCESS}</div>
                            </font>
                        </c:if>
                        
                        <form action="AdminSearchCompanyPostController" method="POST">
                                                            <div>
                                                                
                                                                <input type="hidden" name="page" value="${requestScope.page}"/>
                                                                <input type="hidden" name="txtTitle" value="${param.txtTitle}"/>
                                                                <input type="hidden" name="txtCompanyName" value="${param.txtCompanyName}"/>
                                                                <input type="hidden" name="nameStatus" value="${param.nameStatus}"/>
                                                                <input type="submit" value="Back" class="back-btn" />
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
