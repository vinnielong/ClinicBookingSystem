<%-- 
    Document   : ForgotResetPassword
    Created on : Mar 2, 2022, 11:08:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3><i class="fa fa-lock fa-4x"></i></h3>
                                <h2 class="text-center">Forgot Password?</h2>
                                <p>You can reset your password here.</p>
                                <div class="panel-body">
                                    <form id="register-form" action="forgotresetpassword" class="form" method="post">
                                        <p class="text-danger" style="color: red;">${mess}</p>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                <input type="password" class="form-control" placeholder="Password" name="psw1" value="${password}" id="password" onkeyup='check();' required>
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                <input type="password" class="form-control" placeholder="Confirm Password" name="psw-repeat1" value="${repass}" id="confirm_password" onkeyup='check();' required>
                                            </div>
                                            <div class="form-group">
                                                <br><span id='message'></span>
                                                <input name="recover-submit" style="background: #e74c3c; border-color: #e74c3c;" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    var check = function () {
                        if (document.getElementById('password').value ===
                                document.getElementById('confirm_password').value) {
                            document.getElementById('message').style.color = 'green';
                            document.getElementById('message').innerHTML = 'matching';
                        } else {
                            document.getElementById('message').style.color = 'red';
                            document.getElementById('message').innerHTML = 'not matching';
                        }
                    };
                </script>
            </div>
        </div>
    </body>
</html>
