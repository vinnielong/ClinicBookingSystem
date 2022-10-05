<%-- 
    Document   : Dashboard
    Created on : Mar 12, 2022, 6:29:24 PM
    Author     : hunglx
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp"%>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Dashboard<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard</a></li>
                </ol>
            </div>
        </section>

        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/admin-sidebar.jsp"%>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Overview</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-md-3">
                                    <div class="well dash-box">
                                        <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${doctor}</h2>
                                        <h4>Doctors</h4>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="well dash-box">
                                        <h2><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> ${service}</h2>
                                        <h4>Services</h4>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="well dash-box">
                                        <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> ${appointment}</h2>
                                        <h4>Appointments</h4>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="well dash-box">
                                        <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${patient}</h2>
                                        <h4>Patients</h4>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading" style="background-color: #e74c3c">
                                <h3 class="panel-title" style="color: white;">Revenue</h3>
                            </div>
                            <div class="panel-body">
                                <img src="resource/img/Graph.png" alt="" style="width: 100%"/>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="panel panel-default" style="margin: 0px 0px -15px -15px;">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Latest Appointments</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Patient Name</th>
                                                        <th>Doctor Name</th>
                                                        <th>Time</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listAppointments}" var="a">
                                                        <tr onclick="location.href = 'appointdetail?id=${a.ID}'" style="cursor: pointer;">
                                                            <td>${a.ID}</td>
                                                            <td>${a.patient.name}</td>
                                                            <td>${a.doctor.doctorName}</td>
                                                            <fmt:parseDate var="a_date" value="${a.date}" pattern="yyyy-MM-dd"/>
                                                            <fmt:formatDate var="date" value="${a_date}" pattern="MM/dd/yyyy"/>
                                                            <td>${date}</td>
                                                            <td>
                                                                <c:if test="${a.status == 1}">
                                                                    <span id="span-success" class="badge">Assigned</span>
                                                                </c:if>
                                                                <c:if test="${a.status == 0}">
                                                                    <span id="span-failure" class="badge">Canceled</span>
                                                                </c:if>
                                                                <c:if test="${a.status == 2}">
                                                                    <span id="span-pending" class="badge">Pending</span>
                                                                </c:if>
                                                                <c:if test="${a.status == 3}">
                                                                    <span id="span-success" class="badge">Completed</span>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <a href="appointment" class="btn btn-default" style="margin-left: 170px;">View more Appointments</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="panel panel-default"  style="margin: 0px -15px -15px 0px;">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Latest Reservations</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Patient Name</th>
                                                        <th>Service Name</th>
                                                        <th>Date</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${res}" var="r">
                                                        <tr onclick="location.href = 'ReservationDetailController?id=${r.id}'" style="cursor: pointer;">
                                                            <td>${r.id}</td>
                                                            <td>${r.patientname.name}</td>
                                                            <td>${r.servicename.title}</td>
                                                            <fmt:parseDate var="a_date" value="${r.date}" pattern="yyyy-MM-dd"/>
                                                            <fmt:formatDate var="date" value="${a_date}" pattern="dd/MM/yyyy"/>
                                                            <td>${date}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <a href="reservationlist" class="btn btn-default" style="margin-left: 170px;">View more Reservations</a>
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
</html>
