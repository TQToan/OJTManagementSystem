<%-- 
    Document   : registerCompanyPage1
    Created on : May 23, 2022, 5:28:22 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <c:set var="errors" value="${requestScope.ERROR_REGISTER}" />
        <form action="RegisterCompanyController" method="POST">
            <c:if test="${not empty errors.emailFormatError}">
                <font style="color: red">
                ${errors.emailFormatError}<br />
                </font>
            </c:if>
            <c:if test="${not empty errors.emailDuplicateError}">
                <font style="color: red">
                ${errors.emailDuplicateError}<br />
                </font>
            </c:if>
            Email: <input type="text" name="email" value="${param.email}"  /><br />
            <c:set var="email" value="${param.email}" />
            <c:if test="${not empty errors.passwordLengthError}">
                <font style="color: red">
                ${errors.passwordLengthError}<br />
                </font>
            </c:if>
            password: <input type="password" name="password" value="${param.password}" /><br />
            <c:set var="password" value="${param.password}" />
            <c:if test="${not empty errors.passwordConfirmError}">
                <font style="color: red">
                ${errors.passwordConfirmError}<br />
                </font>
            </c:if>
            Re-password: <input type="password" name="confirmPassword" value="${param.confirmPassword}" /><br />
            <c:set var="confirmPassword" value="${param.confirmPassword}" />
            <input type="submit" value="Send Email To Verification" name="btAction"
                   <c:if test="${not empty sessionScope.VERIFY_EMAIL}" var="verify"> 
                       hidden="hidden"
                   </c:if> />
        </form>
        <c:set var="verifyEmail" value="${sessionScope.VERIFY_EMAIL}"/>
        <c:if test="${not empty verifyEmail}">
            <form action="EmailVerificationController" method="POST">
                <c:if test="${not empty requestScope.SUCCESS_VERIFY}">
                    <font style="color: green">
                    ${requestScope.SUCCESS_VERIFY}<br />
                    </font>
                </c:if>
                <c:if test="${not empty requestScope.FAIL_VERIFY}">
                    <font style="color: red">
                    ${requestScope.FAIL_VERIFY}<br />
                    </font>
                </c:if>
                Verification: <input type="text" name="varification" value="${param.varification}" />
                <input type="hidden" name="email" value="${email}" />
                <input type="hidden" name="password" value="${password}" />
                <input type="hidden" name="confirmPassword" value="${confirmPassword}" />
                <input type="submit" value="Verify Email" name="btAction" />
            </form>
        </c:if>
        <form action="RegisterCompanyPage2JSP" method="POST">
            <input type="hidden" name="email" value="${email}" />
            <input type="hidden" name="password" value="${password}" />
            <input type="hidden" name="confirmPassword" value="${confirmPassword}" />
            <input type="submit" value="Next" name="btAction" />
        </form>
    </body>
</html>
