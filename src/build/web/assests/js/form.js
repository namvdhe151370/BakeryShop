function doDelete(id, name) {
    var c = confirm("Bạn có muốn xoá sản phẩm: " + name + "?");
    if (c) {
        window.location.href = "deleteproductcart?productId=" + id;
    }
}
function myFunction() {
    let title = document.forms["formInsert"]["title"].value;
    let brief = document.forms["formInsert"]["briefInformation"].value;

    let thumbnail = document.forms["formInsert"]["thumbnail"].value;
    let category = document.forms["formInsert"]["postCategoryID"].value;
    let status = document.forms["formInsert"]["status"].value;
    if (title == "") {
        alert("title must be filled out");
        return false;
    } else if (brief == "") {
        alert("brief must be filled out");
        return false;
    } else if (thumbnail == "") {
        alert("thumbnail must be filled out");
        return false;
    } else if (category == "") {
        alert("catgory must be selected");
        return false;
    } else if (status == "") {
        alert("status must be selected");
        return false;
    }
}
function myFunction2() {
    let title = document.forms["formEdit"]["title"].value;
    let brief = document.forms["formEdit"]["briefInformation"].value;
    let Description = document.forms["formEdit"]["Description"].value;
    let category = document.forms["formEdit"]["postCategoryID"].value;
    let status = document.forms["formEdit"]["status"].value;
    if (title == "" || brief == "" || category == "" || status == "") {
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


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


