<%-- 
    Document   : DoctorList
    Created on : Feb 7, 2022, 9:23:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <h3 class="panel-title">Doctors</h3>
                            </div>
                            <div class="panel-body">
                                <form action="doctorlist" method="get">
                                    <div class="row">
                                        <div class="col-md-12">

                                            <div class="col-md-5">
                                                <input class="form-control" type="text" name="search" value="${search}" placeholder="type here to search">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <select class="form-control" name="speciality" style="padding: 4px 12px;">
                                                    <option value="0">---Speciality---</option>
                                                    <c:forEach items="${listSpeciality}" var="s">
                                                        <option value="${s.ID}" ${s.ID==speciality1?"selected":""}>${s.settingname}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="" type="radio" name="gender" value="0"  ${gender1==0?"checked":""}>Female
                                                <input class="" type="radio" name="gender" value="1" ${gender1==1?"checked":""}>Male
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <table id="example" class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Doctor Name</th>
                                            <th>Gender</th>
                                            <th>Speciality</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${doctors}" var="d">
                                            <tr>
                                                <td>${d.id}</td>
                                                <td>${d.doctorName}</td>
                                                <td>
                                                    <c:if test="${d.gender == 1}">Male</c:if>
                                                    <c:if test="${d.gender == 0}">Female</c:if>
                                                    <c:if test="${d.gender == 2}">Other</c:if>
                                                    </td>
                                                    <td>${d.speciality.settingname}</td>
                                                <td>${d.status==1?"Active":"Inactive"}</td>
                                                <td style="text-align: center;"><a class="btn btn-default" href="doctordetailedit?id=${d.id}">Edit</a> </td>
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

