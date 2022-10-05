<%-- 
    Document   : Error404-jsp
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
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Doctor Profile</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Doctor Profile</h2>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Breadcrumb -->

            <!-- Page Content -->
            <div class="content">
                <div class="container">

                    <!-- Doctor Widget -->
                    <div class="card">
                        <div class="card-body">
                            <div class="doctor-widget">
                                <div class="doc-info-left">
                                    <div class="doctor-img">
                                        <img src="data:image/jpeg;base64,${doctor.imageString}" class="img-fluid" alt="User Image">
                                    </div>
                                    <div class="doc-info-cont">
                                        <h4 class="doc-name">Dr. ${doctor.doctorName}</h4>
                                        <p class="doc-speciality">${doctor.role}</p>
                                        <p class="doc-department">${doctor.speciality.settingname}</p>
                                        <div class="rating">
                                            <i class="fas fa-star ${avgrate > 0 ? "filled" : ""}"></i>
                                            <i class="fas fa-star ${avgrate > 1 ? "filled" : ""}"></i>
                                            <i class="fas fa-star ${avgrate > 2 ? "filled" : ""}"></i>
                                            <i class="fas fa-star ${avgrate > 3 ? "filled" : ""}"></i>
                                            <i class="fas fa-star ${avgrate > 4 ? "filled" : ""}"></i>
                                            <span class="d-inline-block average-rating">(${totalfeedback})</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="doc-info-right">
                                    <div class="clini-infos" style="margin-bottom: 70px">
                                        <ul>                          
                                            <li><i class="far fa-comment"></i> ${totalfeedback} Feedbacks</li>
                                        </ul>
                                    </div>
                                    <div class="clinic-booking">
                                        <a class="apt-btn" href="bookappointment?id=${doctor.id}">Book Appointment</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /Doctor Widget -->

                    <!-- Doctor Details Tab -->
                    <div class="card">
                        <div class="card-body pt-0">

                            <!-- Tab Menu -->
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
                            <!-- /Tab Menu -->

                            <!-- Tab Content -->
                            <div class="tab-content pt-0">

                                <!-- Overview Content -->
                                <div role="tabpanel" id="doc_overview" class="tab-pane fade show active">
                                    <div class="row">
                                        <div class="col-md-12 col-lg-12">

                                            <!-- About Details -->
                                            <div class="widget about-widget">
                                                <h4 class="widget-title">About Me</h4>
                                                <p>${doctor.description}</p>
                                            </div>
                                            <!-- /About Details -->

                                            <!-- Specializations List -->
                                            <div class="service-list">
                                                <h4>Specializations</h4>
                                                <ul class="clearfix">
                                                    <li>${doctor.speciality.settingname}</li>
                                                </ul>
                                            </div>
                                            <!-- /Specializations List -->

                                        </div>
                                    </div>
                                </div>
                                <!-- /Overview Content -->

                                <!-- Reviews Content -->
                                <div role="tabpanel" id="doc_reviews" class="tab-pane fade">

                                    <!-- Review Listing -->
                                    <div class="widget review-listing">
                                        <c:forEach items="${listF}" var="feedback">
                                            <ul class="comments-list">

                                                <!-- Comment List -->
                                                <li>
                                                    <div class="comment" style="width: 100%">
                                                        <img class="avatar avatar-sm rounded-circle" alt="user-image" src="data:image/jpeg;base64,${feedback.patient.imageString}">
                                                        <div class="comment-body" style="width: 100%">
                                                            <div id="show${feedback.patient}">
                                                                <div class="meta-data">
                                                                    <span class="comment-author">${feedback.patient.name}</span>
                                                                    
                                                                    <div class="review-count rating">
                                                                        <i class="fas fa-star ${feedback.star > 0 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${feedback.star > 1 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${feedback.star > 2 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${feedback.star > 3 ? "filled" : ""}"></i>
                                                                        <i class="fas fa-star ${feedback.star > 4 ? "filled" : ""}"></i>
                                                                    </div>
                                                                   <fmt:parseDate var="p_date" value="${feedback.date}" pattern="yyyy-MM-dd"/>
                                                                    <fmt:formatDate var="date" value="${p_date}" pattern="dd-MM-yyyy"/>
                                                                    <span class="comment-date">Reviewed ${date}</span>
                                                                </div>
                                                                <p class="comment-content">
                                                                    ${feedback.comment} 
                                                                </p>
                                                            </div>

                                                            <c:if test="${feedback.patient.ID == sessionScope.user.ID}">
                                                                <div class="actions" style="position:absolute;margin-top: -48px;margin-left: 81%" id="editBtn">
                                                                    <a class="btn btn-sm bg-success-light" data-toggle="modal" onclick="myFunctionEditfeedback()" href="#editDoctorFeedback">
                                                                        <i class="fe fe-pencil"></i> Edit
                                                                    </a>
                                                                    <a class="btn btn-sm bg-danger-light" onclick="return confirm('Are you sure you want to delete this feedback?');" href="dtinfo?action=delete&fid=${feedback.id}&sid=${feedback.doctor.id}"/>
                                                                        <i class="fe fe-trash"></i> Delete
                                                                    </a>
                                                                </div>
                                                                <script>
                                                                    function myFunctionEditfeedback() {
                                                                        var x = document.getElementById("editfeedback");
                                                                        var y = document.getElementById("show${feedback.patient}");
                                                                        if (x.style.display === "none") {
                                                                            x.style.display = "block";
                                                                            y.style.display = "none";
                                                                            document.getElementById("editBtn").style.marginTop = "68px";
                                                                        } else {
                                                                            x.style.display = "none";
                                                                            y.style.display = "block";
                                                                            document.getElementById("editBtn").style.marginTop = "-48px";
                                                                        }
                                                                    }
                                                                </script>
                                                                <div id="editfeedback" style="display: none">
                                                                    <form action="doctor_feedbacks" method="get">
                                                                        <div class="form-group" style="margin-bottom: 5px">
                                                                            <input type="hidden" value="edit" name="action">
                                                                            <input type="hidden" value="${feedback.patient.ID}" name="patient_id">
                                                                            <input type="hidden" value="${feedback.doctor.id}" name="doctor_id">
                                                                            <div class="meta-data" >
                                                                                <span class="comment-author">${feedback.patient.name}</span>
                                                                                <span class="comment-date">Reviewed ${date}</span>
                                                                            </div>
                                                                            <div class="star-rating">
                                                                                <input id="star-5" type="radio" name="rating" value="5" ${feedback.star == 5 ? "checked" : ""}>
                                                                                <label for="star-5" title="5 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-4" type="radio" name="rating" value="4" ${feedback.star == 4 ? "checked" : ""}>
                                                                                <label for="star-4" title="4 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-3" type="radio" name="rating" value="3" ${feedback.star == 3 ? "checked" : ""}>
                                                                                <label for="star-3" title="3 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-2" type="radio" name="rating" value="2" ${feedback.star == 2 ? "checked" : ""}>
                                                                                <label for="star-2" title="2 stars">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                                <input id="star-1" type="radio" name="rating" value="1" ${feedback.star == 1 ? "checked" : ""}>
                                                                                <label for="star-1" title="1 star">
                                                                                    <i class="active fa fa-star"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label>Your review</label>
                                                                            <textarea id="review_desc" maxlength="100" class="form-control" placeholder="${feedback.comment}" name="commtent" required></textarea>
                                                                            <div class="d-flex justify-content-between mt-3"><small class="text-muted"><span id="chars">100</span> characters remaining</small></div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="submit-section">
                                                                            <button type="submit" class="btn btn-primary submit-btn">Submit Editing</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>

                                                </li>
                                                <!-- /Comment List -->
                                            </ul>
                                        </c:forEach>

                                        <!-- Show All -->
                                        <div class="all-feedback text-center">
                                            <a href="#" class="btn btn-primary btn-sm">
                                                Show all feedback <strong>(${totalfeedback})</strong>
                                            </a>
                                        </div>
                                        <!-- /Show All -->

                                    </div>
                                    <!-- /Review Listing -->

                                    <!-- Write Review -->
                                    <c:if test="${sessionScope.acc != null && sessionScope.acc.role == 3}">
                                        <div class="write-review">
                                            <h4>Write a review for <strong>Dr. ${doctor.doctorName}</strong></h4>

                                            <!-- Write Review Form -->
                                            <form action="dtinfo?sid=${doctor.id}&choice=3" method="post">
                                                <input type="hidden" name="patientID" value="${sessionScope.user.ID}">
                                                <input type="hidden" name="sid" value="${doctor.id}">
                                                <div class="form-group">
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
                                                    <textarea id="review_desc" maxlength="100" class="form-control" name="comment"></textarea>

                                                    <div class="d-flex justify-content-between mt-3"><small class="text-muted"><span id="chars">100</span> characters remaining</small></div>
                                                </div>
                                                <hr>
                                                <div class="submit-section">
                                                    <button type="submit" class="btn btn-primary submit-btn">Add Review</button>
                                                </div>
                                            </form>
                                            <!-- /Write Review Form -->

                                        </div>
                                    </c:if>
                                    <!-- /Write Review -->

                                </div>
                                <!-- /Reviews Content -->

                               
                            </div>
                        </div>
                        <!-- /Doctor Details Tab -->

                    </div>
                </div>	
            </div>
            <%@include file="resource/components/footer.jsp" %>
        </div>
        <%@include file="resource/components/script.jsp" %>
    </body>
</html>
