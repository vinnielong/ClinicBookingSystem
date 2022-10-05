<%-- 
    Document   : HomePage
    Created on : Feb 24, 2022, 10:40:31 AM
    Author     : Admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.DoctorDAO"%>
<!DOCTYPE html>
<html>
    <%@include file="resource/components/head.jsp" %>
    <body>
        <!-- Main Wrapper -->
        <div class="main-wrapper">

            <!-- Header -->
            <%@include file="resource/components/header.jsp" %>
            <!-- /Header -->

            <!-- Home Banner -->
            <section class="section section-search">
                <div class="container-fluid">
                    <div class="banner-wrapper">
                        <div class="banner-header text-center">
                            <h1>Doccure Healthcare System</h1>
                            <p>Discover the best doctors and services in our hospital.</p>
                            <p>Your health is our top priority.</p>
                        </div>

                    </div>
                </div>
            </section>
            <!-- /Home Banner -->

            <!-- Clinic and Specialities -->
            <section class="section section-specialities">
                <div class="container-fluid">
                    <div class="section-header text-center">
                        <h2>Our Best Services</h2>
                        <p class="sub-title">Here are our 10 best services that have been greatly appreciated by our patients. For more information, please select the service you want.</p>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <!-- Slider -->
                            <div class="specialities-slider slider">
                                <c:forEach items="${listSer}" var="o">
                                    <!-- Slider Item -->
                                    <div class="speicality-item text-center">
                                        <div class="speicality-img">
                                            <img style="margin: 0 auto;border-radius: 50%;height: 150px;" 
                                                 <c:if test="${o.thumbnailString != ''}">
                                                 src="data:image/jpeg;base64,${o.thumbnailString}" class="img-fluid"
                                                 </c:if>
                                                 <c:if test="${o.thumbnailString == ''}">
                                                 src="resource/images/doccure.png" class="img-fluid"
                                                 </c:if>
                                                 alt="Speciality">
                                            <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                                        </div>
                                        <a href="serviceinfo?sid=${o.ID}"><p style="width: 150px;font-size: 14px">${o.title}</p></a>
                                    </div>
                                    <!-- /Slider Item -->
                                </c:forEach>
                            </div>
                            <!-- /Slider -->

                        </div>
                        <div class="col-md-4 features-img">
                            <img src="resource/images/features/feature.png" class="img-fluid" alt="Feature">

                        </div>
                    </div>
                </div>
            </section>
            <!-- Clinic and Specialities -->

            <!-- Popular Section -->
            <section class="section section-doctor">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="section-header ">
                                <h2>Our Top 10 Doctor</h2>
                            </div>
                            <div class="about-content">
                                <p>Doccure gathers a team of experts, doctors, pharmacists and nurses who are highly trained and receive in depth training in Vietnam and many countries with developed Medicine such as the US, UK, France … Vinmec always considers patients as the core, and commits to providing comprehensive and high quality healthcare services to our customers.</p>
                                <a href="dtlist">View All Doctor</a>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="doctor-slider slider">
                                <c:forEach items="${listDoctors}" var="doctor">
                                    <c:set var="dao" value="<%=new DoctorDAO()%>"></c:set>
                                    <c:set var="avgrate" value="${dao.averageRate(doctor.id)}"></c:set>
                                        <!-- Doctor Widget -->
                                        <div class="profile-widget">
                                            <div class="doc-img">
                                                <a href="dtinfo?sid=${doctor.id}">
                                                    <img class="img-fluid" style="height: 250px" alt="User Image" src="data:image/jpeg;base64,${doctor.imageString}">
                                            </a>
                                            <a href="javascript:void(0)" class="fav-btn">
                                                <i class="far fa-bookmark"></i>
                                            </a>
                                        </div>
                                        <div class="pro-content">
                                            <h3 class="title">
                                                <a href="dtinfo?sid=${doctor.id}">${doctor.doctorName}</a>
                                                <i class="fas fa-check-circle verified"></i>
                                            </h3>
                                            <p class="speciality">${doctor.role}</p>
                                            <div class="doc-info-cont">
                                                <div class="rating">
                                                    <i class="fas fa-star ${avgrate > 0 ? "filled" : ""}"></i>
                                                    <i class="fas fa-star ${avgrate > 1 ? "filled" : ""}"></i>
                                                    <i class="fas fa-star ${avgrate > 2 ? "filled" : ""}"></i>
                                                    <i class="fas fa-star ${avgrate > 3 ? "filled" : ""}"></i>
                                                    <i class="fas fa-star ${avgrate > 4 ? "filled" : ""}"></i>

                                                </div>
                                            </div>
                                            <ul class="available-info">
                                                <li>
                                                    <i class="fas fa-map-marker-alt"></i> ${doctor.address}
                                                </li>
                                                <li>
                                                    <i class="far fa-clock"></i> From Monday to Saturday
                                                </li>
                                                <li>
                                                    <i class="far fa-money-bill-alt"></i> Free
                                                    <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
                                                </li>
                                            </ul>
                                            <div class="row row-sm">
                                                <div class="col-6">
                                                    <a href="dtinfo?sid=${doctor.id}" class="btn view-btn">View Profile</a>
                                                </div>
                                                <div class="col-6">
                                                    <a href="bookappointment?id=${doctor.id}" class="btn book-btn">Book Now</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Doctor Widget -->
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- /Popular Section -->

            <!-- Availabe Features -->
            <section class="section section-features">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-5 features-img">
                            <img src="resource/images/features/feature.png" class="img-fluid" alt="Feature">

                        </div>
                        <div class="col-md-7">
                            <div class="section-header">
                                <h2 class="mt-2">Available Specialities in Our System</h2>
                                <p>Doccure Healthcare System is a not-for profit institution, invested by HKTMC Corporation - Vietnam’s leading private economic consortium with the mission of “We care with compassion, professionalism, and wisdom”.</p>
                            </div>
                            <div class="features-slider slider">
                                <c:forEach items="${listSpec}" var="listSpec">
                                    <!-- Slider Item -->
                                    <div class="feature-item text-center">
                                        <img style="margin: 0 auto" src="resource/images/features/feature-01.jpg" class="img-fluid" alt="Feature">
                                        <p style="width:138px !important;margin-top: 20px !important">${listSpec.settingname}</p>
                                    </div>
                                    <!-- /Slider Item -->
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Availabe Features -->
            <div style="width: 90%; text-align: center; margin: 20px auto; padding: 30px auto">
                <h2 class="mt-2" style="margin: 30px auto;font-size: 36px">We are waiting to help you!</h2>
                <!--Google Map-->
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.485409531647!2d105.52487561533206!3d21.013254993682363!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2sFPT%20University!5e0!3m2!1sen!2s!4v1635783917552!5m2!1sen!2s" width="100%" height="500" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                <!--Google Map-->
                <hr>
            </div>


            <!-- Footer -->
            <%@include file="resource/components/footer.jsp" %>
            <!-- /Footer -->

        </div>
        <!-- /Main Wrapper -->
        <%@include file="resource/components/script.jsp" %>


    </body>
</html>
