<%-- 
    Document   : SettingDetail
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Setting<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Setting</a></li>
                </ol>
            </div>
        </section>

        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/admin-sidebar.jsp"%>
                    <div class="col-md-10">
                        <div class="panel panel-default">                         
                            <c:if test="${option == 'add'}">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Add Setting</h3>
                                </div>
                                <form action="settingdetail" method="post">
                                    <div class="panel-body">
                                        <div class="row" style="margin-right: 0px;">      
                                            <input type="hidden" name="id" />
                                            <input type="hidden" name="option" value="add"/>
                                            <div class="col-md-3">Name:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="name"  required=""></div>
                                            <div class="col-md-3">Value:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="number" name="value"  required=""></div>
                                            <br></br>
                                            <div class="col-md-3">Type:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;">
                                                <select class="form-control" name="type" style="padding: 4px 12px;" >
                                                    <option value="1">User Role</option>
                                                    <option value="2" >Speciality</option>
                                                    <option  value="3">Slot</option>
                                                </select>
                                            </div>                                      
                                            <div class="col-md-3">Status:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;">
                                                <input class="" type="radio" name="status" value="1" checked>Active
                                                <input class="" type="radio" name="status" value="0" >De-active
                                            </div>
                                            <br></br>
                                            <div class="col-md-3">Description:</div>
                                            <div class="col-md-9" style="padding: 0px;left: -40px;"><textarea class="form-control" name="description" style="height: 100px;" required=""></textarea>
                                                <br>
                                                <div class="col-md-12" style="padding-right: 0px;">
                                                    <a href="settinglist" class="btn btn-default" style="float: right;">Exit</a><input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to add new setting?');"/>
                                                </div>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                            <c:if test="${option == 'edit'}">
                                <div class="panel-heading main-color-bg">
                                    <h3 class="panel-title">Edit Setting</h3>
                                </div>
                                <form action="settingdetail" method="post">
                                    <div class="panel-body">
                                        <div class="row" style="margin-right: 0px;">      
                                            <input type="hidden" name="id" value="${setting.ID}"/>
                                            <input type="hidden" name="option" value="edit"/>
                                            <div class="col-md-3">Name:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="name" value="${setting.settingname}" required=""></div>
                                            <div class="col-md-3">Value:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;"><input class="form-control" type="text" name="value" value="${setting.value}" required=""></div>
                                            <br></br>
                                            <div class="col-md-3">Type:</div>
                                            <div class="col-md-3" style="padding: 0px;left: -40px;">
                                                <select class="form-control" name="type" style="padding: 4px 12px;" >
                                                    <option  value="1" <c:if test="${setting.typename == 1 }">selected</c:if>>User Role</option>
                                                    <option value="2" <c:if test="${setting.typename == 2 }">selected</c:if>>Speciality</option>
                                                    <option value="3" <c:if test="${setting.typename == 3}">selected</c:if>>Slot</option>
                                                    </select>
                                                </div>                                      
                                                <div class="col-md-3">Status:</div>
                                                <div class="col-md-3" style="padding: 0px;left: -40px;">
                                                    <input class="" type="radio" name="status" value="1" <c:if test="${setting.status == 1 }">checked</c:if>>Active
                                                    <input class="" type="radio" name="status" value="0"  <c:if test="${setting.status == 0 }">checked</c:if>>De-active
                                                </div>
                                                <br></br>
                                                <div class="col-md-3">Description:</div>
                                                <div class="col-md-9" style="padding: 0px;left: -40px;"><textarea class="form-control" name="description" style="height: 100px;" >${setting.description}</textarea>
                                                <br>
                                                <div class="col-md-12" style="padding-right: 0px;">
                                                    <a href="settinglist" class="btn btn-default" style="float: right;" >Exit</a><input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to update this setting?');"/>
                                                </div>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
        </section>

        <%@include file="resource/components/footer.jsp"%>
        <%@include file="resource/components/script.jsp"%>
    </body>
</html>

