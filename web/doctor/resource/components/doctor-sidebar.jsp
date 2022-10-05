<%-- 
    Document   : doctor-sidebar
    Created on : Feb 19, 2022, 2:03:11 PM
    Author     : Vinnie Long
--%>

<div class="col-md-2">
    <div class="list-group">
        <div class="widget-profile pro-widget-content">
            <div class="profile-info-widget">
                <a href="#" class="booking-doc-img">
                    <c:if test="${sessionScope.user.image != null && not empty sessionScope.user.image}">
                        <img src="data:image/jpeg;base64,${sessionScope.user.imageString}" alt="User Image">
                    </c:if>
                    <c:if test="${sessionScope.user.image == null}">
                        <img src="resource/img/NoAvartar.jpg" alt=""/>
                    </c:if>
                </a>
                <div class="profile-det-info">
                    <h5>Dr. ${sessionScope.user.doctorName}</h5>
                </div>
                <div class="patient-details">
                    <h5 class="mb-0">${sessionScope.user.speciality.settingname}</h5>
                </div>
            </div>
        </div>
        <div>
            <a href="index.html" class="list-group-item active main-color-bg">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Navigation
            </a>
            <a href="doctorprofile" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> User Profile</a>
            <a href="mypatient" class="list-group-item"><span class="glyphicon glyphicon-bed" aria-hidden="true"></span> My Patients</a>
            <a href="myappointment" class="list-group-item"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> My Appointments</a>
            <a href="myfeedback" class="list-group-item"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> My Feedback</a>
            <a href="changepassdoctor" class="list-group-item"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> Change Password</a>
        </div>
    </div>
</div>
