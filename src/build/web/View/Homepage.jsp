<%-- 
    Document   : Homepage
    Created on : Jun 1, 2022, 10:01:09 PM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- homepage-231:54-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Homepage </title><link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">
    </head>
    <body>
        <%@include file="public/header2.jsp" %>
        <div id="homepage-2">
            <div id="homepage-2">

                <div class="ps-home-banner bg--cover" id="homepageBanner" >
                    <div class="ps-carousel--nav owl-slider" data-owl-auto="true" data-owl-loop="true" data-owl-speed="5000" data-owl-gap="0" data-owl-nav="true" data-owl-dots="false" data-owl-item="1" data-owl-item-xs="1" data-owl-item-sm="1" data-owl-item-md="1" data-owl-item-lg="1" data-owl-duration="1000" data-owl-mousedrag="off" data-owl-nav-left="&lt;i class='fa fa-arrow-left'&gt;&lt;/i&gt;" data-owl-nav-right="&lt;i class='fa fa-arrow-right'&gt;&lt;/i&gt;">
                        <c:forEach items="${requestScope.lsSlider}" var="c">
                            <c:if test="${c.postID eq null}">
                                <div class="ps-product--banner">
                                    <div class="ps-product__thumbnail"><a href="${c.backlink}"><img src="${c.image}" alt=""></a></div>
                                    <div class="ps-product__content">
                                        <h4>FEATURED PRODUCT</h4><a class="ps-product__title" href="${c.backlink}"> ${c.title} <br> ${c.notes}</a>
                                        <p>Bakery - Sweet - Bio</p><a class="ps-btn" href="${c.backlink}"> Order Now</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${c.productID eq null}">  
                                <div class="ps-product--banner" >
                                    <div class="ps-product__thumbnail" id="postthumnailSlider"><a href="${c.backlink}"><img src="${c.postID.thumbnail}" alt="" ></a></div>
                                    <div class="ps-product__content">
                                        <h4>Hotpost</h4><a class="ps-product__title" href="${c.backlink}"> ${c.postID.postTitle} <br> ${c.notes}</a>
                                        <p>Bakery - Sweet - Bio</p><a class="ps-btn" href="${c.backlink}"> View Now</a>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>


                    </div>
                </div>



                <div class="ps-section ps-home-product">
                    <div class="container">
                        <div class="ps-section__header">
                            <p></p>
                            <h3>Our Special Product</h3><i class="chikery-tt3"></i>
                        </div>
                        <div class="ps-section__content">
                            <div class="row">
                                <c:forEach items="${requestScope.lsProduct}" var="c">
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-6 ">
                                        <div class="ps-product">
                                            <div class="ps-product__thumbnail"><a class="" href="/src/customer/productdetail?productID=${c.productID}"><img  src="${c.thumbnail}" alt=""/></a>
                                            </div>
                                            <div class="ps-product__content">
                                                <div class="ps-product__desc"><a class="ps-product__title" href="/src/customer/productdetail?productID=${c.productID}">${c.productName}</a>
                                                    <p>
                                                        <span>${c.weight}</span>
                                                        <span>${c.time} Min</span>
                                                        <span>${c.degree} <sup>o</sup>C</span>
                                                    </p>
                                                    <span class="ps-product__price sale">  
                                                        <c:choose> 
                                                            <c:when test="${c.discount > 0}">  
                                                                <del>$ ${c.price}</del>
                                                                $<fmt:formatNumber type="number"  maxFractionDigits="3"  value="${c.price * (1-c.discount)}"/>
                                                            </span> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            $ ${c.price}</span>
                                                        </c:otherwise>
                                                    </c:choose> 
                                                </div>
                                                <div class="ps-product__shopping"><a class="ps-btn ps-product__add-to-cart"  href="/src/customer/addtocart?productID=${c.productID}">Add to cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="ps-section__footer text-center"><a class="ps-btn ps-btn--outline" href="/src/customer/productlist">All Products</a></div>
                        </div>

                    </div>
                </div>

                <div class="ps-section ps-home-about bg--cover" id="bghomeabout" >
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 ">
                                <div class="ps-section__image"><img src="https://drive.google.com/uc?export=view&id=1xpqs-zMlXJhZPTJRT40XmBHPz1I0b7FG" alt=""></div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 ">
                                <div class="ps-section__content">
                                    <p>“ Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sed turpis feugiat, mollis felis vel, viverra metus. Sed vel nulla non neque dictum imperdiet hendrerit ”</p>
                                    <div class="ps-section__image"><img src="https://drive.google.com/uc?export=view&id=1wgpmIi1xL7ZHLEgniRw3LN5wd_fO8flE" alt=""></div>
                                    <h5><span>Marry Lulie</span> - Ceo Chikery</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="ps-section ps-home-blog">
                    <div class="container">
                        <div class="ps-section__header">
                            <p>Blog & News</p>
                            <h3>From Our Archive</h3><i class="chikery-tt3"></i>
                        </div>
                        <div class="ps-section__content">
                            <div class="row">
                                <c:forEach items="${requestScope.lsBlog}" var="c">
                                    <div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 ">
                                        <div class="ps-post">
                                            <div class="ps-post__thumbnail"><img src="${c.thumbnail}" alt=""><a class="ps-post__overlay" href="/src/customer/blogdetail?postId=${c.postID}"></a></div>
                                            <div class="ps-post__content">
                                                <p class="ps-post__meta"><fmt:formatDate type="Date" value="${c.postDate}"/>   by<a> ${c.userID.name}</a>
                                                </p><a class="ps-post__title" href="/src/customer/blogdetail?postId=${c.postID}">
                                                    ${c.postTitle}</a><a class="ps-post__morelink" href="/src/customer/blogdetail?postId=${c.postID}">Read More</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="ps-section__footer"><a class="ps-btn ps-btn--outline" href="/src/customer/bloglist">View More</a></div>
                    </div>
                </div>
            </div>
            <%@include file="public/footer.jsp" %>
    </body>

    <!-- homepage-232:47-->
</html>
<script>
    
    <c:if test="${sessionScope.mess=='LoginForCart'}">
    Alert({
        type: "error",
        content: "Please Login to add or view carts!!!"
    }) <%    session.removeAttribute("mess");%>
    </c:if>
    <c:if test="${sessionScope.mess=='AddToCart'}">
    Alert({
        type: "success",
        content: "Add successful!!!"
    })
        <%    session.removeAttribute("mess");%>
    </c:if>
</script>