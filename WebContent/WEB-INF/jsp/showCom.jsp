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
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminShowCom.css">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
</head>
<body>

	<%--公司搜索框 --%>
	<div class="twoManager"><div><a href="${pageContext.request.contextPath}/admin/showCom">管理公司</a><a href="${pageContext.request.contextPath}/admin/showPosition">管理职位</a></div></div>
<div class="cancelP"><a href="<%= request.getContextPath()%>/admin/quit"><span id="quit">退出</span></a></div>
		<div class="abc">
		<form action="<%=request.getContextPath()%>/admin/searchCompany"
			method="post">
			<input class="search-box-ui" type="text" name="searchedCompany" placeholder="公司搜索">
			<input class="search-box-button" type="submit" value="搜索">
		</form>
		</div>
		
		
		
		




<div class="lg-content">
<div class="content-body">
<ul class="lg-content-body-adcompany">
            	<c:forEach var="com" items="${pagerCompany.content}">
                	<li class="lg-content-body-adcompany-companyitem" style="overflow:hidden">
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
                        	
                        	<a href=""><span><c:out value="${com.getPosistionNum()}"></c:out></span><span>在招职位</span></a>
                        	<%-- 审核 --%>
							<c:if test="${com.com_status == 1}">
								<a
									href="${pageContext.request.contextPath }/admin/reviewCompany?com_id=${com.com_id}&status=0"><span class="license">取消授权</span></a>
							</c:if>
							<c:if test="${com.com_status == 0}">
								<a
									href="${pageContext.request.contextPath }/admin/reviewCompany?com_id=${com.com_id}&status=1"><span class="license">授权</span></a>
							</c:if>
                    	</div>
                	</li>
           		</c:forEach>     
          </ul>
</div>
</div>


	
	<div class="viewPage">
		<c:forEach begin="1" end="${pagerCompany.pageCount}" var="now">

			<c:if test="${pagerCompany.pageNow!=now}">
				<a
					href="${pageContext.request.contextPath }/admin/showCom?currentCompanyPage=${now}&">${now}</a>
			</c:if>
			<c:if test="${pagerCompany.pageNow == now}">
				${now}
			</c:if>
		</c:forEach>
		总共${pagerCompany.pageCount}页
	</div>


</body>
</html>