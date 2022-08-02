<%-- 
    Document   : ResetPasword
    Created on : May 30, 2022, 4:57:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Reset Password</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="/src/assests/css/toast.css">
    </head>
    <style>
        .modal-backdrop{
            z-index: 10 !important;
        }
        body{
            background-color: #ece8e8;
        }
        .wrapper--form{
            padding: 16px 32px;
            border: 1px solid;
            min-width: 300px;
            max-width: 520px;
            margin-bottom: 100px;
            background-color: white;
        }
        h3 {
            color: #17c0eb;
            font-size: 36px;
            text-align: center;
        }

        .wrapper--form{
            margin-top: 20%;
        }
        
        .wrapper--form h3, input{
            font-size: 22px !important;
        }
        
    </style>

    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="container">
            <div class="wrapper--form center-block">
                <h3>Forgot Password </h3>
                <p>Enter your account email, we'll send you a link to reset your password.</p>
                <form class="form" role="form" autocomplete="off" action="resetpassword" method="POST">
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" placeholder="*Your email" required="" name="email">
                    </div>
                    <input type="submit" value="Reset Password" class="btn btn-info center-block x">
                </form>
            </div>
            <div class="toastx">
            </div>
        </div>
        <%@include file="../../public/specfooter.jsp" %>                       
    </body>
</html>
<script src="/src/assests/js/toast.js"></script>
<script>
    $(document).ready(function () {
        $(".form").validate({
            onfocusout: false,
            onkeyup: false,
            onclick: false,
            rules: {
                "email": {
                    required: true,
                    email: true
                }
            }
        });
    });

    <c:if test="${requestScope.message=='Success'}">
        Alert({
            type: "success",
            content: "Check your email to reset password"
        })        
    </c:if>
     <c:if test="${requestScope.message=='Fail'}">
        Alert({
            type: "error",
            content: "Check your register's email"
        })        
    </c:if>   
</script>