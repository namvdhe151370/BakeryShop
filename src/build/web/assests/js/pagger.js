/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function pagger(data) {
    var container = document.getElementById(data.id);
    let result = '';
    result += data.url.firstpage;
    for (var i = data.pageindex - data.gap; i < data.pageindex; i++) {
        if (i > 0)
            result += data.url.nextpage(i);
    }
    result += data.url.currentpage;
    for (var i = data.pageindex + 1; i <= data.pageindex + data.gap; i++) {
        if (i <= data.totalpage)
            result += data.url.nextpage(i);
    }
    result += data.url.lastpage;
    container.innerHTML = result;
}

//function pagger(id, pageindex, totalpage, gap, url){
//    var container = document.getElementById(id);
//    let result = '';
//    result+='<li><a href="/src/customer/myorder?page_index='+1+'"><i class="fa fa-caret-left"></i></a></li>';
//    for (var i = pageindex-gap; i < pageindex; i++) {
//        if(i>0)  result+= '<li><a href="/src/customer/myorder?page_index='+i+'">'+i+'</a></li>';
//    }
//    result+= '<li class="active"><a href="/src/customer/myorder?page_index='+pageindex+'">'+pageindex+'</a></li>'
//    for (var i = pageindex+1; i <= pageindex+gap; i++) {
//        if(i<=totalpage)  result+= '<li><a href="/src/customer/myorder?page_index='+i+'">'+i+'</a></li>';
//    }
//    result+='<li><a href="/src/customer/myorder?page_index='+totalpage+'"><i class="fa fa-caret-right"></i></a></li>';
//    container.innerHTML=result;   
//}