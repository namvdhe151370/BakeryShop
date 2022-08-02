<%-- 
    Document   : PostList
    Created on : Jun 12, 2022, 10:47:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>

    <title>Post List</title>
    <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="Flash Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
    <meta name="keywords"
        content="admin templates, bootstrap admin templates, bootstrap 4, dashboard, dashboard templets, sass admin templets, html admin templates, responsive, bootstrap admin templates free download,premium bootstrap admin templates, Flash Able, Flash Able bootstrap admin template">
    <meta name="author" content="Codedthemes" />
   
</head>



<body class="">
    <%@include file="../../public/adNavbar.jsp" %>
    <section class="pcoded-main-container">
        <div class="pcoded-wrapper">
            <div class="pcoded-content">
                <div class="pcoded-inner-content">
                    <div class="main-body">
                        <div class="page-wrapper">
                            <!-- [ breadcrumb ] start -->
                            <div class="page-header">
                                <div class="page-block">
                                    <div class="row align-items-center">
                                        <div class="col-md-12">
                                            <div class="page-header-title">
                                                <h5 class="m-b-10">Postt</h5>
                                            </div>
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                <li class="breadcrumb-item"><a href="#!">Marketing</a></li>
                                                <li class="breadcrumb-item"><a href="#!">Post List</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- [ breadcrumb ] end -->
                            <!-- [ Main Content ] start -->
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>List Posts</h5>
                                        </div>
                                        <div class="card-header">
                                            <h5>Filter</h5>
                                            <div class="col-md-12">
                                                <form method="GET" action="/src/marketing/postlist">
                                                    <div class="form-group row">
                                                        <div class="col-md-6">
                                                            <label for="author">Author</label>
                                                            <input type="text" 
                                                                   class="form-control" 
                                                                   id="author" 
                                                                   placeholder="Enter author name" 
                                                                   value="${requestScope.author}"
                                                                   name="author">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label for="title">Title</label>
                                                            <input type="text" class="form-control" 
                                                                   id="title" 
                                                                   placeholder="Enter Titles" 
                                                                   value="${requestScope.title}"
                                                                   name="title">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <div class="col-md-6">
                                                            <label for="category">Category</label>
                                                            <select class="form-control" id="category" name="category">
                                                                <option value="">Category</option>
                                                                <c:forEach items="${requestScope.listPost_Categories}" var="c">
                                                                    <option value="${c.postCategoryID}"
                                                                        <c:if test="${requestScope.category==c.postCategoryID}">
                                                                            selected
                                                                        </c:if>>${c.postCategoryName}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div> 
                                                        <div class="col-md-6">
                                                            <label for="status">Status</label>
                                                            <select class="form-control" id="status" name="status">
                                                                <option value="">Status</option>
                                                                <option value="0"
                                                                        <c:if test="${requestScope.status==0}">
                                                                            selected
                                                                        </c:if>
                                                                        >Only Me</option>
                                                                <option value="1"
                                                                        <c:if test="${status==1}">
                                                                            selected
                                                                        </c:if>
                                                                        >Public</option>
                                                            </select>
                                                        </div>                                                        
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Filter</button>
                                                    <a href='/src/marketing/postlist' class="btn btn-danger">Clear all</a>  
                                                </form>
                                            </div>
                                        </div>
                                        <div class="card-body table-border-style">
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Id</th>
                                                            <th>Thumbnail</th>
                                                            <th><a id="PostTitle" class="toggle-link" href="">Title</a></th>
                                                            <th><a id="PostCategoryId" class="toggle-link" href="">Category</a></th>
                                                            <th><a id="name" class="toggle-link" href="">Author</a></th>
                                                            <th><a id="Featured" class="toggle-link" href="">Featured</a></th>
                                                            <th><a id="PostStatus" class="toggle-link" href="">Status Information</a></th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:set var="i" value="0"></c:set>
                                                        <c:forEach items="${requestScope.listPosts}" var="p">
                                                        <tr>
                                                            <td>${i=i+1}</td>
                                                            <td>${p.postID}</td>
                                                            <td><img src="${p.thumbnail}" alt=""></td>
                                                            <c:set var="titleParts" value="${fn:split(p.postTitle, ' ')}"/>
                                                            <td>${titleParts[0]} ${titleParts[1]}...</td>
                                                            <td>${p.postCategoryID.postCategoryName}</td>
                                                            <td>${p.userID.name}</td>
                                                            <c:set var="featuredParts" value="${fn:split(p.featured, ' ')}"/>
                                                            <td>${featuredParts[0]} ${featuredParts[1]}...</td>
                                                            <td>
                                                                <c:if test="${p.postStatus == true}">Public</c:if>
                                                                 <c:if test="${p.postStatus == false}">Only ME</c:if>
                                                            </td>
                                                            <td>
                                                                     <a href="/src/marketing/viewpost?postID=${p.postID}"><i class="fa fa-info-circle"></i></a>
                                                                        <a href="/src/marketing/editpost?postID=${p.postID}"> <i class="fa fa-pencil"></i></a>
                                                                        <a href="/src/marketing/changestatuspost?postID=${p.postID}&status=0">
                                                                            <c:if test="${p.postStatus == true}"><i class="fa fa-eye"></i> </c:if> 
                                                                        </a>
                                                                        <a href="/src/marketing/changestatuspost?postID=${p.postID}&status=1">      
                                                                            <c:if test="${p.postStatus == false}"><i class="fa fa-eye-slash"></i> </c:if>   
                                                                        </a> 
                                                             
                                                            </td>
                                                        </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="text-center">
        <nav aria-label="Page navigation example">
            <ul class="pagination" id="pagination" style="justify-content: center">
