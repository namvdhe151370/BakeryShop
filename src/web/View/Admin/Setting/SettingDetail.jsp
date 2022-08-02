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

        <title>Setting Detail</title>
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
                                                <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                <li class="breadcrumb-item"><a href="#!">Admin</a></li>
                                                <li class="breadcrumb-item"><a href="#!">Setting Detail</a></li>
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

                                        <div class="card-body">
                                            <div class="modal-header">						
                                                <h4 class="modal-title">Settings Detail</h4>
                                                <a href="/src/admin/settinglist">  <input type="button" class="btn btn-secondary" value="Back to List"></a> 
                                            </div>

                                            <div>
                                                <form class="row">
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputEmail1">Setting Id</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                               value="${setting.settingId}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputEmail1">Type</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                               value="${setting.typeId.typeID}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Value</label>
                                                        <input type="text" class="form-control"
                                                               id="exampleInputPassword1" value="${setting.value}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Order</label>
                                                        <input type="text" class="form-control"
                                                               id="exampleInputPassword1" value="${setting.order}" readonly>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="exampleInputPassword1">Status</label> <br>
                                                        <input type="text" class="form-control"
                                                               id="exampleInputPassword1" value="${setting.status.status}" readonly>

                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label for="exampleFormControlTextarea1">Depcription</label> 
                                                        <textarea class="form-control" 

                                                                  id="exampleFormControlTextarea1"
                                                                  rows="3" style="height: 96px;" readonly>${setting.description}</textarea>
                                                    </div>


                                                </form>
                                                <div class="modal-footer" >
                                                    <A href="/src/admin/editsetting?settingId=${setting.settingId}">  <input type="submit" class="btn btn-primary" value="Edit">
                                                    </A>              </div>
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