<%-- 
    Document   : ServiceList
    Created on : Feb 19, 2022, 10:21:38 PM
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Service List<small></small></h1>
                    </div>

                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Services</a></li>
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
                                <h3 class="panel-title">Services</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <form action="servicesearch" method="GET">                                           
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" name="result" placeholder="type here to search">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>
                                            <div class="col-md-5" >
                                                <a href="serviceadd" class="btn btn-default" style="float: right;"/>Create New Service</a>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5" ">
                                                <select class="form-control formstyle" name="category" style="padding: 4px 12px;">
                                                    <option 
                                                        value="0">---- Choose Category of Service ----
                                                    </option>
                                                    <c:forEach items="${listCategory}" var="c">
                                                        <option 
                                                            value="${c.ID}">${c.settingname} 
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <br>
                                <table id="example" class="dataTable table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Category</th>
                                            <th>Price</th>
                                            <th>Status</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listServices}" var="service">
                                            <tr onclick="location.href = 'servicefeedback?id=${service.ID}'">
                                                <td>${service.ID}</td>
                                                <td>${service.title}</td>
                                                <td>${service.category.settingname}</td>
                                                <td>${service.price}</td>
                                                <td>
                                                    <c:if test="${service.status == 1}">Active</c:if>
                                                    <c:if test="${service.status == 0}">Inactive</c:if>
                                                    </td>
                                                    <td style="text-align: center;"><a class="btn btn-default" href="servicedetail?id=${service.ID}">Edit</a></td>
                                                <td style="vertical-align: middle;">
                                                    <form action="servicelist" method="post">
                                                        <input type="hidden" value="${service.ID}" name="serviceid">
                                                        <c:if test="${service.status == 1}">
                                                            <input type="checkbox" id="${service.ID}" class="check" checked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                        </c:if>
                                                        <c:if test="${service.status == 0}">
                                                            <input type="checkbox" id="${service.ID}" class="check" unchecked onchange="this.form.submit()" onclick="return confirm('Are you sure you want to change status?');">
                                                        </c:if>
                                                        <label for="${service.ID}" class="checktoggle"></label>
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
