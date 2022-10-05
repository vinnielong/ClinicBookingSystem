<%-- 
    Document   : VerifyEmailResetPass
    Created on : Mar 2, 2022, 9:55:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span>We already send a verification  code to your email.</span>
        
        <form action="forgotpass" method="post">
            <input type="text" name="authcode" >
            <input type="submit" value="verify">
        </form>
    </body>
</html>
