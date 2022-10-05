<%-- 
    Document   : DoctorList
    Created on : Feb 7, 2022, 9:23:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">

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
                    <li><a href="dashboard">Dashboard | Reservation</a></li>
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
                                <h3 class="panel-title">Reservation</h3>
                            </div>
                            <div class="panel-body">
                                <form action="ReservationSearchController" method=" GET">
                                    <div class="col-md-12">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" value="${servicename}" name="servicename" placeholder="type here to search" required="">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>                                        
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="col-md-5"></div>
                                        <div class="col-md-2"></div>
                                        <div class="col-md-5"></div>
                                    </div>
                                </form>
                                <br>
                                <table id="example" class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Patient Name</th>
                                            <th>Service Name</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${res}" var="r">
                                            <tr>
                                                <td>${r.id}</td>
                                                <td>${r.patientname.name}</td>
                                                <td>${r.servicename.title}</td>
                                                <fmt:parseDate var="a_date" value="${r.date}" pattern="yyyy-MM-dd"/>
                                                <fmt:formatDate var="date" value="${a_date}" pattern="dd/MM/yyyy"/>
                                                <td>${date}</td>
                                                <td>
                                                    <c:if test="${r.status == 1}">
                                                        <span id="span-success" class="badge">Assigned</span>
                                                    </c:if>
                                                    <c:if test="${r.status == 0}">
                                                        <span id="span-failure" class="badge">Canceled</span>
                                                    </c:if>
                                                    <c:if test="${r.status == 2}">
                                                        <span id="span-pending" class="badge">Pending</span>
                                                    </c:if>
                                                    <c:if test="${r.status == 3}">
                                                        <span id="span-success" class="badge">Completed</span>
                                                    </c:if>
                                                </td>
                                                <td style="text-align: center;"><a class="btn btn-default" href="ReservationDetailController?id=${r.id}">Detail</a> </td>
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

