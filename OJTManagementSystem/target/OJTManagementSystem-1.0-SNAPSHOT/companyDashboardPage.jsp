<%-- 
    Document   : companyDashboard
    Created on : May 25, 2022, 9:29:19 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Dashboard</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <c:if test="${not empty account}">
            ${account.email}
            ${account.name}
        </c:if>
    </body>
</html>
