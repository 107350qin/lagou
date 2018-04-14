<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/other.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.slider.css">
    <script src="<%=request.getContextPath()%>/jq/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery.slider.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-header.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-content.js"></script>
    <script src="<%=request.getContextPath()%>/js/other.js"></script>
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
</head>
<body>



<!--拉勾header-->
<div class="lg-header">
    <div class="lg-tbar">
        <div class="inner">
            <div class="lg-tbar-l">
                <a class="lg-tbar-l-lgapp" href="https://www.lagou.com/app/download.html" target="_blank">拉勾APP</a>
                <a class="lg-tbar-l-lgos" href="${pageContext.request.contextPath}/com/comland"
                   target="_blank">进入企业版</a>
            </div>
            <ul class="lg-tbar-r">
            <c:if test="${sessionScope.CUR_USER!=null}">
                <li><a href="" class="lg-tbar-r-line"> <c:out value="${sessionScope.CUR_USER.resume.r_name}"></c:out> </a></li>
                <li><a href="${pageContext.request.contextPath}/user/resume">修改简历</a></li>
                <li><a href="${pageContext.request.contextPath}/user/cancel">退出登陆</a></li>
                
            </c:if>
            
            <c:if test="${sessionScope.CUR_USER==null}">
                <li><a href="${pageContext.request.contextPath}/user/land" class="lg-tbar-r-line">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/user/registerclick">注册</a></li>
            </c:if>
            
            </ul>
        </div>
    </div>
    <div class="lg-tnav">
        <div class="inner">
            <div class="lg-tnav-l">
                <a href="${pageContext.request.contextPath}/user/index" class="lg-logo"><h1>
                    拉勾网
                </h1></a>
                <div class="lg-tnav-l-suggestCity">
                   
                    <c:if test="${requestScope.city!=null}">
                     <strong id="changeCity">${requestScope.city}</strong>
                    </c:if>
                    <c:if test="${requestScope.city==null}">
                    	<strong id="changeCity">全国</strong>
                    </c:if>
                    
                    
                 <form id="myform" action="${pageContext.request.contextPath}/user/changeCity" method="post">
                    <select id="select" name="select">
                    <option value="全国">[切换城市]</option>
                    <option value="全国">全国</option>
                    <option value="成都">成都</option>
                    <option value="北京">北京</option>
                    <option value="上海">上海</option>
                    <option value="杭州">杭州</option>
                    <option value="深圳">深圳</option>
                    </select>
                 </form>
                   
                   
                </div>
            </div>
            <ul class="lg-tnav-wrap" id="lg_tnav_wrap">
                <li><a href="#" class="current">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/searchCompany">公司</a></li>
                <li><a href="#">校园</a></li>
                <li><a href="#">言职</a></li>
                <li><a href="#">大鲲</a></li>
            </ul>
        </div>
    </div>
</div>



</body>
</html>