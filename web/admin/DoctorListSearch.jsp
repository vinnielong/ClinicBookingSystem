<%-- 
    Document   : DoctorListSearch
    Created on : Feb 16, 2022, 12:53:59 AM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                    <li><a href="index.html">Dashboard | Doctor</a></li>
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
                                <form action="doctorlistsearch" method="get">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <input class="form-control" type="text" name="search" placeholder="type here to search">
                                        </div>
                                        <div class="col-md-2" style="padding: 0px;">
                                            <input type="submit" value="Search" class="btn btn-default"/>
                                        </div>
                                        <div class="col-md-5" >
                                            <select class="form-control" name="filters" style="padding: 4px 12px;">
                                                <option value="gender">Gender</option>
                                                <option value="speciality">Speciality</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <table class="table table-striped table-hover">
                                    <tr>
                                        <th>ID</th>
                                        <th>Doctor Name</th>
                                        <th>Gender</th>
                                        <th>Speciality</th>
                                        <th>Status</th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${searchDoctor}" var="d">
                                        <tr>
                                            <td>${d.id}</td>
                                            <td>${d.doctorName}</td>
                                            <td> <c:if test="${d.gender == 1}">Male</c:if>
                                                <c:if test="${d.gender == 0}">Female</c:if>
                                                <c:if test="${d.gender == 2}">Other</c:if></td>
                                            <td>${d.speciality.name}</td>
                                            <td>${d.status==1?"Active":"Inactive"}</td>
                                            <td style="text-align: center;"><a class="btn btn-default" href="doctordetailedit?id=${d.id}">Edit</a> </td>
                                        </tr>
                                    </c:forEach>

                                </table>
                                <div class="paging">
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <a class="${i==index?"active":""}" href="doctorlistsearch?index=${i}&search=${search}">${i}</a>
                                    </c:forEach>
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
