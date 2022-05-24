<%-- 
    Document   : studentHomePage
    Created on : May 20, 2022, 4:26:34 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Home Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="SearchCompanyStudentHomeController">
            <select name="nameCompany">
                <option value="">Company Name</option>
                <c:forEach items="${requestScope.COMPANY_NAME}" var="company">
                    <option value="${company.companyID}">${company.account.name}</option>
                </c:forEach>
            </select>
            <select name="nameMajor">
                <option value="">Major</option>
                <c:forEach items="${requestScope.LIST_NAME_MAJOR}" var="major">
                    <option value="${major.majorID}">${major.majorName}</option>
                </c:forEach>
            </select>
            <select name="nameLocation">
                <option value="">Location</option>
                <option value="TPHCM">TPHCM</option>
                <option value="Đồng Nai">Đồng Nai</option>
                <option value="Cần Thơ">Cần Thơ</option>
                <option value="Bình Dương">Bình Dương</option>
            </select>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:forEach items="${requestScope.LIST_POST_HOME}" var="dto">
            <div>
                <div>
                    ${dto.title_Post}
                </div>
                <div>
                    ${dto.workLocation}
                </div>
                <div>
                    ${dto.expirationDate}
                </div>
                <div>
                    ${dto.quantityIterns}
                </div>
                <div>
                    ${dto.company.account.avatar}
                </div>
            </div>

        </c:forEach>
        <c:forEach items="${requestScope.LIST_AVATAR_SIGNED_COMPANY}" var="avatar">
            ${avatar.companyID}
            ${avatar.account.avatar}
        </c:forEach>

    </body>
</html>
