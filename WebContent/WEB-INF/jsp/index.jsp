<%@page import="com.lagou.entity.User"%>
<%@page import="com.lagou.entity.Company"%>
<%@page import="com.lagou.entity.Position"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拉勾网-专业的互联网招聘平台_找工作_招聘_人才网_求职</title>
<link rel="Shortcut Icon"
	href="<%=request.getContextPath()%>/img/favicon_faed927.ico">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/content.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jq/jquery-1.11.1.min.js"></script>
 <script type="text/javascript">
	

	function changeCity(value) {
		$
				.ajax({
					'url' : '/lagou/user/indexAjaxCom',
					'method' : 'post',
					'data' : {
						city : value
					},
					'success' : function(data) {
						var out = $(".lg-content-body-adcompany");
						out.empty();
						data = JSON.parse(data);
						for (var i = 0; i < data.data.length; i++) {
							var li = $('<li class="lg-content-body-adcompany-companyitem"></li>');
							out.append(li);
							var divOne = $('<div class="lg-content-body-adcompany-companyitem-top"></div>');
							var divTwo = $('<div class="lg-content-body-adcompany-companyitem-bottom"></div>');
							divOne.appendTo(li);
							divTwo.appendTo(li);

							var p1 = $('<p></p>');
							var a1 = $('<a></a>').attr(
									'href',
									'lagou/user/companyDetail?com_id='
											+ data.data[i].com_id);
							var img1 = $('<img></img>').css({
								width : '80px',
								height : '80px',
								alt : '公司logo'
							}).attr('src',
									'/lagou/files/' + data.data[i].com_logo);
							a1.append(img1);
							p1.append(a1);

							var p2 = $('<p></p>').attr('class', 'company-name');
							var a2 = $('<a></a>').attr('class', 'theName')
									.attr(
											'href',
											'/lagou/user/companyDetail?com_id='
													+ data.data[i].com_id)
									.text(data.data[i].com_name);
							p2.append(a2);

							var p3 = $('<p></p>');
							var span1 = $('<span></span>').text(
									data.data[i].com_type[0]);
							p3.append(span1);

							var p4 = $('<p></p>');
							var span2 = $('<span></span>').text(
									data.data[i].sign);
							p4.append(span2);

							divOne.append(p1, p2, p3, p4);

							var a7 = $('<a></a>');
							var span11 = $('<span></span>').text(9);
							var span12 = $('<span></span>').text('面试评价');
							a7.append(span11, span12);

							var a8 = $('<a></a>');
							var span21 = $('<span></span>').text(
									data.data[i].posistionNum);
							var span22 = $('<span></span>').text('在招职位');
							a8.append(span21, span22);

							var a9 = $('<a></a>');
							var span31 = $('<span></span>').text('100%');
							var span32 = $('<span></span>').text('简历处理率');
							a9.append(span31, span32);

							divTwo.append(a7, a8, a9);

						}
					}
				})

	}

	$(function() {
		
		
			changeCity("全国");
		
		var select = $('#select');
		select.change(function() {
			changeCity(select.val());
			$('#changeCity').text(select.val());
		})
	})
