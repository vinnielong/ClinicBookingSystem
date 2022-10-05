<%-- 
    Document   : PatientDetail
    Created on : Feb 8, 2022, 8:00:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="index.html">Dashboard</a></li>
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
                                <h3 class="panel-title">Patient Detail</h3>
                            </div>

                            <div class="panel-body">
                                <div class="row" style="margin-right: 0px;">      

                                    <div class="col-md-6">
                                        <div class="form-group">

                                            <label>Full Name:</label>
                                            <input class="form-control formstyle" type="text" name="fullname" value="${st.name}" readonly="">
                                            <label>Gender:</label>
                                            <input class="form-control formstyle" type="text" name="gender" value="${st.gender==1?"Male":"Female"}" readonly="">

                                            <label>DOB:</label>
                                            <input class="form-control formstyle" type="date" name="dob" value="${st.DOB}" readonly="">
                                            <label>Mobile:</label>
                                            <input class="form-control formstyle" type="text" name="phone" value="${st.phone}" readonly="">

                                            <label>Status:</label>
                                            <input class="form-control formstyle" type="text" name="status" value="${st.status==1?"Active":"De-active"}" readonly="">
                                            <label>Email:</label>
                                            <input class="form-control formstyle" type="text" name="email" value="${st.email}" readonly="true">

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div style="text-align: center;">
                                                <label>Avatar</label>
                                            </div>
                                            <img <c:if test="${st.imageString eq null}"> src="resource/img/NoAvartar.jpg"</c:if> <c:if test="${st.imageString ne null}"> src="data:image/jpeg;base64,${st.imageString}" value="${st.imageString}" </c:if> name="image" class="avatar-detail" style="max-height: 165px;"/>
                                                <div style="text-align: center;"><br>
                                                    <label>Change Status:</label>
                                                    <form action="patientdetail" method="post">
                                                            <input type="hidden" name="id" value="${st.ID}">
                                                    <c:if test="${st.status == 1}">
                                                        <input type="checkbox" id="status" class="check" checked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                    </c:if>
                                                    <c:if test="${st.status == 0}">
                                                        <input type="checkbox" id="status" class="check" unchecked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                    </c:if>
                                                    <label for="status" class="checktoggle" style="display: inline-block; margin: 14px 0px -7px 10px"></label>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12" style="padding-right: 0px;">
                                        <a href="patientlist" class="btn btn-default" style="float: right;">Back</a>
                                        <a href="patientedit?id=${st.ID}" class="btn btn-default" style="float: right;">Edit</a>                                      
                                    </div>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
        </section>
        <%@include file="resource/components/footer.jsp"%>
        <%@include file="resource/components/script.jsp"%>
    </body>
</html>
