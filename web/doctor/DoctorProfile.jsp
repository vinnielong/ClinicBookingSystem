<%-- 
    Document   : DoctorProfile
    Created on : Mar 12, 2022, 6:05:58 AM
    Author     : hunglx
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/doctor-header.jsp"%>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>User Profile<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="doctorprofile">User Profile</li>
                </ol>
            </div>
        </section>

        <!-- Page Content -->
        <section id="main">
            <div class="container">
                <div class="row">

                    <div class="col-md-3">
                        <div class="list-group">
                            <div class="widget-profile pro-widget-content">
                                <div class="profile-info-widget">
                                    <a href="#" class="booking-doc-img">
                                        <c:if test="${sessionScope.user.image != null && not empty sessionScope.user.image}">
                                            <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image">
                                        </c:if>
                                        <c:if test="${sessionScope.user.image == null}">
                                            <img src="resource/img/NoAvartar.jpg" alt=""/>
                                        </c:if>
                                    </a>
                                    <div class="profile-det-info">
                                        <h5>Dr. ${sessionScope.user.doctorName}</h5>
                                    </div>
                                    <div class="patient-details">
                                        <h5 class="mb-0">${sessionScope.user.speciality.settingname}</h5>
                                    </div>
                                </div>
                                <div>                                              
                                    <div style="text-align: center;">
                                        <form action="doctorprofile?choice=2" method="post" enctype="multipart/form-data">
                                            <input type="hidden" value="2" name="choice""/>
                                            <input class="" name="avatar" type="file" style="margin: 20px 0px 10px 39px;">
                                            <input class="btn btn-default" type="submit"" value="Update Avatar"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <a href="index.html" class="list-group-item active main-color-bg">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Navigation
                                </a>
                                <a href="doctorprofile" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> User Profile</a>
                                <a href="mypatient" class="list-group-item"><span class="glyphicon glyphicon-bed" aria-hidden="true"></span> My Patients</a>
                                <a href="myappointment" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> My Appointments</a>
                                <a href="myfeedback" class="list-group-item"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> My Feedback</a>
                                <a href="Change" class="list-group-item"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> Change Password</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-9">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Doctor Profile</h3>
                            </div>
                            <div class="panel-body">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <p class="text-danger">${fail}</p>
                                                <p class="text-success">${success}</p>
                                            </div>
                                            <form action="doctorprofile" method="post">
                                                <input type="hidden" value="1" name="choice"/>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Name <span class="text-danger">*</span></label>
                                                        <input type="text" class="form-control" value="${sessionScope.user.doctorName}" name="name" required="" />

                                                        
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Gender <span class="text-danger">*</span></label><br />
                                                        <div style="display: flex;">
                                                            <div style="margin-right: 30px">
                                                                <input type="radio" id="male" name="gender" value="male" ${sessionScope.user.gender == 1 ? 'checked' : ''}>
                                                                  <label for="male">Male</label> 
                                                            </div>
                                                            <div> 
                                                                <input type="radio" id="female" name="gender" value="female" ${sessionScope.user.gender == 0 ? 'checked' : ''}>
                                                                  <label for="female">Female</label>
                                                            </div>                                                        
                                                        </div>                                                  
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Date of Birth <span class="text-danger">*</span></label>
                                                        <div class="cal-icon">
                                                            <input name="dob" type="date" class="form-control" value="${sessionScope.user.dob}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Email <span class="text-danger">*</span></label>
                                                        <input name="email" type="email" class="form-control" value="${sessionScope.user.email}" readonly=""/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Mobile Phone <span class="text-danger">*</span></label>
                                                        <input name="phone" type="text" value="${sessionScope.user.phone}" class="form-control" pattern="\d{8,10}$"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-12" style="margin-bottom: 20px;">
                                                    <label>Description</label>
                                                    <textarea name="description" class="form-control" rows="5">${sessionScope.user.description}</textarea>
                                                </div>
                                                <div class="col-md-12" style="">
                                                    <input class="btn btn-default" type="submit" style="float: right;" value="Update Profile"/>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="resource/components/footer.jsp"%>
        <%@include file="resource/components/script.jsp"%>
    </body>
</html>
