<%@page import="com.lagou.util.StringUtil"%>
<%@page import="com.lagou.entity.Company"%>
<%@page import="com.lagou.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/changeResume.css">
</head>
<body>

<%@ include file="./header.jsp" %>

<!--简历-->
<form action="${pageContext.request.contextPath }/com/changeComInfoDo" method="post"  enctype ="multipart/form-data">
<div class="lg-Seeyourresume">
    <div class="re-header">
        <div class="header-top">
            <img  src="${pageContext.request.contextPath }/files/${sessionScope.com.com_logo}" alt="" style="width: 120px;">
            <img src="//www.lgstatic.com/www/static/mycenter/modules/common/img/tou_c3707c7.png" alt="">
        </div>
        <div class="header-bottom">
           
            <div class="bottom-info">
                <div class="info-top">
                <p>公司logo <input class="file" type="file" name="com_logo"></p>
                <p>公司名字  <input class="ff" type="text" name="com_name" value="${sessionScope.com.com_name }"></p>
                <p>公司地址  <input class="ff" type="text" name="com_address" value="${sessionScope.com.com_address }"></p>
                <p>注册时间  <input class="ff" type="text" name="com_reg" value="${sessionScope.com.com_reg }"></p>
                <p>公司邮箱  <input class="ff" type="text" name="com_email" value="${sessionScope.com.com_email }"></p>
                <p>公司理念  <input class="ff" type="text" name="com_sign" value="${sessionScope.com.sign }"></p>
                
                <p> 公司类型<input class="ff" type="text" name="com_type" value="<%=StringUtil.arrayToString(((Company)(session.getAttribute("com"))).getCom_type()) %>">
               </p>
                
                </div>
            </div>
        </div>
    </div>
    <div class="re-content">
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>公司简介</span>
                <span></span>
            </div>
            <div class="item-content">
               <textarea name="com_desc" style="line-height:25px;">${sessionScope.com.com_desc}</textarea>
            </div>
        </div>
  
    </div>
    <div class="re-bottom">
    
        <input type="submit" value="确认修改"> 
        <a href="${pageContext.request.contextPath }/com/cominfo">取消修改</a>
    </div>
</div>
</form>




<%@ include file="./footer.jsp" %>

</body>
</html>