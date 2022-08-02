<%-- 
    Document   : UserList2
    Created on : Jun 14, 2022, 5:48:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Slider List</title>
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
        <meta name="description"
              content="Flash Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
        <meta name="keywords"
              content="admin templates, bootstrap admin templates, bootstrap 4, dashboard, dashboard templets, sass admin templets, html admin templates, responsive, bootstrap admin templates free download,premium bootstrap admin templates, Flash Able, Flash Able bootstrap admin template">
        <meta name="author" content="Codedthemes" />




        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
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
                                                    <h5 class="m-b-10">Slider</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i
                                                                class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Marketting</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Slider List</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- [ breadcrumb ] end -->
                                <!-- [ Main Content ] start -->
                                <div class="row">


                                    <!-- [ stiped-table ] start -->
                                    <div class="col-xl-12">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5>Striped Table</h5>
                                                <span class="d-block m-t-5">use class <code>table-striped</code> inside
                                                    table element</span>
                                            </div>
                                            <div class="card-body table-border-style" id="customformControl">
                                                <div class="table-responsive">

                                                    <div class="status-filter">
                                                        <select id="status" class="form-control">
                                                            <option value="">Show Status</option>
                                                            <c:forEach items="${lsStatus}" var="c">
                                                                <c:if test="${c.value == 1}">
                                                                    <option value="Active"> 
                                                                        Active</c:if></option>
                                                                    <c:if test="${c.value == 2}">
                                                                    <option value="Deactive"> 
                                                                            Deactive</c:if></option>
                                                                </c:forEach>
                                                        </select>


                                                    </div>


                                                    <table id="Slider" class="table  hover">
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Image</th>
                                                                <th>Title</th>
                                                                <th>Backlink</th>
                                                                <th>Status</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${listslider}" var="c">
                                                                <tr>  
                                                                        <td>${c.sliderID}</td>
                                                                    <td><img src="${c.image}" width="50px" height="50px" alt=""></td>
                                                                    <td>${c.title}</td>
                                                                    <td>${c.backlink}</td>
                                                                    <td>
                                                                        <c:if test="${c.status == 1}">Active
                                                                        </c:if>
                                                                        <c:if test="${c.status == 2}">Deactive
                                                                        </c:if>
                                                                    </td>
                                                                    <td>
                                                                        
                                                                          <a href="/src/marketing/viewslider?sliderId=${c.sliderID}"><i class="fa fa-info-circle"></i></a>
                                                                        <a href="/src/marketing/editslider?sliderId=${c.sliderID}"> <i class="fa fa-pencil"></i></a>   
                                                                        <a href="/src/marketing/sliderchangestatus?sliderId=${c.sliderID}&status=2"  <c:if test="${c.status == 1}"><i class="fa fa-eye"></i> </c:if> </a>
                                                                        <a href="/src/marketing/sliderchangestatus?sliderId=${c.sliderID}&status=1" <c:if test="${c.status == 2}"><i class="fa fa-eye-slash"></i> </c:if>    </a>
                                                                    </td>
                                                                
                                                                     

                                                                </tr>  
                                                            </c:forEach>
                                                        </tbody>
                                                        <tfoot>

                                                        </tfoot>

                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- [ stiped-table ] end -->

                                </div>
                                <!-- [ Main Content ] end -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- Required Js -->

        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
       
        <script src="/src/assests/js/Slider.js"></script>


        </body>

    </html>
