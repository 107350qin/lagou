<%@page import="com.lagou.entity.User"%>
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
</head>
<body>

<%@ include file="./companyheader.jsp" %>

<!--拉勾页面主体content-->
<div class="lg-content">
    <div class="searchview">
        <div class="search-box">
        	<form action="<%=request.getContextPath()%>/user/userSearch" method="post">
            	<input type="text" class="search-box-ui" tabindex="1" maxlength="64" name="keyword" value="" placeholder="搜索职位、公司或地点">
           		<input type="submit" value="搜索" class="search-box-button">
           	</form>	
        </div>
        
    </div>
    <div class="content-body">
            <%-- 热门公司开始 --%>
            <ul class="content-body-clearfix-tabmodule" >
                <li class="clickli-current">热门公司</li>
            </ul>
            <ul class="lg-content-body-adcompany">
            <%
            ArrayList<Company> companies=(ArrayList<Company>)(request.getAttribute("companies"));
            %>
            	<c:forEach var="com" items="${companies}">
            
                	<li class="lg-content-body-adcompany-companyitem">
                    	<div class="lg-content-body-adcompany-companyitem-top">
                       	 	<p>
                        		<a href="${pageContext.request.contextPath}/user/seeCominfo?com_id=${com.getCom_id()}">
                        			<img src='<c:out value="${pageContext.request.contextPath }/files/${com.getCom_logo()}"></c:out>' alt="公司logo" width="80" height="80">
                        		</a>
                        	</p>
                        	<p class="company-name">
                        	<a href="${pageContext.request.contextPath}/user/seeCominfo?com_id=${com.getCom_id()}"><c:out value="${com.getCom_name()}"></c:out></a>
                        	</p>
                        	<p><c:out value="${com.getCom_type()[0]}"></c:out> / <c:out value="${com.getCom_type()[1]}"></c:out></p>
                        	<p><c:out value="${com.getSign()}"></c:out></p>
                    	</div>
                    	<div class="lg-content-body-adcompany-companyitem-bottom">
                        	<a href=""><span>9</span><span>面试评价</span></a>
                        	<a href=""><span><c:out value="${com.getPosistionNum()}"></c:out></span><span>在招职位</span></a>
                        	<a href=""><span>100%</span><span>简历处理率</span></a>
                    	</div>
                	</li>
           		</c:forEach>     
          </ul>
            <%-- 热门公司结束 --%>
            
            
            <div class="lg-content-body-linkbox">
                <dl>
                    <dt><span>友情链接</span></dt>
                    <dd><a href="">拉勾网</a>
                        <a href="">大鲲</a>
                        <a href="">招聘</a>
                        <a href="">app下载</a>
                        <a href="">拉勾云</a>
                        <a href="">中大网校</a>
                        <a href="">人人都是产品经理</a>
                        <a href="">教师招聘</a>
                        <a href="">新建人才网</a>
                        <a href="">研究报告</a>
                        <a href="">互联网的一些事</a>
                        <a href="">人才招聘网</a>
                        <a href="">找工作</a>
                        <a href="">字典</a>
                        <a href="">中国考试网</a>
                    </dd>
                    <span class="lg-content-body-linkbox-expansion">展开<i></i></span>
                </dl>
            </div>
        </div>
    </div>
</div>

<%@ include file="./companyfooter.jsp" %>

</body>
</html>