<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>


	<link rel="stylesheet" type="text/css" href="/src/assests/css/util.css">
	<link rel="stylesheet" type="text/css" href="/src/assests/css/main.css">

	<!--===============================================================================================-->
</head>

<style>
	@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);

	/*reset css*/
	div,
	label {
		margin: 0;
		padding: 0;
	}

	body {
		margin: 20px;
	}

	h1 {
		font-size: 1.5em;
		margin: 10px;
	}

	/****** Style Star Rating Widget *****/
	#rating {
		border: none;
		float: left;
		z-index: 100;
	}

	#rating>input {
		display: none;
	}

	/*ẩn input radio - vì chúng ta đã có label là GUI*/
	#rating>label:before {
		margin: 5px;
		font-size: 1.25em;
		font-family: FontAwesome;
		display: inline-block;
		content: "\f005";
	}


	#rating>.half:before {
		content: "\f089";
		position: absolute;
	}


	#rating>label {
		color: #ddd;
		float: right;
	}

	/*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
	/*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
	#rating>input:checked~label,
	#rating:not(:checked)>label:hover,
	#rating:not(:checked)>label:hover~label {
		color: #FFD700;
	}

	/* Hover vào các sao phía trước ngôi sao đã chọn*/
	#rating>input:checked+label:hover,
	#rating>input:checked~label:hover,
	#rating>label:hover~input:checked~label,
	#rating>input:checked~label:hover~label {
		color: #FFED85;
	}

	#star-container {
		display: flex;
		justify-content: space-around;
	}


	.upload__btn {
		border: 1px black dotted;
		width: 100px;
		height: 100px;
	}

	.upload__inputfile {
		display: none;
	}

	.upload__btn {
		position: relative;
		margin: 5px;
	}

	.upload__btn:hover {
		cursor: pointer;
	}

	.upload__btn p {
            width: 100%;
            text-align: center;
            display: block;
            position: absolute;
	}

	.upload__btn-box {
		margin-bottom: 10px;
		display: flex;
	}

	.upload__img-wrap {
		display: flex;
		flex-wrap: wrap;
		margin: 0 -10px;
	}

	.upload__img-close {
		width: 24px;
		height: 24px;
		border-radius: 50%;
		background-color: rgba(0, 0, 0, 0.5);
		position: absolute;
		top: 10px;
		right: 10px;
		text-align: center;
		line-height: 24px;
		z-index: 10;
		cursor: pointer;
	}

	.upload__img-close:after {
		content: '\2716';
		font-size: 14px;
		color: white;
	}

	.img-bg {
		position: relative;
		z-index: 9;
		background-size: cover;
		padding-bottom: 100%;
	}


	.wrapper-block {
		position: relative;
		display: flex;
	}

	.upload__img-box{
		width: 100px;
		height: 100px;
		position: absolute;
		top: 5px;
		left: 5px;
	}

    #product-image{
        object-fit: cover;
        width: 100px;
        height: 100px;
    }

    .product-feedback{
        padding: 0px !important;
    }
    
    #adding-img{
        padding: 0px 20px;
        font-size: 16px;
        border-radius: 20px
    }
</style>

