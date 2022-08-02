<%-- 
    Document   : ChangePassword
    Created on : May 26, 2022, 10:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous">
        </script>
        <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
        <title>Document</title>
    </head>
    <style>
        body{
            height: 200vh;
        }

        .background-form{
            background-color: rgb(236, 230, 230, 0.5);
            width: 100%;
            height: 100vh;
            transition: all .4s ease-in-out;
            position: fixed;
        }

        .wrapper__form{
            position: absolute;
            padding: 20px 32px;
            width: 500px;
            box-shadow: 10px 10px 8px #c7c5c5;
            background-color: white;
            z-index: 10;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .close{
            float: right;
        }
        
        .hideform{
            opacity: 0;
        }

    </style>
    <body>
        <div class="background-form">        
            <div class="container wrapper__form">
                <form class="form" role="form" autocomplete="off" action="changepassword" method="POST"> 
                    <div class="form-group">
                        <label for="inputName">Current Password</label>
                        <label for="close" class="close">x</label>
                        <input type="password" class="form-control" id="currentpassword" placeholder="Current Password" required="" name="currentpassword">
                        <c:if test="${sessionScope.newPassword!=null}">
                            <p class="alert-danger">Invalid Current Password</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="inputName">New Password</label>
                        <input type="password" class="form-control" 
                               id="password" placeholder="New Password" 
                               required="" 
                               <c:if test="${sessionScope.newPassword!=null}">
                                   value="${sessionScope.newPassword}"
                               </c:if>
                               name="password">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmpassword" 
                               placeholder="Confirm Password" 
                               required="" 
                               <c:if test="${sessionScope.newPassword!=null}">
                                   value="${sessionScope.newPassword}"
                               </c:if>
                               name="confirmpassword">
                    </div>
                    <input type="submit" value="Change Password" class="btn btn-primary center-block">
                </form>
            </div>
        </div>
    </body>
</html>
<script>
    $(document).ready(function () {
        $(".form").validate({
            onfocusout: false,
            onkeyup: false,
            onclick: false,
            rules: {
                "currentpassword": {
                    required: true
                },
                "confirmpassword": {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
                },
                "password": {
                    required: true,
                    minlength: 8,
                },
            }
        });
        $(".close").click(function (e) {
            e.preventDefault();
            $(".background-form").addClass("hideform");
        });
    });
</script>