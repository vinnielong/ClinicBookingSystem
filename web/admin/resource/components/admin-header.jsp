<%-- 
    Document   : AdminHeader
    Created on : Feb 16, 2022, 12:25:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Clinic Booking</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">        
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.acc.role == 1 }">
                <li><a href="#">Welcome Admin</a></li>
                </c:if>
                <c:if test="${sessionScope.acc.role == 4 || sessionScope.acc.role == 5}">
                <li><a href="#">Welcome ${sessionScope.acc.fullName}</a></li>
                </c:if>
                <li><a href="/SWP391G2/logout">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>



