<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                    <li class="breadcrumb-item active" aria-current="page">Change Password</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Change Password</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
                            <div class="profile-sidebar">
                                <div class="widget-profile pro-widget-content">
                                    <div class="profile-info-widget">
                                        <a href="patient_profile?id=${sessionScope.user.ID}" class="booking-doc-img">
                                            <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image"/>
                                        </a>
                                        <div class="profile-det-info">
                                            <h3>${sessionScope.user.name}</h3>
                                            <fmt:parseDate var="p_date" value="${sessionScope.user.DOB}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="date" value="${p_date}" pattern="dd-MM-yyyy"/>
                                            <div class="patient-details">
                                                <h5><i class="fas fa-birthday-cake"></i> ${date}</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="dashboard-widget">
                                    <nav class="dashboard-menu">
                                        <ul>
                                            <li>
                                                <a href="patientprofile">
                                                    <i class="fas fa-user-cog"></i>
                                                    <span>Profile Settings</span>
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
                                            <li class="active">
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
                                    <div class="row">
                                        <div class="col-md-12 col-lg-6">
                                            <p class="text-danger">${mess}</p>
                                            <p class="text-success">${success}</p>
                                            <!-- Change Password Form -->
                                            <form action="changepassword" method="post">
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