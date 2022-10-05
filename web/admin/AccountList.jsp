<%-- 
    Document   : AccountList
    Created on : Feb 26, 2022, 8:05:31 PM
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Manage Account<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Account List</a></li>
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
                                <h3 class="panel-title">Account</h3>
                            </div>
                            <div class="panel-body">
                                <form action="accountlist" method="get">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" name="search" value="${search}" placeholder="type here to search">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>
                                            <div class="col-md-5" >
                                                <a class="btn btn-default" href="addaccount" style="float: right;">Add new Account</a>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <select class="form-control formstyle" name="role" style="padding: 4px 12px;">
                                                    <option value="0">---Role---</option>
                                                    <c:forEach items="${listRole}" var="lr">
                                                    <option value="${lr.ID}" ${role1==lr.ID?"selected":""}>${lr.settingname}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="" type="radio" name="status" value="0"  ${status1==0?"checked":""}>Inactive
                                                <input class="" type="radio" name="status" value="1" ${status1==1?"checked":""}>Active
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <table id="example" class="dataTable table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Full Name</th>
                                            <th>Gender</th>
                                            <th>Email</th>
                                            <th>Mobile</th>
                                            <th>User Name</th>
                                            <th>Status</th>
                                            <th></th>
                                            <th></th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${accounts}" var="a">
                                            <tr>
                                                <td>${a.id}</td>
                                                <td>${a.fullName}</td>
                                                <td>
                                                    <c:if test="${a.gender == 1}">Male</c:if>
                                                    <c:if test="${a.gender == 0}">Female</c:if>
                                                    </td>
                                                    <td>${a.email}</td>
                                                <td>${a.phoneNumber}</td>
                                                <td>${a.username}</td>

                                                <td>${a.status==1?"Active":"Inactive"}</td>
                                                <td style="text-align: center;"><a class="btn btn-default" href="accountdetailedit?id=${a.id}">Edit</a> </td>
                                        <form action="accountlist" method="post">
                                            <td style="vertical-align: middle;">
                                                <input type="hidden" value="${a.id}" name="accountid">
                                                <c:if test="${a.status == 1}">
                                                    <input type="checkbox" id="${a.id}" name="accountstatus" value="0" class="check" checked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                </c:if>
                                                <c:if test="${a.status == 0}">
                                                    <input type="checkbox" id="${a.id}" name="accountstatus" value="1" class="check" unchecked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                </c:if>
                                                <label for="${a.id}" class="checktoggle"></label>
                                            </td>
                                            <td>
                                                <select class="form-control" name="role" style="padding: 4px 12px;" onchange="this.form.submit()">
                                                    <c:forEach items="${listRole}" var="lr">
                                                    <option value="${lr.ID}" ${a.role==lr.ID?"selected":""}>${lr.settingname}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </form>
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
