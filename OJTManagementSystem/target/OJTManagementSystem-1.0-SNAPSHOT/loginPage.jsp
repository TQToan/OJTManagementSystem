<%-- 
    Document   : loginPage
    Created on : May 25, 2022, 9:33:41 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="loginAccountsController" method="POST">
            <c:set var="error" value="${requestScope.ERROR}"></c:set>
            <c:if test="${not empty error}">
                <font color="red">
                ${error.accountError}
                </font>
            </c:if> <br/>
            Enter your email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <c:if test="${not empty error}">
                <font color="red">
                ${error.userEmailEmpty}
                </font>
            </c:if><br/>
            Enter your password: <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty error}">
                <font color="red">
                ${error.userPasswordEmpty}
                </font>
            </c:if> <br/>
            <!--<input type="checkbox" name="AutoLogin" value="ON" /> Auto login <br/>-->
            <input type="submit" value="Login" />
        </form>
        <c:url var="loginGoogle" value="https://accounts.google.com/o/oauth2/auth">
            <c:param name="scope" value="email profile openid"/>
            <c:param name="redirect_uri" value="http://localhost:8080/OJTManagementSystem/LoginGoogleServlet"/>
            <c:param name="response_type" value="code"/>
            <c:param name="client_id" value="206603271676-8up4i9nf258hmlcol6khi7hcfkf7ibcn.apps.googleusercontent.com"/>
            <c:param name="approval_prompt" value="force"/>
        </c:url>
        <a href="${loginGoogle}">Login email @fpt.edu.vn</a>
    </body>
</html>
