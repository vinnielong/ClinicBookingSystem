<%-- 
    Document   : MyAppointmentDetails
    Created on : Feb 19, 2022, 10:22:09 PM
    Author     : Vinnie Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/doctor-header.jsp"%>

        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Appointment Details<small></small></h1>
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
                                <h3 class="panel-title">Appointment Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">      
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <img class="avatar-detail" 
                                                 <c:choose>
                                                     <c:when test="${patientimg != null && not empty patientimg}">
                                                         src="data:image/jpeg;base64,${patientimg}"
                                                     </c:when>
                                                     <c:otherwise>
                                                         src="https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
                                                     </c:otherwise>
                                                 </c:choose>
                                                 value="${patientimg}" style="max-height: 260px;" alt="Patient Image">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Patient Name:</label>
                                            <input name="patientname" value="${appointment.patient.name}" class="form-control" type="text" readonly>
                                            <label>Patient Gender:</label>
                                            <c:if test="${appointment.patient.gender == 1}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <c:if test="${appointment.patient.gender == 0}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <label>Patient Phone Number:</label>
                                            <input name="patientmobile" value="${appointment.patient.phone}" class="form-control" type="text" readonly>
                                            <label>Date of Birth:</label>
                                            <input name="patientdob" value="${appointment.patient.DOB}" class="form-control" type="date" readonly>
                                            <label>Patient Email:</label>
                                            <input name="patientdob" value="${appointment.patient.email}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Appointment Date</label>
                                            <input name="date" value="${appointment.date}" class="form-control" type="date" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Status: </label>
                                            <form action="myappointdetail" method="post">
                                                <input type="text" name="appointid" value="${appointment.ID}" hidden>
                                                <input type="radio" name="status" value="1" <c:if test="${appointment.status == 1}">checked=""</c:if>><span id="span-pending" class="badge">Assigned</span>
                                                <input type="radio" name="status" value="3" <c:if test="${appointment.status == 3}">checked=""</c:if>><span id="span-success" class="badge">Completed</span>
                                                    <button href="submit" class="btn btn-default" style="float: right; margin-right: 10px;" onclick="return confirm('Are you sure you want to update this appointment status?');">Update Status</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea name="desc" class="form-control" style="height: 200px;" readonly>${appointment.description}</textarea>
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
