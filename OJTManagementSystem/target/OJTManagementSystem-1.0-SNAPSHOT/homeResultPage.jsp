<%-- 
    Document   : homeResultPage
    Created on : May 25, 2022, 5:28:04 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/home.css">
        <link rel="stylesheet" href="./assets/css/home-responsive.css">
    </head>
    <body>
        <header class="header ">
            <div class="navbar header__nav_cus">

                <a href="ShowStudentHomeController" class="header__logo">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>
                <div class="header__name">
                    <div class="header__name--show">
                        <c:set var="student" value="${sessionScope.STUDENT_ROLE}"/>
                        Hi, ${student.account.name}             
                        <i class="fas fa-angle-down icon-down"></i>
                    </div>
                    <div class="header__name--hidden">
                        <a href="studentDashboardController" class="header__name--hidden-content">Dashboard</a>
                        <a href="logoutController" class="header__name--hidden-content">Logout</a>
                    </div>
                </div>

            </div>

        </header>

        <main class="main">
            <div class="main__search">
                <h2>Search Jobs</h2>
                <c:set var="companyID" value="${param.nameCompany}"/>
                <c:set var="majorID" value="${param.nameMajor}"/>
                <c:set var="nameLocation" value="${param.nameLocation}"/>
                <c:set var="page" value="${requestScope.page}"/>
                <form action="SearchCompanyStudentHomeController" class="main__search-form">
                    <div class="row">
                        <div class="col-4">
                            <!--                            <input type="text" name="" id="" placeholder="Company">-->
                            <select name="nameCompany">
                                <option value="">Company Name</option>
                                <c:forEach items="${requestScope.COMPANY_NAME}" var="company">
                                    <option value="${company.companyID}" <c:if test="${company.companyID eq companyID}">
                                            selected="selected"
                                        </c:if> >${company.account.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <!--                            <input type="text" name="" id="" placeholder="Job">-->
                            <select name="nameMajor">
                                <option value="">Major</option>
                                <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                                    <option value="${major.majorID}" <c:if test="${major.majorID eq majorID}">
                                            selected="selected"
                                        </c:if> >${major.majorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-2">
                            <select id="city" name="nameLocation"   >
                                <option value="">Location</option>
                                <option value="TP.HCM" <c:if test="${nameLocation eq 'TP.HCM'}">
                                        selected="selected"
                                    </c:if>>TP.HCM</option>
                                <option value="Dong Nai" <c:if test="${nameLocation eq 'Dong Nai'}">
                                        selected="selected"
                                    </c:if>>Đồng Nai</option>
                                <option value="Tay Ninh" <c:if test="${nameLocation eq 'Tay Ninh'}">
                                        selected="selected"
                                    </c:if>>Tây Ninh</option>
                                <option value="Binh Duong" <c:if test="${nameLocation eq 'Binh Duong'}">
                                        selected="selected"
                                    </c:if>>Bình Dương</option>
                            </select>
                        </div>
                        <div class="col-2">
                            <input type="submit" value="Search" class="primary-btn">
                        </div>
                    </div>
                </form>
            </div>
            <div class="resultpage">
                <div class="resultpage__header">
                    Result : ${requestScope.SIZE_OF_LIST}
                </div>
                <div class="main-body-hResultPage">
                    <c:set var="result" value="${requestScope.LIST_RESULT}"/>
                    <c:if test="${not empty result}">
                        <div class="main-body-hResultPage__content">

                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>NO.</th>
                                        <th>Job</th>
                                        <th>Quantity</th>
                                        <th>Company</th>
                                        <th>Location</th>
                                        <th>Creation Date</th>
                                        <th>Expired Date</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${result}" var="post" varStatus="counter">
                                        <tr>
                                            <td>
                                                ${counter.count}
                                            </td>
                                            <td>
                                                <c:url var="showDetail" value="HomeShowCompanyDetailController">
                                                    <c:param name="postID" value="${post.postID}"/>
                                                </c:url>
                                                <a href="${showDetail}">
                                                    ${post.majorName}
                                                </a>
                                            </td>
                                            <td>
                                                ${post.quantityIterns}
                                            </td>
                                            <td>
                                                ${post.company.account.name}
                                            </td>
                                            <td>
                                                ${post.workLocation}
                                            </td>
                                            <td>
                                                ${my:changeDateFormat(post.postingDate)}
                                            </td>
                                            <td>
                                                ${my:changeDateFormat(post.expirationDate)}
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div>                       
                            <div  class="main__pagination">
                                <ul class="pagination main_cus__pagination">
                                    <!--                                     <li class="page-item">
                                                                            <a class="page-link" href="#" aria-label="Previous">
                                                                                 <span aria-hidden="true">&laquo;</span>
                                                                            </a>
                                                                        </li>-->
                                    <div id="pageX" hidden>${requestScope.page}</div>
                                    <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                        <li class="page-item">
                                            <form action="SearchCompanyStudentHomeController" method="POST">
                                                <input type="hidden" name="page" value="${i}">
                                                <input type="hidden" name="nameCompany" value="${companyID}">
                                                <input type="hidden" name="nameMajor" value="${majorID}">
                                                <input type="hidden" name="nameLocation" value="${nameLocation}">
                                                <input type="submit" value="${i}" class="page-link" >
                                            </form>
                                        </li>
                                        <%--   <c:url var="url" value="SearchCompanyStudentHomeController">
                                               <c:param name="page" value="${i}"/>
                                               <c:param name="nameCompany" value="${companyID}"/>
                                               <c:param name="nameMajor" value="${majorID}"/>
                                               <c:param name="nameLocation" value="${nameLocation}"/>
                                           </c:url>
                                           <li class="page-item"><a class="page-link" href="${url}">${i}</a></li> --%>
                                    </c:forEach>
                                    <!--                                    <li class="page-item">
                                                                            <a class="page-link" href="#" aria-label="Next">
                                                                                <span aria-hidden="true">&raquo;</span>
                                                                             </a>
                                                                        </li>-->
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </main>

        <footer class="footer">
            <div class="footer__content">
                <i class="fa-regular fa-copyright"></i> Copyright 2022
            </div>
        </footer>
        <script src="./assets/js/base.js"></script>
    </body>
</html>
