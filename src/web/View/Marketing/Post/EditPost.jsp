<%-- 
    Document   : EditSlider
    Created on : Jun 14, 2022, 9:45:19 AM
    Author     : hellb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    <head>

        <title>Edit Post</title>
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
                                                    <h5 class="m-b-10">Post</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Marketing</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Edit Post</a></li>
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

                                            <div class="card-body table-border-style">
                                                <div class="table-responsive">
                                                    <form action="/src/marketing/editpost" name="formEdit" method="post" enctype="multipart/form-data">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Edit Post</h4>
                                                            <a href="/src/marketing/postlist"> <input type="button"
                                                                         class="btn btn-secondary" value="Back to List"></a>
                                                        </div>

                                                        <div class="modal-body">

                                                            <div class="form-group">
                                                                <label>Title</label>
                                                                <input class="form-control" type="hidden" id="postID" name="postID"   value="${post.postID}" />
                                                                <input value="${post.postTitle}" name="title" type="text"
                                                                       class="form-control">

                                                            </div>

                                                            <div class="form-group">
                                                                <label>BriefInformation</label>
                                                                <input value="${post.briefInformation}" name="briefInformation" type="text"
                                                                       class="form-control">
                                                            </div>


                                                            <div class="form-group">
                                                                <label>description</label>
                                                                <textarea name="Description" id="Description" type="text"
                                                                          class="form-control">${post.postDescription}</textarea>
                                                            </div>
                                                            <div class="form-group">
                                                                <div
                                                                    <div class="ps-form--review form-group">
                                                                        <!-- Upload image input-->
                                                                        <div class="input-group mb-3 px-2 py-2 rounded-pill bg-white shadow-sm">
                                                                            <input id="upload" type="file" onchange="readURL(this);" class="form-control border-0" name="thumbnail" value="${post.thumbnail}">
                                                                            <label id="upload-label" for="upload" class="font-weight-light text-muted">Thumnail</label>

                                                                        </div>

                                                                        <!-- Uploaded image area-->

                                                                        <div class="image-area mt-4"><img id="imageResult" src="${post.thumbnail}" name="raw_thumbnail" class="img-fluid rounded shadow-sm mx-auto d-block"></div>
                                                                    </div>
                                                                </div>

                                                                        

                                                            <div class="form-row">
                                                                <div class="form-group col-md-6">
                                                                    <label>Category</label>

                                                                    <select name="postCategoryID" class="form-control"
                                                                            aria-label="Default select example">
                                                                        <option value="">Choose Category</option>   
                                                                        <c:forEach items="${lsPostCategory}" var="c">
                                                                            <option 
                                                                                <c:if test="${c.postCategoryID == post.postCategoryID.postCategoryID}">selected="selected"</c:if>
                                                                                value="${c.postCategoryID}">${c.postCategoryName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group col-md-6">
                                                                    <label>Status</label>
                                                                    <select name="status" class="form-control">
                                                                        <option value="">Choose Status</option>
                                                                        <option <c:if test="${post.postStatus ==true}">selected="selected" </c:if>value="1">Public</option>
                                                                        <option <c:if test="${post.postStatus ==false}">selected="selected"</c:if> value="0">Only Me</option>

                                                                    </select>
                                                                </div>
                                                            </div>
                                                            </div>

                                                            <div class="modal-footer">
                                                                <input type="submit" name="post" class="btn btn-success" onclick="return myFunction2()" value="Post">
                                                                <input type="submit" name="save" class="btn btn-primary" value="Save">
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
                </div>
            </div>
        </section>

       
        
        <script src="https://cdn.ckeditor.com/4.19.0/standard-all/ckeditor.js"></script>
      <script src="/src/assests/js/form.js"></script>

    </body>

</html>
