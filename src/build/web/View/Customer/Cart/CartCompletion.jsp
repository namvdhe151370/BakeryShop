<%-- 
    Document   : OrderInformation1
    Created on : Jun 16, 2022, 4:32:33 PM
    Author     : Huy Thai
--%>
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
        <title>Completion</title>
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
        <link rel="stylesheet" href="/src/assests/css/cartcompletion.css">
    </head>
    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="container">
            <div class="ps-hero__container">
                <div class="ps-breadcrumb">
                    <ul class="breadcrumb">
                        <li><a href="">Cart</a></li>
                        <li>Settings</li>
                    </ul>
                </div>
                <h1 class="ps-hero__heading"> Cart Completion</h1>
            </div>
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
                                    <li><a class="page-link" href="/src/customer/cartcompletion?pageCate=${pageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                        </c:if> 
                                <li><a class="page-link" href="/src/customer/cartcompletion?pageCate=${pageCate}">${pageCate}/${endCaPage}</a></li>
                                    <c:if test="${pageCate < endCaPage }">
                                    <li><a class="page-link" href="/src/customer/cartcompletion?pageCate=${pageCate+1}"><i class="fa fa-caret-right"></i></a></li>
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
                <form action="/src/customer/cartcompletion" method="post">
                    <div class="ps-shopping__right">
                        <article class="card">
                            <header class="card-header"> <h3>Order Tracking</h3> </header>
                            <div class="card-body">
                                <div class="track">
                                    <c:if test="${requestScope.order.orderStatus == 2}">
                                        <div class="step active"> <span class="icon"> <i class="fa fa-file-text"></i> </span> <span class="text">Submitted</span> </div>
                                        <div class="step "> <span class="icon"> <i class="fa fa-check"></i> </span> <span class="text"> Order confirmed</span> </div>
                                        <div class="step"> <span class="icon"> <i class="fa fa-truck"></i> </span> <span class="text"> On the way </span> </div>
                                        <div class="step"> <span class="icon"> <i class="fa fa-inbox"></i> </span> <span class="text">Ready for pickup</span> </div>
                                    </c:if>
                                    <c:if test="${requestScope.order.orderStatus != 2}">
                                        <div class="step active"> <span class="icon"> <i class="fa fa-file-text"></i> </span> <span class="text">Submitted</span> </div>
                                        <div class="step active"> <span class="icon"> <i class="fa fa-check"></i> </span> <span class="text"> Order confirmed</span> </div>
                                        <div class="step"> <span class="icon"> <i class="fa fa-truck"></i> </span> <span class="text"> On the way </span> </div>
                                        <div class="step"> <span class="icon"> <i class="fa fa-inbox"></i> </span> <span class="text">Ready for pickup</span> </div>
                                    </c:if>
                                </div>
                                <!--<a><button type="button" class="ps-btn">Cancel</button></a>--> 
                            </div>
                        </article>
                        <div class="form-group">
                            <h3>Order Note</h3>
                            <textarea id="Ordernote" class="form-control" rows="4" placeholder="" readonly="">
                                ${requestScope.OrderNote}
                            </textarea>
                        </div>
                        <hr>
                        <h3>Choose Payment Method</h3>
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="false">
                            <div class="ps-section__actions">
                                <input name="payment" class="ps-btn ps-btn--outline " data-target="#carouselExampleIndicators" data-slide-to="0" class="active" aria-pressed="true" value="Ship Code" readonly="">
                                <input name="payment" class="ps-btn ps-btn--outline" data-target="#carouselExampleIndicators" data-slide-to="1" value="Banking Account" readonly="">
                                <!--<input name="payment" class="ps-btn ps-btn--outline " data-target="#carouselExampleIndicators" data-slide-to="2" value="VNPAY QR" readonly="">-->
                            </div>
                            <hr> 
                            <div class="carousel-inner">
                                <div class="carousel-item active" >
                                    <textarea id="Ordernote" class="form-control" rows="4" placeholder="" readonly="">
    Payment When Receiving Product With The Delivery Staff
                                    </textarea>
                                </div>
                                <div class="carousel-item">
                                    <textarea id="Ordernote" class="form-control" rows="5" placeholder="" readonly="">
    Customer Pay For Order Through The Account Number Below
    The Order Will Be Active When Receiving The Money
    Bank System: MBBANK
    Account Name: HA HUY THAI
    Bank Account Number: 0987654321
                                    </textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="ps-checkout__right">
                            <div class="ps-block--your-order">
                                <!--<div class="ps-block__divider"></div>-->
                                <div class="ps-block__detail">
                                    <p style="color: black">Sub Total<span> $ <fmt:formatNumber type = "number" 
                                                      maxIntegerDigits = "3" value = "${requestScope.order.totalMoney}" /> </span></p>
                                    <div class="ps-block__divider"></div>
                                </div>
                                <div class="ps-block__detail">
                                    <p style="color: black">Shopping Fee<span>$0</span></p>
                                    <div class="ps-block__divider"></div>
                                </div>
                                <div class="ps-block__detail">
                                    <p style="color: black">ToTal Cost<span> $ <fmt:formatNumber type = "number" 
                                                      maxIntegerDigits = "3" value = "${requestScope.order.totalMoney}" /></span></p>
                                    <div class="ps-block__divider"></div>
                                </div>
                                <div class="ps-block__footer">
                                </div>
                            </div>
                            <div class="ps-block__footer">
                                <div class="ps-section__actions" style="padding-top: 20px">
                                    <a><button type="submit" class="ps-btn">Submit</button></a>
                                    <a id="btnChoose"  class="ps-btn ps-btn--outline" href="/src/customer/productlist">Get Another Order</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
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

    <c:if test="${sessionScope.mess=='Order Has Been Confirmed'}">
    Alert({
        type: "success",
        content: "Order Has Been Confirmed!!!!"
    })
        <%    session.removeAttribute("mess");%>
    </c:if>

    <c:if test="${sessionScope.mess=='Check Your Email To Confirm Your Order'}">
    Alert({
        type: "success",
        content: "Check Your Email To Confirm Your Order!!!!"
    })
        <%    session.removeAttribute("mess");%>
    </c:if>
</script>