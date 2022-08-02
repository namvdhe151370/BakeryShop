<%-- 
    Document   : newjsp
    Created on : Jul 17, 2022, 2:37:17 PM
    Author     : hellb
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Admin Dashboard</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

       
        <!-- morris css -->

        <link href="/src/assests/plugins/chart-morris/css/morris.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        .form-control {
            background-color: white;
        }
        .cardnam .card{
            height: 300px;
        }
    </style>

    <body class="">
        <!--        navbar-->
        <%@include file="../../public/adNavbar.jsp" %>
        <!-- [ Main Content ] start -->
        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">
                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <form action="dashboard" method="GET">
                                <div class="page-wrapper">
                                    <!-- [ breadcrumb ] start -->
                                    <div class="page-header">
                                        <div class="page-block">
                                            <div class="row align-items-center">
                                                <div class="col-md-12">
                                                    <div class="page-header-title">
                                                        <h5>Home</h5>
                                                    </div>
                                                    <ul class="breadcrumb">
                                                        <li class="breadcrumb-item"><a href=""><i
                                                                    class="feather icon-home"></i></a></li>
                                                        <li class="breadcrumb-item"><a href="">Admin Dashboard</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">


                                        <div class="form-group col-md-3">
                                            <span>From</span>
                                            <input value="${from}" name="from" type="date" id="checkin"
                                                   class="form-control" width="285" onchange="checkdate(this);" >
                                        </div>
                                        <div class="form-group col-md-3">
                                            <span>To</span>
                                            <input value="${to}"  name="to" type="date" id="datepickerTo"
                                                   class="form-control" width="286" style="margin-left: 3px; " onchange="checkoutdate(this);">

                                        </div>
                                        
                                        <div class="form-group col-md-3">

                                            <input type="submit" onclick="checkDateDifference(datepickerFrom, datepickerTo)" name="get" class="btn btn-primary"
                                                   style="margin-left: 6px;margin-top: 18px;" name="search" value="Filter">
                                        </div>
                                    </div>
                                    <!-- [ breadcrumb ] end -->
                                    <!-- [ Main Content ] start -->
                                    <div class="cardnam row">

                                        <div class="col-md-6 col-xl-3">
                                            <div class="card card-social">
                                                <div class="card-block border-bottom">
                                                    <div class="row align-items-center justify-content-center">
                                                        <div class="col-auto">
                                                            <i class="fa fa-bar-chart" aria-hidden="true" style="font-size: 50px"></i>
                                                        </div>
                                                        <div class="col text-right">
                                                            <h3>${countAllOrderByDate}</h3>
                                                            <h5 class="text-c-info mb-0"> <span class="text-muted">NEW ORDERS</span></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-block">
                                                    <div class="row align-items-center justify-content-center card-active mb-3">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Cancelled:</span>${countOrderByDateCancel}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countOrderByDateCancel/countAllOrderByDate*100}%;height:6px;" aria-valuenow="40"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Pending:</span>${countOrderByDatePending}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countOrderByDatePending/countAllOrderByDate*100}%;height:6px;" aria-valuenow="70"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row align-items-center justify-content-center card-active">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Processing:</span>${countOrderByDateProcessing}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countOrderByDateProcessing/countAllOrderByDate*100}%;height:6px;" aria-valuenow="40"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Submitted:</span>${countOrderByDateShipped}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countOrderByDateShipped/countAllOrderByDate*100}%;height:6px;" aria-valuenow="70"
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
                                                        <div class="col-auto">
                                                            <i class="fa fa-bar-chart" aria-hidden="true" style="font-size: 50px"></i>
                                                        </div>
                                                        <div class="col text-right">
                                                            <h3>${totalCus}</h3>
                                                            <h5 class="text-c-info mb-0"><span class="text-muted">Customer</span></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-block">
                                                    <div class="row align-items-center justify-content-center card-active">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Registered:</span>${countCusByDate}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countCusByDate/totalCus*100}%;height:6px;" aria-valuenow="40"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Bought:</span>${countCusBoughtByDate}</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countCusBoughtByDate/totalCus*100}%;height:6px;" aria-valuenow="70"
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
                                                        <div class="col-auto">
                                                            <i class="fa fa-bar-chart" aria-hidden="true" style="font-size: 50px"></i>
                                                        </div>
                                                        <div class="col text-right">
                                                            <h3>${countAllRevenues}$</h3>
                                                            <h5 class="text-c-info mb-0"><span class="text-muted">Revenues</span></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-block">
                                                    <div class="row align-items-center justify-content-center card-active mb-3">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Bready: </span>${countRevenuesByCate5}$</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countRevenuesByCate5/countAllRevenues*100}%;height:6px;" aria-valuenow="40"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Pinpool:</span>${countRevenuesByCate6}$</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countRevenuesByCate6/countAllRevenues*100}%;height:6px;" aria-valuenow="70"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row align-items-center justify-content-center card-active mb-3">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Deliciuex: </span>${countRevenuesByCate7}$</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countRevenuesByCate7/countAllRevenues*100}%;height:6px;" aria-valuenow="40"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Cake: </span>${countRevenuesByCate8}$</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countRevenuesByCate8/countAllRevenues*100}%;height:6px;" aria-valuenow="70"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row align-items-center justify-content-center card-active">
                                                        <div class="">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Cupke: </span>${countRevenuesByCate9}$</h6>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-c-blue" role="progressbar"
                                                                     style="width:${countRevenuesByCate9/countAllRevenues*100}%;height:6px;" aria-valuenow="40"
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
                                                        <div class="col-auto">
                                                            <i class="fa fa-bar-chart" aria-hidden="true" style="font-size: 50px"></i>
                                                        </div>
                                                        <div class="col text-right">
                                                            <h3>
                                                                <fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStar}"/> <i class="fa fa-star " style="color: blue"></i></h3>
                                                            <h5 class="text-c-info mb-0"><span class="text-muted">Feedback</span></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-block">
                                                    <div class="row align-items-center justify-content-center card-active mb-3">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Bready: </span><fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStarByCate5}"/><i class="fa fa-star " style="color: blue"></i></h6>

                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Pinpool:</span><fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStarByCate6}"/><i class="fa fa-star " style="color: blue"></i></h6>

                                                        </div>
                                                    </div>
                                                    <div class="row align-items-center justify-content-center card-active mb-3">
                                                        <div class="col-6">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Deliciuex:</span><fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStarByCate7}"/><i class="fa fa-star " style="color: blue"></i></h6>

                                                        </div>
                                                        <div class="col-6">
                                                            <h6 class="text-center  m-b-10"><span
                                                                    class="text-muted m-r-5">Cake: </span><fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStarByCate8}"/><i class="fa fa-star " style="color: blue"></i></h6>

                                                        </div>
                                                    </div>
                                                    <div class="row align-items-center justify-content-center card-active">
                                                        <div class="">
                                                            <h6 class="text-center m-b-10"><span
                                                                    class="text-muted m-r-5">Cupke: </span><fmt:formatNumber  type="number"  maxFractionDigits="1" value="${averageStarByCate9}"/><i class="fa fa-star " style="color: blue"></i></h6>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="row">
                                        <!-- [ bar-simple Chart ] start -->
                                        <div class="col-xl-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>Trend of order counts</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div id="morris-line-chart" style="height:300px "></div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- [ bar-simple Chart ] end -->

                                    </div>
                                    <!-- [ Main Content ] end -->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        


        <!-- chart-morris Js -->

        <script src="/src/assests/plugins/chart-morris/js/morris.min.js" type="text/javascript"></script>
        <script src="/src/assests/plugins/chart-morris/js/raphael.min.js" type="text/javascript"></script>
        <script src="/src/assests/js/chart-morris-custom.js" type="text/javascript"></script>
    </body>

</html>
<script>
//
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
                        labels: ['Total Order'],
                        lineColors: ['#463699']
                    });
                    // [ line-angle-chart ] end

                }, 700);
                // [ Donut-chart ] end
            });       
        
        // [ bar-simple ] chart end
</script>
<script>
    function checkoutdate(x) {
        var date1 = new Date(document.getElementById('checkin').value);
        var date2 = new Date(x.value);
        var checkindate = date1.toISOString().slice(0, 10);
        var inputdate = date2.toISOString().slice(0, 10);
        var date = new Date();
        if (checkindate > inputdate) {
            alert("Check out date must be after checkin date");
            x.value = "";
        } else {
            x.value = inputdate;
        }
    }
</script>                        