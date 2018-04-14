



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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/Land.css">
    <script src="${pageContext.request.contextPath }/jq/jquery-1.11.1.min.js"></script>
</head>
<body>
<!--拉勾头部header-->
<div class="re-header">
    <a href="<%=request.getContextPath()%>/user/index"></a>
</div>
<!--拉勾content-->
<div class="re-content-box" style="width:300px;">
    <div class="re-content-box-left">
        <div class="re-content-box-left-head">
        <ul class="re-content-box-left-head-ul">
            <li class="li-active">Administrator</li>
        </ul>
        <span class="re-content-box-left-head-triangle"></span>
    	</div>
        <div class="re-content-box-left-body">
 		<form action="<%=request.getContextPath()%>/admin/loginDo" method="post">
            <div class="re-content-box-left-body-item">
                <input type="text" name="account" placeholder="管理员账号">
                <input type="password" name="password" placeholder="管理员密码">
            </div>
            <div class="re-content-box-left-body-forget">
                <a></a>
            </div>
            <div class="re-content-box-left-body-btn">
                <input id="theSubmit" type="submit" value="登录">
            </div>
          </form> 
        </div>
    </div>
</div>

</body>
</html>
