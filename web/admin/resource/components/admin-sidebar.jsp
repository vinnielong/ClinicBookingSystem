<%-- 
    Document   : sidebar
    Created on : Feb 19, 2022, 1:40:46 PM
    Author     : Vinnie Long
--%>

<div class="col-md-2">
    <div class="list-group">
        <a href="#" class="list-group-item active main-color-bg">
            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Navigation
        </a>
        <c:if test="${sessionScope.acc.role == 1 || sessionScope.acc.role == 5}">
            <a href="dashboard" class="list-group-item"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Dashboard</a>
            <a href="accountlist" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Accounts</a>
            <a href="doctorlist" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Doctors</a>
            <a href="patientlist" class="list-group-item"><span class="glyphicon glyphicon-bed" aria-hidden="true"></span> Patients</a>
            <a href="appointment" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Appointments</a>
            <a href="reservationlist" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Reservations</a>
            <a href="servicelist" class="list-group-item"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> Services</a>
            <a href="bloglist" class="list-group-item"><span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span> Blogs</a>
            <c:if test="${sessionScope.acc.role == 1}"><a href="settinglist" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Settings</a></c:if>
        </c:if>
        <c:if test="${sessionScope.acc.role == 4}">
            <a href="appointment" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Appointments</a>
            <a href="reservationlist" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Reservations</a>
        </c:if>
    </div>
</div>
