<%@page import="com.lagou.entity.Pager"%>
<%@page import="com.lagou.entity.Company"%>
<%@page import="com.lagou.entity.Position"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/other.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/posistionList.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.slider.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/companyDetail.css">
    <script src="<%=request.getContextPath()%>/jq/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery.slider.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-header.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-content.js"></script>
    <script src="<%=request.getContextPath()%>/js/other.js"></script>
</head>
<body>

<%@ include file="./header.jsp" %>
   
<!--页content start-->
<%
Company com=(Company)(request.getAttribute("companyClicked"));
%>
<div class="top">
	<div class="top_box">
		<div class="top_box_img">
			<img src='${pageContext.request.contextPath }/files/<%=com.getCom_logo() %>' alt="公司logo">
		</div>
		<div class="top_box_main">
			<a> <%=com.getCom_name() %> </a>
			<p class="firstP"> <%=com.getSign() %> </p>
			<ul>
			<li><p>招聘职位</p> <span><%=com.getPosistionNum() %></span> 个</li>
			<li><p>注册时间</p> <span><%=com.getCom_reg() %></span></li>
			<li><p>公司邮箱</p> <span><%=com.getCom_email() %></span></li>
			<li>
				<p>公司类型</p> 
				<span>
				<%for(String item:com.getCom_type()) 
				out.print(item+"  .  ");
				%>
				</span>
			</li>
			</ul>
			
		</div>
		
	</div>
	<div class="twoLinks">
		<a href="<%=request.getContextPath()%>/user/companyDetail?com_id=<%=com.getCom_id()%>">公司主页</a>
		<a href="<%=request.getContextPath()%>/user/companyDetail_positions?com_id=<%=com.getCom_id()%>">招聘职位</a>
	</div>
</div>

<div class="mid">
	<div class="mid_left">
		<div class="m1">
			<h2>公司简介</h2>
			<p><%=com.getCom_desc() %></p>
		</div>
		<div class="m2">
			<h2>公司位置</h2>
			<p><%=com.getCom_address() %></p>
		</div>
		
	</div>
	<div class="mid_right">
	</div>
</div>

<!--页content end-->

<%@ include file="./footer.jsp" %>

</body>
</html>