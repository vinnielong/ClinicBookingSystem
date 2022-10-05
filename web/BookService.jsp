<%-- 
    Document   : BookAppointment.jsp
    Created on : Mar 11, 2022, 9:32:01 PM
    Author     : vinnielong
--%>

<%@page import="dal.ServiceDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html lang="en">

    <%@include file="resource/components/head.jsp" %>
    <body>
        <div class="main-wrapper">
            <%@include file="resource/components/header.jsp" %>
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="homepage">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Booking</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Booking</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content">
                <div class="container">
                    <div class="row">
                        <div class="col-md-7 col-lg-8">
                            <div class="card">
                                <div class="card-body">
                                    <div class="info-widget">
                                        <h4 class="card-title">Personal Information</h4>
                                        <div class="row">
                                            <div class="col-md-6 col-sm-12">
                                                <div class="form-group card-label">
                                                    <label> Name</label>
                                                    <input class="form-control" type="text" value="${sessionScope.user.name}" readonly="" />
                                                </div>
                                            </div>

                                            <div class="col-md-6 col-sm-12">
                                                <div class="form-group card-label">
                                                    <label>Email</label>
                                                    <input class="form-control" type="email" value="${sessionScope.user.email}" readonly=""/>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <div class="form-group card-label">
                                                    <label>Phone</label>
                                                    <input class="form-control" type="text" value="${sessionScope.user.phone}" readonly=""/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="exist-customer">
                                            Want to change information?
                                            <a href="patientprofile">Click here!</a>
                                        </div>
                                    </div>
                                    <div class="payment-widget">
                                        <!-- Checkout Form -->
                                        <form action="reservation" method="post">
                                            <div class="payment-widget">
                                                <h4 class="card-title">Booking Details</h4>
                                                <input type="hidden" name="patient_id" value="${sessionScope.user.ID}">
                                                <input type="hidden" name="service_id" value="${service.ID}">
                                                <div class="payment-list">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group card-label">
                                                                <label for="card_name" style="margin-bottom: 14px">Date</label>
                                                                <input class="form-control" id="card_name" type="date" value="${date}" name="date" required="" onchange="changeDate(this.value)"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <div class="form-group card-label">
                                                                <label for="card_name">Time</label>
                                                                <select class="form-control" id="time" name="slot" onclick="changeTime(this.value)">
                                                                    <c:forEach items="${cboSlot}" var="o">
                                                                        <option value="${o.ID}">${o.settingname}</option>
                                                                    </c:forEach>>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <div class="form-group card-label">
                                                                <label for="card_name">Description</label>
                                                                <textarea rows="5" cols="5" class="form-control" placeholder="Reason for this appointment (optional)" name="description"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="submit-section mt-4">
                                                    <button type="submit" class="btn btn-primary submit-btn" onclick="return confirm('Are you sure you want to book this service?');">Booking</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:set var="dao" value="<%=new ServiceDAO()%>"></c:set>
                        <c:set var="avgrate" value="${dao.averageRate(service.ID)}"></c:set>
                        <c:set var="feedbackList" value="${dao.getFeedbackByServiceID(service.ID)}"></c:set>

                            <div class="" style="position: static; width: 360px; margin-left: 10px;">
                                <div class="card booking-card">
                                    <div class="card-header">
                                        <h4 class="card-title">Booking Summary</h4>
                                    </div>
                                    <div class="card-body">
                                        <!-- Booking Doctor Info -->
                                        <div class="booking-doc-info">
                                            <a href="serviceinfo?sid=${service.ID}" class="booking-doc-img">
                                            <img src="data:image/jpeg;base64,${service.thumbnailString}" alt="User Image" />
                                        </a>
                                        <div class="booking-info">
                                            <h4>
                                                <a href="serviceinfo?id=${service.ID}">${service.title}</a>
                                            </h4>
                                            <div class="rating">
                                                <i class="fas fa-star ${avgrate > 0 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 1 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 2 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 3 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 4 ? "filled" : ""}"></i>
                                                <span class="d-inline-block average-rating">(${totalFeedback})</span>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="booking-summary" style="margin-top: 10px">
                                        <div class="booking-item-wrap">
                                            <ul class="booking-date">
                                                <li>Fee:  ${service.price}</li>
                                            </ul>
                                            <script>
                                                function changeDate(value) {
                                                    document.getElementById("resDate").innerHTML = value;
                                                }
                                            </script>
                                        </div>
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
