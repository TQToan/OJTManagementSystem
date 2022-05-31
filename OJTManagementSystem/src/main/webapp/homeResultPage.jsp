<%-- 
    Document   : homeResultPage
    Created on : May 25, 2022, 5:28:04 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                                <c:url var="showDetail" value="HomeShowCompanyDetail">
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
                                                ${post.postingDate}
                                            </td>
                                            <td>
                                                ${post.expirationDate}
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div>
                            <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                <c:url var="url" value="SearchCompanyStudentHomeController">
                                    <c:param name="page" value="${i}"/>
                                    <c:param name="nameCompany" value="${companyID}"/>
                                    <c:param name="nameMajor" value="${majorID}"/>
                                    <c:param name="nameLocation" value="${nameLocation}"/>
                                </c:url>
                                <%--<a href="${url}">${i}</a>--%>
                                <div class="main__pagination">
                                    <ul class="pagination main_cus__pagination">

                                        <!--                                        <li class="page-item">
                                                                                    <a class="page-link" href="#" aria-label="Previous">
                                                                                        <span aria-hidden="true">&laquo;</span>
                                                                                    </a>
                                                                                </li>-->

                                        <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                        <!--                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                                                <li class="page-item"><a class="page-link" href="#">3</a></li>-->

                                        <!--                                        <li class="page-item">
                                                                                    <a class="page-link" href="#" aria-label="Next">
                                                                                        <span aria-hidden="true">&raquo;</span>
                                                                                    </a>
                                                                                </li>-->

                                    </ul>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
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
