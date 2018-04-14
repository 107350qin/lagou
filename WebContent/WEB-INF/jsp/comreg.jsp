<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找工作-互联网招聘求职网-拉勾网</title>
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/registerclick.css">
    <style type="text/css">
    .re-content-box .re-content-box-left-body .re-content-box-left-body-Verification p{
    height:10px;
    }
    #msg1,#msg2,#msg3{
    text-align: left;
    height: 20px;
    }
    #thePic{
    margin-top: 30px; 
    }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jq/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#username').blur(function(){
		var username=$(this).val();
		var msg1=$("#msg1");
		$.getJSON("${pageContext.request.contextPath}/com/com_verifyRegUsername",{name:username},function(data){
			msg1.text(data[0]);
			if(data[1]=='0'){
				msg1.css('color','red');
			}else{
				msg1.css('color','green');
			}
		});
	})
	
	$('#password').blur(function(){
		var password=$(this).val();
		var msg2=$("#msg2");
		$.getJSON("${pageContext.request.contextPath}/com/com_verifyRegPassword",{pass:password},function(data){
			msg2.text(data[0]);
			if(data[1]=='0'){
				msg2.css('color','red');
			}else{
				msg2.css('color','green');
			}
		});
	})
	
	$('#checkNum').blur(function(){
		var checkNum=$(this).val();
		var msg3=$("#msg3");
		$.getJSON("${pageContext.request.contextPath}/com/com_verifyRegCheckNum",{val:checkNum},function(data){
			if(data=='0'){
			msg3.text("验证码不正确");
				msg3.css('color','red');
			}else{
			msg3.text(" √ ");
				msg3.css('color','green');
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
<div class="re-content-box" >
<h2>公司注册页 <i>|</i> <span>专注互联网职业机会</span></h2>
    <div class="re-content-box-left">
        <div class="re-content-box-left-body">
		<form action="${pageContext.request.contextPath }/com/comregDo">
            <div class="re-content-box-left-body-phone">
                <input type="text" name="com_account" placeholder="请输入您的账号" id="username">
                <p id="msg1"></p>
            </div>
            <div class="re-content-box-left-body-item">
                <input type="password" name="com_password" placeholder="请输入您的密码" id="password">
                 <p id="msg2"></p>
                <img id="thePic" onclick='this.src=this.src+"?c="+Math.random()' src="${pageContext.request.contextPath }/com/val">
            </div>
            <div class="re-content-box-left-body-Verification">
                <input type="text" name="val" placeholder="请输入验证码" id="checkNum">
            </div>
                 <p id="msg3"></p>
            <div class="re-content-box-left-body-btn">
              <input type="submit" value="注册">
            </div>
         </form>   
            <div class="re-content-box-left-body-text">
                注册代表你已同意<p>「拉勾用户协议」</p>
            </div>
        </div>
    </div>
    <div class="re-content-box-divider"></div>
    <div class="re-content-box-right">
        <h5>已有账号：</h5>
        <a href="${pageContext.request.contextPath }/com/comland" class="login_now">直接登陆</a>
        <h5>使用以下账号直接登陆：</h5>
        <ul>
            <li><a href="" class="icon_sina"></a></li>
            <li><a href="" class="icon_wechat"></a></li>
            <li><a href="" class="icon_tencent"></a></li>
            <li><a href="" class="icon_baidu"></a></li>
        </ul>

    </div>
</div>

</body>
</html>