<%-- 
    Document   : searchCompanyPost
    Created on : May 21, 2022, 2:17:48 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Company</th>
                    <th>Job</th>
                    <th>Quantity</th>
                    <th>Location</th>
                    <th>Date of Creation</th>
                    <th>Date of Expiration</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="result" value="${requestScope.LIST_RESULT}"/>
                <c:set var="nameCompany" value="${param.nameCompany}"/>
                <c:set var="nameMajor" value="${param.nameMajor}"/>
                <c:set var="nameLocation" value="${param.nameLocation}"/>
                <c:set var="page" value="${requestScope.page}"/>
            <div>
                <c:forEach begin="1" end="${requestScope.numberPage}" var="i">
                    <c:url var="url" value="SearchCompanyStudentHomeController">
                        <c:param name="page" value="${i}"/>
                        <c:param name="nameCompany" value="${nameCompany}"/>
                        <c:param name="nameMajor" value="${nameMajor}"/>
                        <c:param name="nameLocation" value="${nameLocation}"/>
                    </c:url>
                    <a href="${url}">${i}</a>
                </c:forEach>
            </div>
            <c:forEach items="${result}" var="post" varStatus="counter">
                <tr>
                    <td>
                        ${counter.count}
                    </td>
                    <td>
                        ${post.company.account.name}
                    </td>
                    <td>
                        ${post.majorName}
                    </td>
                    <td>
                        ${post.quantityIterns}
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

</body>
</html>
