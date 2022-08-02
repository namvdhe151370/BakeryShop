<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <link rel="stylesheet" href="../assests/plugins/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="../assests/plugins/bootstrap4/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assests/plugins/owl-carousel/assets/owl.carousel.css">
        <link rel="stylesheet" href="../assests/plugins/slick/slick/slick.css">
        <link rel="stylesheet" href="../assests/plugins/lightGallery-master/dist/css/lightgallery.min.css">
        <link rel="stylesheet" href="../assests/plugins/jquery-bar-rating/dist/themes/fontawesome-stars.css">
        <link rel="stylesheet" href="../assests/plugins/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" href="../assests/plugins/select2/dist/css/select2.min.css">
        <link rel="stylesheet" href="../assests/plugins/chikery-icon/style.css">
        <link rel="stylesheet" href="../assests/css/style.css">
        <title>Cart Contact</title>
        <link href="../../../assests/css/cartcontact.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        #tfootOrder tr td {
            font-weight: bold;
            font-size: 28px;
            color: #ce873a;
            padding-top: 20px;
        }
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
        .ps-shopping form .error {
            color: red !important;
        }
    </style>
    <body>
        <%@include file="../../public/header.jsp" %>
        <div class="ps-hero ps-hero--shopping bg--cover" id="bgcoverhero">
            <div class="ps-hero__container">
                <div class="ps-breadcrumb">
                    <ul class="breadcrumb">
                        <li><a href="/src/homepage">Home</a></li>
                        <li>Cart Contact</li>
                    </ul>
                </div>
                <h1 class="ps-hero__heading">Cart Contact</h1>
            </div>
        </div>
        <div class="ps-page--shop">
            <div class="container">
                <div class="ps-shopping">
                    <div class="ps-shopping__left">
                        <aside class="widget widget_search">
                            <form class="ps-form--widget-search" action="cartcontact" method="get">
                                <input class="form-control" type="text" placeholder="Search Anything" name="searchkeyword">
                                <button><i class="fa fa-search"></i></button>
                            </form>
                        </aside>
                        <aside class="widget widget_blog widget_blog-categories">
                            <h3 class="widget-title">Categories</h3>
                            <ul>
                                <c:forEach items="${listCategoryCart}" var="C">
                                    <li><a  href="cartcontact?categorykeyword=${C.categoryID}">${C.categoryName}</a></li>
                                    </c:forEach>

                            </ul>
                            <div class="pagination">
                                <ul class="pagination">
                                    <c:if test="${pageCate > CA_PAZE_SIZE}">
                                        <c:if test="${pageCate >1}">  
                                            <li><a class="page-link" href="cartcontact?pageCate=${pageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                                </c:if> 
                                        <li><a class="page-link" href="cartcontact?pageCate=${pageCate}">${pageCate}/${endCaPage}</a></li>
                                            <c:if test="${pageCate < endCaPage }">
                                            <li><a class="page-link" href="cartcontact?pageCate=${pageCate+1}"><i class="fa fa-caret-right"></i></a></li>
                                                </c:if>
                                            </c:if>
                                </ul> 
                            </div>
                        </aside>
                        <aside class="widget widget_shop widget_recent-product">
                            <h3 class="widget-title">Lastest Products</h3>
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
                    <form class="ps-form--checkout" id="login_register_check" action="cartcontact" method="post">
                        <div class="ps-shopping__right">
                            <div class="" style="margin-bottom: 50px;">

                                <h2 style=" text-align: center;">Billing Detail</h2>

                                <div class="row">
                                    <div class="col-xl-8 col-lg-8 col-md-8 col-sm-16 col-16 ">
                                        <div class="form-group">
                                            <label>Full Name</label>
                                            <input class="form-control" value="${sessionScope.user.name}" id="fullname" name="fullname" type="text" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-8 col-8 ">
                                        <div class="form-group">
                                            <label>Gender</label>
                                            <select class="form-control" id="gender" name="gender">
                                                <c:if test="${sessionScope.user.gender == true}">
                                                    <option ${sessionScope.user.name == true?"selected":""}  value="true">Male</option>
                                                    <option value="false">Female</option>
                                                </c:if>
                                                <c:if test="${sessionScope.user.gender == false}">
                                                    <option value="true">Male</option>
                                                    <option ${sessionScope.user.name == false?"selected":""} value="false">Female</option>
                                                </c:if>
                                                
                                                
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xl-8 col-lg-8 col-md-8 col-sm-16 col-16 ">
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input class="form-control" value="${sessionScope.user.email}" id="email"  name="email" type="text" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-8 col-8 ">
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input class="form-control" value="${sessionScope.user.mobile}" id="phone" name="phone" type="text" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" value="${sessionScope.user.address}" id="address" name="address" type="text" placeholder="">
                                </div>
                                <div class="form-group">
                                    <label>Order Note</label>
                                    <textarea class="form-control" name="note" rows="3" placeholder=""></textarea>
                                </div>
                            </div>

                            <div class="ps-product-box">
                                <div class="table-responsive">
                                    <table class="table ps-table ps-table--shopping-cart" style="text-align: center">
                                        <thead id="orderInfoTable">
                                            <tr>
                                                <th>Id</th>
                                                <th id="producTh">Product Name</th>           
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        <tbody id="orderInfoTable">
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
                                                <div class="form-group--number" >
                                                    <a class="ps-product__title" >${c.value.quantity}</a>

                                                </div>
                                            </td>
                                            <td class="total" id="total">$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${c.value.product.price*(1- c.value.product.discount)*c.value.quantity}"/></td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                        <div class="ps-pagination">
                                            <c:if test="${totalProduct > PAZE_SIZE}">
                                                <ul class="pagination">
                                                    <!--pagging product by all product,search by name and product by category-->
                                                    <c:if test="${requestScope.page > 1}">
                                                        <li><a href="cartcontact?page=${requestScope.page-1}"><i class="fa fa-caret-left"></i></a></li>
                                                            </c:if>
                                                            <c:choose>
                                                                <c:when test="${requestScope.endPage<=4}">
                                                                    <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                                        <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                    </c:if>
                                                                    <c:if test="${searchkeyword != null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                    </c:if>
                                                                    <c:if test="${categorykeyword !=null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                    </c:if>

                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:if test="${requestScope.page==1}">
                                                                <c:forEach begin="1" end="${requestScope.page+2}" var="i">
                                                                    <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${searchkeyword != null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${categorykeyword !=null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                <li><a>...</a></li>
                                                                <li class=" ${i==page?"active":""}"><a class="page-link" href="cartcontact?page=${endPage}&pageCate=${pageCate}">${endPage}</a></li>
                                                                </c:if>
                                                                <c:if test="${requestScope.page > 1 && requestScope.page < requestScope.endPage - 2}">
                                                                    <c:forEach begin="${requestScope.page-1}" end="${requestScope.page+1}" var="i">
                                                                        <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${searchkeyword != null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${categorykeyword !=null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                <li><a>...</a></li>

                                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${endPage}&">${endPage}</a></li>
                                                                    </c:if>
                                                                    <c:if test="${searchkeyword != null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${endPage}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                                    </c:if>
                                                                    <c:if test="${categorykeyword !=null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=${endPage}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${endPage}</a></li>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${requestScope.page >= requestScope.endPage - 2 && requestScope.page <= requestScope.endPage}">

                                                                <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=1&pageCate=${pageCate}">1</a></li>
                                                                    </c:if>
                                                                    <c:if test="${searchkeyword != null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=1&searchkeyword=${searchkeyword}&pageCate=${pageCate}">1</a></li>
                                                                    </c:if>
                                                                    <c:if test="${categorykeyword !=null}">
                                                                    <li class=""><a class="page-link" href="cartcontact?page=1&categorykeyword=${categorykeyword}&pageCate=${pageCate}">1</a></li>
                                                                    </c:if>


                                                                <li><a>...</a></li> 
                                                                    <c:forEach begin="${requestScope.endPage-2}" end="${requestScope.endPage}" var="i">
                                                                        <c:if test="${searchkeyword == null&& categorykeyword ==null }">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${searchkeyword != null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&searchkeyword=${searchkeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                        <c:if test="${categorykeyword !=null}">
                                                                        <li class=""><a class="page-link" href="cartcontact?page=${i}&categorykeyword=${categorykeyword}&pageCate=${pageCate}">${i}</a></li>
                                                                        </c:if>
                                                                    </c:forEach>

                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>    

                                                    <c:if test="${page<endPage}">
                                                        <li ><a href="cartcontact?page=${page+1}"><i class="fa fa-caret-right"></i></a></li> 
                                                            </c:if>


                                                </ul>
                                            </c:if>
                                        </div>
                                        <tfoot id="tfootOrder">
                                            <tr>
                                                <td colspan="2">

                                                <td>
                                                <td id="Pay">Total Price:</td>
                                                <td id="Pay">$<fmt:formatNumber  type="number"  maxFractionDigits="2" value="${totalPrice}"/></td>
                                            </tr>

                                        </tfoot>

                                    </table>


                                </div>



                            </div>
                            <div class="ps-section__actions" style="float: right;">
                                <figure>
                                    <a class="ps-btn" href="/src/customer/cartdetails">Change </a>
                                    <button id="btnChoose" class="ps-btn ps-btn--outline" type="submit">Submit</button>

                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>
        <script src="assets/js/vendor/jquery-3.5.1.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            $("#login_register_check").validate({
                rules: {
                    email: {
                        required: true,
                        email: true,
                    },
                   
                    fullname: {
                        required: true,
                    },
                    phone: {
                        required: true,
                        maxlength: 10,
                    },
                    address: {
                        required: true,
                    },
                    
                },
                messages: {
                    email: {
                        required: "Vui lòng nhập email",
                        email: "Email không đúng định dạng",
                    },
                    
                    fullname: {
                        required: "Vui lòng nhập tên",
                    },
                    phone: {
                        required: "Vui lòng nhập số điện thoại",
                        minlength: "Số điện thoại phải gồm 10 chữ số",
                    },
                    address: {
                        required: "Vui lòng nhập địa chỉ",
                    }, 
                },
                
            });
        </script>
    </body>
</html>