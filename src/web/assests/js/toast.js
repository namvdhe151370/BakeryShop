function Alert(toast){
    var element = document.querySelector(".toastx");
    element.innerHTML = `        
    <div class="wrapper">
        <p class="toast--icon"><i class="fa-solid fa-exclamation"></i></p>
        <p class="toast--content">${toast.content}</p>
    </div>`;
    if(toast.type=="error"){
        document.querySelector(".wrapper").setAttribute("class","wrapper wrapper--error")
    }else{
        document.querySelector(".wrapper").setAttribute("class","wrapper wrapper--sucess")
        document.querySelector(".toast--icon").innerHTML='<i class="fa-solid fa-check"></i>';
    }
    document.querySelector(".wrapper").style.animation = `display ease-in-out .2s, remove ease-in-out .5s 1s forwards`;

    const autoRemove = setTimeout(() => {
        document.querySelector(".wrapper").remove();
    }, 1500);

    setTimeout(() => {
        clearTimeout(autoRemove)
    }, 1600);     
}
 