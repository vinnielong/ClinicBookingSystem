<%-- 
    Document   : VerifyEmail
    Created on : Feb 7, 2022, 10:49:12 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>
    <body class="account-page">

        <!-- Main Wrapper -->
        <div class="main-wrapper">
            <!-- Header -->
            <%@include file="resource/components/header.jsp" %>
            <!-- /Header -->
            <!-- Page Content -->
            <div class="content">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-md-8 offset-md-2">

                            <!-- Account Content -->
                            <div class="account-content">
                                <div class="row align-items-center justify-content-center">
                                    <div class="col-md-7 col-lg-6 login-left">	
                                        <img src="resource/images/login-banner.png" class="img-fluid" alt="Login Banner"/>
                                    </div>
                                    <div class="col-md-12 col-lg-6 login-right">
                                        <div class="login-header">
                                            <h3>Verify Account</h3>
                                            <p class="text-danger" style="color: red;">${mess}</p>
                                        </div>

                                        <!-- Forgot Password Form -->
                                        <form action="emailverify" method="post">
                                            <div class="form-group form-focus">
                                                <input type="text" name="authcode" class="form-control floating">
                                                <label class="focus-label">Code</label>
                                            </div>
                                            <button class="btn btn-primary btn-block btn-lg login-btn" type="submit">Verify</button>
                                        </form>
                                        <!-- /Forgot Password Form -->

                                    </div>
                                </div>
                            </div>
                            <!-- /Account Content -->

                        </div>
                    </div>

                </div>

            </div>		
            <!-- /Page Content -->
            <!-- Footer -->
            <%@include file="resource/components/footer.jsp" %>
            <!-- /Footer -->
        </div>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
