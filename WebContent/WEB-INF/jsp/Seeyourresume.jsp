<%@page import="com.lagou.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/footer.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/Seeyourresume.css">
    <script src="${pageContext.request.contextPath }/js/lg-header.js"></script>
</head>
<body>

<%@ include file="./header.jsp" %>





<!--简历-->
<form action="${pageContext.request.contextPath }/user/changeResume" method="post">
<div class="lg-Seeyourresume">
    <div class="re-header">
        <div class="header-top">


            <img  src="${pageContext.request.contextPath }/files/${sessionScope.CUR_USER.resume.r_header}" alt="" style="width: 120px;">
            <img src="//www.lgstatic.com/www/static/mycenter/modules/common/img/tou_c3707c7.png" alt="">
        </div>
        <div class="header-bottom">
            <div class="bottom-name">
                <span>${sessionScope.CUR_USER.resume.r_name }</span>
            </div>
            <div class="bottom-introduce">
                <span></span>
            </div>
            <div class="bottom-info">
                <div class="info-top">
                    <span>${sessionScope.CUR_USER.resume.r_degree }</span>
                    <span>${sessionScope.CUR_USER.resume.sex }</span>
                    <span>${sessionScope.CUR_USER.resume.email }</span>
                    
                </div>
                <div class="info-bottom">
                    <span>${requestScope.resume.email }</span>
                </div>
            </div>
        </div>
    </div>
    <div class="re-content">
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>教育经历</span>
                <span></span>
            </div>
            <div class="item-content">
               <span>${sessionScope.CUR_USER.resume.edu_exp }</span>
            </div>
        </div>
        
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>工作经历</span>
                <span></span>
            </div>
            <div class="item-content">
               <span>${sessionScope.CUR_USER.resume.project_exp }</span>
            </div>
        </div>
        
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>自我描述</span>
                <span></span>
            </div>
            <div class="item-content">
               <span>${sessionScope.CUR_USER.resume.self_avalue }</span>
            </div>
        </div>
        
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>期望工作</span>
                <span></span>
            </div>
            <div class="item-content">
                <span>${sessionScope.CUR_USER.resume.r_category }</span>
            </div>
        </div>

    </div>
    <div class="re-bottom">
        <a href="${pageContext.request.contextPath }/user/changeResume">修改简历</a>
    </div>
</div>
</form>



<%@ include file="./footer.jsp" %>

</body>
</html>