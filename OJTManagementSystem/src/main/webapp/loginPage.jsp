<%-- 
    Document   : loginPage1
    Created on : May 25, 2022, 9:02:48 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/login.css">
    </head>
    <body>
        <header class="header ">

            <div class="navbar">
                <a href="#" class="header__logo ">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>

            </div>
        </header>

        <div class="main">

            <h1 class="heading">Welcome to FPT_OJT</h1>
            <div class="row main-body">
                <div class="container-left offset-1 col-4">
                    <h2 class="container__heading">*Company</h2>
                    <div class="heading-btn">
                        <a href="#" class="heading-btn--btn">Login</a>

                        <a href="RegisterPage1" class="heading-btn--btn">Sign Up</a>
                    </div>

                    <div class="form">
                        <form action="loginAccountsController" method="post">
                            <h5 class="text-danger">
                                <c:set var="error" value="${requestScope.ERROR}"></c:set>
                                <c:if test="${not empty error}">
                                    <font color="red">
                                    ${error.accountError}
                                    </font>
                                </c:if>
                            </h5>
                            <input type="text" class="form-input" name="txtEmail" value="${param.txtEmail}" placeholder="Enter your email"> <br>
                            <h5 class="text-danger">
                                <c:if test="${not empty error}">
                                    <font color="red">
                                    ${error.userEmailEmpty}
                                    </font>
                                </c:if>
                            </h5>
                            <input type="password" class="form-input" name="txtPassword" value="" placeholder="Enter your password">
                            <h5 class="text-danger">
                                <c:if test="${not empty error}">
                                    <font color="red">
                                    ${error.userPasswordEmpty}
                                    </font>
                                </c:if>
                            </h5>
                            <!-- <div class="checkbox-btn">
                                <input type="checkbox" id="auto-fill">
                                <label for="auto-fill">
                                    Auto Login
                                </label>
                            </div> -->

                            <input type="submit" value="Login" class="login-btn">
                        </form>
                    </div>
                </div>

                <div class="container-right offset-1 col-5">
                    <h2 class="container__heading">*FPT University</h2>
                    <p class="container__content">
                        Job information page for FPT university students: <br>
                        - More than 60 companies <br>
                        - Many Professions in all fields <br>
                        - Moderated by the school
                    </p>

                    <c:url var="loginGoogle" value="https://accounts.google.com/o/oauth2/auth">
                        <c:param name="scope" value="email profile openid" />
                        <c:param name="redirect_uri" value="http://localhost:8080/OJTManagementSystem/LoginGoogleServlet" />
                        <c:param name="response_type" value="code" />
                        <c:param name="client_id"
                                 value="206603271676-8up4i9nf258hmlcol6khi7hcfkf7ibcn.apps.googleusercontent.com" />
                        <c:param name="approval_prompt" value="force" />
                    </c:url>
                    <h5 class="text-danger text-center">
                        <c:set var="error" value="${requestScope.ERROR}"></c:set>
                        <c:if test="${not empty error}">
                            <font color="red">
                            ${error.userEmailNotAllow}
                            </font>
                        </c:if>
                    </h5>
                    <a href="${loginGoogle}" class="google-btn row">

                        <i class="fab fa-google google-logo col-2 "></i>
                        <span class="google-content col-10 ">Login email @fpt.edu.vn</span>
                    </a>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="footer__content">
                @copyright 2022
            </div>
        </footer>
    </body>
</html>