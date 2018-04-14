$(function () {
    //hottab
    $(".content-body-clearfix-tabmodule > li").click(function () {
        $(this).addClass("clickli-current").siblings().removeClass("clickli-current");
        $(".content-body-clearfix-tabmodulelist").children().eq($(this).index()).addClass("clickli-db").siblings().removeClass("clickli-db");
    });

    // content-body-clearfix-sidebar-mainNavs
    var target = document.getElementById("content-body-clearfix-sidebar-mainNavs");
    var navs = target.children;
    var subs = document.getElementsByClassName("menu-box-main-sub");
    for (var i = 0; i < navs.length; i++) {
        navs[i].index = i;
        navs[i].onmouseover = function () {
            this.className = "menu-box li-current";
            subs[this.index].className = "menu-box-main-sub db";
        }
        navs[i].onmouseout = function () {
            for (var j = 0; j < navs.length; j++) {
                navs[j].className = "menu-box";
                subs[j].className = "menu-box-main-sub";
            }
        }
    }
})