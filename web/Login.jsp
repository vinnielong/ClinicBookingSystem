<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>

    <body class="account-page">
        <div class="main-wrapper">
            <!-- Header -->
            <%@include file="resource/components/header.jsp" %>
            <!-- /Header -->
            <!-- Page Content -->
            <div class="content">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-md-8 offset-md-2">

                            <!-- Login Tab Content -->
                            <div class="account-content">
                                <div class="row align-items-center justify-content-center">
                                    <div class="col-md-7 col-lg-6 login-left">
                                        <img src="resource/images/login-banner.png" class="img-fluid" alt="Doccure Login"/>
                                    </div>
                                    <div class="col-md-12 col-lg-6 login-right">
                                        <div class="login-header">
                                            <h3>Login <span>Doccure</span></h3>
                                        </div>
                                        <form action="login" method="post">
                                            <p class="text-danger" style="color: red;">${mess}</p>
                                            <div class="form-group form-focus">
                                                <input type="text" name="username" class="form-control floating" required="">
                                                <label class="focus-label">Email</label>
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="password" name="password" class="form-control floating">
                                                <label class="focus-label">Password</label>
                                            </div>
                                            <div class="text-right">
                                                <a class="forgot-link" href="ForgotPassword.jsp">Forgot Password ?</a>
                                            </div>
                                            <button class="btn btn-primary btn-block btn-lg login-btn" type="submit">Login</button>
                                            <div class="text-center dont-have">Don’t have an account? <a href="Register.jsp">Register</a></div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- /Login Tab Content -->

                        </div>
                    </div>

                </div>

            </div>		
            <!-- /Page Content -->
            <!-- Footer -->
            <%@include file="resource/components/footer.jsp" %>
            <!-- /Footer -->
        </div>


        <!-- /Main Wrapper -->
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>