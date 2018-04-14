<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司登陆————找工作-互联网招聘求职网-拉勾网</title>
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/Land.css">
    <script src="${pageContext.request.contextPath }/jq/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/jq/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#username').blur(function(){
		var username=$(this).val();
		var msg1=$("#msg1");
		$.getJSON("${pageContext.request.contextPath}/com/com_verifyLoginUsername",{name:username},function(data){
			msg1.text(data[0]);
			if(data[1]=='0'){
				msg1.css('color','red');
			}else{
				msg1.css('color','green');
			}
		});
	})
}) 
</script>
</head>
<body>
<!--拉勾头部header-->
<div class="re-header">
    <a href="${pageContext.request.contextPath}/user/index"></a>
</div>
<!--拉勾content-->
<div class="re-content-box">
    <div class="re-content-box-left">
        <div class="re-content-box-left-head">
        <ul class="re-content-box-left-head-ul">
            <li class="li-active">密码登陆</li>
            <li>验证码登陆</li>
        </ul>
        <span class="re-content-box-left-head-triangle"></span>
    </div>
        <div class="re-content-box-left-body">
 		<form action="${pageContext.request.contextPath }/com/comlandDo">
            <div class="re-content-box-left-body-item">
                <input type="text" name="com_account" placeholder="请输入常用手机/邮箱" id="username">
                <p id="msg1" style="height:20px;text-align: left;"></p>
                <input type="password" name="com_password" placeholder="请输入密码">
            </div>
            <div class="re-content-box-left-body-forget">
                <a href="">忘记密码？</a>
            </div>
            <div class="re-content-box-left-body-btn">
                <input id="theSubmit" type="submit" value="登录">
            </div>
          </form> 
        </div>
    </div>
    <div class="re-content-box-divider"></div>
    <div class="re-content-box-right">
        <h5>还没有账号：</h5>
        <a href="${pageContext.request.contextPath }/com/comreg" class="login_now">立即注册公司账号</a>
        <h5>使用以下账号直接登陆：</h5>
        <ul>
            <li><a href="" class="icon_sina"></a></li>
            <li><a href="" class="icon_wechat"></a></li>
            <li><a href="" class="icon_tencent"></a></li>
            <li><a href="" class="icon_baidu"></a></li>
        </ul>

    </div>
</div>
 
<!-- <script>
    $(function () {
        $(".re-content-box-left-head-ul > li").on("click",function () {
            $(this).addClass("li-active").siblings().removeClass("li-active");
            if($(".re-content-box-left-head-triangle").css("left") == "0px"){
                $(".re-content-box-left-head-triangle").css("left","145px");
            }else {
                $(".re-content-box-left-head-triangle").css("left","0");
            }
        })
    })
</script> -->
</body>
</html>