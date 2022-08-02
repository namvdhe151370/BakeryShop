<%-- 
    Document   : MKTDashboard
    Created on : Jul 16, 2022, 5:35:36 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Sale Dashboard</title>
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

        <link rel="stylesheet" href="/src/assests/plugins/chart/chart-morris/css/morris.css">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
              rel="stylesheet" />
    </head>
    <style>
        .form-control {
            background-color: white;
        }
    </style>

    <body class="">
        <%@include file="../../public/adNavbar.jsp" %>

        <!-- [ Main Content ] start -->
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
                                                    <h5>Sale</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i
                                                                class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="">Sale Dashboard</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                                <form action="/src/sale/dashboard" method="get">
                                    <div class="form-row">


                                        <div class="form-group col-md-2">
                                            <span>From</span>
                                            <input value="${before}" name="before" type="text" id="before"
                                                   class="form-control" width="285">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span>To</span>
                                            <input value="${then}" name="then" type="text" id="then" 
                                                   class="form-control" width="286" style="margin-left: 3px;">

                                        </div>
                                        <div class="form-group col-md-2">
                                            <span>Order Status</span>
                                            <select class="mb-3 form-control" style="margin-left: 3px;" name="status"> 
                                                <option value="0">All Status</option>
                                                <c:forEach items="${listStatus}" var="c">
                                                    <option <c:if test="${c.orderStatusID eq status}"> selected</c:if>  value="${c.orderStatusID}">
                                                        ${c.status}
                                                    </option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                        <div class="form-group col-md-2">
                                            <span>Sale</span>
                                            <select class="mb-3 form-control" style="margin-left: 3px;" name="saleid">
                                                <option value="0">All Sale</option>
                                                <c:forEach items="${listSale}" var="c">
                                                    <option <c:if test="${c.id eq saleid}"> selected </c:if> value="${c.id}">
                                                        ${c.name}
                                                    </option>
                                                </c:forEach>
                                            </select>

                                        </div>

                                        <div class="form-group col-md-1">

                                            <input type="submit" id="filter" class="btn btn-primary"
                                                   style="margin-left: 6px;margin-top: 18px;" value="Filter"></a>
                                        </div>

                                    </div>
                                </form>
                                <!-- [ breadcrumb ] end -->
                                <!-- [ Main Content ] start -->

                                <div class="row">
                                    <!-- [ bar-simple Chart ] start -->


                                    <div class="col-xl-12">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5>Trend of success/total Order</h5>
                                            </div>
                                            <div class="card-body">
                                                <div id="morris-bar-chart" style="height:300px"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- [ line-angle Chart ] start -->
                                    <div class="col-xl-12">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5>Trend of Revenues</h5>
                                            </div>
                                            <div class="card-body">
                                                <div id="morris-line-chart" class="ChartShadow" style="height:300px"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- [ line-angle Chart ] end -->

                                </div>
                                <!-- [ Main Content ] end -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- [ Main Content ] end -->

        <!-- Warning Section start -->
        <!-- Older IE warning message -->
        <!--[if lt IE 11]>
        <div class="ie-warning">
            <h1>Warning!!</h1>
            <p>You are using an outdated version of Internet Explorer, please upgrade
               <br/>to any of the following web browsers to access this website.
            </p>
            <div class="iew-container">
                <ul class="iew-download">
                    <li>
                        <a href="http://www.google.com/chrome/">
                            <img src="assets/images/browser/chrome.png" alt="Chrome">
                            <div>Chrome</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.mozilla.org/en-US/firefox/new/">
                            <img src="assets/images/browser/firefox.png" alt="Firefox">
                            <div>Firefox</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.opera.com">
                            <img src="assets/images/browser/opera.png" alt="Opera">
                            <div>Opera</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.apple.com/safari/">
                            <img src="assets/images/browser/safari.png" alt="Safari">
                            <div>Safari</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                            <img src="assets/images/browser/ie.png" alt="">
                            <div>IE (11 & above)</div>
                        </a>
                    </li>
                </ul>
            </div>
            <p>Sorry for the inconvenience!</p>
        </div>
    <![endif]-->
        <!-- Warning Section Ends -->

        <!-- Required Js -->






        <!-- chart-morris Js -->
        <script src="/src/assests/plugins/chart/chart-morris/js/raphael.min.js"></script>
        <script src="/src/assests/plugins/chart/chart-morris/js/morris.min.js"></script>

        <script>
            $(document).ready(function () {
                setTimeout(function () {


                    // [ line-angle-chart ] Start
                    Morris.Line({
                        element: 'morris-line-chart',
                        data: [
            <c:forEach items="${chartRevenue}" var="c">
                            {
                                y: '${c.key}',
                                        a: ${c.value},

                            },
            </c:forEach>
                        ],
                        xkey: 'y',
                                redraw: true,
                        resize: true,
                                smooth: false,
                        ykeys: ['a'],
                                hideHover: 'auto',
                        responsive: true,
                                labels: ['Money'],
                        lineColors: ['#2ca961']
                    });
                    // [ line-angle-chart ] end
                    Morris.Bar({
                    element: 'morris-bar-chart',
                            data: [
            <c:forEach items="${chartTotal}"  var="c">
                            {
                            y: '${c.key}',
                                    a: ${c.value},
                <c:forEach items="${chartSuccessTotal}" var="d">
                    <c:if test="${c.key == d.key}"> b: ${d.value}</c:if>
                </c:forEach>
                            ,
                            }
                            ,
            </c:forEach>
                            ],
                    xkey: 'y',
                            barSizeRatio: 0.70,
                            barGap: 3,
                            resize: true,
                    responsive: true,
                            ykeys: ['a', 'b'],
                            labels: ['Success', 'Order', ],
                            barColors: ["#2ca961", "#463699", ]
                    }
                    );
                }, 700);
                // [ Donut-chart ] end
            });
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#before").datepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date.valueOf());
                    $('#then').datepicker('setStartDate', minDate);
                });
                $("#then").datepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date.valueOf());
                    $('#before').datepicker('setEndDate', minDate);
                });
            });
        </script>

    </body>

</html>
