<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Chikery</title><link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/src/assests/plugins/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="/src/assests/plugins/bootstrap4/css/bootstrap.min.css">
        <link rel="stylesheet" href="/src/assests/plugins/owl-carousel/assets/owl.carousel.css">
        <link rel="stylesheet" href="/src/assests/plugins/slick/slick/slick.css">
        <link rel="stylesheet" href="/src/assests/plugins/lightGallery-master/dist/css/lightgallery.min.css">
        <link rel="stylesheet" href="/src/assests/plugins/jquery-bar-rating/dist/themes/fontawesome-stars.css">
        <link rel="stylesheet" href="/src/assests/plugins/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" href="/src/assests/plugins/select2/dist/css/select2.min.css">
        <link rel="stylesheet" href="/src/assests/plugins/chikery-icon/style.css">
        <link rel="stylesheet" href="/src/assests/css/toast.css">
        <link rel="stylesheet" href="/src/assests/css/style.css">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
        <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <style>
        .modal-backdrop.show{
            z-index: 10 !important;
        }
    </style>
    <body>
        <header class="header header--default header--home-4 line" data-sticky="true">
            <div class="header__left">
                <ul class="ps-list--social">
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                </ul>
            </div>
            <div class="header__center">
                <nav class="header__navigation left">
                    <ul class="menu">
                        <li class="menu-item-has-children"><a href="/src/homepage">Home</a><span class="sub-toggle"><i class="fa fa-angle-down"></i></span>

                        </li>
                        <li class="menu-item-has-children"><a href="/src/customer/productlist">Product</a><span class="sub-toggle"><i class="fa fa-angle-down"></i></span>

                        </li>
                        <li class="current-menu-item "><a href="/src/customer/bloglist">About</a>
                        </li>
                    </ul>
                </nav>
                <div class="header__logo"><a class="ps-logo" href="/src/homepage"><img src="https://drive.google.com/uc?export=view&id=1n41W0PEHblSOuPGGGNijvExmsWOWVKn3" alt=""></a></div>
                <nav class="header__navigation right">
                    <ul class="menu">
                        <li class="menu-item-has-children"><a href="/src/customer/bloglist">Blogs</a><span class="sub-toggle"><i class="fa fa-angle-down"></i></span>

                        </li>
                        <li class="menu-item-has-children"><a href="/src/customer/bloglist">News</a><span class="sub-toggle"><i class="fa fa-angle-down"></i></span>

                        </li>
                        <li class="current-menu-item "><a href="contact-us.html">Contact</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="header__right">
                <div class="header__actions">
                    <div class="ps-cart--mini">
                        <c:if test="${sessionScope.user == null}">
                            <a class="ps-cart__toggle" href="/src/customer/cartdetails"><i class="fa fa-shopping-basket"></i><span><i></i></span></a>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null}">
                            <a class="ps-cart__toggle" href="/src/customer/cartdetails">
                                <i class="fa fa-shopping-basket"></i>
                                <span><i>${sessionScope.cartUser.carts.size()}</i></span>
                            </a>
                        </c:if>
                    </div>
                    <c:if test="${sessionScope.user == null}">
                        <a data-toggle="modal" class="ps-search-btn" href="#myRegisterForm"><i class="fa fa-user-plus"></i></a>
                        <a class="ps-popup-open" href="#subscribe"><i class="fa fa-sign-in" ></i></a>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                        <div class="ps-cart--mini"><i class="fa fa-bars"></i></a>
                            <div class="ps-cart__content">
                                <div class="ps-cart__items">
                                    <c:if test="${sessionScope.user.roleID eq 1}">
                                    <div class="ps-product--mini-cart">
                                        <div class="ps-product__content"><a class="ps-product__title" href="/src/admin/dashboard">Admin Manage</a>
                                        </div>
                                    </div>
                                    </c:if>
                                    <c:if test="${sessionScope.user.roleID eq 3}">
                                    <div class="ps-product--mini-cart">
                                        <div class="ps-product__content"><a class="ps-product__title" href="/src/marketing/dashboard">Marketing Manage</a>
                                        </div>
                                    </div>
                                    </c:if>
                                    
                                    <c:if test="${sessionScope.user.roleID eq 2}">
                                    <div class="ps-product--mini-cart">
                                        <div class="ps-product__content"><a class="ps-product__title" href="/src/sale/dashboard">Sale Manage</a>
                                        </div>
                                    </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!--<a href="../customer/myorder?Id=${user.id}"><i class="fa fa-shopping-basket"></i></a>-->
                        <a href="../account/userprofile?Id=${user.id}"><i class="fa fa-user"></i></a>
                        <a href="/src/comment/logout"><i class="fa fa-sign-out" ></i></a>
                        </c:if>
                </div>
            </div>


            <!--Login-->
                <div class="ps-popup" id="subscribe" >
                    <div class="ps-popup__content bg--cover" style="background-image: url(/src/assests/img/bg/subcribe.jpg)"><a class="ps-popup__close ps-btn--close" href="#"></a>
                        <form class="ps-form--subscribe-popup" action="/src/comment/login" method="post">
                            <div class="ps-form__content">
                                <h5>Newletters</h5>
                                <h3>Get Discount <span>30%</span> Off</h3>
                                <p>Sign up to our newsletter and save 30% for you next purchase. No spam, we promise !</p>
                                <div class="form-group">
                                    <input class="form-control" type="text" placeholder="Email Address"  name="Email" required>
                                    <input class="form-control" type="password" placeholder="Password" name="Password" required>
                                    <button type="submit" class="ps-btn">Login</button>
                                </div>
                                <div class="ps-checkout__header">
                                    <p>Haven't get an account ?<a href="#"> Click here to sign up</a></p>
                                    <p>You forget the password<a href="/src/account/resetpassword"> Click here to reset</a></p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>



            <!-- Register-->
            <div class="modal" id="myRegisterForm">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h2 class="text-center">Register New Account</h2> 
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form action="/src/account/register" method="post">
                                <div class="form-group">
                                    <input type="text" name="Name" class="form-control" placeholder="Enter your Name" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" id="email" name="Email" class="form-control" placeholder="Enter your Email" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" id="regispassword" name="Password" class="form-control" placeholder="Enter Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" id="repassword" name="Repassword" class="form-control" placeholder="Re-enter Password" required>
                                </div>
                                <div id="passalert">

                                </div>
                                <div class="form-group">
                                    <input type="text" id="phone" name="Mobile" class="form-control" placeholder="Enter your Mobile Number" required>
                                </div>
                                <div id="phonealert">

                                </div>
                                <div class="form-group">
                                    <input type="text" name="Address" class="form-control" placeholder="Enter your Address" required>
                                </div>
                                <h6>
                                    Gender
                                </h6>
                                <div class="form-gender">
                                    <input type="radio" name="Gender" class="gender-radio" value="1" required>
                                    <label for="gender">
                                        Male
                                    </label>
                                    <input type="radio" name="Gender" class="gender-radio" value="0" required>
                                    <label for="gender">
                                        Female
                                    </label>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary btn-block">Register</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </header>
        <header class="header header--mobile" data-sticky="false">
            <div class="header__content">
                <div class="header__left"><a class="ps-toggle--sidebar" href="#navigation-mobile"><i class="fa fa-bars"></i></a></div>
                <div class="header__center"><a class="ps-logo" href="#"><img src="https://drive.google.com/uc?export=view&id=1n41W0PEHblSOuPGGGNijvExmsWOWVKn3" alt=""></a></div>
                <div class="header__right">
                    <div class="header__actions"><a href="#"><i class="fa fa-heart-o"></i></a></div>
                </div>
            </div>
        </header>
        <div class="ps-panel--sidebar" id="cart-mobile">
            <div class="ps-panel__header">
                <h3>Shopping Cart</h3>
            </div>
            <div class="navigation__content">
                <div class="ps-cart--mobile">
                    <div class="ps-cart__content">
                        <div class="ps-cart__items">
                            <div class="ps-product--mini-cart">
                                <div class="ps-product__thumbnail"><a href="#"><img src="img/product/12.png" alt=""></a></div>
                                <div class="ps-product__content"><span class="ps-btn--close"></span><a class="ps-product__title" href="product-default.html">Jean Woman Summer</a>
                                    <p><strong>Quantity: 1</strong></p><small>$12.00</small>
                                </div>
                            </div>
                            <div class="ps-product--mini-cart">
                                <div class="ps-product__thumbnail"><a href="#"><img src="img/product/13.png" alt=""></a></div>
                                <div class="ps-product__content"><span class="ps-btn--close"></span><a class="ps-product__title" href="product-default.html">Jean Woman Summer</a>
                                    <p><strong>Quantity: 1</strong></p><small>$12.00</small>
                                </div>
                            </div>
                        </div>
                        <div class="ps-cart__footer">
                            <h3>Sub Total:<strong>$48.00</strong></h3>
                            <figure><a class="ps-btn" href="shopping-cart.html">View Cart</a><a class="ps-btn ps-btn--dark" href="checkout.html">Checkout</a></figure>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ps-panel--sidebar" id="navigation-mobile">
            <div class="ps-panel__header">
                <h3>Menu</h3>
            </div>
            <div class="ps-panel__content">
                <ul class="menu--mobile">
                    <li class="menu-item-has-children"><a href="/src/homepage">Home</a><span class="sub-toggle"></span>
                        <ul class="sub-menu">
                            <li class="current-menu-item "><a href="/src/homepage">Homepage 1</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-2.html">Homepage 2</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-3.html">Homepage 3</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-4.html">Homepage 4</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-5.html">Homepage 5</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-6.html">Homepage 6</a>
                            </li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children"><a href="/src/customer/productlist">Shop</a><span class="sub-toggle"></span>
                        <ul class="sub-menu">
                            <li class="current-menu-item "><a href="/src/customer/productlist">Shop Default</a>
                            </li>
                            <li class="current-menu-item "><a href="shop-fullwidth.html">Shop Fullwidth</a>
                            </li>
                            <li class="current-menu-item "><a href="shop-fullwidth-no-sidebar.html">Shop Fullwidth No Sidebar</a>
                            </li>
                            <li class="current-menu-item "><a href="shop-no-sidebar.html">Shop No Sidebar</a>
                            </li>
                            <li class="current-menu-item "><a href="shop-sidebar-right.html">Shop Sidebar Right</a>
                            </li>
                            <li class="current-menu-item "><a href="shop-horizontal.html">Shop Horizontal</a>
                            </li>
                            <li class="menu-item-has-children"><a href="product-default.html">Product</a><span class="sub-toggle"><i class="fa fa-angle-down"></i></span>
                                <ul class="sub-menu">
                                    <li class="current-menu-item "><a href="product-default.html">Product Default</a>
                                    </li>
                                    <li class="current-menu-item "><a href="product-background.html">Product Background</a>
                                    </li>
                                    <li class="current-menu-item "><a href="product-background-center.html">Product Background Center</a>
                                    </li>
                                    <li class="current-menu-item "><a href="product-background-center-2.html">Product Background Center 2</a>
                                    </li>
                                    <li class="current-menu-item "><a href="product-sticky.html">Product Sticky</a>
                                    </li>
                                    <li class="current-menu-item "><a href="product-thumbnail-left.html">Product Thumbnail Left</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="current-menu-item "><a href="/src/customer/bloglist">About</a>
                    </li>
                    <li class="menu-item-has-children"><a href="/src/customer/bloglist">Pages</a><span class="sub-toggle"></span>
                        <ul class="sub-menu">
                            <li class="current-menu-item "><a href="/src/customer/bloglist">About</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-2.html">Checkout</a>
                            </li>
                            <li class="current-menu-item "><a href="whishlist.html">Whishlist</a>
                            </li>
                            <li class="current-menu-item "><a href="homepage-4.html">Compare</a>
                            </li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children"><a href="/src/customer/bloglist">News</a><span class="sub-toggle"></span>
                        <ul class="sub-menu">
                            <li class="current-menu-item "><a href="/src/customer/bloglist">Blog grid</a>
                            </li>
                            <li class="current-menu-item "><a href="blog-grid-full-width.html">Blog grid fullwidth</a>
                            </li>
                            <li class="current-menu-item "><a href="blog-list.html">Blog List</a>
                            </li>
                            <li class="current-menu-item "><a href="blog-detail.html">Blog Detail</a>
                            </li>
                        </ul>
                    </li>
                    <li class="current-menu-item "><a href="contact-us.html">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="navigation--list">
            <div class="navigation__content"><a class="navigation__item active" href="/src/homepage"><i class="fa fa-home"></i></a><a class="navigation__item ps-toggle--sidebar" href="#navigation-mobile"><i class="fa fa-bars"></i></a><a class="navigation__item ps-toggle--sidebar" href="#search-sidebar"><i class="fa fa-search"></i></a><a class="navigation__item ps-toggle--sidebar" href="#cart-mobile"><i class="fa fa-shopping-bashomepageket"></i></a></div>
        </div>
    </body>
