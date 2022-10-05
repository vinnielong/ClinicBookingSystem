<%-- 
    Document   : Services-jsp
    Created on : Mar 9, 2022, 5:50:16 PM
    Author     : vinnielong
--%>

<%@page import="dal.ServiceDAO"%>
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
                                        <a href="service">Services</a>
                                    </li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Available Services in Our System</h2>
                        </div>
                        <div class="col-md-4 col-12 d-md-block d-none" style="margin-left: -30px;">
                            <div class="sort-by">
                                <span class="sort-title">Sort by</span>
                                <span class="sortby-fliter">
                                    <select class="select" name="choice" onChange="location = this.value;">
                                        <option>Select</option>
                                        <option class="sorting" value="service?choice=1&option=sort">Highest Price</option>
                                        <option class="sorting" value="service?choice=2&option=sort">Lowest Price</option>
                                        <option class="sorting" value="service?choice=3&option=sort">Alphabetical Order</option>
                                    </select>
                                </span>
                            </div>
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
                                    <h4 class="card-title mb-0">Search Filter</h4>
                                    <form action="service" method="GET">
                                        <div class="card-body">
                                            <div class="input-group">
                                                <input value="search" name="option" type="text" hidden="" >
                                                <input value="${value}" name="txt" type="text" class="form-control" placeholder="Search">
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
                            <c:forEach items="${listServices}" var="o">
                                <c:set var="dao" value="<%=new ServiceDAO()%>"></c:set>
                                <c:set var="avgrate" value="${dao.averageRate(o.ID)}"></c:set>
                                <c:set var="feedbackList" value="${dao.getFeedbackByServiceID(o.ID)}"></c:set>
                                    <!-- Doctor Widget -->
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="doctor-widget">
                                                <div class="doc-info-left">
                                                    <div class="doctor-img">
                                                        <a href="serviceinfo?sid=${o.ID}">
                                                        <c:choose>
                                                            <c:when test="${o.thumbnailString ne ''}"> 
                                                                <img src="data:image/jpeg;base64,${o.thumbnailString}" style="height: 150px;width: 150px" class="img-fluid" alt="User Image">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="resource/images/doccure.png" style="height: 150px;width: 150px" class="img-fluid"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </a>
                                                </div>
                                                <div class="doc-info-cont">
                                                    <h4 class="doc-name"><a href="serviceinfo?sid=${o.ID}">${o.title}</a></h4>

                                                    <h5 class="doc-department">${o.category.settingname}</h5>
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
                                                        <b><a style="color: aqua" href="serviceinfo?sid=${o.ID}">See more...</a></b>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="doc-info-right">
                                                <div class="clini-infos">
                                                    <ul>
                                                        <li><i class="far fa-comment"></i> ${feedbackList.size()} Feedbacks</li>
                                                        <li><i class="far fa-money-bill-alt"></i><fmt:formatNumber value="${o.price}" type="number"></fmt:formatNumber> $</li>
                                                            <br>
                                                        </ul>
                                                    </div>
                                                    <div class="clinic-booking">
                                                        <div class="clinic-booking">
                                                            <a class="view-pro-btn" href="serviceinfo?sid=${o.ID}">View Details</a>
                                                        <a class="apt-btn" href="reservation?id=${o.ID}">Book Service</a>
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
                        paggerSearch('pagination', 'txt', '${value}', ${pageIndex}, ${totalPages}, 2);
                    </script>
                </c:when>
                <c:otherwise>
                    <script>paggerBasic('pagination', ${pageIndex}, ${totalPages}, 2);</script>
                </c:otherwise>
            </c:choose>
    </body>
</html>