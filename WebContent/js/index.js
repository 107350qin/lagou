/*
由Mr.Yang编写 2017.12.28*/


/*banner nav tab*/
function tab(obj){
    var target = document.getElementById(obj);
    var lis = target.getElementsByTagName("li");
    for(var i = 1 ; i < lis.length ; i++){
        lis[i].onmouseover = function(){
            this.className="current";
        }
        lis[i].onmouseout =function () {
            for(var j = 0 ; j < lis.length ; j++){
                lis[j].className = "";
            }
        }
    }
};

// content-body-clearfix-sidebar-mainNavs
function navs(obj) {
    var target = document.getElementById("content-body-clearfix-sidebar-mainNavs");
    var navs =target.children;
    for(var i = 0 ; i <navs.length;i++){
        navs[i].onmouseover = function (ev) {
            this.className = "menu-box li-current";
        }
        navs[i].onmouseout = function (ev) {
            for(var j = 0 ; j <navs.length ; j++){
                navs[j].className = "menu-box";
            }
        }
    }

}