<!--                <li class="page-item"><a class="page-link" href="#">First</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Last</a></li>-->
            </ul>
        </nav>   
    </div>
                                                        
    <script src="/src/assests/js/pagger.js"></script>
    <script>
    function ConfigureUrl(keywords){
        let currentUrl = window.location.href;
        keywords.forEach(element => {
            const params = new URLSearchParams(window.location.search)
            let QueryValue = params.get(element);
            if(QueryValue!=null){
                if(currentUrl.includes("?"+element)){
                currentUrl = currentUrl.replaceAll("?"+element+"="+QueryValue,"");
                }else if(currentUrl.includes("&"+element)){
                    currentUrl = currentUrl.replaceAll("&"+element+"="+QueryValue,"");
                }
            }
        });
        if(currentUrl.includes("?"))    return (currentUrl+"&");
        else if(currentUrl.includes("&"))  return (currentUrl.replace("&","?")+"&"); 
        else    return (currentUrl+"?"); 
    }
    let keywordsPagging = ["page_index"];
    let configureUrl = ConfigureUrl(keywordsPagging);
    console.log(configureUrl);
    pagger({
        id: 'pagination',
        pageindex: ${requestScope.page_index}, 
        totalpage: ${requestScope.totalpage}, 
        gap: 1, 
        url: {
            firstpage: '<li class="page-item"><a class="page-link" href="'+configureUrl+"page_index=1"+'">First</a></li>',
            lastpage: '<li class="page-item"><a class="page-link" href="'+configureUrl+"page_index=${requestScope.totalpage}"+'">Last</a></li>',
            currentpage: '<li id="activepagging" class="active"><a class="page-link" href="'  +configureUrl+"page_index=${requestScope.page_index}"+  '">${requestScope.page_index}</a></li>',
            nextpage: function (i){
                return '<li class="page-item"><a class="page-link" href="'+configureUrl+"page_index="+ ''+i+'">'+i+'</a></li>';
            }
    }});
    
    var link_data = document.getElementsByClassName("toggle-link");
    let keywordsSort = ["orderby", "direction"];
    let configureUrlSort = ConfigureUrl(keywordsSort);
    for (var i = 0; i < link_data.length; i++) {
        console.log(link_data[i].id)
        if(window.location.href.includes("desc"))   link_data[i].href = configureUrlSort + "orderby="+link_data[i].id+"&direction=asc";
        else    link_data[i].href = configureUrlSort + "orderby="+link_data[i].id+"&direction=desc";
//        if('${requestScope.urlOrder}'.includes("?")){
//          if('${requestScope.currentUrl}'.includes("desc")){
//                link_data[i].href = '${requestScope.urlOrder}&orderby='+link_data[i].id+'&direction=asc';
//            }else{
//                link_data[i].href = '${requestScope.urlOrder}&orderby='+link_data[i].id+'&direction=desc';
//            }      
//        }else {
//            if('${requestScope.currentUrl}'.includes("desc")){
//                link_data[i].href = '${requestScope.urlOrder}?orderby='+link_data[i].id+'&direction=asc';
//            }else{
//                link_data[i].href = '${requestScope.urlOrder}?orderby='+link_data[i].id+'&direction=desc';
//            }  
//        }
    }





</script>

</body>
</html>

