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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/posistionList.css">
</head>
<body>

<%@ include file="./header.jsp" %>

<!--拉勾页面主体content-->
<div class="lg-content">
    <div class="searchview">
        <div class="search-box">
        	<form action="<%=request.getContextPath()%>/user/search" method="post">
	            <input type="text" class="search-box-ui" tabindex="1" maxlength="64" name="keyword" value="${requestScope.keyword}">
	            <input type="submit" value="搜索" class="search-box-button">
	        </form>
        </div>
        <dl class="search-hotsearch">
            <dt>相关搜索：</dt>
            <dd><a href="#">Android</a></dd>
            <dd><a href="#">Java</a></dd>
            <dd><a href="#">UI设计师</a></dd>
            <dd><a href="#">PHP</a></dd>
            <dd><a href="#">销售经理</a></dd>
            <dd><a href="#">产品经理</a></dd>
            <dd><a href="#">C++</a></dd>
            <dd><a href="#">内容运营</a></dd>
            <dd><a href="#">拉勾APP</a></dd>
        </dl>
    </div>
   
<!--页content start-->


<div class="mainDiv">
<%
Pager<Position> pager=(Pager<Position>)(request.getAttribute("pager"));
%> 
<c:forEach var="position" items="${pager.getContent()}">
<div class="everyDiv">
<div class="left">
<a href="<%=request.getContextPath()%>/user/positionDetail?p_id=<c:out value="${position.getP_id()}"></c:out>" class="a"><c:out value="${position.getP_name()}"></c:out></a>
<span class="b">[<c:out value="${position.getCompany().getCom_address()}"></c:out>]</span>
<span class="c"><c:out value="${position.getP_release()}"></c:out>发布</span>

<p class="see1">
<span class="d"><c:out value="${position.getP_salary()}"></c:out></span>
<span class="e"><c:out value="${position.getP_experience()}"></c:out></span>
&nbsp;<span class="f"><c:out value="${position.getP_education()}"></c:out></span>
</p>
<p class="see2">
<c:forEach var="sm0" items="${position.getP_category()}">
<span class="g"><c:out value="${sm0}"></c:out></span>&nbsp;&nbsp;
</c:forEach>
</p>
</div>

<div class="right">
<a href="<%=request.getContextPath()%>/user/companyDetail?com_id=<c:out value="${position.getCompany().getCom_id()}"></c:out>"><span class="h"><c:out value="${position.getCompany().getCom_name()}"></c:out></span></a>
<p>
<c:forEach var="sm1" items="${position.getCompany().getCom_type()}">
<span class="i"><c:out value="${sm1}"></c:out></span>&nbsp;
</c:forEach>
</p>
<img class="j" alt="logo" src="${pageContext.request.contextPath }/files/<c:out value="${position.getCompany().getCom_logo()}"></c:out>">
</div>
</div>
</c:forEach>       
          
</div>
</div>
<%-- 分页楼 --%>
<div class="divide">
<c:if test="${ pager.getPageNow()>1}">
<a href="<%=request.getContextPath()%>/user/search?pageNow=<%=pager.getPageNow()-1%>&keyword=<%=request.getAttribute("keyword")%>"> 上一页 </a>
</c:if>
<c:forEach var="i" begin="1" end="${pager.getPageCount()}">
<c:if test="${i == pager.getPageNow()}">
<a style="background-color:#FFA5AC;"><c:out value="${i}"></c:out> </a>
</c:if>
<c:if test="${i != pager.getPageNow()}">
<a href="<%=request.getContextPath()%>/user/search?pageNow=<c:out value="${i}"></c:out>&keyword=<%=request.getAttribute("keyword")%>"> <c:out value="${i}"></c:out> </a>
</c:if>
</c:forEach>
<c:if test="${ pager.getPageNow()<pager.getPageCount()}">
<a href="<%=request.getContextPath()%>/user/search?pageNow=<%=pager.getPageNow()+1%>&keyword=<%=request.getAttribute("keyword")%>"> 下一页 </a>
</c:if>
<form action="<%=request.getContextPath()%>/user/positionJump">
<input type="hidden" name="keyword" value="<%=request.getAttribute("keyword")%>">
<input type="text" name="pageNow" style="background-color:#DEF5F0;color:#00B38A;">
<input type="submit" value="跳转" style="cursor:pointer;width:50px;">
</form>
</div>
<!--页content end-->


<%@ include file="./footer.jsp" %>
</body>
</html>