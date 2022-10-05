<%-- 
    Document   : Register
    Created on : Jan 15, 2022, 2:51:46 PM
    Author     : Admin
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

                            <!-- Register Content -->
                            <div class="account-content">
                                <div class="row align-items-center justify-content-center">
                                    <div class="col-md-7 col-lg-6 login-left">
                                        <img src="resource/images/login-banner.png" class="img-fluid" alt="Doccure Register"/>
                                    </div>
                                    <div class="col-md-12 col-lg-6 login-right">
                                        <div class="login-header">
                                            <c:if test="${role==null}">
                                                <h3>Patient Register <a href="registration?role=2">Are you a Doctor?</a></h3>
                                            </c:if>
                                            <c:if test="${role==2}">
                                                <h3>Doctor Register <a href="registration">Not a Doctor?</a></h3>
                                            </c:if>
                                        </div>

                                        <!-- Register Form -->
                                        <form action="registration" method="post">
                                            <p class="text-danger" style="color: red;">${mess}</p>
                                            <input type="hidden" name="role" value="${role==null?3:2}"/>
                                            <div class="form-group form-focus">
                                                <input type="text" Name="fullname1" value="${fullname}" required="" class="form-control floating">
                                                <label class="focus-label">Name</label>
                                            </div>
                                            <div style="margin-bottom: 22px;">
                                                Gender
                                                <input type="radio" style="margin-left: 55px;" Name="gender1" value="male" ${gender!=0?"checked":""}>Male
                                                <input type="radio" style="margin-left: 55px;" Name="gender1" value="female" ${gender==0?"checked":""}>Female
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="email" Name="email1" value="${email}" required="" class="form-control floating">
                                                <label class="focus-label">Email</label>
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="text" class="form-control floating" name="phonenumber1" value="${phone}" required="">
                                                <label class="focus-label">Mobile Number</label>
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="text" class="form-control floating" name="username1" value="${username}" required>
                                                <label class="focus-label">User Name</label>
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="password" class="form-control floating" name="psw1" value="${password}" id="password" onkeyup='check();' required="">
                                                <label class="focus-label">Create Password</label>
                                            </div>
                                            <div class="form-group form-focus">
                                                <input type="password" class="form-control floating" name="psw-repeat1" value="${repass}" id="confirm_password" onkeyup='check();' required>
                                                <label class="focus-label">Confirm Password</label>
                                            </div>
                                            <br><span id='message'></span>
                                            <div class="text-right">
                                                <a class="forgot-link" href="Login.jsp">Already have an account?</a>
                                            </div>
                                            <button class="btn btn-primary btn-block btn-lg login-btn" type="submit">Signup</button>

                                        </form>
                                        <!-- /Register Form -->

                                    </div>
                                </div>
                            </div>
                            <!-- /Register Content -->

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
        <script>
            var check = function () {
                if (document.getElementById('password').value ===
                        document.getElementById('confirm_password').value) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'matching';
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'not matching';
                }
            }
        </script>
    </body>

</html>
