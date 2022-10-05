
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="en">
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/doctor-header.jsp"%>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Change Password<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="Change">Change Password</a></li>
                </ol>
            </div>
        </section>

        <!-- Page Content -->
        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/doctor-sidebar.jsp"%>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Change Password</h3>
                            </div>
                            <div class="panel-body">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-12 col-lg-6">
                                                <p class="text-danger">${mess}</p>
                                                <p class="text-success">${success}</p>
                                                <!-- Change Password Form -->
                                                <form action="changepassdoctor" method="post">
                                                    <div class="form-group">
                                                        <label>Old Password <span class="text-danger">*</span></label>
                                                        <input type="password" class="form-control" name="oldPassword" value="${oldPassword}" required="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label>New Password <span class="text-danger">*</span></label>
                                                        <input type="password" class="form-control" name="newPassword" value="${newPassword}" required="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Confirm Password <span class="text-danger">*</span></label>
                                                        <input type="password" class="form-control" name="confirmPassword"  value="${confirmPassword}" required="">
                                                    </div>
                                                    <div class="submit-section">
                                                        <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                                                    </div>
                                                </form>
                                                <!-- /Change Password Form -->
                                            </div>
                                            <div class="col-md-12 col-lg-6" style="margin-top: 20px">
                                                <p class="mb-2">Password requirements</p>
                                                <p class="small text-muted mb-2">To create a new password, you have to meet all of the following requirements:</p>
                                                <ul class="small text-muted pl-4 mb-0">
                                                    <li>At least 8 characters</li>
                                                    <li>At least upper-case letter</li>
                                                    <li>At least one number</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <br><br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%@include file="resource/components/footer.jsp"%>
    <%@include file="resource/components/script.jsp"%>Ï
</body>

<!-- doccure/change-password.html  30 Nov 2019 04:12:18 GMT -->
</html>