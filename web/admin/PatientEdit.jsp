<%-- 
    Document   : PatientDetail
    Created on : Feb 8, 2022, 8:00:45 PM
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
                                <h3 class="panel-title">Patient Edit</h3>
                            </div>
                            <form action="patientedit" method="POST">  
                                <div class="panel-body">
                                    <div class="row" style="margin-right: 0px;">      
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input  type="hidden" name="id" value="${st.ID}">
                                                <label>Full Name:</label>
                                                <input class="form-control formstyle" type="text" name="fullname" value="${st.name}" required="">
                                                <label>Gender:</label>
                                                <c:if test="${st.gender == 0}">
                                                    <input type="radio" name="gender" value="1">Male     
                                                    <input type="radio" name="gender" value="0" checked="">Female
                                                </c:if>
                                                <c:if test="${st.gender == 1}">
                                                    <input type="radio" name="gender" value="1" checked="">Male        
                                                    <input type="radio" name="gender" value="0">Female
                                                </c:if>
                                                <br>
                                                <label>DOB:</label>
                                                <input class="form-control formstyle" type="date" name="dob" value="${st.DOB}" required="">
                                                <label>Phone:</label>
                                                <input class="form-control formstyle" type="number" name="phone" value="${st.phone}" required="">                 
                                                <label>Address:</label>
                                                <input class="form-control formstyle" type="text" name="address" value="${st.address}" required="">
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-right: 0px;">     
                                            <a href="patientlist" class="btn btn-default" style="float: right;">Cancel</a>
                                            <input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to edit this patient?');"/>                                             
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
