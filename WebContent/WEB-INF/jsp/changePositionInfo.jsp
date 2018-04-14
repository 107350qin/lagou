<%@page import="com.lagou.entity.Position"%>
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

<!--职位修改信息的页面-->
<form action="${pageContext.request.contextPath }/com/changePositionInfoDo" method="post">
<div class="lg-Seeyourresume">
    <div class="re-header">
        <div class="header-top">
            <img  src="${pageContext.request.contextPath }/files/${requestScope.position.company.com_logo}" alt="" style="width: 120px;">
            <img src="//www.lgstatic.com/www/static/mycenter/modules/common/img/tou_c3707c7.png" alt="">
        </div>
        <div class="header-bottom">
           
            <div class="bottom-info">
                <div class="info-top">
                <p><input type="hidden" name="p_id" value="${requestScope.position.p_id}"></p>
                <p>职位名字  <input class="ff" type="text" name="p_name" value="${requestScope.position.p_name}"></p>
                <p>发布时间  <input class="ff" type="text" name="p_release" value="${requestScope.position.p_release}"></p>
                <p>职位薪水  <input class="ff" type="text" name="p_salary" value="${requestScope.position.p_salary}"></p>
                <p>要求经验  <input class="ff" type="text" name="p_experience" value="${requestScope.position.p_experience}"></p>
                <p>要求学历  <input class="ff" type="text" name="p_education" value="${requestScope.position.p_education}"></p>
                <p>招收人数  <input class="ff" type="text" name="p_personNum" value="${requestScope.position.p_personNum}"></p>
                
                <p> 职位特点<input class="ff" type="text" name="p_category" value="<%=StringUtil.arrayToString(((Position)(request.getAttribute("position"))).getP_category()) %>">
               </p>
                
                </div>
            </div>
        </div>
    </div>
    <div class="re-content">
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>职位简介</span>
                <span></span>
            </div>
            <div class="item-content">
               <textarea name="p_desc" style="line-height:25px;">${requestScope.position.p_desc}</textarea>
            </div>
        </div>
  
    </div>
    <div class="re-bottom">
    
        <input type="submit" value="确认修改"> 
        <a href="${pageContext.request.contextPath }/com/seePositionDetail?p_id=${requestScope.position.p_id}">取消修改</a>
    </div>
</div>
</form>




<%@ include file="./footer.jsp" %>

</body>
</html>