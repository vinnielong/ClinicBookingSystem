<%-- 
    Document   : PatientList
    Created on : Feb 8, 2022, 8:00:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp"%>

        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Manage Patients<small></small></h1>
                    </div>
                    <div class="col-md-2">
                        <div class="dropdown create">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                Function
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="#">Add Employees</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="index.html">Dashboard</a></li>
                    <li class="active">Patients</li>
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
                                <h3 class="panel-title">Patient List</h3>
                            </div>
                            <div class="panel-body">           
                                <div class="row">
                                    <form action="patientsearch" method="GET">                                           
                                        <div class="col-md-5">
                                            <input class="form-control"  value="${name}" type="text" name="result" placeholder="Type name to search" required>
                                        </div>
                                        <div class="col-md-2" style="padding: 0px;">
                                            <input type="submit" value="Search" class="btn btn-default"/>
                                    </form>
                                </div>
                                <br>
                                <table class="dataTable  table table-striped table-hover" id="example">
                                    <tr>
                                        <th>ID</th>                                    
                                        <th>Full Name</th>
                                        <th>Gender</th>
                                        <th>Date of Birth</th>
                                        <th>Status</th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${listPatient}" var="Patient" >
                                        <tr>
                                            <td>${Patient.ID}</td>
                                            <td>${Patient.name}</td>
                                            <td>
                                                  <c:if test="${Patient.gender == 1}">Male</c:if>
                                                    <c:if test="${Patient.gender == 0}">Female</c:if>
                                            </td>
                                           
                                             <fmt:parseDate var="a_date" value="${Patient.DOB}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate var="date" value="${a_date}" pattern="MM/dd/yyyy"/>
                                            <td>${date}</td>
                                            <td>
                                                  <c:if test="${Patient.status == 1}">Active</c:if>
                                                    <c:if test="${Patient.status == 0}">Inactive</c:if>
                                            </td>
                                            <td style="text-align: center;"><a class="btn btn-default" href="patientedit?id=${Patient.ID}">Edit</a> <a class="btn btn-danger" href="patientdetail?id=${Patient.ID}" style="padding: 4px;">Detail</a></td>
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

