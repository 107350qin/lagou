$(function () {
    /*lg-logintoolbar定位效果*/
    var footertop = $(document).height() - $(".lg-footer").height() - $(window).height();
    var timer = null;
    var num = 0;
    $(window).scroll(function () {
        $(document).scrollTop
        function sendnumadd() {
            num += 2;
            if (num <= 68) {
                $(".lg-logintoolbar").css("bottom", num + "px");
            } else {
                clearInterval(timer);
            }
        }

        var docscrolltop = $(document).scrollTop();
        if (docscrolltop >= footertop) {
            clearInterval(timer);
            timer = setInterval(sendnumadd, 1);
            num = 0;
        } else {
            $(".lg-logintoolbar").css("bottom", "0");
            clearInterval(timer);
        }
    });

    /*feedback-icon*/
    $(".product-fk-icon").on("mouseenter", function () {
        $(this).css("background-position", "-38px 0");
    }).on("mouseleave", function () {
        $(this).css("background-position", "0 0");
    });

});