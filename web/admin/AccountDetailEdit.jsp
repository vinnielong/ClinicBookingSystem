<%-- 
    Document   : AccountDetailEdit
    Created on : Feb 27, 2022, 8:50:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>
    <body>
        <%@include file="resource/components/admin-header.jsp" %>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Account Details<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Account Details</a></li>
                </ol>
            </div>
        </section>

        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/admin-sidebar.jsp" %>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Edit Account</h3>
                            </div>
                            <form action="accountdetailedit" method="post" enctype="multipart/form-data">
                                <div class="panel-body">
                                    <div class="row" style="margin-right: 0px;">      

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>ID:</label>
                                                <input class="form-control formstyle" type="text" name="idid" value="${account.id}" readonly="true"/>
                                                <label>Full Name:</label>
                                                <input class="form-control formstyle" type="text" name="fullname" value="${account.fullName}" required="">
                                                <label>Gender:</label>
                                                <input class="" type="radio" name="gender" value="0" checked=${account.gender==0?"checked":""}>Female
                                                <input class="" type="radio" name="gender" value="1" checked=${account.gender==1?"checked":""}>Male
                                                <br>
                                                <label>Mobile:</label>
                                                <input class="form-control formstyle" type="text" name="phone" value="${account.phoneNumber}" required="">
                                                <label>Role:</label>
                                                <select class="form-control formstyle" name="role" style="padding: 4px 12px;">
                                                    <c:forEach items="${listRole}" var="lr">
                                                    <option value="${lr.ID}" ${account.role==lr.ID?"selected":""}>${lr.settingname}</option>
                                                    </c:forEach>
                                                </select>
                                                <label>Status:</label>
                                                <input type="checkbox" id="status1" name="status" class="check" value="${account.status}" ${account.status==1?"checked":""} >
                                                <label for="status1" class="checktoggle"></label>
                                                <br>
                                                <label>Email:</label>
                                                <input class="form-control formstyle" type="text" name="email" value="${account.email}" readonly="true">
                                                <label>User Name:</label>
                                                <input class="form-control formstyle" type="text" name="username" value="${account.username}" readonly="true">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div style="text-align: center;">
                                                    <label>Avatar</label>
                                                </div>
                                                <img <c:if test="${avatar eq null}"> src="resource/img/NoAvartar.jpg"</c:if> <c:if test="${avatar ne null}"> src="data:image/jpeg;base64,${avatar}" value="${avatar}" </c:if> name="image" class="avatar-detail" style="max-height: 165px;"/>
                                                    <br></br>
                                                    <div style="text-align: center;">
                                                        <label>Choose Photos To Update Avatar</label>
                                                        <input class="fileinputform" name="avatar" type="file"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12" style="padding-right: 0px;">
                                                <a href="accountlist" class="btn btn-default" style="float: right;">Exit</a>
                                                <input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to save new account?');"/>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        <%@include file="resource/components/footer.jsp" %>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
