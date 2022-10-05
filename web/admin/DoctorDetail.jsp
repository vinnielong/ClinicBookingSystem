<%-- 
    Document   : DoctorDetail
    Created on : Feb 7, 2022, 9:24:22 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp" %>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Manage Doctors<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Doctor</a></li>
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
                                <h3 class="panel-title">Edit Doctor</h3>
                            </div>
                            <form action="doctordetailedit" method="post">
                                <div class="panel-body">
                                    <div class="row" style="margin-right: 0px;">      
                                        <input type="hidden" name="id" value="${doctorById.id}"/>
                                        <div class="col-md-3">Doctor Name:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="doctorname" value="${doctorById.doctorName}" required=""></div>
                                        <div class="col-md-3">Role:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="role" value="${doctorById.role}" required=""></div>
                                        <br></br>
                                        <div class="col-md-3">Gender:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;">
                                            <input class="" type="radio" name="gender" value="0" ${doctorById.gender==0?"checked":""}>Female
                                            <input class="" type="radio" name="gender" value="1" ${doctorById.gender==1?"checked":""}>Male
                                        </div>
                                        <div class="col-md-3">Date of birth:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="date" name="dob" value="${doctorById.dob}" required=""></div>
                                        <br></br>
                                        <div class="col-md-3">Phone number:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="phone" value="${doctorById.phone}" required=""></div>
                                        <div class="col-md-3">Email:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" value="${doctorById.email}" disabled></div>
                                        <br></br>
                                        <div class="col-md-3">Speciality:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;">
                                            <select class="form-control" name="speciality" style="padding: 4px 12px;" >
                                                <c:forEach items="${settingses}" var="s">
                                                    <option <c:if test="${doctorById.speciality.ID eq s.ID}">
                                                            selected="selected"
                                                        </c:if> value="${s.ID}">${s.settingname}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-3">Status:</div>
                                        <div class="col-md-3" style="padding: 0px;left: -40px;">
                                            <input type="checkbox" id="status1" name="status" class="check" value="${doctorById.status}" ${doctorById.status==1?"checked":""} >
                                            <label for="status1" class="checktoggle"></label>
                                            <br>
                                        </div>
                                        <br></br>
                                        <div class="col-md-3">Description:</div>
                                        <div class="col-md-9" style="padding: 0px;left: -40px;"><textarea class="form-control" name="description" style="height: 100px;" required="">${doctorById.description}</textarea>
                                            <br>
                                            <div class="col-md-12" style="padding-right: 0px;">
                                                <a href="doctorlist" class="btn btn-default" style="float: right;">Exit</a><input class="btn btn-default" type="submit" style="float: right;" value="Save"/>
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-6">
                            <div class="panel panel-default" style="margin: 0px 0px -15px -15px;">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Appointments</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Patient Name</th>
                                                        <th>Time</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${appointments}" var="a">
                                                        <tr>
                                                            <td>${a.ID}</td>
                                                            <td>${a.patient.name}</td>
                                                            <fmt:parseDate var="a_date" value="${a.date}" pattern="yyyy-MM-dd"/>
                                                            <fmt:formatDate var="date" value="${a_date}" pattern="MM/dd/yyyy"/>
                                                            <td>${date}</td>
                                                            <td><c:if test="${a.status == 1}">
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
                                                                </c:if></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="panel panel-default" style="margin: 0px -15px -15px 0px;">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Feedback</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Patient Name</th>
                                                        <th>Rate</th>
                                                        <th>Comment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listFeedback}" var="feedback">
                                                        <tr>
                                                            <td>${feedback.id}</td>
                                                            <td>${feedback.patient.name}</td>
                                                            <td><div class="review-count rating">
                                                                    <i class="fas fa-star ${feedback.star > 0 ? "filled" : ""}"></i>
                                                                    <i class="fas fa-star ${feedback.star > 1 ? "filled" : ""}"></i>
                                                                    <i class="fas fa-star ${feedback.star > 2 ? "filled" : ""}"></i>
                                                                    <i class="fas fa-star ${feedback.star > 3 ? "filled" : ""}"></i>
                                                                    <i class="fas fa-star ${feedback.star > 4 ? "filled" : ""}"></i>
                                                                </div></td>
                                                            <td> ${feedback.comment} </td>
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

