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

        <title>Edit Slider</title>
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
        <!-- Favicon icon -->
        <link rel="icon" href="/src/assests/img/favicon.ico" type="image/x-icon">
        <!-- fontawesome icon -->
        <link rel="stylesheet" href="/src/assests/plugins/font-awesome/css/font-awesome.min.css">
        <!-- animation css -->
        <link rel="stylesheet" href="/src/assests/plugins/animation/css/animate.min.css">
        <!-- vendor css -->
        <link rel="stylesheet" href="/src/assests/css/style_admin.css">
        <!--for Alert-->
        <link rel="stylesheet" href="/src/assests/css/toast.css">
    </head>

    <style>
        .table-striped img{
            height: 50px;
            width: 50px;
        }
    </style>

    <body class="">
        <%@include file="../../public/adNavbar.jsp" %>
        <!-- [ Main Content ] start -->
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
                                                    <h5 class="m-b-10">Slider</h5>
                                                </div>
                                                <ul class="breadcrumb">
                                                    <li class="breadcrumb-item"><a href=""><i class="feather icon-home"></i></a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Marketting</a></li>
                                                    <li class="breadcrumb-item"><a href="#!">Edit Slider</a></li>
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
                                                <form action="../marketing/editslider" method="post" enctype="multipart/form-data">
                                                        <div class="modal-header">						
                                                            <h4 class="modal-title">Edit Slider</h4>
                                                           <a href="../marketing/sliderlist"> <input type="button"
                                                                                                      class="btn btn-success" value="Back to List"></a>
                                                        </div>

                                                        <div class="modal-body">					
                                                            <div class="form-group">
                                                                <label>Slider ID</label>
                                                                <input value="${slider.sliderID}" name="sliderId" type="text" class="form-control" readonly required>
                                                            </div>
                                                            <c:if test="${slider.productID.productID != 0}">
                                                                <div class="form-group">
                                                                    <label>ProductId</label>
                                                                    <input value="${slider.productID.productID}" name="productId" type="text" class="form-control"  readonly required>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${slider.postID.postID != 0}">
                                                                <div class="form-group">
                                                                    <label>PostId</label>
                                                                    <input value="${slider.postID.postID}" name="postId" type="text" class="form-control" readonly  required>
                                                                </div>
                                                            </c:if>
                                                            <div class="form-group">
                                                                <label>Title</label>
                                                                <input value="${slider.title}" name="title" type="text" class="form-control"   required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Back link</label>
                                                                <input value="${slider.backlink}" name="backlink" type="text" class="form-control"   required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Image</label>
                                                                <div class="input-group mb-3 px-2 py-2 rounded-pill bg-white shadow-sm">
                                                                    <input id="upload" name="image" type="file" onchange="readURL(this);" class="form-control border-0">
                                                                  

                                                                </div>

                                                                <!-- Uploaded image area-->

                                                                <div class="image-area mt-4"><img id="imageResult" src="${slider.image}" alt="" class="img-fluid rounded shadow-sm mx-auto d-block"></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Note</label>
                                                                <textarea name="note" class="form-control"   required>${slider.notes}</textarea>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Status</label>
                                                                <select name="statusSlider" class="form-control" aria-label="Default select example">
                                                                    <option ${slider.status == 1?"selected":""} value="1">Active</option>
                                                                    <option ${slider.status == 2?"selected":""} value="2">Deactive</option>
                                                                </select>
                                                            </div>

                                                        </div>

                                                        <div class="modal-footer">
                                                            <input type="submit" class="btn btn-succsess" value="Save">
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
        <script src="/src/assests/js/vendor-all.min.js"></script>
        <script src="/src/assests/plugins/bootstrap4/js/bootstrap.min.js"></script>
        <script src="/src/assests/js/pcoded.min.js"></script>
         <script src="https://cdn.ckeditor.com/4.19.0/standard-all/ckeditor.js"></script>
 <script>

    function myFunction() {
      let title = document.forms["formInsert"]["title"].value;
      let brief = document.forms["formInsert"]["briefInformation"].value;
      let Description = document.forms["formInsert"]["Description"].value;
      let thumbnail = document.forms["formInsert"]["thumbnail"].value;
      let category = document.forms["formInsert"]["postCategoryID"].value;
      let status = document.forms["formInsert"]["status"].value;
      if (title == "" || brief == "" || Description == "" || thumbnail == "" || category == "" || status == "") {
        alert("All content must be filled out");
        return false;
      }
    }
    function readURL(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
          $('#imageResult')
            .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
      }
    }

    $(function () {
      $('#upload').on('change', function () {
        readURL(input);
      });
    });
    var input = document.getElementById('upload');
    var infoArea = document.getElementById('upload-label');

    input.addEventListener('change', showFileName);

    function showFileName(event) {
      var input = event.srcElement;
      var fileName = input.files[0].name;
      infoArea.textContent = 'File name: ' + fileName;
    }
    CKEDITOR.replace('Description', {
      extraPlugins: 'editorplaceholder',
      editorplaceholder: 'Write your description here...',
      removeButtons: 'PasteFromWord'
    });


  </script>
    </body>

</html>
