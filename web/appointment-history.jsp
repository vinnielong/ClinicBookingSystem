<%-- 
    Document   : patient_dashboard
    Created on : Nov 1, 2021, 11:47:36 AM
    Author     : admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>
    <body>
        <%@include file="resource/components/header.jsp" %>
        <div class="breadcrumb-bar">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-md-12 col-12">
                        <nav aria-label="breadcrumb" class="page-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="home">Home</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    Appointments History
                                </li>
                            </ol>
                        </nav>
                        <h2 class="breadcrumb-title">Appointment History</h2>
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
                                    <a href="patientprofile" class="booking-doc-img">
                                        <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image" />
                                    </a>
                                    <div class="profile-det-info">
                                        <h3>${sessionScope.user.name}</h3>
                                        <div class="patient-details">
                                            <fmt:parseDate var="p_date" value="${sessionScope.user.DOB}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="date" value="${p_date}" pattern="dd-MM-yyyy"/>
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
                                        <li class="active">
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
                                <div class="tab-content pt-0">
                                    <div id="pat_appointments" class="tab-pane fade show active">
                                        <div class="card card-table mb-0">
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <table class="datatable table table-hover table-center mb-0 "> 
                                                        <thead>
                                                            <tr>
                                                                <th>Appointment ID</th>
                                                                <th>Doctor Avatar</th>
                                                                <th>Doctor Name</th>
                                                                <th>Doctor ID</th>
                                                                <th>Date/Time</th>
                                                                <th>Status</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${res}" var="res">
                                                                <tr>
                                                                    <td>${res.ID}</td>
                                                                    <td>
                                                                        <h2 class="table-avatar">
                                                                            <a class="avatar avatar-sm mr-2">
                                                                                <img class="avatar-img rounded-circle" src="data:image/jpeg;base64,${res.doctor.imageString}" alt="Doctor Image" style="width: 150%;height: 150%;margin-left: 20px;margin-top: -10px;">
                                                                            </a>
                                                                        </h2>
                                                                    </td>
                                                                    <td>Dr. ${res.doctor.doctorName}</td>
                                                                    <td>${res.doctor.id}</td>
                                                                    <fmt:parseDate var="p_datee" value="${res.date}" pattern="yyyy-MM-dd"/>
                                                                    <fmt:formatDate var="datee" value="${p_datee}" pattern="dd-MM-yyyy"/>
                                                                    <td>${datee}<span class="d-block text-info">${res.slot.settingname}</span><td>
                                                                        <c:if test="${res.status == 1}">
                                                                            <span class="badge badge-pill bg-success-light">
                                                                                Completed
                                                                            </span>
                                                                        </c:if>
                                                                        <c:if test="${res.status == 2}">
                                                                            <span class="badge badge-pill bg-warning-light">
                                                                                Pending
                                                                            </span>
                                                                        </c:if>
                                                                        <c:if test="${res.status == 0}">
                                                                            <span class="badge badge-pill bg-danger-light">
                                                                                Cancel
                                                                            </span>
                                                                        </c:if>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>                                                      
                                                </div>                          
                                            </div>
                                        </div>
                                    </div>
                                    <br>
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
