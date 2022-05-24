<%-- 
    Document   : registerCompanyPage2
    Created on : May 23, 2022, 8:44:34 PM
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
        <c:set var="errors" value="${requestScope.ERROR_REGISTER_COMPANY}"/>
        <form action="RegisterCompanyDetailsController" method="POST" enctype="multipart/form-data">
            <c:if test="${not empty errors.companyNameLengthError}">
                <font style="color: red">
                    ${errors.companyNameLengthError}<br />
                </font>
            </c:if>
                Company Name: <input type="text" name="companyName" value="${requestScope.companyName}"  placeholder="*Company Name"> <br />
            City: <select name="city"   >
                <option value="TP.HCM" <c:if test="${requestScope.city eq 'TP.HCM'}" >
                        selected="selected"
                </c:if> >TP.HCM</option>
                <option value="Dong Nai" <c:if test="${requestScope.city eq 'Dong Nai'}" >
                        selected="selected"
                </c:if> >Đồng Nai</option>
                <option value="Tay Ninh" <c:if test="${requestScope.city eq 'Tay Ninh'}" >
                        selected="selected"
                </c:if> >Tây Ninh</option>
                <option value="Binh Duong" <c:if test="${requestScope.city eq 'Binh Duong'}" >
                        selected="selected"
                </c:if> >Bình Dương</option>
            </select><br />
            <c:if test="${not empty errors.companyAddressLengthError}">
                <font style="color: red">
                    ${errors.companyAddressLengthError}<br />
                </font>
            </c:if>
                Address: <input type="text" name="companyAddress" value="${requestScope.companyAddress}" placeholder="*Company Address"> <br />
            <c:if test="${not empty errors.companyDescriptionLegthError}">
                <font style="color: red">
                    ${errors.companyDescriptionLegthError}<br />
                </font>
            </c:if>
                Description: <textarea name="companyDescription" value="${requestScope.companyDescription}" placeholder="*Company Summary" cols="30" rows="3">${requestScope.companyDescription}</textarea><br />
            <c:if test="${not empty errors.companyPhoneLengthError}">
                <font style="color: red">
                    ${errors.companyPhoneLengthError}<br />
                </font>
            </c:if>
                Phone<input type="text" name="companyPhone" value="${requestScope.companyPhone}"  placeholder="*Phone"><br />
            <c:if test="${not empty errors.companyLogoLenthError}">
                <font style="color: red">
                    ${errors.companyLogoLenthError}<br />
                </font>
            </c:if>
            <c:if test="${not empty requestScope.companyLogo}">
                <img src="./avatars/${requestScope.companyLogo}" /> 
            </c:if>
            Logo: <input type="file"  name="companyLogo" >
            <input type="submit" name="btAction"  value="Sign Up">
        </form>
<%--        <form method="POST">
            <input type="hidden" name="email" value="${param.email}" />
            <input type="hidden" name="password" value="${param.password}" />
            <input type="hidden" name="confirmPassword" value="${param.confirmPassword}" />
            <input type="submit" value="Back" name="btAction" />
        </form> --%>
    </body>
</html>
