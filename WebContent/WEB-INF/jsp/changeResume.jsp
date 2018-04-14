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
<form action="${pageContext.request.contextPath }/user/changeResumeDo" method="post"  enctype ="multipart/form-data">
<div class="lg-Seeyourresume">
    <div class="re-header">
        <div class="header-top">
            <img  src="${pageContext.request.contextPath }/files/<%=((User)session.getAttribute("CUR_USER")).getResume().getR_header() %>" alt="" style="width: 120px;">
            <img src="//www.lgstatic.com/www/static/mycenter/modules/common/img/tou_c3707c7.png" alt="">
        </div>
        <div class="header-bottom">
           
            <div class="bottom-info">
                <div class="info-top">
                <p>头 &nbsp;&nbsp; &nbsp;&nbsp;像 <input class="file" type="file" name="r_header"></p>
                <p>名 &nbsp;&nbsp; &nbsp; &nbsp;字 <input class="ff" type="text" name="r_name" value="${sessionScope.CUR_USER.resume.r_name }"></p>
                <p>学  &nbsp;&nbsp; &nbsp;&nbsp;历 <input class="ff" type="text" name="r_degree" value="${sessionScope.CUR_USER.resume.r_degree }"></p>
                <p>性  &nbsp;&nbsp; &nbsp;&nbsp;别 <input class="ff" type="text" name="sex" value="${sessionScope.CUR_USER.resume.sex }"></p>
                <p>邮  &nbsp;&nbsp; &nbsp;&nbsp;箱 <input class="ff" type="text" name="email" value="${sessionScope.CUR_USER.resume.email }"></p>
                <p>求职意向 <input class="ff" type="text" name="r_category" value="${sessionScope.CUR_USER.resume.r_category }"></p>
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
               <textarea name="edu_exp">${sessionScope.CUR_USER.resume.edu_exp }</textarea>
            </div>
        </div>
        
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>工作经历</span>
                <span></span>
            </div>
            <div class="item-content">
               <textarea name="project_exp">${sessionScope.CUR_USER.resume.project_exp }</textarea>
            </div>
        </div>
        
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>自我描述</span>
                <span></span>
            </div>
            <div class="item-content">
               <textarea name="self_avalue">${sessionScope.CUR_USER.resume.self_avalue }</textarea>
            </div>
        </div>
        
    

    </div>
    <div class="re-bottom">
    
        <input type="submit" value="确认修改"> 
        <a href="${pageContext.request.contextPath }/user/resume">取消修改</a>
    </div>
</div>
</form>




<%@ include file="./footer.jsp" %>

</body>
</html>