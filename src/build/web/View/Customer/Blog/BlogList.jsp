<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <!-- blog-list37:39-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Blog List</title><link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300i,400,400i,500,500i,600,600i,700&amp;display=swap" rel="stylesheet">


    </head>
    <body>
        <%@include file="../../public/header.jsp" %>
        <!--include search-sidebar-->
        <div class="ps-page">
            <div class="ps-hero bg--cover" id="bgcoverhero" >
                <div class="ps-hero__container">
                    <div class="ps-breadcrumb">
                        <ul class="breadcrumb">
                            <li><a href="/src/homepage">Home</a></li>
                            <li>Blog Grid</li>
                        </ul>
                    </div>
                    <h1 class="ps-hero__heading">Blog & News</h1>
                </div>
            </div>
            <div class="container">
                <div class="ps-blog ps-blog--sidebar">
                    <div class="ps-blog__left">
                        <!-- loop to show list of blogs  -->
                        <c:forEach items="${requestScope.lsPagingBlog}" var="c">
                            <div class="ps-post">
                                <div class="ps-post__thumbnail"><img src="${c.thumbnail}" alt=""><a class="ps-post__overlay" href="/src/customer/blogdetail?postId=${c.postID}"></a></div>
                                <div class="ps-post__content">
                                    <p class="ps-post__meta">by<a href="#"> ${c.userID.email}</a></p><a class="ps-post__title" href="/src/customer/blogdetail?postId=${c.postID}">${c.postTitle}</a>
                                    <p>${c.briefInformation}</p>
                                    <a class="ps-post__morelink" href="/src/customer/blogdetail?postId=${c.postID}">Read More</a>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="ps-blog__footer">
                            <div class="ps-pagination" >
                                <ul class="pagination">

                                    <!--  if the current page people using is more than 1, create a fontawsome to lift back   -->  
                                    <!--  the step of pagination is 1, there are 2 main cases  -->
                                    <!--  if the last number of page is less or equal to 4, 
                                    loop number from 1 to the last page index  -->

                                    <!-- if the last number of page is more than 4, there are 3 cases   -->
                                    <!--  if the current page people using is equal 1, 
                                          loop number from 1 to 3, create a <li> tag ... and <li> last page number  -->
                                    <!--  if the current page people using is one of three last page number,
                                         loop number from end page -2 to end page,
                                         create a <li> tag ... after  first page number -->
                                    <!--  if the current page people using is one of three last page number,
                                            loop number from end page -2 to end page,
                                            create a <li> tag ... after  first page number -->
                                    <c:if test="${requestScope.currentPage > 1}">
                                        <li><a href="/src/customer/bloglist?currentPage=${requestScope.currentPage - 1}"><i class="fa fa-caret-left"></i></a></li>
                                            </c:if> 

                                    <c:choose>
                                        <c:when test="${requestScope.endPage <= 4}">
                                            <c:forEach begin="1" end="${requestScope.endPage}" var="c">
                                                <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${c}</a></li>
                                                </c:forEach>

                                        </c:when>

                                        <c:otherwise>

                                            <c:if test="${requestScope.currentPage == 1}">
                                                <c:forEach begin="1" end="${requestScope.currentPage + 2}" var="c">
                                                    <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${c}</a></li>
                                                    </c:forEach>
                                                <li><a>...</a></li>
                                                <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${endPage}</a></li>
                                                </c:if>

                                            <c:if test="${requestScope.currentPage > 1 && requestScope.currentPage < endPage-2}">
                                                <c:forEach begin="${requestScope.currentPage-1}" end="${requestScope.currentPage + 1}" var="c">
                                                    <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${c}</a></li>
                                                    </c:forEach>
                                                <li><a>...</a></li>
                                                <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${endPage}</a></li>
                                                </c:if>

                                            <c:if test="${requestScope.currentPage >= endPage - 2 && requestScope.currentPage <= endPage}">
                                                <li><a href="/src/customer/bloglist?currentPage=1&categoryID=${categoryID}&currentPageCate=${currentPageCate}">1</a></li>
                                                <li><a>...</a></li>
                                                    <c:forEach begin="${requestScope.endPage -2}" end="${endPage}" var="c">
                                                    <li><a href="/src/customer/bloglist?currentPage=${c}&categoryID=${categoryID}&currentPageCate=${currentPageCate}">${c}</a></li>
                                                    </c:forEach>
                                                </c:if>


                                        </c:otherwise>
                                    </c:choose>

                                    <!--  if the current page people using is less than endPage, create a fontawsome to lift forward   -->       
                                    <c:if test="${requestScope.currentPage < endPage}">
                                        <li><a href="/src/customer/bloglist?currentPage=${requestScope.currentPage - 1}&categoryID=${categoryID}&currentPageCate=${currentPageCate}"><i class="fa fa-caret-right"></i></a></li>
                                            </c:if> 

                                </ul>
                            </div></div>
                    </div>
                    <div class="ps-blog__right">
                        <aside class="widget widget_search">
                            <form class="ps-form--widget-search" action="/src/customer/bloglist" name="searchBlog" method="post">
                                <input class="form-control" type="text" name="keyword" placeholder="Search Anything">
                                <button type="submit" onclick="allLetter(document.searchBlog.keyword)"><i class="fa fa-search"></i></button>
                            </form>
                        </aside>
                        <aside class="widget widget_blog widget_blog-categories">
                            <h3 class="widget-title">Categories</h3>
                            <ul class="ps-list--arrow">
                                <li><a href="/src/customer/bloglist?categoryID=0">All Category</a></li>
                                <!--  show blog category list  -->
                                <c:forEach items="${requestScope.lsPagingBlogCategory}" var="c">
                                    <li><a href="/src/customer/bloglist?categoryID=${c.postCategoryID}&currentPageCate=${currentPageCate}&currentPage=${currentPage}">${c.postCategoryName}</a></li>
                                    </c:forEach>
                            </ul>
                            <div class="pagination">
                                <ul class="pagination">
                                    <!--  if the current page people using is more than 1, create a fontawsome to lift back   -->  
                                    <c:if test="${currentPageCate >1}">  
                                        <li><a href="/src/customer/bloglist?currentPageCate=${currentPageCate-1}&categoryID=${categoryID}&currentPage=${currentPage}"><i class="fa fa-caret-left"></i></a></li>
                                            </c:if> 
                                    <li><a href="#">${currentPageCate}/${requestScope.blogCatePage}</a></li>
                                        <c:if test="${currentPageCate < blogCatePage }">
                                        <!--  if the current page people using is less than end page number, create a fontawsome to lift forward   -->  
                                        <li><a href="/src/customer/bloglist?currentPageCate=${currentPageCate+1}&categoryID=${categoryID}&currentPage=${currentPage}"><i class="fa fa-caret-right"></i></a></li>
                                            </c:if>
                                </ul>
                            </div>

                        </aside>
                        <aside class="widget widget_blog widget_recent-posts">
                            <h3 class="widget-title">LATEST POST</h3>
                            <!--  show the latest pos  -->
                            <c:forEach items="${requestScope.lsRecentPost}" var="c">
                                <div class="ps-post--sidebar">
                                    <div class="ps-post__thumbnail"><a class="ps-post__overlay" href="/src//src/customer/blogdetail?postId=${c.postID}"></a><img src="${c.thumbnail}" alt=""></div>
                                    <div class="ps-post__content"><a class="ps-post__title" href="/src//src/customer/blogdetail?postId=${c.postID}">${c.postTitle}</a>
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



    </body>

    <!-- blog-list38:23-->
</html>