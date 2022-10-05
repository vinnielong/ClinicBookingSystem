<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                    <li class="breadcrumb-item">
                                        <a href="homepage">Home</a>
                                    </li>
                                    <li class="breadcrumb-item active" aria-current="page">
                                        Reservation History
                                    </li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Reservation History</h2>
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
                                            <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image"/>
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
                                            <li>
                                                <a href="appointmenthistory">
                                                    <i class="fas fa-calendar-check"></i>
                                                    <span>Appointment History</span>
                                                </a>
                                            </li>
                                            <li class="active">
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
                                    <!-- Tab Content -->
                                    <div class="tab-content pt-0">
                                        <!-- Appointment Tab -->
                                        <div id="pat_appointments" class="tab-pane fade show active">
                                            <div class="card card-table mb-0">
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table table-hover table-center mb-0">
                                                            <thead>
                                                                <tr>
                                                                    <th>Reservation ID</th>                                                                
                                                                    <th>Service</th>
                                                                    <th>Date</th>
                                                                    <th>Price</th>
                                                                    <th>Status</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${resList}" var="res">
                                                                    <tr>
                                                                        <td>${res.id}</td>
                                                                        <td>${res.servicename.title}</td>
                                                                        <fmt:parseDate var="p_date" value="${res.date}" pattern="yyyy-MM-dd"/>
                                                                        <fmt:formatDate var="date" value="${p_date}" pattern="dd-MM-yyyy"/>
                                                                        <td>${date}<span class="d-block text-info">${res.slot.settingname}</span></td>
                                                                        <td><fmt:formatNumber value = "${res.servicename.price}" type = "number"/> VND</td>
                                                                        <td>
                                                                            <c:if test="${res.status == 1}">
                                                                                <span class="badge badge-pill bg-success-light">
                                                                                    Complete
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

                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="load-more text-center">
                                            <ul class="pagination" >
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
        <script>paggerBasic('pagination', ${pageIndex}, ${totalPages}, 2);</script>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
