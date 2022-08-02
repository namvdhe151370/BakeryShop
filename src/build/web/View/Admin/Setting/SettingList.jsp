<%-- 
    Document   : UserList2
    Created on : Jun 14, 2022, 5:48:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Setting List</title>
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
    <style>

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
                                                    <h5 class="m-b-10">Setting</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i
                                                                class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Admin</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Setting List</a></li>
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
                                            
                                            <div class="card-body table-border-style" id="customformControl">
                                                <div class="table-responsive">

                                                    <div class="status-filter">
                                                        <select id="status" class="form-control">
                                                            <option value="">Show Status</option>
                                                            <c:forEach items="${lsStatus}" var="c">
                                                                <option value="${c.status}">${c.status}</option>
                                                            </c:forEach>
                                                        </select>


                                                    </div>
                                                    <div class="type-filter">
                                                        <select id="type" class="form-control">
                                                            <option value="">All Type</option>
                                                            <c:forEach items="${lsType}" var="c">
                                                                <option value="${c.typeName}">
                                                                    ${c.typeName}</option>
                                                                </c:forEach>
                                                        </select>


                                                    </div>



                                                </div>

                                                <table id="filterTable" class="table  hover">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Type</th>
                                                            <th>Value</th>
                                                            <th>Order</th>

                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${lsSettings}" var="c">
                                                            <tr>
                                                                <td>${c.settingId}</td>
                                                                <td>${c.typeId.typeName}</td>
                                                                <td>${c.value}</td>
                                                                <td>${c.order}</td>

                                                                <td>${c.status.status}</td>
                                                                <td>
                                                                    <a href="/src/admin/viewsetting?settingId=${c.settingId}"><i class="fa fa-info-circle"></i></a>
                                                                    <a href="/src/admin/editsetting?settingId=${c.settingId}"><i class="fa fa-edit"></i></a>
                                                                    <a href="/src/admin/settingchangestatus?id=${c.settingId}&status=2">     <c:if test="${c.status.statusID == 1}"><i class="fa fa-eye"></i> </c:if> </a>
                                                                    <a href="/src/admin/settingchangestatus?id=${c.settingId}&status=1">       <c:if test="${c.status.statusID == 2}"><i class="fa fa-eye-slash"></i> </c:if>    
                                                                        </a></td>
                                                                </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Type</th>
                                                            <th>Value</th>
                                                            <th>Order</th>

                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
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

    <script>
      
    </script>
    <script src="/src/assests/js/settings.js"></script>


</body>

</html>
