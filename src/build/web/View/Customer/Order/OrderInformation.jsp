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
        <title>Chikery</title>
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
        <link rel="stylesheet" href="/src/assests/css/order.css">
        <link rel="stylesheet" href="plugins/chikery-icon/style.css">


    </head>
    <style>
        #orderform{
            padding-top: 200px !important;
        }
    </style>

    <body>
        <%@include file="../../public/header.jsp" %>

        <div class="container">
            <div class="ps-shopping" id="orderform">
                <div class="ps-shopping__left">
                    <aside class="widget widget_search">
                        <form class="ps-form--widget-search" action="/src/customer/productlist" method="get">
                            <input class="form-control" type="text" placeholder="Search Anything" name="searchkeyword">
                            <button type="submit"><i class="fa fa-search"></i></button>
                        </form>
                    </aside>
                    <aside class="widget widget_shop widget_categories">
                        <h3>Categories</h3>
                        <ul>
                            <!-- print list category-->
                            <c:forEach items="${sessionScope.listCategory}" var="C">
                                <li><a  href="/src/customer/productlist?categorykeyword=${C.categoryID}">${C.categoryName}</a></li>
                                </c:forEach>
                        </ul>
                        <div class="pagination">
                            <ul class="pagination">
                                <c:if test="${pageCate >1}">  
                                    <li><a class="page-link" href="/order/orderinformation?pageCate=${pageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                        </c:if> 
                                <li><a class="page-link" href="/order/orderinformation?pageCate=${pageCate}">${pageCate}/${endCaPage}</a></li>
                                    <c:if test="${pageCate < endCaPage }">
                                    <li><a class="page-link" href="/order/orderinformation?pageCate=${pageCate+1}"><i class="fa fa-caret-right"></i></a></li>
                                        </c:if>
                            </ul> 
                        </div>
                    </aside>

                    <aside class="widget widget_shop widget_recent-product">
                        <h3 class="widget-title">Latest Products</h3>
                        <c:forEach items="${topProduct}" var="T">
                            <div class="ps-product--sidebar">
                                <div class="ps-product__thumbnail" >
                                    <a class="ps-product__overlay" href="/src/customer/productdetail?productID=${T.productID}"><img src="${T.thumbnail}"  alt=""/></a>
                                </div>
                                <div class="ps-product__content">
                                    <a class="ps-product__title" style="padding-top: 10px" href="/src/customer/productdetail?productID=${T.productID}">${T.productName}</a> 
                                    <p><span>${T.weight}g </span><span> ${T.time} Min</span><span> ${T.degree} <sup>o</sup>C</span></p>
                                    <c:choose>
                                        <c:when test="${T.discount ==0}"><span class="ps-product__price">$${T.price}</span></c:when>
                                        <c:otherwise><span class="ps-product__price"><del>$${T.price}</del>    $<fmt:formatNumber type="number"  maxFractionDigits="2" value="${T.price*(1- T.discount)}"/></span></c:otherwise>
                                    </c:choose>
                                </div>

                            </div>
                        </c:forEach>

                    </aside>
                </div>
                <div class="ps-shopping__right">

                    <div class="ps-shopping__top" >
                        <div class="ps-shopping__top__left"  style="margin-right: 100px;">
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-18 col-18 ">
                                    <h3>Order Detail</h3>
                                    <p>Order ID: <span>${requestScope.order.oderID}</span></p>
                                    <p>Order Date: <span>${requestScope.order.orderDate}</span></p>
                                    <p>Total Cost: <span>$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${requestScope.order.totalMoney}"/></span></p>
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
                                                        <form action="/src/order/orderinformation" method="POST">
                                                            <input type="hidden" name="OrderID" value="${requestScope.order.oderID}">
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
                                                                <tbody id="orderInfoTable">

                                                                    <c:forEach items="${requestScope.order_details}" var="odd">    
                                                                        <tr>
                                                                            <td id="productTd">
                                                                                <div class="ps-product--cart">
                                                                                    <div class="ps-product" id="productthumbnail">
                                                                                        <div class="ps-product__thumbnail">
                                                                                            <img src="${odd.productId.thumbnail}" alt="" />
                                                                                            <a class="ps-product__overlay" href="/src/customer/productdetail?productID=${odd.productId.productID}"></a>
                                                                                            <span class="ps-badge ps-badge--sale"><i><fmt:formatNumber type = "percent" maxIntegerDigits = "3" value = "${odd.productId.discount}" /></i></span>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="ps-product__content">
                                                                                        <a class="ps-product__title" href="/src/customer/productdetail?productID=${odd.productId.productID}">${odd.productId.productName}</a>
                                                                                        <div class="ps-product__meta">
                                                                                            <p id="metap" style="font-size: 15px ; ">From <a class="metaa">Hoang</a></p>
                                                                                        </div>
                                                                                        <div class="ps-section__actions">
                                                                                            <a href="/src/order/addtocart?productID=${odd.productId.productID}&orderId=${requestScope.order.oderID}"> <button type="button" class="btnOrderChoose">Rebuy</button></a>
                                                                                            <c:if test="${odd.statusFeedback==true}">
                                                                                                <a href="/src/customer/feedback?productId=${odd.productId.productID}&orderId=${requestScope.order.oderID}"> <button type="button" class="btnOrderChoose">FeedBack</button></a>  
                                                                                            </c:if>
                                                                                            <c:if test="${odd.statusFeedback==false}">
                                                                                                <button type="button" class="btnOrderChoose" disabled>FeedBack</button>                       
                                                                                            </c:if>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </td>
                                                                            <td>${odd.productId.categoryID.categoryName}</td>
                                                                            <td>$${odd.productId.price}</td>
                                                                            <td>${odd.quantity}</td>
                                                                            <td class="total">
                                                                                <fmt:formatNumber type = "CURRENCY" maxIntegerDigits = "5" value = "${odd.productId.price*(1- odd.productId.discount)*odd.quantity}" />
                                                                            </td>

                                                                        </tr>
                                                                    </tbody>
                                                                </c:forEach>    
                                                                <tfoot id="tfootOrder">
                                                                    <tr>
                                                                        <td colspan="2" >
                                                                            <div class="ps-section__actions">
                                                                                <figure><a class="ps-btn" href="/src/order/updateorder?OrderId=${requestScope.order.oderID}">Update</a>
                                                                                    <button type="submit" id="btnChoose"  class="ps-btn ps-btn--outline">Cancel</button>
                                                                                </figure>
                                                                            </div>
                                                                        <td>
                                                                        <td id="Pay">Total Pay: </td>
                                                                        <td id="Pay">$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${requestScope.order.totalMoney}"/></td>
                                                                    </tr>
                                                                </tfoot>
                                                            </table>
                                                        </form>
                                                    </div>
                                                </div>

                                                </div>
                                                </div>
                                                </div>
                                                <!--    
                                                  <div id="back2top"><i class="pe-7s-angle-up"></i></div>
                                                  <div class="ps-site-overlay"></div>
                                                  <div id="loader-wrapper">
                                                    <div class="loader-section section-left"></div>
                                                    <div class="loader-section section-right"></div>
                                                  </div>
                                                  <div class="ps-search" id="site-search"><a class="ps-btn--close" href="#"></a>
                                                    <div class="ps-search__content">
                                                      <form class="ps-form--primary-search" action="#" method="post">
                                                        <input class="form-control" type="text" placeholder="Search for...">
                                                        <button><i class="fa fa-search"></i></button>
                                                      </form>
                                                    </div>
                                                  </div>-->
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

                                                    <c:if test="${sessionScope.mess=='Product was added to Cart'}">
                                                    Alert({
                                                        type: "success",
                                                        content: "Product was added to Cart!!!"
                                                    })
                                                        <%    session.removeAttribute("mess");%>
                                                    </c:if>
                                                    <c:if test="${sessionScope.mess=='CanCel Order Successfull'}">
                                                    Alert({
                                                        type: "success",
                                                        content: "CanCel Order Successfull!!!"
                                                    })
                                                        <%    session.removeAttribute("mess");%>
                                                    </c:if>
                                                        
                                                    <c:if test="${sessionScope.mess=='Update Order Sucessfull'}">
                                                    Alert({
                                                        type: "success",
                                                        content: "Update Order Sucessfull!!!"
                                                    })
                                                        <%    session.removeAttribute("mess");%>
                                                    </c:if>
                                                </script>