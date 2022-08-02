<%-- 
    Document   : UserProfile
    Created on : May 30, 2022, 3:43:42 PM
    Author     : Huy Thai
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Profile</title>
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
        <link rel="stylesheet" href="/src/assests/css/userprofile.css">
        
    </head>
    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="container bootstrap snippet profile--wrapper">
            <div class="row">
                <div class="col-sm-3"><!--left col-->
                    <div class="text-center">
                        <img src="/src/uploads/avatar/${user.image}" class="avatar img-circle img-thumbnail" alt="avatar">
                    </div>
                    </hr>
                    <br>

                </div><!--/col-3-->
                <div class="col-sm-9">

                    <div class="tab-content">
                        <div class="tab-pane active" id="home">
                            <form class="form" action="" method="post" id="registrationForm">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <h4>Name</h4>
                                        <a class="form-control" style="color: black">${user.name}</a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <h4>Phone</h4>
                                        <a class="form-control" style="color: black">${user.mobile}</a>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <h4>Email</h4>
                                        <a class="form-control" style="color: black">${user.email}</a>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <h4>Address</h4>
                                        <a class="form-control" style="color: black">${user.address}</a>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <h4>Gender</h4>
                                        <c:if test="${requestScope.user.gender == true}">
                                            <a class="form-control" style="color: black">Male</a>
                                        </c:if>
                                        <c:if test="${requestScope.user.gender == false}">
                                            <a class="form-control" style="color: black">Female</a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <br>
                                        <a class="btn btn-lg btn-success"  data-toggle="modal" href="#UpdateInfor"><i class="glyphicon glyphicon-ok-sign"></i>Update Information</a>
                                        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Change password</button>
                                        <a class="btn btn-warning" href="/src/order/myorder">My orders</a>
                                    </div>
                                </div>
                            </form>
                        </div><!--/tab-pane-->
                    </div><!--/tab-pane-->
                </div><!--/tab-pane-->
            </div><!--/tab-content-->
        </div>
        <!--Update User Information -->
<div class="modal fade" id="UpdateInfor">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="/src/account/updateuserprofile?Id=${user.id}" method="POST" enctype='multipart/form-data'>
                        <div class="modal-header">						
                            <h4 class="modal-title" >Update User Information</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="text-center">
                            <img src="/src/uploads/avatar/${user.image}" class="avatar img-circle img-thumbnail" alt="avatar" style="width: 250px;height: 180px">
                            <hr>
                            <input type="file" name="images" class="text-center center-block file-upload">
                        </div>
                        </hr>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="Name" type="text" class="form-control" value="${user.name}" required>
                            </div>
                            <div class="form-group">
                                <label>Mobile</label>
                                <input name="Mobile" type="text" class="form-control" value="${user.mobile}" required>
                            </div>

                            <div class="form-gender">
                                <label>
                                    Gender
                                </label>
                                </br>
                                <c:if test="${user.gender == true}">
                                    <input type="radio" name="Gender" class="gender-radio" value="1" required checked="">
                                    <label for="gender">
                                        Male
                                    </label>
                                    </br>
                                    <input type="radio" name="Gender" class="gender-radio" value="0" required>
                                    <label for="gender">
                                        Female
                                    </label>
                                </c:if>
                                <c:if test="${user.gender == false}">
                                    <input type="radio" name="Gender" class="gender-radio" value="1" required>
                                    <label for="gender">
                                        Male
                                    </label>
                                    </br>
                                    <input type="radio" name="Gender" class="gender-radio" value="0" required checked="">
                                    <label for="gender">
                                        Female
                                    </label>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input name="Address" type="text" class="form-control"  value="${user.address}" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Save">       
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div >
            <!-- Trigger the modal with a button -->
            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">						
                            <h4 class="modal-title" >Change password</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form class="form form-changepassword" role="form" autocomplete="off" action="/src/account/changepassword" method="POST"> 
                                <div class="form-group">
                                    <label for="inputName">Current Password</label>
                                    <input type="password" class="form-control" id="currentpassword" placeholder="Current Password" required="" name="currentpassword">
                                </div>
                                <div class="form-group">
                                    <label for="inputName">New Password</label>
                                    <input type="password" class="form-control" 
                                           id="password" placeholder="New Password" 
                                           required="" 
                                           name="password">
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3">Confirm Password</label>
                                    <input type="password" class="form-control" id="confirmpassword" 
                                           placeholder="Confirm Password" 
                                           required="" 
                                           name="confirmpassword">
                                </div>
                                <input type="submit" value="Change Password" class="btn btn-primary center-block">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="toastx">
        </div>     
        <%@include file="../../public/specfooter.jsp" %>                       
    </body>
    <script src="/src/assests/js/uploadimage.js"></script>
    <script src="/src/assests/js/toast.js"></script>
</html>
<script>
    $(document).ready(function () {
        $(".form-changepassword").validate({
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

    <c:if test="${requestScope.message=='Success'}">
    Alert({
        type: "success",
        content: "Change password successfully"
    })
    </c:if>
    <c:if test="${requestScope.message=='Fail'}">
    Alert({
        type: "error",
        content: "Check your current password"
    })
    </c:if>
        <c:if test="${sessionScope.mess=='Successfull'}">
    Alert({
        type: "success",
        content: "Change Information successfully"
    }) 
            <%    session.removeAttribute("mess");%>
    </c:if>
</script>
<script>
    $(document).ready(function () {
        var readURL = function (input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.avatar').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }


        $(".file-upload").on('change', function () {
            readURL(this);
        });
    });
</script>