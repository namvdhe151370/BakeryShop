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
        <title>My Order</title>
        <style>
            .ps-shopping__right .ps-form--widget-search{
                width: 80%;
            }

            .table-responsive{       
                padding: 16px 0px;
                margin: 50px 0px;
                border: 1px solid lightgray;
            }

            .infor-order{
                padding: 16px 0px;
            }

            .infor-order a, span{
                font-family: Times New Roman;
                font-size: 18px;
            }
            .infor-order .col-md-4:not(:first-child){
                text-align: center
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
                        <li>My Order</li>
                    </ul>
                </div>
                <h1 class="ps-hero__heading">My Orders</h1>
            </div>
        </div>
        <div class="ps-page--shop">
            <div class="container">
                <div class="ps-shopping">
                    <div class="ps-shopping__left">
                        <aside class="widget widget_shop widget_categories">
                            <h3>Categories</h3>
                            <ul class="category">
                                <!-- print list category-->
                                <c:forEach items="${requestScope.listCategories}" var="c">
                                    <li><a  href="/src/customer/productlist?categorykeyword=${c.categoryID}">${c.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </aside>

                        <aside class="widget widget_shop widget_recent-product">
                            <h3 class="widget-title">Latest Products</h3>
                            <c:forEach items="${listProducts}" var="p">
                                <div class="ps-product--sidebar">
                                    <div class="ps-product__thumbnail" >
                                        <a class="ps-product__overlay" href="details?productID=${p.productID}"><img src="${p.thumbnail}"  alt=""/></a>
                                    </div>
                                    <div class="ps-product__content">
                                        <a class="ps-product__title" style="padding-top: 10px" href="/src/customer/productdetail?productID=${p.productID}">${p.productName}</a> 
                                        <p><span>${p.weight}g </span><span> ${p.time} Min</span><span> ${p.degree} <sup>o</sup>C</span></p>
                                        <c:choose>
                                            <c:when test="${p.discount ==0}"><span class="ps-product__price">$${p.price}</span></c:when>
                                            <c:otherwise><span class="ps-product__price"><del>$${p.price}</del>    $<fmt:formatNumber type="number"  maxFractionDigits="2" value="${p.price*(1- p.discount)}"/></span></c:otherwise>
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
                                <form class="ps-form--widget-search" action="/src/customer/productlist" method="get">
                                    <input class="form-control" type="text" placeholder="Search" name="searchkeyword">
                                    <button type="submit"><i class="fa fa-search" ></i> Search</button>
                                </form>                            
                            </figure>
                        </div>

                        <div class="ps-product-box">
                            <c:forEach items="${requestScope.listOrders}" var="o">
                                <div class="table-responsive">
                                    <div class="infor-order row">
                                        <div class="col-md-4">
                                            <a href="" class="btn btn-primary" style="margin-left: 10px">Order ID: ${o.oderID}</a>
                                        </div>
                                        <div class="col-md-4">
                                            <span>Order Date: ${o.orderDate}</span>
                                        </div>
                                        <div class="col-md-4">
                                            <span>Status: ${o.status}</span>
                                        </div>
                                    </div>
                                    <table class="table ps-table ps-table--shopping-cart">
                                        <thead>
                                            <tr>
                                                <th>Product Name</th>
                                                <th>Unit Price</th>
                                                <th style="padding: 30px 40px">Quantity</th>
                                                <th>Total</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <div class="ps-product--cart">
                                                        <div class="ps-product__thumbnail"><img src="${o.listOder_Details.get(0).productId.thumbnail}" alt=""><a class="ps-product__overlay" href="product-detail.html"></a></div>
                                                        <div class="ps-product__content"><a class="ps-product__title" href="product-detail.html">${o.listOder_Details.get(0).productId.productName}</a></div>
                                                    </div>
                                                </td>
                                                <td>${o.listOder_Details.get(0).productId.price}$</td>
                                                <td>
                                                    <div class="form-group--number">
                                                        <input class="form-control" type="text" placeholder="1" value="${o.listOder_Details.get(0).quantity}" readonly>
                                                    </div>
                                                </td>
                                                <td class="total">${o.listOder_Details.get(0).quantity*o.listOder_Details.get(0).productId.price}$</td>
                                            </tr>
                                        </tbody>
                                    </table>                
                                    <div class="row" style="text-align: center">
                                        <div class="col-md-4">
                                            <a href="/src/order/orderinformation?OrderID=${o.oderID}&userID=${user.id}" class="btn btn-warning" style="margin-left: 10px; padding: 10px 0px; font-size: 18px">View Order Detail Information</a>
                                        </div>   
                                        <div class="col-md-4">
                                            <span>Numbers of products: ${o.listOder_Details.size()}</span>
                                        </div>
                                        <div class="col-md-4">
                                            <span>Total Money: ${o.totalMoney}$</span>
                                        </div>
                                    </div>
                                </div>                               
                            </c:forEach>
                        </div>
                        <div class="ps-pagination">    
                            <ul class="pagination" id="pagination"> 
                            </ul>                    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>
        <script src="/src/assests/js/pagger.js"></script>
        <script>
            pagger({
                    id: 'pagination',
                    pageindex: ${requestScope.page_index}, 
                    totalpage: ${requestScope.totalpage}, 
                    gap: 1, 
                    url: {
                        firstpage: '<li><a href="/src/order/myorder?page_index='+1+'"><i class="fa fa-caret-left"></i></a></li>',
                        lastpage: '<li><a href="/src/order/myorder?page_index='+${requestScope.totalpage}+'"><i class="fa fa-caret-right"></i></a></li>',
                        currentpage: '<li class="active"><a href="/src/order/myorder?page_index='+${requestScope.page_index}+'">'+${requestScope.page_index}+'</a></li>',
                        nextpage: function (i){
                            return '<li><a href="/src/order/myorder?page_index=' + i + '">' + i + '</a></li>';
                        }
                    }});
        </script>
</html>

