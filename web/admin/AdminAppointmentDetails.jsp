<%-- 
    Document   : AdminAppointmentDetails
    Created on : Feb 12, 2022, 10:30:45 AM
    Author     : Vinnie Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp"%>

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
                    <li><a href="dashboard">Dashboard</a> | Appointments</li>
                </ol>
            </div>
        </section>

        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/admin-sidebar.jsp"%>
                    <div class="col-md-5">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Patient Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">      
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <img class="avatar-detail" 
                                                 <c:choose>
                                                     <c:when test="${patientimg != null && not empty patientimg}">
                                                         src="data:image/jpeg;base64,${patientimg}"
                                                     </c:when>
                                                     <c:otherwise>
                                                         <img src="resource/img/NoAvartar.jpg" alt=""/>
                                                 </c:otherwise>
                                            </c:choose>
                                            value="${patientimg}" alt="User Image">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Name:</label>
                                            <input name="patientname" value="${appointment.patient.name}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Gender:</label>
                                            <c:if test="${appointment.patient.gender == 1}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <c:if test="${appointment.patient.gender == 0}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Mobile:</label>
                                            <input name="patientmobile" value="${appointment.patient.phone}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Date of Birth:</label>
                                            <input name="patientdob" value="${appointment.patient.DOB}" class="form-control" type="date" readonly>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Doctor Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">      
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <img class="avatar-detail"
                                                 <c:choose>
                                                     <c:when test="${doctorimg != null && not empty doctorimg}">
                                                         src="data:image/jpeg;base64,${doctorimg}"
                                                     </c:when>
                                                     <c:otherwise>
                                                         <img src="resource/img/NoAvartar.jpg" alt=""/>
                                                 </c:otherwise>
                                            </c:choose>
                                            value="${doctorimg}" alt="User Image">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Name:</label>
                                            <input name="doctorname" value="${appointment.doctor.doctorName}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Gender:</label>
                                            <c:if test="${appointment.doctor.gender == 1}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <c:if test="${appointment.doctor.gender == 0}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Mobile:</label>
                                            <input name="doctormobile" value="${appointment.doctor.phone}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Speciality:</label>
                                            <input name="speciality" value="${appointment.doctor.speciality.settingname}" class="form-control" type="text" readonly>
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
                                <h3 class="panel-title">Appointment Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-6">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Date</label>
                                            <input name="date" value="${appointment.date}" class="form-control" type="date" readonly>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.acc.role == 4}">
                                        <form action="appointdetail" method="post">
                                            <input type="text" name="id" value="${appointment.ID}" hidden>
                                            <input type="text" name="option" value="status" hidden>
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label>Status</label>
                                                    <input type="radio" name="status" value="0" <c:if test="${appointment.status == 0}">checked=""</c:if>><span id="span-failure" class="badge">Canceled</span>
                                                    <input type="radio" name="status" value="1" <c:if test="${appointment.status == 1}">checked=""</c:if>><span id="span-success" class="badge">Assigned</span>
                                                    <input type="radio" name="status" value="2" <c:if test="${appointment.status == 2}">checked=""</c:if>><span id="span-pending" class="badge">Pending</span>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <button href="submit" class="btn btn-default" style="float: right; display: inline-block" onclick="return confirm('Are you sure you want to update this appointment status?');">Update</button>
                                                    </div>
                                                </div>
                                            </form>
                                    </c:if>
                                    <c:if test="${sessionScope.acc.role == 1}">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Status</label>
                                                <c:if test="${appointment.status == 0}">
                                                    <span id="span-failure" class="badge">Canceled</span>
                                                </c:if>
                                                <c:if test="${appointment.status == 1}">
                                                    <span id="span-success" class="badge">Assigned</span>
                                                </c:if>
                                                <c:if test="${appointment.status == 2}">
                                                    <span id="span-pending" class="badge">Pending</span>
                                                </c:if>
                                                <c:if test="${appointment.status == 3}">
                                                    <span id="span-success" class="badge">Completed</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <form action="appointdetail" method="post">
                                            <input type="text" name="id" value="${appointment.ID}" hidden>
                                            <input type="text" name="option" value="staff" hidden>
                                            <div class="col-md-12" style="margin-bottom: -10px;">
                                                <div class="form-group">
                                                    <label>Assigned Staff: ${appointment.staff.fullName}</label><br>
                                                    <label>Change Assigned Staff</label>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <select name="cboStaff" class="form-control" style="height:auto;">
                                                        <c:forEach items="${cboStaff}" var="c">
                                                            <option value="${c.id}">${c.fullName} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <button href="submit" class="btn btn-default" style="float: right; display: inline-block" onclick="return confirm('Are you sure you want to update this appointment staff?');">Update</button>
                                                </div>
                                            </div>
                                        </form>
                                    </c:if>
                                </div>
                                <div class="col-sm-6"> 
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="desc" class="form-control" style="height: 200px;" readonly>${appointment.description}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <button href="appoinment" class="btn btn-default" style="float: right;">Back to Appointment List</button>

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
