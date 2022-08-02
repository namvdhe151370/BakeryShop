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

        <title>Slider Detail</title>
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
    .slider-img{
        width: 100px;
        height: 100px;
        object-fit: cover;
    }
</style>
   

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
                                                    <h5 class="m-b-10">Slider</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Marketting</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Slider Detail</a></li>
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
                                            <div class="card-header">
                                                <div class="row">
                                                    <h5 style="padding:0px 20px 0px 15px">Slider Detail</h5>                                               
                                                    <div class="custom-control custom-switch">
                                                        <input type="checkbox" class="custom-control-input" 
                                                               disabled 
                                                               <c:if test="${slider.status>0}">checked</c:if>
                                                               id="customSwitch0">    
                                                        <label class="custom-control-label" for="customSwitch0"></label>
                                                    </div>                             
                                                </div>

                                                
                                            </div>
                                        <div class="card-body">
                                            <div>
                                                <form class="row">
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputEmail1">Slider ID</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                            aria-describedby="emailHelp" value="${slider.sliderID}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Title</label>
                                                        <input type="email" class="form-control"
                                                            id="exampleInputPassword1" value="${slider.title}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleFormControlTextarea1">Notes</label> 
                                                        <textarea class="form-control" 
                                                                  readonly
                                                                  id="exampleFormControlTextarea1"
                                                                    rows="3" style="height: 96px;">${slider.notes}</textarea>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Image</label> <br>
                                                        <img src="${slider.image}" class="slider-img" alt="">
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Backlink</label>
                                                        <input type="text" class="form-control"
                                                            id="exampleInputPassword1" value="${slider.backlink}" readonly>
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
      
    </body>

</html>
