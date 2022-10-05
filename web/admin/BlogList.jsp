<%-- 
    Document   : BlogList
    Created on : Mar 11, 2022, 5:36:09 PM
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Manage Blogs<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="main">
            <div class="container">
                <div class="row">
                    <%@include file="resource/components/admin-sidebar.jsp"%>
                    <div class="col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading main-color-bg">
                                <h3 class="panel-title">Blog</h3>
                            </div>
                            <div class="panel-body">
                                <form action="bloglist" method="get">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" name="search" value="${search}" placeholder="type here to search">
                                            </div>
                                            <div class="col-md-2" style="padding: 0px;">
                                                <input type="submit" value="Search" class="btn btn-default"/>
                                            </div>
                                            <div class="col-md-5" >
                                                <a class="btn btn-default" href="addblog" style="float: right;">Add new Blog</a>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <select class="form-control formstyle" name="categorylist" style="padding: 4px 12px;">
                                                    <option value="0">---Category---</option>
                                                    <c:forEach items="${categories}" var="cate">
                                                        <option value="${cate.ID}" ${category1==cate.ID?"selected":""}>${cate.settingname}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                <select class="form-control formstyle" name="authorlist" style="padding: 4px 12px;">
                                                    <option value="0">---Author---</option>
                                                    <c:forEach items="${authors}" var="a">
                                                        <option value="${a.id}" ${author1==a.id?"selected":""}>${a.fullName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-5">
                                                Status:  
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
                                            <th>Thumbnail</th>
                                            <th>Title</th>
                                            <th>Category</th>
                                            <th>Author</th>
                                            <th>Featured</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${blogs}" var="b">
                                            <tr>
                                                <td style="vertical-align: middle;">${b.id}</td>
                                                <td><img class="img-responsive img-circle" width="304" height="150" <c:if test="${b.thumbnailString eq null or b.thumbnailString eq ''}"> src="resource/img/NoAvartar.jpg"</c:if> <c:if test="${b.thumbnailString ne null}"> src="data:image/jpeg;base64,${b.thumbnailString}" value="${b.thumbnailString}" </c:if> alt="Blog Image"></td>
                                                    <td style="text-align: center;vertical-align: middle;">
                                                    ${b.title}
                                                </td>
                                                <td style="text-align: center;vertical-align: middle;">${b.category.settingname}</td>
                                                <td style="text-align: center;vertical-align: middle;">${b.author.fullName}</td>


                                        <form action="bloglist" method="post">
                                            <input type="hidden" value="${b.id}" name="blogid">
                                            <td style="vertical-align: middle;">
                                                <c:if test="${b.featured == 1}">
                                                    <input type="checkbox" id="${b.id}" name="blogfeatured" value="1" class="check" checked onchange="this.form.submit()">
                                                </c:if>
                                                <c:if test="${b.featured == 0}">
                                                    <input type="checkbox" id="${b.id}" name="blogfeatured" value="0" class="check" unchecked onchange="this.form.submit()">
                                                </c:if>
                                                <label for="${b.id}" class="checktoggle"></label>
                                            </td>
                                            <td style="vertical-align: middle;">
                                                <c:if test="${b.status == 1}">
                                                    <input type="checkbox" id="${b.author}" name="blogstatus" value="1" class="check" checked onchange="this.form.submit()">
                                                </c:if>
                                                <c:if test="${b.status == 0}">
                                                    <input type="checkbox" id="${b.author}" name="blogstatus" value="0" class="check" unchecked onchange="this.form.submit()">
                                                </c:if>
                                                <label for="${b.author}" class="checktoggle"></label>
                                            </td>

                                        </form>
                                        <td style="text-align: center;vertical-align: middle;"><a class="btn btn-default" href="blogdetailedit?id=${b.id}">Edit</a> </td>
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
