<%-- 
    Document   : companyProfile
    Created on : Jun 13, 2022, 9:04:37 AM
    Author     : ThanhTy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company - Profile</title>
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1-web/css/all.min.css">
    <link rel="stylesheet" href="./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="./assets/css/company.css">
    <link rel="stylesheet" href="./assets/css/company-responsive.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

    <main class="row">
        <c:set var="companyProfile" value="${requestScope.COMPANY_PROFILE}"/>
        <nav class="col-xl-2  nav-fixed col-md-3">
            <a href="companyDashboard.html" class="nav__logo ">
                <img src="./assets/img/logo.png" alt="" class="nav--logo">
            </a>
            <a href="CompanyShowProfileController" class=" nav__infor--link text-truncate">
                <i class="fas fa-user-circle nav__infor--icon"></i>
                ${companyProfile.account.name}
            </a>

            <ul class="nav__content">
                <li class="nav__items">
                    <a href="companyDashboard.html" class="nav__item--link">
                        <i class="fas fa-palette "></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav__items">
                    <a href="CompanyShowProfileController" class="nav__item--link">
                        <i class="fas fa-user-edit"></i>
                        My Profile
                    </a>
                </li>
                <li class="nav__items">
                    <a href="CompanyShowPostController" class="nav__item--link">
                        <i class="fas fa-pen"></i>
                        My Posts
                    </a>
                </li>
                <li class="nav__items">
                    <a href="companyInternsManage.html" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Interns Management
                    </a>
                </li>
                <li class="nav__items">
                    <a href="companyApplManage.html" class="nav__item--link">
                        <i class="fas fa-poll-h"></i>
                        Internship Application
                    </a>
                </li>
                <li class="nav__items">
                    <a href="login.html" class="nav__item--link">
                        <i class="fas fa-power-off"></i>
                        Logout
                    </a>
                </li>
            </ul>

        </nav>
        <div class="main-body offset-xl-2 col-xl-10 offset-md-3 col-md-9 col-12">
            
            <c:set var="errors" value="${requestScope.ERROR_UPDATE_COMPANYPROFILE}"/>
            <div class="row">
                <div class="main-body-cprofile offset-xl-3 col-xl-6 offset-2 col-8">
                    <div class="main-body-cprofile__header">
                        Company Profile*
                    </div>
                    <form action="CompanyUpdateProfileController" method="POST" enctype="multipart/form-data">
                        <div class="cprofile__input row">
                            <label class="col-4 cprofile--label" for="cName">Company's Name</label>
                            <div class="col-8  profile--input-none-hover ">${companyProfile.account.name}</div>
                            
                        </div>

                        <div class="cprofile__input row">
                            <label class="col-4 cprofile--label" for="address">Address</label>
                            <input type="text" class="col-8 cprofile--input " name="addressUpdate" id="address" value="${companyProfile.address}">
                            <h5 class="text-danger offset-4 col-8 text-start ">
                                <c:if test="${not empty errors.companyAddressLengthError}">
                                    ${errors.companyAddressLengthError}
                                </c:if>
                            </h5>
                        </div>
                        <div class="cprofile__input row">
                            <label class="col-4 cprofile--label" for="city">City</label>

                            <select id="city" name="cityUpdate"  class="col-8 cprofile--input " >
                                <option value="" disabled selected>Choose City</option>
                                <option value="TP.HCM" <c:if test="${companyProfile.city eq 'TP.HCM'}" >
                                        selected="selected"
                                    </c:if> >TP.HCM</option>
                                <option value="Dong Nai" <c:if test="${companyProfile.city eq 'Dong Nai'}" >
                                        selected="selected"
                                    </c:if> >Dong Nai</option>
                                <option value="Tay Ninh" <c:if test="${companyProfile.city eq 'Tay Ninh'}" >
                                        selected="selected"
                                    </c:if> >Tay Ninh</option>
                                <option value="Binh Duong" <c:if test="${companyProfile.city eq 'Binh Duong'}" >
                                        selected="selected"
                                    </c:if> >Binh Duong</option>
                            </select>
                            <h5 class="text-danger offset-4 col-8 text-start ">
                                <c:if test="${not empty errors.companyCityError}">
                                    ${errors.companyCityError}
                                </c:if>
                            </h5>
                        </div>
                        <div class="cprofile__input row">
                            <label class="col-4 cprofile--label" for="email">Contaxt Email</label>
                            <input type="email" readonly class="col-8 cprofile--input " name="email" id="email" value="${companyProfile.account.email}">
                        </div>
                        <div class="cprofile__input row">
                            <label class="col-4 cprofile--label" for="phone">Phone</label>
                            <input type="text" class="col-8 cprofile--input " name="phoneUpdate" id="phone" value="${companyProfile.phone}">
                            <h5 class="text-danger offset-4 col-8 text-start ">
                                <c:if test="${not empty errors.companyPhoneLengthError}">
                                    ${errors.companyPhoneLengthError}
                                </c:if>
                            </h5>
                        </div>
                        <div class="cprofile__input row">
                            <label class="col-12 cprofile--label" for="descript">Company Description</label>
                            <textarea name="descriptUpdate" class="col-12 cprofile--input cprofile--input-textarea" id="descript" cols="30" rows="4" >
                                ${companyProfile.company_Description}
                            </textarea>
                            <h5 class="text-danger  text-start ">
                                <c:if test="${not empty errors.companyDescriptionLegthError}">
                                    ${errors.companyDescriptionLegthError}
                                </c:if>
                            </h5>
                        </div>
                        <div class=" cprofile-file-input">
                            <label for="avatar" >
                                Logo:
                                <div class="input-file">
                                    <input type="file" name="avatar" class="col-8" value="" id="avatar" />
                                </div>
                            </label>

                        </div>
                        <h5 class="text-danger  text-start ">
                            <c:if test="${not empty errors.companyLogoLengthError}">
                                <h5 class="text-danger offset-4 col-8 text-start">
                                    ${errors.companyLogoLengthError}
                                </h5>
                            </c:if>
                        </h5>
                        <div class="cprofile-edit-btn primary-btn">
                            <i class="fas fa-edit"></i>
                            <input type="submit" class="cprofile-edit--input" value="Edit">
                        </div>
                    </form>
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
