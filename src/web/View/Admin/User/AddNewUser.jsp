<%-- 
    Document   : EditSlider
    Created on : Jun 14, 2022, 9:45:19 AM
    Author     : hellb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    <head>

        <title>Add user</title>
        <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 11]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        <!-- Meta -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="description" content="Flash Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
        <meta name="keywords"
              content="admin templates, bootstrap admin templates, bootstrap 4, dashboard, dashboard templets, sass admin templets, html admin templates, responsive, bootstrap admin templates free download,premium bootstrap admin templates, Flash Able, Flash Able bootstrap admin template">
        <meta name="author" content="Codedthemes" />
       
    </head>


    <body class="">
        <%@include file="../../public/adNavbar.jsp" %>
        <section class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">
                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <!-- [ breadcrumb ] start -->
                                <div class="page-header">
                                    <div class="page-block">
                                        <div class="row align-items-center">
                                            <div class="col-md-12">
                                                <div class="page-header-title">
                                                    <h5 class="m-b-10">User</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Admin</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Add User</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- [ breadcrumb ] end -->
                                <!-- [ Main Content ] start -->
                                <div class="row">
                                    <div class="col-xl-12">
                                        <div class="card">
                                            
                                            <div class="card-body table-border-style">
                                                <div class="table-responsive">
                                                    <form action="/src/user/addnew" method="post">
                                                        <div class="modal-header">						
                                                            <h4 class="modal-title">Add New User</h4>
                                                            <a href="/src/user/userlist">  <input type="button" class="btn btn-success" value="Back to List"></a> 
                                                        </div>

                                                        <div class="modal-body">	
                                                            <div class="form-group">
                                                                

                                                                <!-- Uploaded image area-->

                                                                <div class="image-area mt-4"><img id="imageResult" src="https://www.w3schools.com/howto/img_avatar.png" alt="" class="img-fluid rounded shadow-sm mx-auto d-block"></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Full Name</label>                                                             
                                                                <input  name="Name" type="text" class="form-control">
                                                            </div>

                                                            <div class="form-group">
                                                                <label>Phone</label>
                                                               <input  name="Mobile" type="text" class="form-control">
                                                            </div>


                                                            <div class="form-group">
                                                                <label>Email</label>
                                                                <input  name="Email" type="text" class="form-control" >
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Password</label>
                                                                <input  name="Password" type="text" class="form-control" >
                                                            </div>

                                                            <div class="form-group">
                                                                <label>Address</label>
                                                                <input  name="Address" type="text" class="form-control"  >
                                                            </div>

                                                            <div class="form-group">
                                                                <label>Gender</label>
                                                                <select class="form-control" name="Gender">
                                                                    <option value="1">Male</option>
                                                                    <option value="0">Female</option>
                                                                </select>

                                                                </div>
                                                                 <div class="form-group">
                                                                <label>Status</label>
                                                                <select name="status" class="form-control" aria-label="Default select example">
                                                                    <option  value="1">Active</option>
                                                                    <option  value="0">Block</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Role</label>
                                                             <select name="Role" class="form-control" aria-label="Default select example">
                                                              
                                                                 <c:forEach items="${listRole}" var="c">
                                                                     <option <c:if test="${c.roleID == user.roleID}"> selected="selected"</c:if> value="${c.roleID}">${c.roleName}</option>
                                                              </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="modal-footer">
                                                            <input type="submit" class="btn btn-success" value="Send">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
   
        <script>
         
        </script>
    </body>

</html>
