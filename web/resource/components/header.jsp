<%-- 
    Document   : AdminHeader
    Created on : Feb 16, 2022, 12:25:00 AM
    Author     : Admin
--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header">
    <nav class="navbar navbar-expand-lg header-nav">
        <div class="navbar-header">
            <a id="mobile_btn" href="javascript:void(0);">
                <span class="bar-icon">
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            </a>
            <a href="homepage" class="navbar-brand logo">
                <img src="resource/images/logo.png" class="img-fluid" alt="Logo">
            </a>
        </div>
        <div class="main-menu-wrapper">
            <div class="menu-header">
                <a href="homepage" class="menu-logo">
                    <img src="resource/images/logo.png" class="img-fluid" alt="Logo">
                </a>
                <a id="menu_close" class="menu-close" href="javascript:void(0);">
                    <i class="fas fa-times"></i>
                </a>
            </div>
            <ul class="main-nav">
                <li class=""><a href="homepage">Home</a></li>              
                <li class=""><a href="dtlist">Doctors List</a></li>
                <li class=""><a href="service">Services List</a></li>
                <li class=""><a href="webbloglist">Blogs List</a></li>
                <li class="login-link">
                    <a href="login">Login / Sign up</a>
                </li>
            </ul>
        </div>
        <ul class="nav header-navbar-rht">
            <li class="nav-item contact-item">
                <div class="header-contact-img">
                    <i class="far fa-hospital"></i>
                </div>
                <div class="header-contact-detail">
                    <p class="contact-header">Contact</p>
                    <p class="contact-info-header"> +1 315 369 5943</p>
                </div>
            </li>
            <c:if test="${sessionScope.acc == null}">
                <li class="nav-item">
                    <a class="nav-link header-login" href="login">login / Sign up </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.acc != null}">
                <li class="nav-item dropdown has-arrow logged-item">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                        <span class="user-img">
                            <img class="rounded-circle" src="data:image/jpeg;base64,${sessionScope.user.imageString}" width="31" alt="" />
                        </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="user-header">
                            <a class="dropdown-item" href="patientprofile">
                                <div class="avatar avatar-sm">
                                    <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="" class="avatar-img rounded-circle"/>
                                </div>
                                <div class="user-text">
                                    <h6>${sessionScope.user.name}</h6>
                                    <p class="text-muted mb-0">Patient</p>
                                </div>
                            </a>
                        </div>
                        <a class="dropdown-item" href="patientprofile">User Profile</a>
                        <a class="dropdown-item" href="appointmenthistory">Appointment History</a>
                        <a class="dropdown-item" href="reservationhistory">Reservation History</a>
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </nav>
</header>
