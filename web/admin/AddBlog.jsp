<%-- 
    Document   : AddBlog
    Created on : Mar 12, 2022, 1:53:54 PM
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
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Add New Blog<small></small></h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="index.html">Dashboard | Add Blogs</a></li>
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
                                <h3 class="panel-title">Add Blog</h3>
                            </div>
                            <form action="addblog" method="post" enctype="multipart/form-data">
                                <div class="panel-body">
                                    <div class="row" style="margin-right: 0px;">      

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Title: </label>
                                                <input class="form-control formstyle" type="text" name="title" required="">
                                                <label>Flag:</label>
                                                <input type="checkbox" id="flag1" name="flag" class="check" value="1" checked="">
                                                <label for="flag1" class="checktoggle"></label>
                                                <br>
                                                <label>Category:</label>
                                                <select class="form-control formstyle" name="categorylist" style="padding: 4px 12px;">
                                                    <option value="0">---Category---</option>
                                                    <c:forEach items="${categories}" var="cate">
                                                        <option value="${cate.ID}">${cate.settingname}</option>
                                                    </c:forEach>

                                                </select>
                                                <label>Status:</label>
                                                <input type="checkbox" id="status1" name="status" class="check" value="1" checked="">
                                                <label for="status1" class="checktoggle"></label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div style="text-align: center;">
                                                    <label>Thumbnail</label>
                                                </div>
                                                <img src="resource/img/NoAvartar.jpg" name="image" class="avatar-detail" style="max-height: 165px;"/>
                                                    <br></br>
                                                    <div style="text-align: center;">
                                                        <label>Choose Photos To Update Thumbnail</label>
                                                        <input class="fileinputform" name="thumbnail" type="file"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <label>Brief Infomation: </label>
                                                    <textarea class="ckeditor" name="brief" required=""></textarea>
                                            </div>
                                            <div class="col-md-12">
                                                <label>Content: </label>
                                                <textarea class="ckeditor" name="content" required=""></textarea>
                                            </div>
                                            <div class="col-md-12" style="padding-right: 0px;">
                                                <a href="bloglist" class="btn btn-default" style="float: right;">Exit</a>
                                                <input class="btn btn-default" type="submit" style="float: right;" value="Save" onclick="return confirm('Are you sure you want to add new blog?');"/>
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
        <%@include file="resource/components/script.jsp" %>    </body>
</html>
