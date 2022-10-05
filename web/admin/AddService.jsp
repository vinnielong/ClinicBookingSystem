<%-- 
    Document   : AddService
    Created on : Feb 20, 2022, 7:18:26 PM
    Author     : Vinnie Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp" %>
    <body>
        <%@include file="resource/components/admin-header.jsp" %>
        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Service<small></small></h1>
                    </div>
                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Service</a></li>
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
                                <h3 class="panel-title">Create New Service</h3>
                            </div>
                            <form action="serviceadd" method="post" enctype="multipart/form-data">
                                <div class="panel-body">
                                    <div class="row" style="margin-right: 0px;">      
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Title:</label>
                                                <input class="form-control formstyle" type="text" name="title" required="">
                                                <label>Price:</label>
                                                <input class="form-control formstyle" type="text" name="price" required="">
                                                <label>Category:</label>
                                                <select class="form-control formstyle" name="category" style="padding: 4px 12px;" >
                                                    <c:forEach items="${listCategory}" var="c">
                                                        <option 
                                                            value="${c.ID}">${c.settingname} 
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <label>Status:</label>
                                                <input type="checkbox" id="status" name="status" value="status" class="check" checked >
                                                <label for="status" value="active" class="checktoggle" style="display: inline-block; margin: 14px 0px -7px 10px"></label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Description:</label>
                                            <div class="form-group" style="padding: 0px;left: -40px;">
                                                <textarea class="form-control" name="description" value="description" style="height: 100px;">${service.description}</textarea>
                                                <br>
                                                <label>Thumbnail</label>
                                                <input class="fileinputform" name="thumbnail" type="file"/><br></br>
                                                <div class="col-md-12" style="padding-right: 0px;">
                                                    <a href="servicelist" class="btn btn-default" style="float: right;">Exit</a>
                                                    <input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to add new service?');"/>
                                                </div>
                                            </div>
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

