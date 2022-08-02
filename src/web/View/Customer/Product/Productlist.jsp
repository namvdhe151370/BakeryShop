<%-- 
    Document   : Productlist
    Created on : May 26, 2022, 8:39:20 PM
    Author     : hellb
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">
        <title>Product List</title>
        <style>
            .ps-shopping__right .ps-form--widget-search{
                width: 80%;
            }
        </style> 
    </head>
    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="ps-hero ps-hero--shopping bg--cover" id="bgcoverhero">
            <div class="ps-hero__container">
                <div class="ps-breadcrumb">
                    <ul class="breadcrumb">
                        <li><a href="/src/homepage">Home</a></li>
                        <li>Shop Page</li>
                    </ul>
                </div>
                <h1 class="ps-hero__heading">Products List</h1>
            </div>
        </div>
        <div class="ps-page--shop">
            <div class="container">
                <div class="ps-shopping">
                    <div class="ps-shopping__left">
                        <aside class="widget widget_shop widget_categories">
                            <h3>Categories</h3>
                            <ul>
                                <!-- print list category-->
                                <c:forEach items="${listCategory}" var="C">
                                    <li><a  href="productlist?categorykeyword=${C.categoryID}">${C.categoryName}</a></li>
                                    </c:forEach>
                            </ul>
                           <c:if test="${totalCategory > CA_PAZE_SIZE && cateStatus==1}">
                                <div class="pagination">
                                    <ul class="pagination">
                                        <c:if test="${pageCate >1}">  
                                            <li><a class="page-link" href="productlist?pageCate=${pageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                                </c:if> 
                                        <li><a class="page-link" href="productlist?pageCate=${pageCate}">${pageCate}/${endCaPage}</a></li>
                                            <c:if test="${pageCate < endCaPage }">
                                            <li><a class="page-link" href="productlist?pageCate=${pageCate+1}"><i class="fa fa-caret-right"></i></a></li>
                                                </c:if>
                                    </ul> 
                                </div>
                            </c:if>
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
                        <div class="ps-shopping__top">
                            <p></p>
                            <figure>
                                <form class="ps-form--widget-search" action="productlist" method="get">
                                    <input class="form-control" type="text" placeholder="Search" name="searchkeyword">
                                    <button type="submit"><i class="fa fa-search" ></i> Search</button>
                                </form>                            
                            </figure>
                        </div>

                        <div class="ps-product-box">
                            <div class="row">
                                <!--check list product exist-->
                                <c:choose>
                                    <c:when test="${listProduct==null ||listProduct.size() ==0}"><h4>None</h4></c:when>
                                </c:choose>
                                <!--take list product -->    
                                <c:forEach items="${listProduct}" var="P">
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-6">
                                        <div class="ps-product">
                                            <div class="ps-product__thumbnail">
                                                <a class="" href="/src/customer/productdetail?productID=${P.productID}"><img  src="${P.thumbnail}" alt=""/></a>
                                                <c:if test="${P.discount !=0}"><span class="ps-badge ps-badge--sale"><i><fmt:formatNumber type="number"  maxFractionDigits="0" value="${P.discount*100}"/>%</i></span></c:if>
                                                </div>
                                                <div class="ps-product__content">
                                                    <div class="ps-product__desc">
                                                        <a class="ps-product__title" href="/src/customer/productdetail?productID=${P.productID}">${P.productName}</a> <br>
                                                    <p><span>${P.weight}g </span><span> ${P.time} Min</span><span> ${P.degree} <sup>o</sup>C</span></p>
                                                    <c:choose>
                                                        <c:when test="${P.discount ==0}"><span class="ps-product__price">$${P.price}</span></c:when>
                                                        <c:otherwise><span class="ps-product__price">
                                                                <del>$${P.price}</del> $<fmt:formatNumber type="number"  maxFractionDigits="2" value="${P.price*(1- P.discount)}"/>
                                                            </span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>

                                                <div class="ps-product__shopping "><a class="ps-btn ps-product__add-to-cart" href="addtocart?productID=${P.productID}">Add to cart</a>
                                                    <div class="ps-product__actions"><a href="/src/customer/productdetail?productID=${P.productID}"><i class="fa fa-commenting-o"></i></a></div>
                                                </div>


                                            </div>
                                        </div>

                                    </div>
                                </c:forEach>



                            </div>
                        </div>

                        <div class="ps-pagination">
                            <ul class="pagination">
                                <!--pagging product by all product,search by name and product by category-->
                                <c:if test="${requestScope.page>1}">
                                    <li><a href="productlist?page=${requestScope.page-1}"><i class="fa fa-caret-left"></i></a></li>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${requestScope.endPage<=4}">
                                                <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                    <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                <li class=""><a class="page-link" href="productlist?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${searchkeyword != null}">
                                                <li class=""><a class="page-link" href="productlist?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${categorykeyword !=null}">
                                                <li class=""><a class="page-link" href="productlist?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                </c:if>

                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${requestScope.page==1}">
                                            <c:forEach begin="1" end="${requestScope.page+2}" var="i">
                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${searchkeyword != null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${categorykeyword !=null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                            <li><a>...</a></li>
                                            <li class=" ${i==page?"active":""}"><a class="page-link" href="productlist?page=${endPage}&pageCate=${pageCate}">${endPage}</a></li>
                                            </c:if>
                                            <c:if test="${requestScope.page > 1 && requestScope.page < requestScope.endPage - 2}">
                                                <c:forEach begin="${requestScope.page-1}" end="${requestScope.page+1}" var="i">
                                                    <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${searchkeyword != null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${categorykeyword !=null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                            <li><a>...</a></li>

                                            <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                <li class=""><a class="page-link" href="productlist?page=${endPage}&">${endPage}</a></li>
                                                </c:if>
                                                <c:if test="${searchkeyword != null}">
                                                <li class=""><a class="page-link" href="productlist?page=${endPage}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                </c:if>
                                                <c:if test="${categorykeyword !=null}">
                                                <li class=""><a class="page-link" href="productlist?page=${endPage}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${requestScope.page >= requestScope.endPage - 2 && requestScope.page <= requestScope.endPage}">

                                            <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                <li class=""><a class="page-link" href="productlist?page=1&pageCate=${pageCate}">1</a></li>
                                                </c:if>
                                                <c:if test="${searchkeyword != null}">
                                                <li class=""><a class="page-link" href="productlist?page=1&searchkeyword=${searchkeyword}&pageCate=${pageCate}">1</a></li>
                                                </c:if>
                                                <c:if test="${categorykeyword !=null}">
                                                <li class=""><a class="page-link" href="productlist?page=1&categorykeyword=${categorykeyword}&pageCate=${pageCate}">1</a></li>
                                                </c:if>


                                            <li><a>...</a></li> 
                                                <c:forEach begin="${requestScope.endPage-2}" end="${requestScope.endPage}" var="i">
                                                    <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${searchkeyword != null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${categorykeyword !=null}">
                                                    <li class=""><a class="page-link" href="productlist?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>

                                        </c:if>
                                    </c:otherwise>
                                </c:choose>    

                                <c:if test="${page<endPage}">
                                    <li ><a href="productlist?page=${page+1}"><i class="fa fa-caret-right"></i></a></li> 
                                        </c:if>


                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>
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