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

        <title>MKT Dashboard</title>
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
                                                    <h5>Dashboard</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i
                                                                class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">MKT Dashboard</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form action="/src/marketing/dashboard" method="get">
                                    <div class="form-row">


                                        <div class="form-group col-md-3">
                                            <span>From</span>
                                            <input value="${before}" name="before" type="text" id="before"
                                                   class="form-control" width="285">
                                        </div>
                                        <div class="form-group col-md-3">
                                            <span>To</span>
                                            <input value="${then}" name="then" type="text" id="then" 
                                                   class="form-control" width="286" style="margin-left: 3px;">

                                        </div>
                                        <div class="form-group col-md-3">

                                            <input type="submit" id="filter" class="btn btn-primary"
                                                   style="margin-left: 6px;margin-top: 18px;" value="Filter"></a>
                                        </div>

                                    </div>
                                </form>
                                <!-- [ breadcrumb ] end -->
                                <!-- [ Main Content ] start -->
                                <div class="row">

                                    <div class="col-md-6 col-xl-3">
                                        <div class="card card-social">
                                            <div class="card-block border-bottom">
                                                <div class="row align-items-center justify-content-center">

                                                    <div class="col text-right">
                                                        <h3>${totalPost}</h3>
                                                        <h5 class="text-c-info mb-0">+ ${totalPostInRange} <span class="text-muted"> Total
                                                                Customers</span></h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-block">
                                                <div class="row align-items-center justify-content-center card-active">
                                                    <div class="col-6">
                                                        <h6 class="text-center m-b-10"><span
                                                                class="text-muted m-r-5">Active:</span>${activePost}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-blue" role="progressbar"
                                                                 style="width:${activePostPercentage}%;height:6px;" aria-valuenow="40"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-6">
                                                        <h6 class="text-center  m-b-10"><span
                                                                class="text-muted m-r-5">Deactive:</span>${deactivePost}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-green" role="progressbar"
                                                                 style="width: ${deactivePostPercentage}%;height:6px;" aria-valuenow="70"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xl-3">
                                        <div class="card card-social">
                                            <div class="card-block border-bottom">
                                                <div class="row align-items-center justify-content-center">

                                                    <div class="col text-right">
                                                        <h3>${totalProduct}</h3>
                                                        <h5 class="text-c-info mb-0">+${totalProductInRange} <span class="text-muted">Total
                                                                Products</span></h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-block">
                                                <div class="row align-items-center justify-content-center card-active">
                                                    <div class="col-6">
                                                        <h6 class="text-center m-b-10"><span
                                                                class="text-muted m-r-5">On Stock:</span>${activeProduct}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-blue" role="progressbar"
                                                                 style="width:${activePostPercentage}%;height:6px;" aria-valuenow="40"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-6">
                                                        <h6 class="text-center  m-b-10"><span
                                                                class="text-muted m-r-5">Out Stock:</span>${deactiveProduct}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-green" role="progressbar"
                                                                 style="width:${deactivePostPercentage}%;height:6px;" aria-valuenow="70"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xl-3">
                                        <div class="card card-social">
                                            <div class="card-block border-bottom">
                                                <div class="row align-items-center justify-content-center">

                                                    <div class="col text-right">
                                                        <h3>${totalCustomer}</h3>
                                                        <h5 class="text-c-info mb-0">+${totalCustomerInRange} <span class="text-muted">Total
                                                                Customers</span></h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-block">
                                                <div class="row align-items-center justify-content-center card-active">
                                                    <div class="col-6">
                                                        <h6 class="text-center m-b-10"><span
                                                                class="text-muted m-r-5">Active:</span>${activeCustomer}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-blue" role="progressbar"
                                                                 style="width:${activeCustomerPercentage}%;height:6px;" aria-valuenow="40"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-6">
                                                        <h6 class="text-center  m-b-10"><span
                                                                class="text-muted m-r-5">Deactive</span> ${deactiveCustomer}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-green" role="progressbar"
                                                                 style="width:${deactiveCustomerPercentage}%;height:6px;" aria-valuenow="70"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xl-3">
                                        <div class="card card-social">
                                            <div class="card-block border-bottom">
                                                <div class="row align-items-center justify-content-center">

                                                    <div class="col text-right">
                                                        <h3>${totalFeedback}</h3>
                                                        <h5 class="text-c-info mb-0">+${totalFeedbackInRange} <span class="text-muted">Total
                                                                Feedbacks</span></h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-block">
                                                <div class="row align-items-center justify-content-center card-active">
                                                    <div class="col-6">
                                                        <h6 class="text-center m-b-10"><span
                                                                class="text-muted m-r-5">Active:</span>${activeFeedback}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-blue" role="progressbar"
                                                                 style="width:${activeFeedbackPercentage}%;height:6px;" aria-valuenow="40"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-6">
                                                        <h6 class="text-center  m-b-10"><span
                                                                class="text-muted m-r-5">Deactive:</span>${deactiveFeedback}</h6>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-c-green" role="progressbar"
                                                                 style="width:${deactiveFeedbackPercentage}%;height:6px;" aria-valuenow="70"
                                                                 aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                                <div class="row">
                                    <!-- [ bar-simple Chart ] start -->

                                    <!-- [ line-angle Chart ] start -->
                                    <div class="col-xl-12">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5>Trend of New Customers</h5>
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
            <c:forEach items="${chartData}" var="c">
                            {
                                y: '${c.key}',
                                a: ${c.value}

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
                        labels: ['New Customer'],
                        lineColors: ['#463699']
                    });
                    // [ line-angle-chart ] end

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
