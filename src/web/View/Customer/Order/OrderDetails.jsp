<%-- 
    Document   : OrderInformation1
    Created on : Jun 16, 2022, 4:32:33 PM
    Author     : Huy Thai
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <!-- shop-default37:30-->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Order Details</title>
        <link
            href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="plugins/bootstrap4/css/bootstrap.min.css">
        <link rel="stylesheet" href="plugins/owl-carousel/assets/owl.carousel.css">
        <link rel="stylesheet" href="plugins/slick/slick/slick.css">
        <link rel="stylesheet" href="plugins/lightGallery-master/dist/css/lightgallery.min.css">
        <link rel="stylesheet" href="plugins/jquery-bar-rating/dist/themes/fontawesome-stars.css">
        <link rel="stylesheet" href="plugins/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" href="plugins/select2/dist/css/select2.min.css">
        <link rel="stylesheet" href="plugins/chikery-icon/style.css">
        <link rel="stylesheet" href="/src/assests/css/order.css">
    </head>
    <style>
        #orderform{
            padding-top: 200px !important;
        }
        #Ordernote{
            text-align: left;
        }
    </style>
    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="container">
            <div class="ps-shopping" id="orderform">
                <div class="ps-shopping__right">
                    <div class="ps-shopping__top" >
                        <div class="ps-shopping__top__left"  style="margin-right: 100px;">
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-18 col-18 ">
                                    <h3>Order Detail</h3>
                                    <p>Order ID: <span>${requestScope.order.oderID}</span></p>
                                    <p>Customer Name: <span>${requestScope.user.name}</span></p>
                                    <p>Customer Email: <span>${requestScope.user.email}</span></p>
                                    <p>Customer Mobile: <span>${requestScope.user.mobile}</span></p>
                                    <p>Sale Name: <span>${requestScope.sale.name}</span></p>
                                    <p>Order Date: <span>${requestScope.order.orderDate}</span></p>
                                    <p>Total Cost: <span><fmt:formatNumber type = "CURRENCY" maxIntegerDigits = "5" value = "${requestScope.order.totalMoney}"/></span></p>
                                    <p>Status: <span>${requestScope.order.status}</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="ps-shopping__top__right"  >
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-18 col-18 ">
                                    <h3>Receiver Information</h3>
                                    <p>Name: <span>${requestScope.user.name}<span></p>
                                                <p>Email: <span>${requestScope.user.email}</span></p>
                                                <c:if test="${requestScope.user.gender == true}" ><p>Gender: Male</p>
                                                </c:if>
                                                <c:if test="${requestScope.user.gender == false}" >
                                                    <p>Gender: Female</p>
                                                </c:if>
                                                <p>Mobile: <span>${requestScope.user.mobile}</span></p>
                                                <p>Address: <span>${requestScope.user.address}</span></p>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                <div class="ps-product-box">
                                                    <div class="table-responsive">
                                                        <table class="table ps-table ps-table--shopping-cart">
                                                            <thead id="orderInfoTable">
                                                                <tr>
                                                                    <th id="producTh">Product Name</th>
                                                                    <th>Category</th>
                                                                    <th>Price</th>
                                                                    <th>Quantity</th>
                                                                    <th>Total</th>
                                                                </tr>
                                                            </thead>

                                                            <c:forEach items="${requestScope.order_details}" var="odd">  
                                                                <tbody id="orderInfoTable">
                                                                    <tr>
                                                                        <td id="productTd">
                                                                            <div class="ps-product--cart">
                                                                                <div class="ps-product" id="productthumbnail">
                                                                                    <div class="ps-product__thumbnail">
                                                                                        <img src="${odd.productId.thumbnail}"/>
                                                                                        <a class="ps-product__overlay" href="/src/customer/productdetail?productID=${odd.productId.productID}"></a>
                                                                                        <span class="ps-badge ps-badge--sale"><i><fmt:formatNumber type = "percent" maxIntegerDigits = "3" value = "${odd.productId.discount}" /></i></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="ps-product__content">
                                                                                    <a class="ps-product__title" href="/src/customer/productdetail?productID=${odd.productId.productID}">${odd.productId.productName}</a>
                                                                                    <div class="ps-product__meta">
                                                                                        <p id="metap" style="font-size: 15px ; ">From <a class="metaa">Hoang</a></p>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </td>
                                                                        <td>${odd.productId.categoryID.categoryName}</td>
                                                                        <td>$${odd.productId.price}</td>
                                                                        <td>${odd.quantity}</td>
                                                                        <td class="total">
                                                                            <fmt:formatNumber type = "CURRENCY" maxIntegerDigits = "5" value = "${odd.quantity *(odd.price - (odd.discount * odd.price))}" />
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </c:forEach>    
                                                            <tfoot id="tfootOrder">
                                                                <tr>
                                                                    <c:if test="${user.id == 2}">
                                                                        <td colspan="2" >
                                                                            <div class="ps-section__actions">
                                                                                <figure><a class="ps-btn" data-toggle="modal" href="#UpdateOrder1">Update</a>
                                                                                    <a href="/src/sale/orderlist" id="btnChoose"  class="ps-btn ps-btn--outline">Back To List</a>
                                                                            </div>
                                                                        </td>
                                                                    </c:if>
                                                                    <c:if test="${user.id != 2}">
                                                                        <td colspan="2" >
                                                                            <div class="ps-section__actions">
                                                                                <figure><a class="ps-btn" data-toggle="modal" href="#UpdateOrder2">Update</a>
                                                                                    <a href="/src/sale/orderlist" id="btnChoose"  class="ps-btn ps-btn--outline">Back To List</a>
                                                                            </div>
                                                                        </td>
                                                                    </c:if>
                                                                    <td id="Pay">Total Pay: </td>
                                                                    <td id="Pay"><fmt:formatNumber type = "CURRENCY" maxIntegerDigits = "5" value = "${requestScope.order.totalMoney}"/></td>
                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                <!--Update by sale manager-->
                                                <div class="modal fade" id="UpdateOrder1">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <form action="/src/order/orderdetails" method="post">
                                                                <div class="modal-header">						
                                                                    <h3 class="modal-title" >Update Order Details</h3>
                                                                    <input type="hidden" name="OrderID" value="${requestScope.order.oderID}"/>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                </div>
                                                                </hr>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <h4>Status</h4>
                                                                        <select name="Statusxyz" class="form-control">
                                                                            <c:forEach items="${orderstatus}" var="o">
                                                                                <c:if test="${o == \"1\"}">
                                                                                    <option ${order.orderStatus == o?"selected":""} value="${o}">Cancel</option>
                                                                                </c:if>
                                                                                <c:if test="${o ==\"2\"}">
                                                                                    <option ${order.orderStatus == o?"selected":""} value="${o}">Pending</option>
                                                                                </c:if>
                                                                                <c:if test="${o ==\"3\"}">
                                                                                    <option ${order.orderStatus == o?"selected":""}  value="${o}">Processing</option>
                                                                                </c:if>
                                                                                <c:if test="${o ==\"4\"}">
                                                                                    <option ${order.orderStatus == o?"selected":""}  value="${o}">Shipped</option>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <h4>Order Note</h4>
                                                                        <textarea id="Ordernote" class="form-control" rows="4" placeholder="" name="note">
