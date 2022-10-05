<%-- 
    Document   : ServiceFeedback
    Created on : Mar 12, 2022, 1:46:35 AM
    Author     : vinnielong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp"%>
    <body>
        <%@include file="resource/components/admin-header.jsp"%>

        <header id="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Service Feedback<small></small></h1>
                    </div>

                </div>
            </div>
        </header>

        <section id="breadcrumb">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a href="dashboard">Dashboard | Service Feedback</a></li>
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
                                <h3 class="panel-title">Services Feedback</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12" style="text-align: center;">
                                        <h2>Feedback of ${service.title}</h2>
                                    </div>
                                </div>
                                <br>
                                <div class="tab-pane">
                                    <div style="text-align: right">
                                        <button class="btn btn-small btn-primary"" >
                                            All<a href="servicefeedback?star=999&id=${service.ID}&option=filter" style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                        <button class="btn btn-small btn-primary"" >
                                            1<a href="servicefeedback?star=1&id=${service.ID}&option=filter"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                        <button class="btn btn-small btn-primary"">
                                            2<a href="servicefeedback?star=2&id=${service.ID}&option=filter"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                        <button class="btn btn-small btn-primary"" >
                                            3<a href="servicefeedback?star=3&id=${service.ID}&option=filter"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                        <button class="btn btn-small btn-primary"" >
                                            4<a href="servicefeedback?star=4&id=${service.ID}&option=filter"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                        <button class="btn btn-small btn-primary"" >
                                            5<a href="servicefeedback?star=5&id=${service.ID}&option=filter"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                        </button>
                                    </div>
                                    <br>
                                    <div class="widget review-listing">
                                        <ul class="comments-list">
                                            <c:forEach items="${listF}" var="o">
                                                <li>
                                                    <div class="comment" style="width: 100%">
                                                        <img class="avatar-feedback avatar-sm" alt="User Image" src="data:image/jpeg;base64,${o.patient.imageString}">
                                                        <div class="comment-body" style="width: 100%">
                                                            <div id="show1">
                                                                <div class="meta-data">
                                                                    <span class="comment-author">${o.patient.name}</span>
                                                                    <div class="review-count rating">
                                                                        <i class="fas fa-star ${o.star > 0 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${o.star > 1 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${o.star > 2 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${o.star > 3 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${o.star > 4 ? "filled" : ""}"></i>
                                                                    </div>
                                                                </div>
                                                                <p class="comment-content">
                                                                    ${o.comment}
                                                                </p>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
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
