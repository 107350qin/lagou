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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/posistionList.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.slider.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/companyDetail_positions.css">
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
            <div class="lg-tbar-l">
                <a class="lg-tbar-l-lgapp" href="https://www.lagou.com/app/download.html" target="_blank">拉勾APP</a>
                <a class="lg-tbar-l-lgos" href="https://easy.lagou.com/dashboard/index.htm?from=c_index"
                   target="_blank">进入企业版</a>
            </div>
            <ul class="lg-tbar-r">
                <li><a href="" class="lg-tbar-r-line">登录</a></li>
                <li><a href="">注册</a></li>
            </ul>
        </div>
    </div>
    <div class="lg-tnav">
        <div class="inner">
            <div class="lg-tnav-l">
                <a href="<%=request.getContextPath()%>/user/index" class="lg-logo"><h1>
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

<%
Company com=(Company)(request.getAttribute("companyClicked"));
Pager<Position> pager=(Pager<Position>)request.getAttribute("pager");
%>
<div class="top">
	<div class="top_box">
		<div class="top_box_img">
			<img src='${pageContext.request.contextPath }/files/<%=com.getCom_logo() %>' alt="公司logo">
		</div>
		<div class="top_box_main">
			<a> <%=com.getCom_name() %> </a>
			<p class="firstP"> <%=com.getSign() %> </p>
			<ul>
			<li><p>招聘职位</p> <span><%=com.getPosistionNum() %></span> 个</li>
			<li><p>注册时间</p> <span><%=com.getCom_reg() %></span></li>
			<li><p>公司邮箱</p> <span><%=com.getCom_email() %></span></li>
			<li>
				<p>公司类型</p> 
				<span>
				<%for(String item:com.getCom_type()) 
				out.print(item+"  .  ");
				%>
				</span>
			</li>
			</ul>
			
		</div>
		
	</div>
	<div class="twoLinks">
		<a href="<%=request.getContextPath()%>/user/companyDetail?com_id=<%=com.getCom_id()%>">公司主页</a>
		<a href="<%=request.getContextPath()%>/user/companyDetail_positions?com_id=<%=com.getCom_id()%>">招聘职位</a>
	</div>
</div>


<%-- 该公司发布的职位列表 --%>
<div class="positions">
<c:forEach var="position" items="${pager.getContent()}">
<div>
<p class="p1">
	<span><a href="<%=request.getContextPath()%>/user/positionDetail?p_id=<c:out value="${position.getP_id()}"></c:out>"><c:out value="${position.getP_name()}"></c:out></a></span>
	<span> [<%=com.getCom_address() %>] </span>
	<span><c:out value="${position.getP_release()}"></c:out></span>
</p>
<p class="p2">
	<span><c:out value="${position.getP_salary()}"></c:out></span>
	<span><c:out value="${position.getP_experience()}"></c:out></span>/
	<span><c:out value="${position.getP_education()}"></c:out></span>
</p>
</div>
</c:forEach>
</div>




<%-- 分页楼 --%>
<div class="divide">
<c:if test="${ pager.getPageNow()>1}">
<a href="<%=request.getContextPath()%>/user/companyDetail_positions?pageNow=<%=pager.getPageNow()-1%>&com_id=<%=com.getCom_id()%>"> 上一页 </a>
</c:if>
<c:forEach var="i" begin="1" end="${pager.getPageCount()}">
<c:if test="${i == pager.getPageNow()}">
<a style="background-color:#FFA5AC;"><c:out value="${i}"></c:out> </a>
</c:if>
<c:if test="${i != pager.getPageNow()}">
<a href="<%=request.getContextPath()%>/user/companyDetail_positions?pageNow=<c:out value="${i}"></c:out>&com_id=<%=com.getCom_id()%>"> <c:out value="${i}"></c:out> </a>
</c:if>
</c:forEach>
<c:if test="${ pager.getPageNow()<pager.getPageCount()}">
<a href="<%=request.getContextPath()%>/user/companyDetail_positions?pageNow=<%=pager.getPageNow()+1%>&com_id=<%=com.getCom_id()%>"> 下一页 </a>
</c:if>
<form action="<%=request.getContextPath()%>/user/companyDetail_positions_jump">
<input type="hidden" name="com_id" value="<%=com.getCom_id()%>">
<input type="text" name="pageNow" style="background-color:#DEF5F0;color:#00B38A;">
<input type="submit" value="跳转" style="cursor:pointer;width:50px;">
</form>
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
<!--登陆注册工具栏-->
<div class="lg-logintoolbar">
    <div class="lg-logintoolbar-center">
        <em></em>
        <p><span>在拉勾，发现新的职业机会</span>
            <span>298796公司 &nbsp &nbsp &nbsp <i></i> &nbsp &nbsp &nbsp 3943965职位</span>
        </p>
        <div class="lg-logintoolbar-center-right">
            <a href="page/Land.html">登陆</a>
            <a href="page/register.html">注册</a>

        </div>
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