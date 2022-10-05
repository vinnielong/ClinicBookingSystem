<%-- 
    Document   : DoctorAppointments
    Created on : Feb 12, 2022, 10:31:03 AM
    Author     : Vinnie Long
--%>

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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>My Appointments<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="myappointment">My Appointments</li>
                </ol>
            </div>
        </section>

        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/doctor-sidebar.jsp"%>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Appointments</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">                                         
                                    <div class="col-md-5">
                                    </div>
                                    <div class="col-md-2" ">
                                    </div>
                                </div>
                                <table id="example" class="dataTable table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Patient Name</th>
                                            <th>Patient ID</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listAppointments}" var="a">
                                            <tr onclick="location.href = 'myappointdetail?id=${a.ID}'" style="cursor: pointer;">
                                                <td>${a.ID}</td>
                                                <td>${a.patient.name}</td>
                                                <td>${a.patient.ID}</td>
                                                <fmt:parseDate var="a_date" value="${a.date}" pattern="yyyy-MM-dd"/>
                                                <fmt:formatDate var="date" value="${a_date}" pattern="dd/MM/yyyy"/>
                                                <td>${date}</td>
                                                <td>
                                                    <c:if test="${a.status == 1}">
                                                        <span id="span-pending" class="badge">Assigned</span>
                                                    </c:if>
                                                    <c:if test="${a.status == 3}">
                                                        <span id="span-success" class="badge">Completed</span>
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
            </div>
        </section>
        <%@include file="resource/components/footer.jsp"%>
        <%@include file="resource/components/script.jsp"%>
    </body>
</html>
