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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Manage Reservations<small></small></h1>
                    </div>

                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard</a> | Reservations</li>
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
                                                     <c:when test="${res.patientname.imageString != null && not empty res.patientname.imageString}">
                                                         src="data:image/jpeg;base64,${res.patientname.imageString}"
                                                     </c:when>
                                                     <c:otherwise>
                                                         src="resource/img/NoAvartar.jpg"
                                                     </c:otherwise>
                                                 </c:choose>
                                                 alt="User Image">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Name:</label>
                                            <input name="patientname" value="${res.patientname.name}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Gender:</label>
                                            <c:if test="${res.patientname.gender == 1}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                            <c:if test="${res.patientname.gender == 0}">
                                                <input name="doctorgender" value="Male" class="form-control" type="text" readonly>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Mobile:</label>
                                            <input name="patientmobile" value="${res.patientname.phone}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Date of Birth:</label>
                                            <input name="patientdob" value="${res.patientname.DOB}" class="form-control" type="date" readonly>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Service Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">      
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <img class="avatar-detail"
                                                 <c:choose>
                                                     <c:when test="${res.servicename.thumbnailString != null && not empty res.servicename.thumbnailString}">
                                                         src="data:image/jpeg;base64,${res.servicename.thumbnailString}"
                                                     </c:when>
                                                     <c:otherwise>
                                                         src="resource/img/NoAvartar.jpg"
                                                     </c:otherwise>
                                                 </c:choose>
                                                 alt="User Image">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Name:</label>
                                            <input name="doctorname" value="${res.servicename.title}" class="form-control" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Category</label>
                                            <input name="doctorname" value="${res.servicename.category.settingname}" class="form-control" type="text" readonly>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Price:</label>
                                            <input name="doctormobile" value="${res.servicename.price}" class="form-control" type="text" readonly>
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
                                <h3 class="panel-title">Reservation Information</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-6">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Date</label>
                                            <input name="date" value="${res.date}" class="form-control" type="date" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <c:if test="${res.status == 1}">
                                                <span id="span-success" class="badge">Assigned</span>
                                            </c:if>
                                            <c:if test="${res.status == 0}">
                                                <span id="span-failure" class="badge">Canceled</span>
                                            </c:if>
                                            <c:if test="${res.status == 2}">
                                                <span id="span-pending" class="badge">Pending</span>
                                            </c:if>
                                            <c:if test="${res.status == 3}">
                                                <span id="span-success" class="badge">Completed</span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.acc.role==1}">
                                        <form action="ReservationDetailController" method="POST">
                                            <input name="id" value="${res.id}" class="form-control" type="hidden" >
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Assigned Staff: ${res.staff.fullName}</label><br>
                                                    <label>Change Assigned Staff</label>
                                                    <select name="cboStaff" class="form-control" style="height:auto;">
                                                        <c:forEach items="${cboStaff}" var="c">
                                                            <option value="${c.id}">${c.fullName} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div> 
                                        </c:if>
                                </div>
                                <div class="col-sm-6"> 
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="desc" class="form-control" style="height: 200px;" readonly>${res.description}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <a href="reservationlist" class="btn btn-default" style="float: right;">Exit</a>
                                <c:if test="${sessionScope.acc.role==1}">
                                    <button href="submit" class="btn btn-default" style="float: right; margin-right: 10px;" onclick="return confirm('Are you sure you want to change the staff ?');">Save</button>
                                </c:if>      
                                </form>
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
