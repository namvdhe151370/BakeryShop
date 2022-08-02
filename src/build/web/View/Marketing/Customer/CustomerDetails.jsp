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

        <title>Flash Able - Most Trusted Admin Template</title>
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
        <!-- Favicon icon -->
        <link rel="icon" href="/src/assests/img/favicon.ico" type="image/x-icon">
        <!-- fontawesome icon -->
        <link rel="stylesheet" href="/src/assests/plugins/font-awesome/css/font-awesome.min.css">
        <!-- animation css -->
        <link rel="stylesheet" href="/src/assests/plugins/animation/css/animate.min.css">
        <!-- vendor css -->
        <link rel="stylesheet" href="/src/assests/css/style_admin.css">
        <!--for Alert-->
        <link rel="stylesheet" href="/src/assests/css/toast.css">
    </head>

    <style>
        .table-striped img{
            height: 50px;
            width: 50px;
        }
    </style>

    <body class="">
         <%@include file="../../public/adNavbar.jsp" %>
        <!-- [ Main Content ] start -->
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
                                                    <h5 class="m-b-10"></h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href="index.html"><i class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!"></a></li>
                                                    <li class="breadcrumb-item"><a href="#!"></a></li>
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
                                                    <form action="/src/user/edit" method="post">
                                                        <div class="modal-header">						
                                                            <h4 class="modal-title">Customer Detail</h4>
                                                            <a href="/src/marketing/customeredit?Id=${user.id}">  <input type="button" class="btn btn-primary" value="Edit"></a> 
                                                        </div>
                                                        <div class="modal-body">	
                                                            <div class="form-group">
                                                                <!-- Uploaded image area-->
                                                                <div class="image-area mt-4"><img id="imageResult" src="${user.image}" alt="" class="img-fluid rounded shadow-sm mx-auto d-block"></div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="form-group col-md-8">
                                                                    <label>Full Name</label>
                                                                    <input class="form-control" id="name" name="Name"
                                                                           value="${user.name}" readonly />
                                                                </div>
                                                                <div class="form-group col-md-4">
                                                                    <label>Gender</label>
                                                                    <input class="form-control" id="gender" name="gender"
                                                                           value="<c:if test="${user.gender == false}">Female</c:if><c:if test="${user.gender ==true}">Male</c:if>"readonly />
                                                                    </div>
                                                                </div>
                                                                <div class="form-row">
                                                                    <div class="form-group col-md-8">
                                                                        <label>Phone</label>
                                                                        <input class="form-control" id="mobile" name="mobile"
                                                                               value="${user.mobile}"  readonly/>
                                                                </div>

                                                                <div class="form-group col-md-4">
                                                                    <label>Status</label>
                                                                    <input class="form-control" id="status" name="status"
                                                                           value="<c:if test="${user.status == 1}">Active</c:if>
                                                                           <c:if test="${user.status == 0}">Block</c:if>" readonly/>
                                                                    </div>
                                                                <div class="form-row">
                                                                    <div class="form-group col-md-8">
                                                                        <label>Address</label>
                                                                        <input class="form-control" id="postID" name="address"
                                                                               value="${user.address}" readonly/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <h4 class="modal-title">Customer Changes History</h4>  
                                                        <div class="modal-body">
                                                            <table id="filterTable" class="table  hover">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Email</th>
                                                                        <th>Full Name</th>
                                                                        <th>Gender</th>
                                                                        <th>Mobile</th>
                                                                        <th>Address</th>
                                                                        <th>Update By</th>
                                                                        <th>Update Date</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${requestScope.lsCus}" var="ls">
                                                                        <tr>
                                                                            <td>${ls.email}</td>
                                                                            <td>${ls.name}</td>
                                                                            <c:if test="${ls.gender == true}">
                                                                                <td>Male</td>   
                                                                            </c:if>
                                                                            <c:if test="${ls.gender == false}">
                                                                                <td>Female</td>   
                                                                            </c:if>
                                                                            <td>${ls.mobile}</td>
                                                                            <td>${ls.address}</td>
                                                                            <td>${ls.updateby}</td>
                                                                            <td>${ls.updateDate}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a href="/src/marketing/customer" type="submit" class="btn btn-secondary">Back</a>
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

        <script src="/src/assests/js/vendor-all.min.js"></script>
        <script src="/src/assests/plugins/bootstrap4/js/bootstrap.min.js"></script>
        <script src="/src/assests/js/pcoded.min.js"></script>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#imageResult')
                                .attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            }

            $(function () {
                $('#upload').on('change', function () {
                    readURL(input);
                });
            });
        </script>
    </body>

</html>
