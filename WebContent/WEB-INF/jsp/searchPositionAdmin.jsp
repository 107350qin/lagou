<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/content.css"> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminShowCom.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/searchCompanyAdmin.css">
</head>
<body>



	<%--职位搜索框 --%>
	<div class="abc" style="padding-bottom:50px;">
		<form action="<%=request.getContextPath()%>/admin/searchPosition"
			method="get">
			<input style="margin-left:20px;"class="search-box-ui" type="text" name="searchedPosition" placeholder="职位搜索">
			<input class="search-box-button" type="submit" value="搜索">
		</form>
	</div>



<ul id="content">
	<%-- 显示待审核的公司 --%>
	<c:forEach items="${posiPager.content}" var="position">
		<li class="view">
			<div class="top">
				<img src="${pageContext.request.contextPath }/files/${position.company.com_logo}">
				<div class="top_div">
					<div class="one">
						<div class="name">职位名称 : ${position.p_name }</div>
						<div class="address">发布公司  : ${position.company.com_name }</div>
						<div>发布时间 : ${position.p_release}</div>
					</div>
					<div class="two" style="width:250px;">
						<div>职位特点 : 
							<c:forEach items="${position.p_category}" var="category">
							<span class="category">${category}</span>
							</c:forEach>
						</div>
						<div>薪水 : ${position.p_salary}</div>
						<div>工作经验  : ${position.p_experience}</div>
						<div>要求学历  : ${position.p_education}</div>
						<div>招聘人数  : ${position.p_personNum}</div>
					</div>
					<div class="three">
						<%-- 审核 --%>
						<c:if test="${position.p_state == 1}">
							<a
								href="${pageContext.request.contextPath }/admin/reviewPosition?p_id=${position.p_id}&status=0">取消授权</a>
						</c:if>
						<c:if test="${position.p_state == 0}">
							<a
								href="${pageContext.request.contextPath }/admin/reviewPosition?p_id=${position.p_id}&status=1">授权</a>
						</c:if>
					</div>
				</div>
			</div>
			职位简介 :
				<div class="desc" style="padding-top:10px;">${position.p_desc }</div><br>
			
		</li>
	</c:forEach>
	</ul>

	
	
	<div align="right" style="width: 80%">
		<c:forEach begin="1" end="${posiPager.pageCount}" var="now">

			<c:if test="${posiPager.pageNow!=now}">
			${requestScope.KEY}
				<a
					href="${pageContext.request.contextPath }/admin/searchPosition?currentPage=${now}&searchedPosition=${requestScope.KEY}">${now}</a>
			</c:if>
			<c:if test="${pagerPosition.pageNow == now}">
				${now}
			</c:if>
		</c:forEach>
		总共${posiPager.pageCount}页
	</div>
	<a style="color:white;line-height:30px;text-align:center;border-radius:50px;display:block;margin-left:320px;width:60px;height:30px;background-color:#00B38A;" href="${pageContext.request.contextPath }/admin/showPosition">返回</a>
</body>
</html>