<%-- 
    Document   : MyPatientDetail
    Created on : Mar 14, 2022, 6:05:30 PM
    Author     : hunglx
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>My Patients<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="mypatient">My Patients</li>
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
                                <h3 class="panel-title">My Patients</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">      
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <c:choose>
                                                <c:when test="${patient.imageString != null && not empty patient.imageString}">
                                                    <img class="avatar-detail" src="data:image/jpeg;base64,${patient.imageString}" style="max-height: 260px;" alt="Patient Image">  
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="avatar-detail" src="resource/img/NoAvartar.jpg" style="max-height: 260px;" alt="Patient Image">  
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Patient Name:</label>
                                            <input name="patientname" value="${patient.name}" class="form-control" type="text" readonly>
                                            <label>Patient Gender:</label>
                                            <c:if test="${patient.gender == 1}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <c:if test="${patient.gender == 0}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <label>Patient Phone Number:</label>
                                            <input name="patientmobile" value="${patient.phone}" class="form-control" type="text" readonly>
                                            <label>Date of Birth:</label>
                                            <input name="patientdob" value="${patient.DOB}" class="form-control" type="date" readonly>
                                            <label>Patient Email:</label>
                                            <input name="patientdob" value="${patient.email}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Patient Appointments</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">                                         
                                    <div class="col-md-12">
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
                                                    <tr>
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
                </div> 
            </div>
        </section>
        <%@include file="resource/components/footer.jsp"%>
        <%@include file="resource/components/script.jsp"%>
    </body>
</html>