${requestScope.OrderNote}
                                                                        </textarea>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <h4>Sale</h4>
                                                                        <select name="sale" class="form-control">
                                                                            <c:forEach items="${salelist}" var="c">
                                                                                <option <c:if test="${c == order.saleId}">selected</c:if> value="${c}">
                                                                                    <c:if test="${c == 2}">Vinh</c:if>
                                                                                    <c:if test="${c == 2008}">hoang</c:if>
                                                                                    <c:if test="${c == 2009}">Thái</c:if>
                                                                                    </option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <input type="submit" class="btn btn-success" value="Save" style="font-size: 2rem">       
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--Update by sale-->
                                                <div class="modal fade" id="UpdateOrder2">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <form action="/src/order/orderdetails" method="post">
                                                                <div class="modal-header">						
                                                                    <h3 class="modal-title" >Update Order Details</h3>
                                                                    <input type="hidden" name="OrderID" value="${requestScope.order.oderID}"/>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                </div>
                                                                </hr>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <h4>Status</h4>
                                                                        <select name="Statusxyz" class="form-control">
                                                                            <c:forEach items="${orderstatus}" var="c">
                                                                                <option <c:if test="${c == order.orderStatus}">selected</c:if> value="${c}">
                                                                                    <c:if test="${c == 1}">Cancel</c:if>
                                                                                    <c:if test="${c == 2}">Pending</c:if>
                                                                                    <c:if test="${c == 3}">Processing</c:if>
                                                                                    <c:if test="${c == 4}">Shipped</c:if>
                                                                                    </option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <h4>Order Note</h4>
                                                                        <textarea id="Ordernote" class="form-control" rows="4" placeholder="" name="note">
${requestScope.OrderNote}
                                                                        </textarea>
                                                                    </div>
                                                                    <input class="form-control" name="sale"
                                                                           value="${order.saleId}" type="hidden" />
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <input type="submit" class="btn btn-success" value="Save" style="font-size: 2rem">       
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div> 
                                                <!--    
                                                <!-- Plugins-->
                                                <script src="plugins/jquery-1.12.4.min.js"></script>
                                                <script src="plugins/owl-carousel/owl.carousel.min.js"></script>
                                                <script src="plugins/bootstrap4/js/bootstrap.min.js"></script>
                                                <script src="plugins/imagesloaded.pkgd.js"></script>
                                                <script src="plugins/masonry.pkgd.min.js"></script>
                                                <script src="plugins/isotope.pkgd.min.js"></script>
                                                <script src="plugins/jquery.matchHeight-min.js"></script>
                                                <script src="plugins/slick/slick/slick.min.js"></script>
                                                <script src="plugins/jquery-bar-rating/dist/jquery.barrating.min.js"></script>
                                                <script src="plugins/slick-animation.min.js"></script>
                                                <script src="plugins/lightGallery-master/dist/js/lightgallery-all.min.js"></script>
                                                <script src="plugins/jquery-ui/jquery-ui.min.js"></script>
                                                <script src="plugins/sticky-sidebar/dist/sticky-sidebar.min.js"></script>
                                                <script src="plugins/jquery.slimscroll.min.js"></script>
                                                <script src="plugins/select2/dist/js/select2.full.min.js"></script>
                                                <script src="plugins/gmap3.min.js"></script>
                                                <script src="js/main.js"></script>
                                                <script
                                                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxflHHc5FlDVI-J71pO7hM1QJNW1dRp4U&amp;region=GB"></script>
                                                <%@include file="../../public/footer.jsp" %>
                                                </body>
                                                <!-- shop-default37:32-->
                                                </html>
                                                <script src="/src/assests/js/toast.js"></script>
                                                <script>
                                                    <c:if test="${requestScope.mess=='Product was added to Cart'}">
                                                    Alert({
                                                        type: "success",
                                                        content: "Product was added to Cart!!!"
                                                    })
                                                    </c:if>
                                                    <c:if test="${sessionScope.mess=='Update Order Successfully'}">
                                                    Alert({
                                                        type: "success",
                                                        content: "Update Order Successfully!!!"
                                                    })
                                                        <%    session.removeAttribute("mess");%>
                                                    </c:if>
                                                </script>