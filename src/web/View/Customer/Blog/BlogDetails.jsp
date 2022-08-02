<%-- 
    Document   : BlogDetails
    Created on : May 26, 2022, 9:41:29 PM
    Author     : long4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- blog-detail38:23-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Blog Details</title><link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">

    </head>
    <body>
        <%@include file="../../public/header.jsp" %>
        <!--include search-sidebar-->
        <div class="ps-page">
            <div class="ps-hero bg--cover" id="bgcoverhero">
                <div class="ps-hero__container">
                    <div class="ps-breadcrumb">
                        <ul class="breadcrumb">
                            <li><a href="/src/homepage">Home</a></li>
                            <li>Blog Grid</li>
                        </ul>
                    </div>
                    <div class="ps-post--detail-header">
                        <h1>${post.postTitle}</h1>
                        <div class="ps-post__meta"><span>${post.postDate}  by <a href="#">Admin</a></span></div>
                    </div>
                </div>
            </div>
            <div class="ps-hero--blog img-fluid"><img src="${post.thumbnail}" alt=""></div>
            <div class="container">
                <div class="ps-blog ps-blog--sidebar">
                    <div class="ps-blog__left">
                        <div class="ps-post--detail">
                            <div class="ps-post__content">
                                <p><strong class="large">${post.briefInformation}</strong></p>
                                <p>${post.postDescription}</p>
                            </div>
                        </div>
                    </div>
                    <div class="ps-blog__right">
                        <aside class="widget widget_search">
                            <form class="ps-form--widget-search" action="customer/bloglist" method="post">
                                <input class="form-control" type="text" name="keyword" placeholder="Search Anything">
                                <button><i class="fa fa-search"></i></button>
                            </form>
                        </aside>
                        <aside class="widget widget_blog widget_blog-categories">
                            <h3 class="widget-title">Categories</h3>
                            <ul class="ps-list--arrow">
                                <li><a href="customer/bloglist?categoryID=0">All Category</a></li>
                                <!--  show blog category list  -->
                                <c:forEach items="${requestScope.lsPagingBlogCategory}" var="c">
                                    <li><a href="/src/customer/bloglist?categoryID=${c.postCategoryID}">${c.postCategoryName}</a></li>
                                    </c:forEach>
                            </ul>
                            <div class="pagination">
                                <ul class="pagination">
                                    <!--  if the current page people using is more than 1, create a fontawsome to lift back   -->  
                                    <c:if test="${currentPageCate >1}">  
                                        <li><a href="/src/customer/blogdetail?postId=${postId}&currentPageCate=${currentPageCate-1}"><i class="fa fa-caret-left"></i></a></li>
                                            </c:if> 
                                    <li><a href="#">${currentPageCate}/${requestScope.blogCatePage}</a></li>
                                        <c:if test="${currentPageCate < blogCatePage }">
                                        <!--  if the current page people using is less than end page number, create a fontawsome to lift forward   -->  
                                        <li><a href="/src/customer/blogdetail?postId=${postId}&currentPageCate=${currentPageCate+1}"><i class="fa fa-caret-right"></i></a></li>
                                            </c:if>
                                </ul>
                            </div>

                        </aside>
                        <aside class="widget widget_blog widget_recent-posts">
                            <h3 class="widget-title">LATEST POST</h3>
                            <!--  show the latest pos  -->
                            <c:forEach items="${requestScope.lsRecentPost}" var="c">
                                <div class="ps-post--sidebar">
                                    <div class="ps-post__thumbnail"><a class="ps-post__overlay" href="/src/customer/blogdetail?postId=${c.postID}"></a><img src="${c.thumbnail}" alt=""></div>
                                    <div class="ps-post__content"><a class="ps-post__title" href="/src/customer/blogdetail?postId=${c.postID}">${c.postTitle}</a>
                                        <p>By<a href="#"> ${c.userID.email}</a></p>
                                    </div>
                                </div>
                            </c:forEach>

                        </aside>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../public/footer.jsp" %>
        <!-- Plugins-->

    </body>

    <!-- blog-detail38:29-->
</html>