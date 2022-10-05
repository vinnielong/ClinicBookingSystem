<%-- 
    Document   : WebBlogDetail
    Created on : Mar 13, 2022, 2:11:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>
    <body>
        <!-- Main Wrapper -->
        <div class="main-wrapper">

            <!-- Header -->
            <%@include file="resource/components/header.jsp" %>
            <!-- /Header -->

            <!-- Breadcrumb -->
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="homepage">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Blog</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Blog Details</h2>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Breadcrumb -->

            <!-- Page Content -->
            <div class="content">
                <div class="container">

                    <div class="row">

                        <div class="col-lg-8 col-md-12">

                            <!-- Blog Details -->
                            <div class="blog-view">
                                <div class="blog blog-single-post">
                                    <c:if test="${blog.thumbnailString ne ''}"> 
                                        <div class="blog-image">
                                            <a href="javascript:void(0);"><img alt="" src="data:image/jpeg;base64,${blog.thumbnailString}" value="${blog.thumbnailString}" class="img-fluid"></a>
                                        </div>
                                    </c:if> 
                                    <h3 class="blog-title">${blog.title}</h3>
                                    <div class="blog-info clearfix">
                                        <div class="post-left">
                                            <ul>
                                                <li>
                                                    <div class="post-author">
                                                        <span>${blog.author.fullName}</span></a>
                                                    </div>
                                                </li>
                                                <li><i class="far fa-calendar"></i><fmt:formatDate type="date" value="${blog.postDate}" /></li>
                                                <li><a href="webbloglist?categoryId=${blog.category.ID}"><i class="fa fa-tags"></i>${blog.category.settingname}</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="blog-content">
                                        ${blog.content}
                                    </div>
                                </div>
                            </div>
                            <!-- /Blog Detail -->
                        </div>

                        <!-- Blog Sidebar -->
                        <div class="col-lg-4 col-md-12 sidebar-right theiaStickySidebar">

                            <!-- Search -->
                            <div class="card search-widget">
                                <div class="card-body">
                                    <form class="search-form" action="webbloglist" method="get">
                                        <div class="input-group">
                                            <input type="text" placeholder="Search..." name="txtSearch" value="${search}" class="form-control" required="">
                                            <div class="input-group-append">
                                                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- /Search -->

                            <!-- Latest Posts -->
                            <div class="card post-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Featured Posts</h4>
                                </div>
                                <c:forEach items="${top5Featured}" var="f">
                                    <div class="card-body">
                                        <ul class="latest-posts">
                                            <li>
                                                <div class="post-thumb">
                                                    <a href="webblogdetail?id=${f.id}">
                                                        <img class="img-fluid" <c:if test="${f.thumbnailString eq null or f.thumbnailString eq ''}"> src="http://haiphongtours.vn/wp-content/uploads/2016/10/no-image-available.jpg"</c:if> <c:if test="${f.thumbnailString ne null}"> src="data:image/jpeg;base64,${f.thumbnailString}" value="${f.thumbnailString}" </c:if> alt="Featured">
                                                        </a>
                                                    </div>
                                                    <div class="post-info">
                                                        <h4>
                                                                <a href="webblogdetail?id=${f.id}">${f.title}</a>
                                                    </h4>
                                                    <p><fmt:formatDate type="date" value="${f.postDate}" />  </p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- /Latest Posts -->

                            <!-- Categories -->
                            <div class="card category-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Blog Categories</h4>
                                </div>
                                <c:forEach items="${categories}" var="cate">
                                    <div class="card-body">
                                        <ul class="categories">
                                            <li><a href="webbloglist?categoryId=${cate.ID}">${cate.settingname} <span><i class="fas fa-angle-double-right"></i></span></a></li>

                                        </ul>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- /Categories -->
                        </div>
                        <!-- /Blog Sidebar -->

                    </div>
                </div>

            </div>		
            <!-- /Page Content -->
            <!-- Footer -->
            <%@include file="resource/components/footer.jsp" %>
            <!-- /Footer -->
        </div>
        <!-- /Main Wrapper -->
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
