<%-- 
    Document   : ProductDetails
    Created on : May 31, 2022, 8:56:03 PM
    Author     : long4
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- product-default30:49-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Product Details | ${product.productName}</title><link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">

    </head>
    <body>
        <%@include file="../../public/header.jsp" %>  
        <!--include search-sidebar-->
        <div class="ps-page ps-page--product-detail">
            <div class="ps-hero ps-hero--shopping bg--cover" id="bgcoverhero">
                <div class="ps-hero__container">
                    <div class="ps-breadcrumb">
                        <ul class="breadcrumb">
                            <li><a href="/src/homepage">Home</a></li>
                            <li>Product Details</li>
                        </ul>
                    </div>
                    <h1 class="ps-hero__heading">Product Details</h1>
                </div>
            </div>
            <div class="container">
                <div class="ps-product--detail">
                    <div class="ps-product__header">
                        <div class="ps-product__thumbnail" data-vertical="false">
                            <figure>
                                <div class="ps-wrapper">
                                    <!--If the product has a discount, the value of the discount will be printed-->
                                    <c:if test="${product.discount > 0}">
                                        <span class="ps-badge ps-badge--sale">
                                            <i><fmt:formatNumber type="number"  maxFractionDigits="0" value="${product.discount*100}"/>%</i></span>
                                        </c:if>
                                    <div class="ps-product__gallery" data-arrow="true">
                                        <c:forEach items="${listPim}" var="o">
                                            <div class="item"><img src="${o.image}" alt=""></a></div>
                                            </c:forEach>  
                                    </div>
                                </div>
                            </figure>
                            <div class="ps-product__variants" data-item="5" data-md="4" data-sm="4" data-arrow="false">
                                <c:forEach items="${listPim}" var="o">
                                    <div class="item"><img src="${o.image}" alt=""></div>
                                    </c:forEach>  
                            </div>
                        </div>
                        <div class="ps-product__info">
                            <h1>${product.productName}</h1>
                            <div class="ps-product__meta">
                                <div class="ps-product__rating">
                                    <!--If the product has no reviews, the system will print the message "There are no reviews yet" , if there are reviews, the average rate will be printed.-->
                                    <c:choose>
                                        <c:when test="${avg==null||avg==0}"><h4>There are no reviews yet</h4></c:when>
                                        <c:otherwise>
                                            <select class="ps-rating" data-read-only="true">
                                                <c:forEach begin="1" end="${avg + (avg % 1 == 0 ? 0 : 0.5)}" var="c">
                                                    <option value="1">${c}</option>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${5.4-avg}" var="c">
                                                    <option value="2">${c}</option>
                                                </c:forEach>    
                                            </select> 
                                        </c:otherwise>
                                    </c:choose> 
                                </div>
                            </div>
                            <h4 class="ps-product__price sale">
                                <!-- show price after apply discount if product have discount else show price only-->
                                <c:if test="${product.discount > 0}" >
                                    <del> $${product.price}</del>
                                </c:if> 
                                $<fmt:formatNumber type="number"  maxFractionDigits="2" value="${product.price*(1-product.discount)}"/> 
                            </h4>
                            <div class="ps-product__desc" style="font-size: 20px">
                                ${product.description}
                            </div>
                            <div class="ps-product__specification">
                                <p><strong> CATEGORIES:</strong><a href="">${product.categoryID.categoryName}</a></p>
                            </div>
                            <div class="ps-product__shopping">
                               
                                <a class="ps-btn" href="addtocart?productID=${product.productID}">Order now</a>
                                <div class="ps-product__actions"><a href="#"><i class="icon-heart"></i></a><a href="#"><i class="icon-chart-bars"></i></a></div>
                            </div>
                            <div class="ps-product__sharing">
                                <div class="ps-product__actions"><a href="#"><i class="fa fa-heart-o"></i></a><a href="#"><i class="fa fa-random"></i></a></div>
                                <p>Share This:<a class="facebook" href="#"><i class="fa fa-facebook"></i></a><a class="twitter" href="#"><i class="fa fa-twitter"></i></a><a class="google" href="#"><i class="fa fa-google-plus"></i></a><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a><a class="instagram" href="#"><i class="fa fa-instagram"></i></a></p>
                            </div>
                        </div>
                    </div>
                    <div class="ps-product__content ps-tab-root">
                        <ul class="ps-tab-list">
                            <li class="active"><a href="#tab-1">Reviews</a></li>
                        </ul>
                        <div class="ps-tabs">
                            <div class="ps-tab active" id="tab-1">
                                <div class="ps-reviews">
                                    <!--If the product has no reviews, the system will display the message "There are currently no reviews for this product", and if there is a response, the system will print it.-->
                                    <c:if test="${listF==null ||listF.size() ==0}"><h2>There are currently no reviews for this product</h2></c:if>
                                    <c:forEach items="${listF}" var="f">
                                        <div class="ps-block--review">
                                            <div class="ps-block__thumbnail"><img src="/src/uploads/avatar/${f.userId.image}" alt=""></div>
                                            <div class="ps-block__content">
                                                <figure>
                                                    <figcaption>By <strong> ${f.userId.name}</strong> <span> ${f.updateDate}</span></figcaption>
                                                    <select class="ps-rating" data-read-only="true">
                                                        <c:forEach begin="1" end="${f.ratedStar}" var="c">
                                                            <option value="1">${c}</option>
                                                        </c:forEach>
                                                        <c:forEach begin="1" end="${5-f.ratedStar}" var="c">
                                                            <option value="2">${c}</option>
                                                        </c:forEach>    
                                                    </select>
                                                </figure>
                                                <p>${f.note}</p>
                                                <c:forEach items="${f.listImages}" var="s">
                                                    <img src="../uploads/Feedback/${s.imageName}" style="max-width: 100px">
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ps-section ps-related-product">
                <div class="container">
                    <div class="ps-section__header">
                        <p>You may also like</p>
                        <h3>Latest Products</h3><i class="chikery-tt3"></i>
                    </div>
                    <div class="ps-section__content">
                        <div class="row">
                            <c:forEach items="${listLP}" var="c">
                                <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6 col-6 ">
                                    <div class="ps-product">
                                        <div class="ps-product__thumbnail"><img src="${c.thumbnail}" alt=""/><a class="ps-product__overlay" href="details?productID=${c.productID}"></a>
                                            <!-- show discount if product have discount-->
                                            <c:if test="${c.discount!= 0}">
                                                <span class="ps-badge ps-badge--sale"><i><fmt:formatNumber type="number"  maxFractionDigits="0" value="${c.discount*100}"/>%</i></span>
                                            </c:if>
                                        </div>
                                        <div class="ps-product__content">
                                            <div class="ps-product__desc"><a class="ps-product__title" href="details?productID=${c.productID}">${c.productName}</a>
                                                <p>
                                                    <span>${c.weight}</span>
                                                    <span>${c.time} Min</span>
                                                    <span>${c.degree} <sup>o</sup>C</span>
                                                </p>
                                                <span class="ps-product__price sale">
                                                    <!-- show price after apply discount if product have discount else show price only-->
                                                    <c:choose> 
                                                        <c:when test="${c.discount > 0}">  
                                                            <del>$${c.price}</del>
                                                            $<fmt:formatNumber type="number"  maxFractionDigits="3"  value="${c.price * (1-c.discount)}"/>
                                                        </span> 
                                                    </c:when>
                                                    <c:otherwise>
                                                        $${c.price}
                                                    </c:otherwise>
                                                </c:choose> 
                                            </div>
                                            <div class="ps-product__shopping"><a class="ps-btn ps-product__add-to-cart" href="addtocart?productID=${c.productID}">Add to cart</a>
                                                <div class="ps-product__actions"><a href="#"><i class="fa fa-heart-o"></i></a><a href="#"><i class="fa fa-random"></i></a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>

    </body>

    <!-- product-default31:02-->
</html>
<script src="/src/assests/js/toast.js"></script>
<script>

    <c:if test="${sessionScope.mess=='AddToCart'}">
    Alert({
        type: "success",
        content: "Add successful!!!"
    })
    <%    session.removeAttribute("mess");%>
    </c:if>

</script>