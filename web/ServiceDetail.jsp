<%-- 
    Document   : ServiceDetail.jsp
    Created on : Mar 10, 2022, 1:12:16 AM
    Author     : vinnielong
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp" %>
    <body>
        <div class="main-wrapper">
            <%@include file="resource/components/header.jsp" %>          
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item">
                                        <a href="homepage">Home</a>
                                    </li>
                                    <li class="breadcrumb-item " >
                                        <a href="service">Services</a>
                                    </li>
                                    <li class="breadcrumb-item active" aria-current="page">
                                        ${service.category.settingname}
                                    </li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">${service.title}</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container">
                    <div class="card">
                        <div class="card-body">
                            <div class="doctor-widget">
                                <div class="doc-info-left">
                                    <div class="doctor-img">
                                        <c:choose>
                                            <c:when test="${o.thumbnailString ne ''}"> 
                                                <img src="data:image/jpeg;base64,${service.thumbnailString}" class="img-fluid" alt="User Image">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="resource/images/doccure.png" class="img-fluid" alt="User Image"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    </style>
                                    <div class="doc-info-cont">
                                        <h4 class="doc-name">${service.title}</h4>
                                        <h5 class="doc-department">${service.category.settingname}</h5>
                                        <div class="rating">
                                            <div class="review-count rating">
                                                <i class="fas fa-star ${avgrate > 0 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 1 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 2 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 3 ? "filled" : ""}"></i>
                                                <i class="fas fa-star ${avgrate > 4 ? "filled" : ""}"></i>
                                            </div>
                                            <span class="d-inline-block average-rating">(Average of ${totalfeedback} Feedbacks)</span>
                                        </div>
                                        <div class="clinic-details">
                                            <c:if test="${avrate >= 4}">
                                                <p class="recommended" style="margin-bottom: 10px;font-weight: bold"> Recommended Service</p>
                                            </c:if>
                                        </div>
                                        <h4 style="color: #00d2e6">Related Services:</h4>
                                        <div class="clinic-services">
                                            <ul>
                                                <c:forEach items="${listService}" var="o">
                                                    <c:if test="${o.title != service.title}">
                                                        <li><a href="serviceinfo?sid=${o.ID}">${o.title}</a></li>
                                                        </c:if>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="doc-info-right">
                                    <div class="clini-infos">
                                        <ul>
                                            <li><i class="far fa-comment"></i> ${totalfeedback} Feedbacks</li>
                                            <li><i class="far fa-money-bill-alt"></i> <fmt:formatNumber value = "${service.price}" type = "number"/> $</li>
                                            <br>
                                        </ul>
                                    </div>
                                    <br><br>
                                    <div class="clinic-booking">
                                        <a class="apt-btn" href="reservation?id=${service.ID}">Book Service</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-body pt-0">

                            <nav class="user-tabs mb-4">
                                <ul class="nav nav-tabs nav-tabs-bottom nav-justified">
                                    <li class="nav-item">
                                        <a class="nav-link active" href="#doc_overview" data-toggle="tab">Overview</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#doc_reviews" data-toggle="tab">Reviews</a>
                                    </li>
                                </ul>
                            </nav>

                            <div class="tab-content pt-0">
                                <div role="tabpanel" id="doc_overview" class="tab-pane fade show active">
                                    <div class="row">
                                        <div class="col-md-12 col-lg-12">                                           
                                            <div class="widget about-widget">
                                                <h4 class="widget-title">${service.title}</h4>
                                                <p>${service.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div role="tabpanel" id="doc_reviews" class="tab-pane fade">
                                    <c:if test="${totalfeedback != 0}">
                                        <div style="text-align: right">
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter" >
                                                All<a href="serviceinfo?star=999&&sid=${service.ID}&&option=filter#doc_reviews" style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter" >
                                                1<a href="serviceinfo?star=1&&sid=${service.ID}&&option=filter#doc_reviews"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter">
                                                2<a href="serviceinfo?star=2&&sid=${service.ID}&&option=filter#doc_reviews"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter" >
                                                3<a href="serviceinfo?star=3&&sid=${service.ID}&&option=filter#doc_reviews"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter" >
                                                4<a href="serviceinfo?star=4&&sid=${service.ID}&&option=filter#doc_reviews"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                            <button class="btn btn-small btn-primary" data-toggle="portfilter" >
                                                5<a href="serviceinfo?star=5&&sid=${service.ID}&&option=filter#doc_reviews"style="color: yellow"> <i class="fas fa-star filled"></i></a>
                                            </button>
                                        </div>
                                    </c:if>
                                    <c:if test="${totalfeedback == 0}">
                                        <c:if test="${sessionScope.acc.role == 3}">
                                            <div style="text-align: center; color: gray;">
                                                <h3 style="margin: 45px auto">No feedback! Please give us your thought!</h3>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.role != 3}">
                                            <div style="text-align: center; color: gray;">
                                                <h3 style="margin: 45px auto">No feedback! Please login to Patient account and give us your thought!</h3>
                                            </div>
                                        </c:if>
                                    </c:if>
                                    <div class="widget review-listing">
                                        <ul class="comments-list">
                                            <c:forEach items="${listF}" var="o">
                                                <li>
                                                    <div class="comment" style="width: 100%">
                                                        <img class="avatar avatar-sm rounded-circle" alt="User Image" src="data:image/jpeg;base64,${o.patient.imageString}">
                                                        <div class="comment-body" style="width: 100%">
                                                            <div id="show${o.id}">
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
                                                            <c:if test="${o.patient.name == sessionScope.user.name}">
                                                                <button style="float: right; color: red; margin-left: 10px; border: solid 1px; padding: 2px;">
                                                                    <a style="color: red;margin: 14px;font-size: 18px;" onclick="return confirm('Are you sure you want to delete this comment?');" href="serviceinfo?fid=${o.id}&&sid=${service.ID}&&option=delete"><i class="fas fa-trash-alt"></i></a>
                                                                </button> 
                                                                <button style="float: right; color: #20C0F3; margin-left: 10px; border: solid 1px; padding: 2px;";" onclick="myFunctionEditfeedback${o.id}()">
                                                                    <a style="color: black;margin: 14px;font-size: 18px;"><i class="fas fa-pencil-alt"></i></a>
                                                                </button>
                                                                <script>
                                                                    function myFunctionEditfeedback${o.id}() {
                                                                        var x = document.getElementById("editfeedback${o.id}");
                                                                        var y = document.getElementById("show${o.id}");
                                                                        if (x.style.display === "none") {
                                                                            x.style.display = "block";
                                                                            y.style.display = "none";
                                                                        } else {
                                                                            x.style.display = "none";
                                                                            y.style.display = "block";
                                                                        }
                                                                    }
                                                                </script>
                                                                <div id="editfeedback${o.id}" style="display: none">
                                                                    <form action="serviceinfo?fid=${o.id}&&sid=${service.ID}&&choice=2" method="post">
                                                                        <input name="patientID" value="${sessionScope.user.ID}" style="display: none">
                                                                        <div class="form-group" style="margin-bottom: 5px">
                                                                            <div class="meta-data" style="width: 50%">
                                                                                <span class="comment-author">${o.patient.name}</span>
                                                                            </div>
                                                                            <div class="star-rating">
                                                                                <input id="star-5${o.id}" type="radio" name="${o.id}" value="5" ${o.star == 5 ? "checked" : ""}>
                                                                                <label for="star-5${o.id}" title="5 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-4${o.id}" type="radio" name="${o.id}" value="4" ${o.star == 4 ? "checked" : ""}>
                                                                                <label for="star-4${o.id}" title="4 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-3${o.id}" type="radio" name="${o.id}" value="3" ${o.star == 3 ? "checked" : ""}>
                                                                                <label for="star-3${o.id}" title="3 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-2${o.id}" type="radio" name="${o.id}" value="2" ${o.star == 2 ? "checked" : ""}>
                                                                                <label for="star-2${o.id}" title="2 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-1${o.id}" type="radio" name="${o.id}" value="1" ${o.star == 1 ? "checked" : ""}>
                                                                                <label for="star-1${o.id}" title="1 star">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label>Your review</label>
                                                                            <textarea id="review_desc" maxlength="100" class="form-control" placeholder="${o.comment}" name="comment" required></textarea>
                                                                            <div class="d-flex justify-content-between mt-3"><small class="text-muted"><span id="chars">100</span> characters remaining</small></div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="submit-section">
                                                                            <button type="submit"  onclick="return confirm('Are you sure you want to update this comment?');" class="btn btn-primary submit-btn">Submit Editing</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach> 
                                        </ul>
                                    </div>      

                                    <div class="all-feedback text-center">
                                        <a href="#" class="btn btn-primary btn-sm">
                                            Show all feedback <strong>(${totalfeedback})</strong>
                                        </a>
                                    </div>

                                    <c:if test="${sessionScope.acc.role == 3}">
                                        <div class="write-review">
                                            <h4>Write a review for Service <strong>${service.title}</strong></h4>                                                                                    
                                            <form action="serviceinfo?sid=${service.ID}&&choice=3" method="post">
                                                <input name="patientID" value="${sessionScope.user.ID}" style="display: none">
                                                <div class="form-group" style="margin-bottom: 5px">
                                                    <label>Review</label>
                                                    <div class="star-rating">
                                                        <input id="star5" type="radio" name="rate" value="5">
                                                        <label for="star5" title="5 stars">
                                                            <i class="active fa fa-star"></i>
                                                        </label>
                                                        <input id="star4" type="radio" name="rate" value="4">
                                                        <label for="star4" title="4 stars">
                                                            <i class="active fa fa-star"></i>
                                                        </label>
                                                        <input id="star3" type="radio" name="rate" value="3">
                                                        <label for="star3" title="3 stars">
                                                            <i class="active fa fa-star"></i>
                                                        </label>
                                                        <input id="star2" type="radio" name="rate" value="2">
                                                        <label for="star2" title="2 stars">
                                                            <i class="active fa fa-star"></i>
                                                        </label>
                                                        <input id="star1" type="radio" name="rate" value="1">
                                                        <label for="star1" title="1 star">
                                                            <i class="active fa fa-star"></i>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Your review</label>
                                                    <textarea id="review_desc" maxlength="100" class="form-control" name="comment" required></textarea>
                                                </div>
                                                <br>
                                                <div class="submit-section">
                                                    <button type="submit" class="btn btn-primary submit-btn">Add Review</button>
                                                </div>
                                            </form>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="resource/components/footer.jsp" %>
        </div>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
