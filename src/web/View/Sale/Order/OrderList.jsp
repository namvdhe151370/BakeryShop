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

        <title>Sale - Order List</title>
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
        <link rel="stylesheet" href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    </head>
    <style>
        select.form-control {
            display: inline;
            width: 150px;
            margin-left: 25px;
            margin-bottom: 15px;
        }

        input.form-control {
            display: inline;
            width: 150px;
            margin-left: 25px;
            margin-bottom: 15px;
            color: black;
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
                                                    <h5 class="m-b-10">Order List</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i
                                                                class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Sale</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Order List</a></li>
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

                                                    <div class="Mindate-filter">
                                                        <input type="text" class="form-control" id="min" name="min"
                                                               placeholder="Min Date">
                                                    </div>
                                                    <div class="Maxdate-filter">

                                                        <input type="text" class="form-control" id="max" name="max"
                                                               placeholder="Max Date">
                                                    </div>
                                                    <!-- Create the drop down filter -->
                                                    <div class="Sale">
                                                        <select id="sale" class="form-control">
                                                            <option value="">Show Sale Name</option>
                                                            <c:forEach items="${listS}" var="s">
                                                                <option value="${s.name}">${s.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    
                                                    <div class="status-filter">
                                                        <select id="status" class="form-control">
                                                            <option value="">Show Status</option>
                                                            <c:forEach items="${listOs}" var="c">
                                                                <option value="${c.status}">${c.status}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    
                                                    <!-- Set up the datatable -->
                                                    <table class="table hover" id="filterTable">
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Product</th>
                                                                <th>Customer Name</th>
                                                                <th>Total Money</th>
                                                                <th>Ordered Date</th>
                                                                <th>Sale Name</th>
                                                                <th>Status</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>

                                                        <tbody style="">
                                                            <c:forEach items="${listOd}" var="o">
                                                                <tr>
                                                                    <td>${o.oderID}</td>
                                                                    <td>
                                                                        ${o.listOder_Details.get(0).productId.productName}
                                                                        <c:if test="${o.listOder_Details.size()-1>0}">
                                                                            & ${o.listOder_Details.size()-1} more products
                                                                        </c:if>
                                                                    </td>
                                                                    <td>${o.userId.name}</td>
                                                                    <td>${o.totalMoney}</td>
                                                                    <td>${o.orderDate}</td>
                                                                    <td>${o.saleName}</td>
                                                                    <td>${o.status}</td>
                                                                    <td style="text-align: center;">
                                                                       <a href="/src/order/orderdetails?OrderID=${o.oderID}"> View </a>
                                                                    </td>
                                                                </tr> 
                                                            </c:forEach>

                                                        </tbody>
                                                        <tfoot>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Product</th>
                                                                <th>Customer Name</th>
                                                                <th>Total Money</th>
                                                                <th>Ordered Date</th>
                                                                <th>Sale Name</th>
                                                                <th>Status</th>
                                                                <th>Actions</th>
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

        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.2/moment.min.js"></script>
        <script src="https://cdn.datatables.net/datetime/1.1.2/js/dataTables.dateTime.min.js"></script>

        <script>
            $("document").ready(function () {
                $("#filterTable").dataTable({
                    "searching": true
                });
                //Get a reference to the new datatable
                var table = $('#filterTable').DataTable();
                //Take the category filter drop down and append it to the datatables_filter div. 
                //You can use this same idea to move the filter anywhere withing the datatable that you want.
                $("#sale").insertBefore($("#filterTable_filter.dataTables_filter"));
                $("#status").insertBefore($("#filterTable_filter.dataTables_filter"));
                $("#min").insertBefore($("#filterTable_filter.dataTables_filter"));
                $("#max").insertBefore($("#filterTable_filter.dataTables_filter"));
                //Get the column index for the Category column to be used in the method below ($.fn.dataTable.ext.search.push)
                //This tells datatables what column to filter on when a user selects a value from the dropdown.
                //It's important that the text used here (Category) is the same for used in the header of the column to filter
                var saleIndex = 0;
                $("#filterTable th").each(function (i) {
                    if ($($(this)).html() == "Sale Name") {
                        saleIndex = i;
                        return false;
                    }
                });
                var statusIndex = 0;
                $("#filterTable th").each(function (i) {
                    if ($($(this)).html() == "Status") {
                        statusIndex = i;
                        return false;
                    }
                });
                var minDate, maxDate;

                //Use the built in datatables API to filter the existing rows by the Category column
                $.fn.dataTable.ext.search.push(
                        function (settings, data, dataIndex) {
                            var selectedItem = $('#sale').val()
                            var sale = data[saleIndex];
                            if (selectedItem === "" || sale.includes(selectedItem)) {
                                return true;
                            }
                            return false;
                        }
                );
                $.fn.dataTable.ext.search.push(
                        function (settings, data, dataIndex) {
                            var selectedItem = $('#status').val()
                            var status = data[statusIndex];
                            if (selectedItem === "" || status.includes(selectedItem)) {
                                return true;
                            }
                            return false;
                        }
                );
                $.fn.dataTable.ext.search.push(
                        function (settings, data, dataIndex) {
                            var min = minDate.val();
                            var max = maxDate.val();
                            var date = new Date(data[4]);

                            if (
                                    (min === null && max === null) ||
                                    (min === null && date <= max) ||
                                    (min <= date && max === null) ||
                                    (min <= date && date <= max)
                                    ) {
                                return true;
                            }
                            return false;
                        }
                );

                // Create date inputs
                minDate = new DateTime($('#min'), {
                    format: 'YYYY-MM-DD'
                });
                maxDate = new DateTime($('#max'), {
                    format: 'YYYY-MM-DD'
                });
                //Set the change event for the Category Filter dropdown to redraw the datatable each time
                //a user selects a new filter.
                $("#sale").change(function (e) {
                    table.draw();
                });
                $("#status").change(function (e) {
                    table.draw();
                });
                $('#min, #max').on('change', function () {
                    table.draw();
                });



                table.draw();
            });
        </script>


    </body>

</html>
