<%-- 
    Document   : PatientList
    Created on : Feb 8, 2022, 8:00:45 PM
    Author     : hunglx
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
                                <form action="mypatient" method="GET">
                                    <div class="col-md-5">
                                        <input type="text" name="value" class="form-control"  placeholder="type here to search">
                                    </div>
                                    <input type="submit" value="Search" class="btn btn-default"/>
                                </form>
                                <br>
                                <table class="table dataTable table-striped table-hover">
                                    <tr>
                                        <th>Name</th>
                                        <th>Last Booking Date</th>
                                        <th>DOB</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                    </tr>
                                    <c:forEach items="${patient}" var="d">
                                        <tr onclick="location.href = 'mypatientdetail?id=${d.ID}'" style="cursor: pointer;">
                                            <td><h2><img class="avatar-img rounded-circle" src="data:image/jpeg;base64,${d.imageString}" alt="Doctor Image"  style="height: 60px;border-radius: 40px;"></h2>${d.name}</td>
                                            <fmt:parseDate var="p_date" value="${d.maxDate}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="maxdate" value="${p_date}" pattern="dd/MM/yyyy"/>
                                            <td style="padding-top:50px;">${maxdate}</td>
                                            <fmt:parseDate var="dob_date" value="${d.DOB}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="dobdate" value="${dob_date}" pattern="dd/MM/yyyy"/>
                                            <td style="padding-top:50px;">${dobdate}</td>
                                            <td style="padding-top:50px;">${d.email}</td>
                                            <td style="padding-top:50px;">${d.phone}</td>
                                        </tr>
                                    </c:forEach>
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

