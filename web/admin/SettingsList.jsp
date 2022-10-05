<%-- 
    Document   : SettingsList
    Created on : Jan 19, 2022, 11:42:28 PM
    Author     : Vinnie Long
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Settings List<small></small></h1>
                    </div>

                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Settings</a></li>
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
                                <h3 class="panel-title">Settings</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <form action="search" method="GET">                                           
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
                                                <select class="form-control formstyle" name="type" style="padding: 4px 12px;">
                                                    <option value="0">-----------Type-----------</option>
                                                    <option value="1" <c:if test="${type == 1}">selected</c:if>>User Role</option>
                                                    <option value="2" <c:if test="${type == 2}">selected</c:if>>Speciality</option>
                                                    <option value="3" <c:if test="${type == 3}">selected</c:if>>slot</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="col-md-5">
                                                    Status:  
                                                    <input class="" type="radio" name="status" value="1" <c:if test="${status == 1}">checked</c:if>>Active
                                                <input class="" type="radio" name="status" value="0" <c:if test="${status == 0}">checked</c:if>>Inactive
                                                </div>
                                            </div>
                                        </form>
                                        <div class="col-md-5" >
                                            <a href="settingdetail?option=add" class="btn btn-default" style="float: right;"/>Add</a>
                                        </div>
                                    </div>
                                    <br>
                                    <table id="example" class="dataTable table table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Type Name</th>
                                                <th>Setting Name</th>
                                                <th>Setting Value</th>
                                                <th>Status</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listSettings}" var="setting">
                                            <tr>
                                                <td>${setting.ID}</td>
                                                <td><c:if test="${setting.typename == 1}">User Role</c:if>
                                                    <c:if test="${setting.typename == 2}">Speciality</c:if>
                                                    <c:if test="${setting.typename == 3}">Slot</c:if></td>
                                                <td>${setting.settingname}</td>
                                                <td>${setting.value}</td>
                                                <td>
                                                    <c:if test="${setting.status == 1}">Active</c:if>
                                                    <c:if test="${setting.status == 0}">Inactive</c:if>
                                                    </td>
                                                    <td style="text-align: center;"><a class="btn btn-default" href="settingdetail?id=${setting.ID}&option=edit">Edit</a></td>
                                                <td style="vertical-align: middle;">
                                                    <form action="settinglist" method="post">
                                                        <input type="hidden" value="${setting.ID}" name="settingid">
                                                        <c:if test="${setting.status == 1}">
                                                            <input type="checkbox" id="${setting.ID}" class="check" checked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                        </c:if>
                                                        <c:if test="${setting.status == 0}">
                                                            <input type="checkbox" id="${setting.ID}" class="check" unchecked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                        </c:if>
                                                        <label for="${setting.ID}" class="checktoggle"></label>
                                                    </form>
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

