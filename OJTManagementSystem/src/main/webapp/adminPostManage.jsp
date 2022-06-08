<%-- 
    Document   : adminPostManage
    Created on : Jun 4, 2022, 5:58:41 PM
    Author     : ThanhTy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/tlds/myapplicationlib.tld" prefix="my"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Post Management</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/admin.css">
        tblg
    </head>

    <body>
        <header></header>

        <main class="row">
            <nav class="col-2  nav-fixed">
                <c:set var="admin" value="${sessionScope.ADMIN_ROLE}" />
                <a href="#" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${admin.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="./adminStuManage.html" class="nav__item--link">
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
                        <a href="./adminInterAppl.html" class="nav__item--link">
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
            <div class="main-body  offset-2 col-10">
                <div class="row">
                    <div class="main-body-aPostManage ">
                        <div class="main-body-aPostManage__header">
                            Post Management
                        </div>


                        <div class="main-body-aPostManage__search">
                            <form action="AdminSearchCompanyPostController" >
                                <table class="table">
                                    <tbody>
                                    <input type="hidden" name="save" value="adminSearchCompanyPostPage" />
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="text" name="txtTitle" value="${param.txtTitle}" placeholder="Title">
                                        </td>
                                        <td>
                                            <input type="text" name="txtCompanyName" value="${param.txtCompanyName}" placeholder="Company">
                                        </td>
                                        <td>
                                            <select id="city" name="nameStatus" class="">
                                                <option value="" selected>Status</option>
                                                <option value="Accept" class="text-success" <c:if test="${param.nameStatus eq 'Accept'}">
                                                        selected="selected"
                                                    </c:if>>Accept</option>
                                                <option value="Denied" class="text-danger" <c:if test="${param.nameStatus eq 'Denied'}">
                                                        selected="selected"
                                                    </c:if>>Denied</option>
                                                <option value="Waiting" class="text-warning" <c:if test="${param.nameStatus eq 'Waiting'}">
                                                        selected="selected"
                                                    </c:if>>Waiting</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="submit" value="Search" class=" aPostManage-search-btn">
                                        </td>
                                    </tr>
                                    </tbody>

                                </table>
                            </form>
                        </div>

                        <c:set var="companyPostList" value="${requestScope.COMPANY_POST_LIST}"/>
                        <div class="main-body-aPostManage__content">
                            <div class="resultpage__header">
                                Result : ${requestScope.SIZE_OF_LIST}
                            </div>
                            <c:if test="${not empty requestScope.COMPANY_POST_LIST}">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>Title</th>
                                            <th>Vacancy</th>
                                            <th>Posting Date</th>
                                            <th>Company</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${companyPostList}" 
                                                   var="post" 
                                                   varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>
                                                    <a href="AdminViewPostDetailController?postID=${post.postID}&page=${requestScope.page}">${post.title_Post}</a>
                                                </td>
                                                <td>${post.vacancy}</td>
                                                <td>${post.postingDate}</td>
                                                <td>${post.company.account.name}</td>

                                                <c:if test="${post.statusPost eq 2}">
                                                    <td class="text-success">
                                                        <strong>
                                                            Accept
                                                        </strong>
                                                    </td>
                                                </c:if>
                                                <c:if test="${post.statusPost eq 0 or post.statusPost eq 3}">
                                                    <td class="text-danger">
                                                        <strong>
                                                            Denied
                                                        </strong>
                                                    </td>
                                                </c:if>  
                                                <c:if test="${post.statusPost eq 1}">
                                                    <td class="text-warning">
                                                        <strong>
                                                            Waiting
                                                        </strong>
                                                    </td>
                                                </c:if>


                                                <td>
                                                    <c:set var="listCompanyPost" value="${requestScope.COMPANY_POST_LIST}"/>
                                                    <c:set var="statusAcceptCompanyPost" value="${my:getStatusAcceptCompanyPost(listCompanyPost, post.postID)}"/>
                                                    <c:if test="${statusAcceptCompanyPost eq false or post.statusPost eq 1}">
                                                        <form action="AdminUpdatePostController" method="POST">
                                                            <div>
                                                                <input type="hidden" name="save" value="adminPostManagePage" />
                                                                <input type="hidden" name="school_confirm" value="true" />
                                                                <input type="hidden" name="statusPost" value="2" />
                                                                <input type="hidden" name="postID" value="${post.postID}" />
                                                                <input type="hidden" name="page" value="${requestScope.page}"/>
                                                                <input type="hidden" name="txtTitle" value="${param.txtTitle}"/>
                                                                <input type="hidden" name="txtCompanyName" value="${param.txtCompanyName}"/>
                                                                <input type="hidden" name="nameStatus" value="${param.nameStatus}"/>
                                                                <input type="submit" value="Accept" class="primary-btn accept-btn" 
                                                                       <c:if test="${post.statusPost eq 3}">
                                                                           hidden
                                                                       </c:if>/>
                                                            </div>
                                                        </form>
                                                    </c:if>

                                                    <c:if test="${statusAcceptCompanyPost eq true or post.statusPost eq 1}">
                                                        <form action="AdminUpdatePostController" method="POST">    
                                                            <div>
                                                                <input type="hidden" name="save" value="adminPostManagePage" />
                                                                <input type="hidden" name="school_confirm" value="false" />
                                                                <input type="hidden" name="statusPost" value="0" />
                                                                <input type="hidden" name="postID" value="${post.postID}" />
                                                                <input type="hidden" name="page" value="${requestScope.page}"/>
                                                                <input type="hidden" name="txtTitle" value="${param.txtTitle}"/>
                                                                <input type="hidden" name="txtCompanyName" value="${param.txtCompanyName}"/>
                                                                <input type="hidden" name="nameStatus" value="${param.nameStatus}"/>
                                                                <input type="submit" value="Reject" class="primary-btn reject-btn"
                                                                       <c:if test="${post.statusPost eq 3}">
                                                                           hidden
                                                                       </c:if>/>
                                                            </div>
                                                        </form>
                                                    </c:if>

                                                    <%--<c:if test="${not empty requestScope.UPDATE_SUSCESS or post.statusPost eq 2}">
                                                        autocomplete="off" hidden 
                                                    </c:if> 
                                                    
                                                    <c:if test="${not empty requestScope.UPDATE_SUSCESS or post.statusPost eq 1}">
                                                                           autocomplete="off" hidden 
                                                                       </c:if> 
                                                    
                                                    --%>


                                                    <%--<form action="AdminViewPostDetailController">
                                                        <input type="hidden" name="postID" value="${post.postID}" />
                                                        <input type="hidden" name="school_confirm" value="${post.school_confirm}" />
                                                        <input type="hidden" name="statusPost" value="${post.statusPost}" />
                                                        <input type="submit" value="Detail" />
                                                    </form>
                                                    <a href="AdminViewPostDetailController?postID=${post.postID}">View post detail</a>--%>

                                                </td>
                                            </tr>
                                        </c:forEach>


                                    </tbody>

                                </table>
                                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                    <c:url var="url" value="AdminSearchCompanyPostController">
                                        <c:param name="save" value="adminSearchCompanyPostPage" />
                                        <c:param name="page" value="${i}"/>
                                        <c:param name="txtTitle" value="${param.txtTitle}"/>
                                        <c:param name="txtCompanyName" value="${param.txtCompanyName}"/>
                                        <c:param name="nameStatus" value="${param.nameStatus}"/>
                                    </c:url>
                                    <div style="display: inline-block" class="main__pagination">
                                        <ul class="pagination main_cus__pagination">
                                            <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty requestScope.COMPANY_POST_LIST}">
                                <p3>
                                    You have not any post job yet!
                                </p3>
                            </c:if>

                        </div>





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
