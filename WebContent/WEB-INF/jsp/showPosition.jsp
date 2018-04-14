<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/content.css"> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminShowPosition.css">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
</head>
<body>


	<div class="twoManager"><div><a href="${pageContext.request.contextPath}/admin/showCom">管理公司</a><a href="${pageContext.request.contextPath}/admin/showPosition">管理职位</a></div></div>
<div class="cancelP"><a href="<%= request.getContextPath()%>/admin/quit"><span id="quit">退出</span></a></div>
		<div class="abc"><form action="<%=request.getContextPath()%>/admin/searchPosition"
			method="post">
			<input class="search-box-ui"  type="text" name="searchedPosition" placeholder="职位搜索">
			<input class="search-box-button"  type="submit" value="搜索">
		</form>
		</div>


<div class="lg-content">
<div class="content-body">
<ul class="lg-content-body-adcompany">
<%-- 显示待审核的职位 --%>
	<c:forEach items="${pagerPosition.content}" var="positon">
		<li class="lg-content-body-adcompany-companyitem">
			<div class="lg-content-body-adcompany-companyitem-top">
				<p class="company-name">
					<a href="${pageContext.request.contextPath}/user/positionDetail?p_id=${positon.p_id}">${positon.p_name }</a>
				</p><br>
				<p>
					<c:forEach items="${positon.p_category}" var="category">
						<span class="category">${category}</span>
					</c:forEach>
				</p>
				<p>发布时间  : ${positon.p_release}</p><br>
				<p>薪资  : ${positon.p_salary}</p><br>
				<p>工作经历  : ${positon.p_experience}</p><br>
				<p>学历要求  : ${positon.p_education}</p><br>
				<p>公司名字  : ${positon.company.com_name}</p><br>
				<p>招聘人数  : ${positon.p_personNum}</p><br>
				<p style="height:20px;overflow:hidden">职位描述  : ${positon.p_desc}</p><br>
			</div>
			<div class="lg-content-body-adcompany-companyitem-bottom">
			<%-- 审核 --%>
			<c:if test="${positon.p_state == 1}">
				<a style="margin-top:7px;color:00B38A;"
					href="${pageContext.request.contextPath }/admin/reviewPosition?p_id=${positon.p_id}&status=0">取消授权</a>
			</c:if>
			<c:if test="${positon.p_state == 0}">
				<a style="margin-top:7px;color:00B38A;"
					href="${pageContext.request.contextPath }/admin/reviewPosition?p_id=${positon.p_id}&status=1">授权</a>
			</c:if>	
			</div>
		</li>
	</c:forEach>
	          </ul>
</div>
</div>
	
	
	
		<div class="viewPage">
		<c:forEach begin="1" end="${pagerPosition.pageCount}" var="now">

			<c:if test="${pagerPosition.pageNow != now}">
				<a
					href="${pageContext.request.contextPath }/admin/showPosition?currentPositionPage=${now}&currentCompanyPage=${pagerCompany.pageNow}">${now}</a>
			</c:if>
			<c:if test="${pagerPosition.pageNow == now}">
				${now}
			</c:if>
		</c:forEach>
		总共${pagerPosition.pageCount}页
	</div>





</body>
</html>