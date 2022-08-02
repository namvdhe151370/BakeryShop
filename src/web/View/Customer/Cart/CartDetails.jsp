<%-- 
    Document   : CartDetails
    Created on : Jun 9, 2022, 9:49:32 PM
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
       
        <title>Cart Details</title>
        <style>
            .ps-shopping__right .ps-form--widget-search{
                width: 80%;
            }
            .ps-table thead > tr > th:first-child {
                text-align: left;
                padding-left: 20px !important;

            }
            .ps-table thead > tr > th{
                text-align: center;
            }
            .ps-shopping-cart .ps-shopping-cart__total table tbody tr.total{
                border-top:  none !important;
            }
            .ps-section__actions{
                width: 300px !important;
            }

            .ps-table--shopping-cart tbody > tr > td:first-child {
                min-width: 20px !important;
                padding-left: 20px !important;
            }
            .ps-pagination{
                margin: 20px 0px;
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
                        <li>Cart Details</li>
                    </ul>
                </div>
                <h1 class="ps-hero__heading">Cart Detail</h1>
            </div>
        </div>
        <div class="ps-page--shop">
            <div class="container">
                <div class="ps-shopping row">
                    <div class="ps-shopping__left col-3">
                        <aside class="widget widget_shop widget_categories">
                            <h3>Categories</h3>
                            <ul>
                                <!-- print list category-->

                                <c:forEach items="${listCategoryCart}" var="C">
                                    <li><a  href="cartdetails?categorykeyword=${C.categoryID}">${C.categoryName}</a></li>
                                    </c:forEach>

                            </ul>
                            <div class="pagination">
                                <ul class="pagination">
                                    <c:if test="${totalCategory > CA_PAZE_SIZE && cateCartStatus ==1}">
                                        <c:if test="${pageCate >1}">  
                                            <li><a class="page-link" href="cartdetails?pageCate=${pageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                                </c:if> 
                                        <li><a class="page-link" href="cartdetails?pageCate=${pageCate}">${pageCate}/${endCaPage}</a></li>
                                            <c:if test="${pageCate < endCaPage }">
                                            <li><a class="page-link" href="cartdetails?pageCate=${pageCate+1}"><i class="fa fa-caret-right"></i></a></li>
                                                </c:if>
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
                    <div class="ps-shopping__right col-9 pl-5">
                        <div class="ps-shopping-cart" style="padding-top: 0px;">
                            <div class="ps-shopping__top">
                                <p></p>
                                <figure>
                                    <form class="ps-form--widget-search" action="cartdetails" method="get">
                                        <input class="form-control" type="text" placeholder="Search" name="searchkeyword">
                                        <button type="submit"><i class="fa fa-search" ></i> Search</button>
                                    </form>                            
                                </figure>
                            </div>
                            <c:if test="${cart.carts.size()== 0}">
                                <h1>Nothing in cart</h1>
                            </c:if>
                            <c:if test="${cart.carts.size()!= 0}">
                                <div class="table-responsive">
                                    <table class="table ps-table ps-table--shopping-cart">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Product Name</th>
                                                <th>Unit Price</th>
                                                <th>Quantity</th>
                                                <th>Total</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="i" value="0"></c:set>
                                            <c:forEach items="${cart.carts}" var="c">

                                            
                                                <tr>
                                                    <td>${i=i+1}</td>
                                                <input type="hidden" name="productId" value="${c.value.product.productID}">
                                                <td>
                                                    <div class="ps-product--cart">
                                                        <div class="ps-product__thumbnail"><img src="${c.value.product.thumbnail}" alt=""/><a class="ps-product__overlay" ></a></div>

                                                        <div class="ps-product__content"><a class="ps-product__title" >${c.value.product.productName}</a></div>
                                                    </div>
                                                </td>

                                                <td><c:choose>
                                                        <c:when test="${c.value.product.discount ==0}"><span class="ps-product__price"> $${c.value.product.price}</span></c:when>
                                                        <c:otherwise><span class="ps-product__price"><del>$${c.value.product.price}</del>    $<fmt:formatNumber type="number"  maxFractionDigits="2" value="${c.value.product.price*(1- c.value.product.discount)}"/></span></c:otherwise>
                                                    </c:choose></td>
                                                <td>
                                                    <div class="form-group--number" style="width: 100px">
                                                    <input class="form-control"  onchange="doUpdate(${c.value.product.productID}, '${c.value.product.productName}', ${c.value.quantity})" id="quantity${c.value.product.productID}" name="quantity" type="number" min="1" value="${c.value.quantity}" oninput="validity.valid||(value='');" onpress="isNumber(event)">
                                                    </div>
                                                </td>
                                                <td class="total" id="total">$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${c.value.product.price*(1- c.value.product.discount)*c.value.quantity}"/></td>
                                                <td class="ps-table__actions"><a class="ps-btn--close"  onclick="doDelete(${c.value.product.productID}, '${c.value.product.productName}')"></a></td>
                                                </tr>
                                            
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="ps-pagination">
                                    <c:if test="${totalProduct > PAZE_SIZE}">
                                        <ul class="pagination">
                                            <!--pagging product by all product,search by name and product by category-->
                                            <c:if test="${requestScope.page > 1}">
                                                <li><a href="cartdetails?page=${requestScope.page-1}"><i class="fa fa-caret-left"></i></a></li>
                                                    </c:if>
                                                    <c:choose>
                                                        <c:when test="${requestScope.endPage<=4}">
                                                            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${searchkeyword != null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${categorykeyword !=null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                            </c:if>

                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${requestScope.page==1}">
                                                        <c:forEach begin="1" end="${requestScope.page+2}" var="i">
                                                            <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${searchkeyword != null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${categorykeyword !=null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                            </c:forEach>
                                                        <li><a>...</a></li>
                                                        <li class=" ${i==page?"active":""}"><a class="page-link" href="cartdetails?page=${endPage}&pageCate=${pageCate}">${endPage}</a></li>
                                                        </c:if>
                                                        <c:if test="${requestScope.page > 1 && requestScope.page < requestScope.endPage - 2}">
                                                            <c:forEach begin="${requestScope.page-1}" end="${requestScope.page+1}" var="i">
                                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${searchkeyword != null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${categorykeyword !=null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                            </c:forEach>
                                                        <li><a>...</a></li>

                                                        <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${endPage}&">${endPage}</a></li>
                                                            </c:if>
                                                            <c:if test="${searchkeyword != null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${endPage}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                            </c:if>
                                                            <c:if test="${categorykeyword !=null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=${endPage}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${requestScope.page >= requestScope.endPage - 2 && requestScope.page <= requestScope.endPage}">

                                                        <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                            <li class=""><a class="page-link" href="cartdetails?page=1&pageCate=${pageCate}">1</a></li>
                                                            </c:if>
                                                            <c:if test="${searchkeyword != null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=1&searchkeyword=${searchkeyword}&pageCate=${pageCate}">1</a></li>
                                                            </c:if>
                                                            <c:if test="${categorykeyword !=null}">
                                                            <li class=""><a class="page-link" href="cartdetails?page=1&categorykeyword=${categorykeyword}&pageCate=${pageCate}">1</a></li>
                                                            </c:if>


                                                        <li><a>...</a></li> 
                                                            <c:forEach begin="${requestScope.endPage-2}" end="${requestScope.endPage}" var="i">
                                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${searchkeyword != null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                                <c:if test="${categorykeyword !=null}">
                                                                <li class=""><a class="page-link" href="cartdetails?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                </c:if>
                                                            </c:forEach>

                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>    

                                            <c:if test="${page<endPage}">
                                                <li ><a href="cartdetails?page=${page+1}"><i class="fa fa-caret-right"></i></a></li> 
                                                    </c:if>


                                        </ul>
                                    </c:if>


                                </div>
                                <div class="ps-section__footer">
                                    <div class="ps-section__actions" >
                                        <figure style="text-align: center"><a class="ps-btn " href="/src/customer/productlist">Choose More Product</a></figure>
                                    </div>
                                    <figure class="ps-shopping-cart__total">
                                        <figcaption>Cart Total</figcaption>
                                        <table class="table">


                                            <tr class="totalPrice">
                                                <td>Total Price:</td>
                                                <td><span id="totalprice">$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${totalPrice}"/></span></td>
                                            </tr>
                                        </table>
                                        <a class="ps-btn ps-btn--fullwidth" href="/src/customer/cartcontact">Proceed to checkout</a>
                                    </figure>
                                </div>
                            </c:if>
                        </div>


                        <div class="ps-pagination">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>
        <script>
          function doUpdate(id, name, oldquantity) {
                var quantity = document.getElementById("quantity" + id).value;
                console.log(quantity);
                if(quantity === ""){
                    alert("Quantity is NULL now!");
                }else{
                    var c = confirm("Are you want to update: " + name +  "?");
                    if (c) {
                        window.location.href = "updatequantity?productId=" + id + "&quantity=" + quantity;
                    } else {
                        window.location.href = "updatequantity?productId=" + id + "&quantity=" + oldquantity;
                    }
                }
            }
            // this prevents from typing non-number text, including "e".
            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                let charCode = (evt.which) ? evt.which : evt.keyCode;
                if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
                    evt.preventDefault();
                } else {
                    return true;
                }
            }
            function doDelete(id, name) {
                var c = confirm("Are you sure you want to delete: " + name + "?");
                if (c) {
                    window.location.href = "deleteproductcart?productId=" + id;
                }
            }
        </script>
</html>
