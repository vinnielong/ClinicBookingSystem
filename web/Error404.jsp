<%-- 
    Document   : Error404-jsp
    Created on : Mar 4, 2022, 5:50:16 PM
    Author     : vinnielong
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Doccure - Error 404</title>

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="resource/images/favicon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="resource/css/bootstrap.min.css">

        <!-- Fontawesome CSS -->
        <link href="resource/plugins/fontawesome/css/fontawesome.min.css" rel="stylesheet"/>

        <!-- Main CSS -->
        <link rel="stylesheet" href="resource/css/style.css">


        <style>
            .error-page {
                align-items: center;
                color: #1f1f1f;
                display: flex;
            }
            .error-page .main-wrapper {
                display: flex;
                flex-wrap: wrap;
                height: auto;
                justify-content: center;
                width: 100%;
                min-height: unset;
            }
            .error-box {
                margin: 0 auto;
                max-width: 480px;
                padding: 1.875rem 0;
                text-align: center;
                width: 100%;
            }
            .error-box h1 {
                color: #00d0f1;
                font-size: 10em;
            }
            .error-box p {
                margin-bottom: 1.875rem;
            }
            .error-box .btn {
                border-radius: 50px;
                font-size: 18px;
                font-weight: 600;
                min-width: 200px;
                padding: 10px 20px;
            }
            .btn-primary {
                background-color: #00d0f1;
                border: 1px solid #00d0f1;
            }
            .btn-primary:hover,
            .btn-primary:focus,
            .btn-primary.active,
            .btn-primary:active,
            .open > .dropdown-toggle.btn-primary {
                background-color: #19c1dc;
                border: 1px solid #19c1dc;
            }
            .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
                color: #fff;
                background-color: #00d0f1;
            }
            .btn-primary.active.focus,
            .btn-primary.active:focus,
            .btn-primary.active:hover,
            .btn-primary.focus:active,
            .btn-primary:active:focus,
            .btn-primary:active:hover,
            .open > .dropdown-toggle.btn-primary.focus,
            .open > .dropdown-toggle.btn-primary:focus,
            .open > .dropdown-toggle.btn-primary:hover {
                background-color: #19c1dc;
                border: 1px solid #19c1dc;
            }
            .btn-primary.active:not(:disabled):not(.disabled),
            .btn-primary:active:not(:disabled):not(.disabled),
            .show > .btn-primary.dropdown-toggle {
                background-color: #19c1dc;
                border-color: #19c1dc;
                color: #fff;
            }
            .btn-primary.active:focus:not(:disabled):not(.disabled),
            .btn-primary:active:focus:not(:disabled):not(.disabled),
            .show > .btn-primary.dropdown-toggle:focus {
                box-shadow: unset;
            }
            .btn-primary.disabled, .btn-primary:disabled {
                background-color: #00d0f1;
                border-color: #00d0f1;
                color: #fff;
            }
        </style>
    </head>
    <body class="error-page">

        <!-- Main Wrapper -->
        <div class="main-wrapper">

            <div class="error-box">
                <h1>404</h1>
                <h3 class="h2 mb-3">Oops! Page not found!</h3>
                <p class="h4 font-weight-normal">The page you requested was not found.</p>
                <a 
                    <c:choose>
                        <c:when test="${sessionScope.acc.role == 1 || sessionScope.acc.role == 4 ||sessionScope.acc.role == 5}">
                            href="/SWP391G2/admin/dashboard"
                        </c:when>
                        <c:when test="${sessionScope.acc.role == 2}">
                            href="/SWP391G2/doctor/myappointment"
                        </c:when>
                        <c:otherwise>
                            href="homepage"
                        </c:otherwise>
                    </c:choose>
                    class="btn btn-primary">Back to Home
                </a>
            </div>

        </div>
        <!-- /Main Wrapper -->

    </body>

</html>
