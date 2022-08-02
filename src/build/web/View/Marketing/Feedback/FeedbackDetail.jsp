<%-- 
    Document   : PostList
    Created on : Jun 12, 2022, 10:47:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>

    <title>Feedback Detail</title>
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
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>

<style>
    .checked {
        color: orange;
    }

    .img-product {
        width: 100px;
        height: 100px;
    }
    .img-product1{
        width: 100px;
        height: 100px;
        object-fit: cover;
    }
    
    .img-feedback{
        width: 150px;
        height: 120px;
        object-fit: cover;
    }
</style>

<body class="">
    <%@include file="../../public/adNavbar.jsp" %>
    <div class="pcoded-main-container">
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
                                                <h5 class="m-b-10">Marketting</h5>
                                            </div>
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item"><a href=""><i
                                                            class="feather icon-home"></i></a></li>
                                                <li class="breadcrumb-item"><a href="#!">Marketting</a></li>
                                                <li class="breadcrumb-item"><a href="#!">Feedback Detail</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- [ breadcrumb ] end -->
                            <!-- [ Main Content ] start -->
                            <div class="row">
                                <!-- [ form-element ] start -->
                                <div class="col-sm-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h3>Feedback Detail</h3>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div>
                                                    <h5 style="padding:0px 20px 0px 15px">Feedback Status:        
                                                </div>
                                                    <div class="custom-control custom-switch">
                                                        <c:if test='${feedback.feedbackStatus==true}'>
                                                            <input type="checkbox" checked class="custom-control-input" id="customSwitch0">
                                                        </c:if>
                                                        <c:if test='${feedback.feedbackStatus==false}'>
                                                            <input type="checkbox" class="custom-control-input" id="customSwitch0">
                                                        </c:if>        
                                                        <label class="custom-control-label" for="customSwitch0"></label>
                                                    </div>
                                                </h5>
                                            </div>
                                            <hr>
                                            <div>

                                                <form class="row">
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputEmail1">Full name</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                               aria-describedby="emailHelp" value="${feedback.userId.name}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Email</label>
                                                        <input type="email" class="form-control"
                                                            id="exampleInputPassword1" value="${feedback.userId.email}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Mobile</label>
                                                        <input type="text" class="form-control"
                                                            id="exampleInputPassword1" value="${feedback.userId.mobile}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Rated Star</label> <br>
                                                        <c:forEach var = "i" begin = "1" end = "${feedback.ratedStar}">
                                                            <span class="fa fa-star checked"></span>
                                                        </c:forEach>
                                                        <c:forEach var = "i" begin = "${feedback.ratedStar+1}" end = "5">
                                                            <span class="fa fa-star"></span>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label for="exampleInputPassword1">Image Status</label> <br>
                                                        <div class="card">
                                                            <div class="card-body table-border-style">
                                                                <div class="table-responsive">
                                                                    <table class="table">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>#</th>
                                                                                <th>Image</th>
                                                                                <th>Status</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:if test="${feedback.listImages.size()!=0}">
                                                                                <c:forEach var = "i" begin = "0" end = "${feedback.listImages.size()-1}">
                                                                                    <tr>
                                                                                        <td>${i+1}</td>
                                                                                        <td>
                                                                                            <img src="/src/uploads/Feedback/${feedback.listImages.get(i).imageName}" class="img-feedback" alt="">
                                                                                        </td>
                                                                                        <td>
                                                                                            <div class="custom-control custom-switch" style="padding-left: 2.5rem">
                                                                                              <input type="checkbox" 
                                                                                                     class="custom-control-input" 
                                                                                                     <c:if test='${feedback.listImages.get(i).status==true}'>
                                                                                                         checked
                                                                                                     </c:if>
                                                                                                     id="customSwitch${feedback.listImages.get(i).feedbackImageId}">
                                                                                              <label class="custom-control-label" for="customSwitch${feedback.listImages.get(i).feedbackImageId}"></label>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>                                                   
                                                                                </c:forEach>    
                                                                            </c:if>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Product</label> <br>
                                                        <div>Product Name:  ${feedback.productId.productName}</div>
                                                        <img src="${feedback.productId.thumbnail}" class="img-product1" alt="">
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleFormControlTextarea1">Feedback</label>
                                                        <textarea class="form-control" readonly id="exampleFormControlTextarea1"
                                                            rows="3" style="height: 96px;">${feedback.note}</textarea>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <!-- [ form-element ] end -->
                                <!-- [ Main Content ] end -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>                                               
</body>
</html>
<script>
    $(".custom-control-input").change(function (e) { 
        e.preventDefault();
        $.post("/src/marketing/feedbackdetail", {
            status: this.checked, 
            imageId: this.id.replaceAll("customSwitch",""),
            feedbackId: ${feedback.feedbackId}
        },
            function (data, textStatus, jqXHR) {
            },
        );
    });
</script>