</html>
<div class="toastx">
</div> 
<script src="/src/assests/js/toast.js"></script>
<script>
    <c:if test="${requestScope.mess=='Fail'}">
    Alert({
        type: "error",
        content: "Email or Password incorrect!!!!"
    })
    </c:if>
    <c:if test="${requestScope.mess=='Check your Email'}">
    Alert({
        type: "success",
        content: "Check your Email!!"
    })
    </c:if>
    <c:if test="${requestScope.mess=='Cancel success'}">
    Alert({
        type: "success",
        content: "Cancel success!!"
    })
    </c:if>
</script>
<script  type="text/javascript">
    $(document).ready(function () {
        var x_timer;
        //check phone
        $('#phone').keyup(function (e) {
            clearTimeout(x_timer);
            var phone = $("#phone").val();
            x_timer = setTimeout(function () {
                check_phone(phone);
            }, 1000);
        });
        function check_phone(phone) {
            var isphone = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test(phone);
            if (!isphone) {
                $('#phonealert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Please enter valid phone number!</div></div>');
            } else {
                $('#phonealert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Phonenumber</div></div>');
            }
        }
        // check pass
        $("#repassword").keyup(function (e)
        {
            clearTimeout(x_timer);
            var password = $("#password").val();
            var repassword = $("#repassword").val();
            x_timer = setTimeout(function () {
                check_password(password, repassword);
            }, 1000);
        });
        function check_password(password, repassword) {
            if (password != repassword) {
                $('#passalert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Password and confirm password not correct!</div></div>');
            } else {
                $('#passalert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Password</div></div>');
            }
        }
    });
    <c:if test="${requestScope.mess=='Add successfull'}">
    Alert({
        type: "success",
        content: "Add successfull!!"
    })
    </c:if>
        
    <c:if test="${requestScope.fail=='fail'}">
    Alert({
        type: "error",
        content: "No permission. Please try again."
    })
    </c:if>

</script>
