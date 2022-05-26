<%-- 
    Document   : register2
    Created on : May 25, 2022, 1:59:49 PM
    Author     : Thai Quoc Toan <toantqse151272@fpt.edu.vn>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
        <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/register.css">
    </head>
    <body>
        <header class="header ">
            <div class="navbar">
                <a href="loginPage" class="header__logo ">
                    <img src="./assets/img/logo.png" alt="" class="logo">
                </a>
                <a href="loginPage" class="primary-btn ">Login</a>
            </div>
        </header>
        <c:set var="errors" value="${requestScope.ERROR_REGISTER_COMPANY}"/>
        <div class="main">
            <div class="container-fluid ">
                <div class="row ">
                    <div class="container-left offset-1 col-4">
                        <img src="./assets/img/ojt.png" alt="" class="container-left--img">
                    </div>

                    <div class="container-right offset-1 col-5">
                        <div class="header-right">COMPANY REGISTRATION</div>
                        <div class="header-right--step">STEP 2: COMPANY INFORMATION </div>
                        <div class="right-form">
                            <form action="RegisterCompanyDetailsController" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                                <input type="text" class="right-form__input" name="companyName" value="${requestScope.companyName}" placeholder="*Company Name" /> 
                                <c:if test="${not empty errors.companyNameLengthError}">
                                    <font style="color: red">
                                    <div class="text-danger">${errors.companyNameLengthError}</div>
                                    </font>
                                </c:if>

                                <select id="city" name="city"  class="right-form__input" >
                                    <option value="" disabled selected>*Choose City</option>
                                    <option value="TP.HCM" <c:if test="${requestScope.city eq 'TP.HCM'}" >
                                            selected="selected"
                                        </c:if> >TP.HCM</option>
                                    <option value="Dong Nai" <c:if test="${requestScope.city eq 'Dong Nai'}" >
                                            selected="selected"
                                        </c:if> >Dong Nai</option>
                                    <option value="Tay Ninh" <c:if test="${requestScope.city eq 'Tay Ninh'}" >
                                            selected="selected"
                                        </c:if> >Tay Ninh</option>
                                    <option value="Binh Duong" <c:if test="${requestScope.city eq 'Binh Duong'}" >
                                            selected="selected"
                                        </c:if> >Binh Duong</option>
                                </select>
                                <c:if test="${not empty errors.companyCityError}">
                                    <font style="color: red">
                                    <div class="text-danger">${errors.companyCityError}</div>
                                    </font>
                                </c:if>
                                <!--                            <div class="text-danger">Your error</div>-->
                                <input type="text" name="companyAddress" value="${requestScope.companyAddress}" class="right-form__input" placeholder="*Company Address" /> 
                                <c:if test="${not empty errors.companyAddressLengthError}">
                                    <font style="color: red">
                                    <div class="text-danger">${errors.companyAddressLengthError}</div>
                                    </font>
                                </c:if>

                                <textarea name="companyDescription" class="right-form__input righ-form-textarea" 
                                          placeholder="*Company Summary" cols="30" rows="3">${requestScope.companyDescription}</textarea>
                                <c:if test="${not empty errors.companyDescriptionLegthError}">
                                    <font style="color: red">
                                    <div class="text-danger">${errors.companyDescriptionLegthError}</div>
                                    </font>
                                </c:if>

                                <input type="text" name="companyPhone" value="${requestScope.companyPhone}" class="right-form__input" placeholder="*Phone" />
                                <c:if test="${not empty errors.companyPhoneLengthError}">
                                    <font style="color: red">
                                    <div class="text-danger">${errors.companyPhoneLengthError}</div>
                                    </font>
                                </c:if>

                                <div class="right-file-input">
                                    <label for="myfile" >
                                        *Logo:
                                        <%-- <c:if test="${not empty requestScope.companyLogo}">
                                            <img src="./avatars/${requestScope.companyLogo}" /> 
                                        </c:if> --%>
                                    </label>
                                    <input type="file" name="companyLogo" value="${requestScope.companyLogo}" id="myfile"/>
                                </div>
                                <div class="text-danger">Your error</div>


                                <div class="end-form-btn">
                                    <!--                                <a href="./register1.html" class="back-btn primary-btn">Back</a>-->
                                    <input type="hidden" name="companyAccount" value="${verifyEmail}" />
                                    <input type="hidden" name="email" value="${email}" />
                                    <input type="hidden" name="password" value="${password}" />
                                    <input type="submit" class=" primary-btn " name="btAction" value="Sign Up" />
                                </div>

                            </form>
                        </div>
                    </div>
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