<body>
    <%@include file="../../public/header.jsp" %>
    
    <div class="ps-hero ps-hero--shopping bg--cover" id="bgcoverhero">
        <div class="ps-hero__container">
            <div class="ps-breadcrumb">
                <ul class="breadcrumb">
                    <li><a href="/src/homepage">Home</a></li>
                    <li>Feedback</li>
                </ul>
            </div>
            <h1 class="ps-hero__heading">Feedback Product</h1>
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
                        <div class="contact1">
                            <div class="product-feedback contact1-form validate-form" style="padding: 15px;">
                                <img id="product-image" src="${product.thumbnail}">
                                <span>
                                    ${product.productName} </br>
                                </span>
                            </div>
                            <div class="container-contact1">
                                <form class="contact1-form validate-form" method="POST" action="/src/customer/feedback" enctype="multipart/form-data" >
                                    <input type="hidden" name="productId" value="${product.productID}">
                                    <input type="hidden" name="orderId" value="${orderId}">                                 
                                    <div class="wrap-input1 validate-input" id="star-container">
                                        <div id="rating">
                                            <input type="radio" id="star5" name="rating" value="5" checked />
                                            <label class="full" for="star5" title="Awesome - 5 stars"></label>

                                            <input type="radio" id="star4" name="rating" value="4" />
                                            <label class="full" for="star4" title="Pretty good - 4 stars"></label>

                                            <input type="radio" id="star3" name="rating" value="3" />
                                            <label class="full" for="star3" title="Meh - 3 stars"></label>

                                            <input type="radio" id="star2" name="rating" value="2" />
                                            <label class="full" for="star2" title="Kinda bad - 2 stars"></label>

                                            <input type="radio" id="star1" name="rating" value="1" />
                                            <label class="full" for="star1" title="Sucks big time - 1 star"></label>
                                        </div>
                                    </div>

                                    <div class="wrap-input1 validate-input" data-validate = "Message is required">
                                        <textarea class="input1" name="message" placeholder="Message"></textarea>
                                        <span class="shadow-input1"></span>
                                    </div>


                                    <div class="upload__box">
                                        <div class="upload__btn-box">
<!--                                            <div class="wrapper-block"> 
                                                <label class="upload__btn">
                                                    <p>Upl ad</p>
                                                    <input type="file" class="upload__inputfile">
                                                </label>
                                            </div>-->
                                            <div class="wrapper-block"> 
                                                <label class="btn btn-warning" id="adding-img">
                                                    <p>Upload</p>
                                                    <input type="file" class="upload__inputfile" name="images">
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container-contact1-form-btn">
                                        <button class="contact1-form-btn">
                                                <span>
                                                        Send Feedback
                                                        <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                                                </span>
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>
                           
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>       








    <%@include file="../../public/specfooter.jsp" %>         




	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() { dataLayer.push(arguments); }
		gtag('js', new Date());

		gtag('config', 'UA-23581568-13');
	</script>

	<!--===============================================================================================-->
	<script src="/src/assests/js/main_1.js"></script>
        <script src="/src/assests/js/toast.js"></script>
</body>

</html>

<script>
    function calcRate(r) {
            const f = ~~r,//Tương tự Math.floor(r)
                    id = 'star' + f + (r % f ? 'half' : '')
            id && (document.getElementById(id).checked = !0)
    }

	jQuery(document).ready(function () {
		ImgUpload();
		RemoveImage();           
	});

	var imgArray = [];
    function ImgUpload() {
        $(document).on("change", '.upload__inputfile', function (e) {
            e.preventDefault();
            if($(".upload__inputfile").length==1){
                $(this).parent().attr('class', 'upload__btn');
                $(this).parent().attr('id', '');
            }

            if ($(this).parent().is(':last-child') && $('.upload__inputfile').length<${requestScope.maxNumberOfImages}) {
                    $('.upload__btn-box').append(`<div class="wrapper-block"> 
                                                    <label class="upload__btn">
                                                        <p>Upload</p>
                                                        <input type="file" class="upload__inputfile" name="images">
                                                    </label>
                                                    </div>`);
            }

            if (e.target.files.length) {
                const src = URL.createObjectURL(e.target.files[0]);
                $(this).parent().parent().append(
                    `<div class='upload__img-box'> 
                            <div style='background-image: url("`+src+`")'  class='img-bg'>
                                    <div class='upload__img-close'>
                                    </div>
                            </div>
                    </div>`
                )
            }
        });
    }
    function RemoveImage(){
        $(document).on("click", ".upload__img-close", function () {
                $(this).parentsUntil(".upload__btn-box").remove();
                if($(".wrapper-block").length==0){
                $(".upload__btn-box").append(`<div class="wrapper-block"> 
                                                <label class="btn btn-warning" id="adding-img">
                                                    <p>Upload</p>
                                                    <input type="file" class="upload__inputfile" name="images">
                                                </label>
                                            </div>`);
        }
        });
    }


</script>