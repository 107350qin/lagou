<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/content.css"> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminShowCom.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/searchCompanyAdmin.css">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
</head>
<body>

	<%--公司搜索框 --%>
	<div class="abc" style="padding-bottom:25px;">
		<form action="<%=request.getContextPath()%>/admin/searchCompany"
			method="get">
			<input class="search-box-ui"  type="text" name="searchedCompany" placeholder="公司搜索">
			<input class="search-box-button" type="submit" value="搜索">
		</form>
	</div><br>

<ul id="content">
	<%-- 显示待审核的公司 --%>
	<c:forEach items="${comPager.content}" var="company">
		<li class="view">
			<div class="top">
				<img src="${pageContext.request.contextPath }/files/${company.com_logo}">
				<div class="top_div">
					<div class="one">
						<div class="name">公司名字 : ${company.com_name }</div>
						<div class="address">公司地址 : ${company.com_address }</div>
						<div>注册时间 : ${company.com_reg }</div>
					</div>
					<div class="two">
						<div>公司特点 : 
							<c:forEach var="mm" items="${company.com_type }">
							${mm}
							</c:forEach>
						</div>
						<div>邮箱 : ${company.com_email }</div>
						<div>标语  : ${company.sign }</div>
					</div>
					<div class="three">
						<%-- 审核 --%>
						<c:if test="${company.com_status == 1}">
							<a class="thaA"
								href="${pageContext.request.contextPath }/admin/reviewCompany?com_id=${company.com_id}&status=0">取消授权</a>
						</c:if>
						<c:if test="${company.com_status == 0}">
							<a class="thaA"
								href="${pageContext.request.contextPath }/admin/reviewCompany?com_id=${company.com_id}&status=1">授权</a>
						</c:if>
					</div>
				</div>
			</div>
			公司简介 :
				<p class="desc">${company.com_desc }</p>
			
		</li>
	</c:forEach>
	</ul>
	<div align="right" style="width: 80%">
		<c:forEach begin="1" end="${comPager.pageCount}" var="now">

			<c:if test="${comPager.pageNow!=now}">
			
				<a
					href="${pageContext.request.contextPath }/admin/searchCompany?currentPage=${now}&searchedCompany=${sessionScope.COM_KEY}">${now}</a>
			</c:if>
		</c:forEach>
		总共${comPager.pageCount}页
	</div>
	<a style="color:white;line-height:30px;text-align:center;border-radius:50px;display:block;margin-left:320px;width:60px;height:30px;background-color:#00B38A;" href="${pageContext.request.contextPath }/admin/showCom">返回</a>
</body>
</html>