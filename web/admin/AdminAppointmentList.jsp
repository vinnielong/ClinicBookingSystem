<%-- 
    Document   : AdminAppointmentList
    Created on : Feb 12, 2022, 10:30:22 AM
    Author     : Vinnie Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp"%>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Appointments<small></small></h1>
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
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Appointments</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <form action="appointsearch" method="GET">                                           
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" name="result" value="${result}" placeholder="type here to search">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <select class="form-control formstyle" name="doctor" style="padding: 4px 12px;">
                                                    <option value="0">-----------Doctor-----------</option>
                                                    <c:forEach items="${doctorList}" var="s">
                                                        <option value="${s.id}" ${doctor==s.id?"selected":""}>${s.doctorName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                Status:  
                                                <input type="radio" name="status" value="0" <c:if test="${status == 0}">checked=""</c:if>><span id="span-failure" class="badge">Canceled</span>
                                                <input type="radio" name="status" value="1" <c:if test="${status == 1}">checked=""</c:if>><span id="span-success" class="badge">Assigned</span>
                                                <input type="radio" name="status" value="2" <c:if test="${status == 2}">checked=""</c:if>><span id="span-pending" class="badge">Pending</span>
                                                <input type="radio" name="status" value="3" <c:if test="${status == 3}">checked=""</c:if>><span id="span-success" class="badge">Completed</span>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <br>
                                    <table id="example" class="dataTable table table-striped table-hover">
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
