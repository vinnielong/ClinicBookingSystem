<%-- 
    Document   : Error404-jsp
    Created on : Mar 9, 2022, 5:50:16 PM
    Author     : vinnielong
--%>

<%@page import="dal.DoctorDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="resource/components/head.jsp" %>
    <body>      
        <div class="main-wrapper">
            <%@include file="resource/components/header.jsp" %>        
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-8 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item">
                                        <a href="homepage">Home</a>
                                    </li>
                                    <li class="breadcrumb-item active" aria-current="page">
                                        <a href="doctor">Doctor</a>
                                    </li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Available Doctor in Our System</h2>
                        </div>                      
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 col-lg-4 col-xl-3 theiaStickySidebar">
                            <!-- Search Filter -->
                            <div class="card search-filter">
                                <div class="card-body"> 
                                    <h4 class="card-title mb-0">Search filter</h4>
                                    <form action="dtlist" method="GET">
                                        <div class="card-body">
                                            <div class="filter-widget">
                                            <h4>Gender</h4>
                                            <div>
                                                <label class="custom_check">
                                                    <input type="checkbox" name="gender_type" value="Male" ${gender==1?"checked":""}>
                                                    <span class="checkmark"></span> Male Doctor
                                                </label>
                                            </div>
                                            <div>
                                                <label class="custom_check">
                                                    <input type="checkbox" name="gender_type" value="Female" ${gender==0?"checked":""}>
                                                    <span class="checkmark"></span> Female Doctor
                                                </label>
                                            </div>
                                                     <input type="text" hidden name="option" value="search">
                                        </div>
                                        </div>
                                        <div class="filter-widget">
                                            <h4>Select Specialist</h4>
                                            <c:forEach items="${listSpecialities}" var="spec">
                                                <div>
                                                    <label class="custom_check">
                                                        <input type="checkbox" id="saveState" value="${spec.settingname}" name="speciality"<c:if test="${spec.settingname == speciality}">checked=""</c:if>>
                                                        <span class="checkmark"></span> ${spec.settingname}
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="btn-search">
                                            <button type="submit" class="btn btn-block">Search</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 col-lg-8 col-xl-9">
                            <c:forEach items="${listDoctor}" var="o">
                                <c:set var="dao" value="<%=new DoctorDAO()%>"></c:set>
                                <c:set var="avgrate" value="${dao.averageRate(o.id)}"></c:set>
                                <c:set var="feedbackList" value="${dao.getFeedbackByDoctorID(o.id)}"></c:set>
                                    <!-- Doctor Widget -->
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="doctor-widget">
                                                <div class="doc-info-left">
                                                    <div class="doctor-img">
                                                        <a href="dtinfo?sid=${o.id}">
                                                        <img src="data:image/jpeg;base64,${o.imageString}" style="height: 150px;width: 150px" class="img-fluid" alt="User Image">
                                                    </a>
                                                </div>
                                                <div class="doc-info-cont">
                                                    <h4 class="doc-name"><a href="dtinfo?sid=${o.id}">${o.doctorName}</a></h4>

                                                    <h5 class="doc-department">${o.speciality.settingname}</h5>
                                                    <div class="rating">
                                                        <i class="fas fa-star ${avgrate > 0 ? "filled" : ""}"></i>
                                                        <i class="fas fa-star ${avgrate > 1 ? "filled" : ""}"></i>
                                                        <i class="fas fa-star ${avgrate > 2 ? "filled" : ""}"></i>
                                                        <i class="fas fa-star ${avgrate > 3 ? "filled" : ""}"></i>
                                                        <i class="fas fa-star ${avgrate > 4 ? "filled" : ""}"></i>
                                                        <span class="d-inline-block average-rating">(${feedbackList.size()})</span>
                                                    </div>
                                                    <div class="clinic-details">
                                                        <b>Description:</b><p style="margin: 0;width: 90%;height: 60px;overflow: hidden;-webkit-mask-image: linear-gradient(180deg, #000 75%, transparent);">${o.description}</p>
                                                        <b><a style="color: aqua" href="dtinfo?sid=${o.id}">See more...</a></b>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="doc-info-right">
                                                <div class="clini-infos">
                                                    <ul>
                                                        <li><i class="far fa-comment"></i> ${feedbackList.size()} Feedbacks</li>
                                                    
                                                            <br>
                                                        </ul>
                                                    </div>
                                                    <div class="clinic-booking">
                                                        <div class="clinic-booking">
                                                            <a class="view-pro-btn" href="dtinfo?sid=${o.id}">View Details</a>
                                                        <a class="apt-btn" href="bookappointment?id=${o.id}">Book Appointment</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="load-more text-center">
                                <ul class="pagination"></ul>	
                            </div>
                        </div>
                    </div>
                </div>		
                <%@include file="resource/components/footer.jsp" %>
            </div>
            <%@include file="resource/components/script.jsp" %>
            <c:choose>
                <c:when test="${optionval == 'sort'}">
                    <script>paggerSort('pagination', 'choice', ${choiceval}, ${pageIndex}, ${totalPages}, 2);</script>
                </c:when>
                <c:when test="${optionval == 'search'}"> 
                    <script>
                        var arrStr = '${listSpecSearch}';
                        var arr = [];
                        if (arrStr != '') {
                            arr = arrStr.split(',');
                        } else {
                            arr = [];
                        }
                        paggerSearch('pagination', 'gender', '${value}', ${pageIndex}, ${totalPages}, 2);
                    </script>
                </c:when>
                <c:otherwise>
                    <script>paggerBasic('pagination', ${pageIndex}, ${totalPages}, 2);</script>
                </c:otherwise>
            </c:choose>
    </body>
</html>