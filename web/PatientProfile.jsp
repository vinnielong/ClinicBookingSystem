<%-- 
    Document   : PatientProfile
    Created on : Mar 12, 2022, 6:06:09 AM
    Author     : hunglx
--%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp" %>
    <body>
        <div class="main-wrapper">
            <%@include file="resource/components/header.jsp" %>
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="homepage">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Profile Settings</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Profile Settings</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Profile Sidebar -->
                        <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
                            <div class="profile-sidebar">
                                <div class="widget-profile pro-widget-content">
                                    <div class="profile-info-widget">
                                        <a href="patient_profile?id=${sessionScope.user.ID}" class="booking-doc-img">
                                            <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image" />
                                        </a>
                                        <div class="profile-det-info">
                                            <h3>${sessionScope.user.name}</h3>
                                            <fmt:parseDate var="p_date" value="${sessionScope.user.DOB}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="date" value="${p_date}" pattern="dd/MM/yyyy"/>
                                            <div class="patient-details">
                                                <h5><i class="fas fa-birthday-cake"></i> ${date}</h5>
                                            </div>
                                        </div>
                                        <div>
                                            <form action="patientprofile?choice=2" method="post" enctype="multipart/form-data">
                                                <input type="file" name="avatar" class="change-photo-btn upload btn-icon" style="margin-top: 29px;margin-bottom: 10px;">
                                                <br>
                                                <input type="hidden" value="2" name="choice" "/>
                                                <button type="submit" class="btn btn-primary change-photo-btn"">
                                                    Update Avatar
                                                </button>   
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="dashboard-widget">
                                    <nav class="dashboard-menu">
                                        <ul>
                                            <li class="active">
                                                <a href="patientprofile">
                                                    <i class="fas fa-user-cog"></i>
                                                    <span>User Profile</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="appointmenthistory">
                                                    <i class="fas fa-calendar-check"></i>
                                                    <span>Appointment History</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="reservationhistory">
                                                    <i class="fas fa-columns"></i>
                                                    <span>Reservation History</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="changepassword">
                                                    <i class="fas fa-lock"></i>
                                                    <span>Change Password</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="logout">
                                                    <i class="fas fa-sign-out-alt"></i>
                                                    <span>Logout</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-7 col-lg-8 col-xl-9">
                            <div class="card">
                                <div class="card-body">
                                    <p class="text-danger">${fail}</p>
                                    <p class="text-success">${success}</p>
                                    <form action="patientprofile" method="post">
                                        <input type="hidden" value="1" name="choice"/>
                                        <div class="row form-row">
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Name <span class="text-danger">*</span></label>
                                                    <input type="text" class="form-control" value="${sessionScope.user.name}" name="name" />
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Gender <span class="text-danger">*</span></label><br />
                                                    <div style="display: flex;margin-top: 15px;font-size: 16px">
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
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Date of Birth <span class="text-danger">*</span></label>
                                                    <div class="cal-icon">
                                                        <input name="dob" type="date" class="form-control" value="${sessionScope.user.DOB}"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Email <span class="text-danger">*</span></label>
                                                    <input name="email" type="email" class="form-control" value="${sessionScope.user.email}" readonly=""/>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Mobile Phone <span class="text-danger">*</span></label>
                                                    <input name="phone" type="text" value="${sessionScope.user.phone}" class="form-control" pattern="\d{8,10}$"/>
                                                    <span class="form-text text-muted">Phone number must be between 8 to 10 digits</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="submit-section">
                                            <button type="submit" class="btn btn-primary submit-btn">
                                                Save changes
                                            </button>                                            
                                        </div>
                                    </form>
                                </div>
                            </div>                         
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="resource/components/footer.jsp" %>
        </div>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