</script>  
</head>
<body>

	<%@ include file="./header.jsp"%>

	<!--拉勾页面主体content-->
	<div class="lg-content">
		<div class="searchview">
			<div class="search-box">
				<form action="<%=request.getContextPath()%>/user/search"
					method="post">
					<input type="text" class="search-box-ui" tabindex="1"
						maxlength="64" name="keyword" value="" placeholder="搜索职位、公司或地点">
					<input type="submit" value="搜索" class="search-box-button">
				</form>
			</div>
			<dl class="search-hotsearch">
				<dt>热门搜索：</dt>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=Android">Android</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=Java">Java</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=UI设计师">UI设计师</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=PHP">PHP</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=销售经理">销售经理</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=产品经理">产品经理</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=C++">C++</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=内容运营">内容运营</a>
				</dd>
				<dd>
					<a
						href="${pageContext.request.contextPath}/user/search?keyword=拉勾APP">拉勾APP</a>
				</dd>
			</dl>
		</div>
		<div class="content-body">
			<div class="content-body-clearfix">
				<div class="content-body-clearfix-sidebar">
					<div class="content-body-clearfix-sidebar-mainNavs"
						id="content-body-clearfix-sidebar-mainNavs">




						<c:forEach var="link" items="${requestScope.links}">
							<div class="menu-box">
								<div class="menu-box-main">

									<div class="menu-box-main-list">
										<h2>${link.ml_title}</h2>

										<c:forEach var="linkLeft" items="${link.ml_link}">
											<a
												href="${pageContext.request.contextPath}/user/search?keyword=${linkLeft}">${linkLeft}</a>
										</c:forEach>
										<i></i>
									</div>


									<div class="menu-box-main-sub">
										<c:forEach var="oneRightDl" items="${link.list}">
											<dl>
												<dt>
													<span>${oneRightDl.mr_title}</span>
												</dt>
												<dd>
													<c:forEach var="oneRightDl_links"
														items="${oneRightDl.mr_link}">
														<a
															href="${pageContext.request.contextPath}/user/search?keyword=${oneRightDl_links}"
															class="curr">${oneRightDl_links}</a>
													</c:forEach>
												</dd>
											</dl>
										</c:forEach>
									</div>

								</div>
							</div>

						</c:forEach>







					</div>

				</div>
				<div class="content-body-clearfix-home-banner">
					<div class="content-body-clearfix-home-banner-testSlider"></div>
				</div>

				<%-- 热门职位开始 --%>
				<ul class="content-body-clearfix-tabmodule"
					id="content-body-clearfix-tabmodule">
					<li class="clickli-current">热门职位</li>
				</ul>
				<div class="content-body-clearfix-tabmodulelist">
					<div class="content-body-clearfix-tabmodulelist-hotjob">

						<ol>
							<!-- 列表显示9个推荐职位 -->
							<%
								ArrayList<Position> positions = (ArrayList<Position>) (request.getAttribute("positions"));
							%>
							<c:forEach var="position" items="${positions}">
								<li>
									<div class="content-body-clearfix-tabmodulelist-hotjob-litop">
										<div
											class="content-body-clearfix-tabmodulelist-hotjob-litop-one">
											<span> <a class="theName"
												href="${pageContext.request.contextPath}/user/positionDetail?p_id=${position.getP_id()}"><c:out
														value="${position.getP_name()}"></c:out></a>
											</span><span>[<c:out value="${position.getP_release()}"></c:out>]
											</span><span></span><span><c:out
													value="${position.getP_salary()}"></c:out></span>
										</div>
										<div
											class="content-body-clearfix-tabmodulelist-hotjob-litop-two">
											<span><c:out value="${position.getP_experience()}"></c:out>
												/ <c:out value="${position.getP_education()}"></c:out> </span>
										</div>
										<div
											class="content-body-clearfix-tabmodulelist-hotjob-litop-three">
											<c:forEach var="sm0" items="${ position.getP_category()}">
												<span> <c:out value="${sm0}"></c:out>
												</span>
											</c:forEach>
										</div>
									</div>
									<div class="content-body-clearfix-tabmodulelist-hotjob-libtm">
										<a
											href="${pageContext.request.contextPath}/user/companyDetail?com_id=${position.getCompany().getCom_id()}">
											<img
											src="<c:out value="${pageContext.request.contextPath }/files/${position.getCompany().getCom_logo()}"></c:out>"
											alt="图片" width="40" height="40">
										</a>
										<div
											class="content-body-clearfix-tabmodulelist-hotjob-libtm-bottom">
											<span><a class="theName"
												href="${pageContext.request.contextPath}/user/companyDetail?com_id=${position.getCompany().getCom_id()}"><c:out
														value="${position.getCompany().getCom_name()}"></c:out></a></span> <span>
												<c:forEach var="sm1"
													items="${position.getCompany().getCom_type()}">
													<c:out value="${sm1}"></c:out> /
	                                	</c:forEach> <c:out
													value="${position.getCompany().getCom_address()}"></c:out>
											</span>
										</div>
									</div>
								</li>
							</c:forEach>
						</ol>

						<a href="${pageContext.request.contextPath}/user/search?keyword= "
							class="content-body-clearfix-tabmodulelist-more">查看更多</a>
					</div>
				</div>
				<%-- 热门职位结束 --%>



				<%-- 热门公司开始 --%>
				<ul class="content-body-clearfix-tabmodule">
					<li class="clickli-current">热门公司</li>
				</ul>
				<ul class="lg-content-body-adcompany">


					<%-- ajax已被取代     <%
            ArrayList<Company> companies=(ArrayList<Company>)(request.getAttribute("companies"));
            %>
            	<c:forEach var="com" items="${companies}">
            
                	<li class="lg-content-body-adcompany-companyitem">
                    	<div class="lg-content-body-adcompany-companyitem-top">
                       	 	<p>
                        		<a href="${pageContext.request.contextPath}/user/companyDetail?com_id=${com.getCom_id()}">
                        			<img src='<c:out value="${pageContext.request.contextPath }/files/${com.getCom_logo()}"></c:out>' alt="公司logo" width="80" height="80">
                        		</a>
                        	</p>
                        	<p class="company-name">
                        	<a class="theName" href="${pageContext.request.contextPath}/user/companyDetail?com_id=${com.getCom_id()}"><c:out value="${com.getCom_name()}"></c:out></a>
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
           		</c:forEach>  --%>

				</ul>
				<%-- 热门公司结束 --%>


				<a
					href="${pageContext.request.contextPath}/user/userSearch?keyword= "
					class="content-body-clearfix-tabmodulelist-more">查看更多</a>
				<div class="lg-content-body-linkbox">
					<dl>
						<dt>
							<span>友情链接</span>
						</dt>
						<dd>
							<a href="">拉勾网</a> <a href="">大鲲</a> <a href="">招聘</a> <a href="">app下载</a>
							<a href="">拉勾云</a> <a href="">中大网校</a> <a href="">人人都是产品经理</a> <a
								href="">教师招聘</a> <a href="">新建人才网</a> <a href="">研究报告</a> <a
								href="">互联网的一些事</a> <a href="">人才招聘网</a> <a href="">找工作</a> <a
								href="">字典</a> <a href="">中国考试网</a>
						</dd>
						<span class="lg-content-body-linkbox-expansion">展开<i></i></span>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="./footer.jsp"%>

</body>
</html>