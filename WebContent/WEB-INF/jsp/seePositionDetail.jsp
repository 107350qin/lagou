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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/other.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.slider.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/positionClicked.css">
    <script src="<%=request.getContextPath()%>/jq/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery.slider.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-header.js"></script>
    <script src="<%=request.getContextPath()%>/js/lg-content.js"></script>
    <script src="<%=request.getContextPath()%>/js/other.js"></script>
</head>
<body>
<!--拉勾header-->
<div class="lg-header">
    <div class="lg-tbar">
        <div class="inner">
        </div>
    </div>
    <div class="lg-tnav">
        <div class="inner">
            <div class="lg-tnav-l">
                <a href="<%=request.getContextPath()%>/user/index" class="lg-logo">
                <h1>
                    拉勾网
                </h1></a>
                <div class="lg-tnav-l-suggestCity">
                    <strong>成都站</strong>
                    <em class="lg-tnav-l-suggestCity-btn">[切换城市]</em>
                </div>
            </div>
            <ul class="lg-tnav-wrap" id="lg_tnav_wrap">
                <li><a href="#" class="current">首页</a></li>
                <li><a href="#">公司</a></li>
                <li><a href="#">校园</a></li>
                <li><a href="#">言职</a></li>
                <li><a href="#">大鲲</a></li>
            </ul>
        </div>
    </div>
</div>
   
<!--页content start-->


<div class="top">
	<div class="top_box">
		<div class="left">
			<p class="p1"><c:out value="${requestScope.positionClicked.company.com_name}"></c:out></p>
			<p class="p2"><c:out value="${requestScope.positionClicked.p_name}"></c:out></p>
			<p class="p3">
				<span><c:out value="${requestScope.positionClicked.p_salary}"></c:out></span>&nbsp;&nbsp;/
				<c:out value="${requestScope.positionClicked.company.com_address}"></c:out>/
				<c:out value="${requestScope.positionClicked.p_experience}"></c:out>/
				<c:out value="${requestScope.positionClicked.p_education}"></c:out>
			</p>
			<p class="p4">
				<c:forEach var="sm" items="${requestScope.positionClicked.p_category}">
				<span><c:out value="${sm}"></c:out></span>
				</c:forEach>
			</p>
			<p class="p5"><c:out value="${requestScope.positionClicked.p_release}"></c:out></p>
			
		</div>
		
		<div class="right">
			<a class="a1" href="${pageContext.request.contextPath}/com/changePositionInfo?p_id=${requestScope.positionClicked.p_id}">修改职位信息</a>
			<a class="a2" href="${pageContext.request.contextPath}/com/revocationJob?p_id=${requestScope.positionClicked.p_id}">撤销职位</a>
		</div>
		
	</div>
</div>

<div class="middle">
	<div class="middle_l">
		<h3>职位描述</h3>
		<p><c:out value="${requestScope.positionClicked.p_desc}"></c:out></p>
	</div>
	<div class="middle_r">
		<img src="<c:out value="${pageContext.request.contextPath }/files/${requestScope.positionClicked.company.com_logo}"></c:out>"/>
		<h3><c:out value="${requestScope.positionClicked.company.com_name}"></c:out></h3>
		<p><c:out value="${requestScope.positionClicked.company.com_address}"></c:out></p>
		
		<p>
			<c:forEach var="am" items="${requestScope.positionClicked.company.com_type}">
			<c:out value="${am}"></c:out>
			</c:forEach>
		</p>
		<p>邮箱:<c:out value="${requestScope.positionClicked.company.com_email}"></c:out></p>
	</div>
</div>




























<!--页content end-->










<!--拉勾页面脚部footer-->
<div class="lg-footer">
    <i class="lg-footer-icon"></i>
    <div class="lg-footer-inner">
        <a href="">拉勾APP <i></i></a>
        <a href="">拉勾微博</a>
        <a href="">拉勾微信</a>
        <a href="">拉勾小程序</a>
        <a href="">帮助中心</a>
        <a href="">联系我们</a>
        <a href="">招聘解决方案</a>
        <span>服务热线 : <em>4006-2828-35 (9:00 - 18:00)</em></span>
    </div>
    <div class="lg-footer-copyright">
        <span>@2018 Lagou 京ICP备14023790号-2 京公网安备 11010802024043号</span>
    </div>
</div>

<!--页面小部件-->
<a  class="backtop"></a>
<div class="product-fk">
    <div class="product-fk-icon"></div>
    <span>我要反馈</span>
</div>
 
<script>
    window.onload = function () {
        /*调用banner nav tab函数*/
        tab("lg_tnav_wrap");
        //调用slider框架
        $('.content-body-clearfix-home-banner-testSlider').slider({
            originType: 'tuoyuan'
        });
        function goTop(obj) {
            $(window).scroll(function() {
                var $scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop; //兼容浏览器
                if($scrollTop > 0) { //滚动高度可调
                    $(obj).show();
                } else {
                    $(obj).hide();
                };

            });
            $(obj).on("click",function () {
                $("html,body").animate({scrollTop:0},800);
            })
        }
        goTop(".backtop");

    };


</script>

</body>
</html>