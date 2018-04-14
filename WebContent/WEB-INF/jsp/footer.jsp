<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
</head>
<body>


<!--拉勾页面脚部footer-->
<div class="lg-footer">
    <i class="lg-footer-icon"></i>
    <div class="lg-footer-inner">
        <a href="">拉勾APP <i></i></a>
        <a href="">拉勾微博</a>
        <a href="">拉勾微信</a>
        <a href="">拉勾小程序</a>
        <a href="">帮助中心</a>
        <a href="">联系我们</a>
        <a href="">招聘解决方案</a>
        <span>服务热线 : <em>4006-2828-35 (9:00 - 18:00)</em></span>
    </div>
    <div class="lg-footer-copyright">
        <span>@2018 Lagou 京ICP备14023790号-2 京公网安备 11010802024043号</span>
    </div>
</div>
<!--登陆注册工具栏-->
<div class="lg-logintoolbar">
    <div class="lg-logintoolbar-center">
        <em></em>
        <p><span>在拉勾，发现新的职业机会</span>
            <span>298796公司 &nbsp &nbsp &nbsp <i></i> &nbsp &nbsp &nbsp 3943965职位</span>
        </p>
        <div class="lg-logintoolbar-center-right">
            <a href="${pageContext.request.contextPath}/user/land">登陆</a>
            <a href="${pageContext.request.contextPath}/user/registerclick">注册</a>

        </div>
    </div>
</div>
<!--页面小部件-->
<a  class="backtop"></a>
<div class="product-fk">
    <div class="product-fk-icon"></div>
    <span>我要反馈</span>
</div>

<script>
    window.onload = function () {
        /*调用banner nav tab函数*/
        tab("lg_tnav_wrap");
        //调用slider框架
        $('.content-body-clearfix-home-banner-testSlider').slider({
            originType: 'tuoyuan'
        });
        function goTop(obj) {
            $(window).scroll(function() {
                var $scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop; //兼容浏览器
                if($scrollTop > 0) { //滚动高度可调
                    $(obj).show();
                } else {
                    $(obj).hide();
                };

            });
            $(obj).on("click",function () {
                $("html,body").animate({scrollTop:0},800);
            })
        }
        goTop(".backtop");

    };


</script>




</body>
</html>