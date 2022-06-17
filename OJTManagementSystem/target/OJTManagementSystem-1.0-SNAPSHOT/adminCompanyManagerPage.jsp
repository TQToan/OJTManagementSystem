<%-- 
    Document   : adminCompanyManagerPage
    Created on : Jun 6, 2022, 1:59:08 PM
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
        <title>Admin - Company Management</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/admin.css">
    </head>
    <body>
        <header></header>

        <main class="row">
            <c:set var="admin" value="${sessionScope.ADMIN_ROLE}" />
            <nav class="col-xl-2  nav-fixed col-md-3">
                <a href="#" class="nav__logo ">
                    <img src="./assets/img/logo.png" alt="" class="nav--logo">
                </a>
                <a href="#" class=" nav__infor--link text-truncate">
                    <i class="fas fa-user-circle nav__infor--icon"></i>
                    ${admin.name}
                </a>

                <ul class="nav__content">
                    <li class="nav__items">
                        <a href="ShowAdminStudentManagementController" class="nav__item--link">
                            <i class="fas fa-university"></i>
                            Student Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminCompanyManagerController" class="nav__item--link">
                            <i class="far fa-building"></i>
                            Company Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminShowPostManagementController" class="nav__item--link">
                            <i class="fas fa-pen"></i>
                            Post Management
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="AdminShowInternApplicationController" class="nav__item--link">
                            <i class="fas fa-clipboard-check"></i>
                            Internship Application
                        </a>
                    </li>
                    <li class="nav__items">
                        <a href="ShowStudentEvaluationController" class="nav__item--link">
                            <i class="fas fa-poll-h"></i>
                            Evaluation
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
            <div class="main-body offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">
                <div class="row">
                    <div class="main-body-aComManage ">
                        <c:set var="listCompany" value="${requestScope.LIST_COMPANY}"/>
                        <c:set var="page" value="${requestScope.page}"/>
                        <c:set var="sizePage" value="${requestScope.SIZE_PAGE}"/>
                        <div class="main-body-aComManage__header">
                            Company Management
                        </div>


                        <div class="main-body-aComManage__search">
                            <form action="SearchCompanyAdminManagerController">
                                <div class="row">
                                    <div class="col-4">
                                        <select id="city" name="selectCompany"  class="admin--select" >
                                            <option value="" >Company</option>
                                            <c:forEach var="allCompany" items="${requestScope.LIST_ALL_COMPANY}">
                                                <option value="${allCompany.companyID}"
                                                        <c:if test="${allCompany.companyID eq param.selectCompany}">
                                                            selected="selected"
                                                        </c:if>
                                                        >
                                                    ${allCompany.account.name}                                                   
                                                </option>  
                                            </c:forEach>
                                        </select>  
                                    </div>

                                    <div class="col-4">
                                        <input type="text" name="txtEmail" placeholder="Email" value="${param.txtEmail}" class="admin--input">     
                                    </div>

                                    <div class="col-2">
                                        <select id="city" name="selectStatus"  class="admin--select" >
                                            <option value="">Status</option>
                                            <option value="Success" class="text-success">                                                                          
                                                Signed
                                            </option>
                                            <option value="Denied" class="text-danger">                                                       
                                                Not Yet
                                            </option>
                                        </select>   
                                    </div>

                                    <div class="col-2">
                                        <input type="submit" value="Search" class=" admin-search-btn">
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="main-body-aComManage__content">
                            <div class="resultpage__header">
                                Result : ${sizePage}
                            </div>
                            <c:if test="${not empty listCompany}">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>Company Name</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="companyDTO" items="${listCompany}" varStatus="count" >
                                        <form action="AdminUpdateStatusCompanyController" method="POST">

                                            <tr>
                                                <td>
                                                    ${count.count}
                                                </td>
                                                <td>${companyDTO.account.name}</td>
                                                <td>${companyDTO.account.email}</td>
                                                <td>${companyDTO.phone}</td>
                                                <td>${companyDTO.address}</td>                                           
                                                <td>
                                                    <select id="city" name="Status"  class="">                                                    
                                                        <option value="Success" class="text-success" <c:if  test="${companyDTO.is_Signed eq true}">
                                                                selected="selected"
                                                            </c:if>>
                                                            Signed
                                                        </option>                                                    
                                                        <option value="Denied" class="text-danger" <c:if  test="${companyDTO.is_Signed eq false}">
                                                                selected="selected"
                                                            </c:if>>
                                                            Not Yet
                                                        </option>
                                                    </select> 
                                                </td>

                                                <td>                                              
                                                    <input type="hidden" name="page" value="${param.page}" />
                                                    <input type="hidden" name="companyID" value="${companyDTO.companyID}" />
                                                    <input type="submit" value="Update" class="aUpdate-btn" />
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </c:if>
                            <c:if test="${empty listCompany}">
                                <h3 class="text-center" style="margin-top: 20px">
                                    Company List does not has any result!
                                </h3>
                            </c:if>
                        </div>


                        <div class="main__pagination">
                            <ul class="pagination main_cus__pagination">     
                                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                                    <c:url var="url" value="SearchCompanyAdminManagerController">
                                        <c:param name="page" value="${i}"/>
                                        <c:param name="selectCompany" value="${param.selectCompany}"/>
                                        <c:param name="txtEmail" value="${param.txtEmail}"/>
                                        <c:param name="selectStatus" value="${param.selectStatus}"/>
                                    </c:url>                       
                                        <li class="page-item">
                                            <a class="page-link" href="${url}">${i}</a>
                                        </li>
                                </c:forEach>
                            </ul>
                                
                        </div>


                    </div>
                </div>
            </div>

        </main>

        <footer class="footer">
            <div class="footer__content">
                 <i class="fa-regular fa-copyright"></i> Copyright 2022
            </div>

        </footer>

    </body>
</html>
