<%@page import="com.lagou.util.StringUtil"%>
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
                <li><a href="${pageContext.request.contextPath }/user/index" class="current">首页</a></li>
                <li><a href="${pageContext.request.contextPath }/user/searchCompany">公司</a></li>
                <li><a href="#">校园</a></li>
                <li><a href="#">言职</a></li>
                <li><a href="#">大鲲</a></li>
            </ul>
        </div>
    </div>
</div>
<!--拉勾页面主体content-->
<div class="lg-content">
    <div class="searchview">
        <div class="search-box">
        	<form action="<%=request.getContextPath()%>/user/search" method="get">
	            <input type="text" class="search-box-ui" tabindex="1" maxlength="64" name="keyword" value="<%=request.getAttribute("keyword")%>">
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


<div class="mainDiv" style="margin:0 auto;width:75%;">
<%
Pager<Position> pager=(Pager<Position>)(request.getAttribute("pager"));
%> 
 <ul class="lg-content-body-adcompany">
            	<c:forEach var="com" items="${requestScope.pager.content}">
            
                	<li class="lg-content-body-adcompany-companyitem">
                    	<div class="lg-content-body-adcompany-companyitem-top">
                       	 	<p>
                        		<a href="${pageContext.request.contextPath}/user/seeCominfo?com_id=${com.com_id}">
                        			<img src='<c:out value="${pageContext.request.contextPath }/files/${com.com_logo}"></c:out>' alt="公司logo" width="80" height="80">
                        		</a>
                        	</p>
                        	<p class="company-name">
                        	<a href="${pageContext.request.contextPath}/user/seeCominfo?com_id=${com.com_id}"><c:out value="${com.com_name}"></c:out></a>
                        	</p>
                        	<p><c:out value="${com.com_type[0]}"></c:out> / <c:out value="${com.com_type[1]}"></c:out></p>
                        	<p><c:out value="${com.sign}"></c:out></p>
                    	</div>
                    	<div class="lg-content-body-adcompany-companyitem-bottom">
                        	<a href=""><span>9</span><span>面试评价</span></a>
                        	<a href=""><span><c:out value="${com.posistionNum}"></c:out></span><span>在招职位</span></a>
                        	<a href=""><span>100%</span><span>简历处理率</span></a>
                    	</div>
                	</li>
           		</c:forEach>     
          </ul>     
          
</div>

<%-- 分页楼 --%>
<div class="divide">
<c:if test="${ pager.getPageNow()>1}">
<a href="<%=request.getContextPath()%>/user/userSearch?pageNow=<%=pager.getPageNow()-1%>&keyword=<%=request.getAttribute("keyword")%>"> 上一页 </a>
</c:if>
<c:forEach var="i" begin="1" end="${pager.getPageCount()}">
<c:if test="${i == pager.getPageNow()}">
<a style="background-color:#FFA5AC;"><c:out value="${i}"></c:out> </a>
</c:if>
<c:if test="${i != pager.getPageNow()}">
<a href="<%=request.getContextPath()%>/user/userSearch?pageNow=<c:out value="${i}"></c:out>&keyword=<%=request.getAttribute("keyword")%>"> <c:out value="${i}"></c:out> </a>
</c:if>
</c:forEach>
<c:if test="${ pager.getPageNow()<pager.getPageCount()}">
<a href="<%=request.getContextPath()%>/user/userSearch?pageNow=<%=pager.getPageNow()+1%>&keyword=<%=request.getAttribute("keyword")%>"> 下一页 </a>
</c:if>
<form action="<%=request.getContextPath()%>/user/userSearch">
<input type="hidden" name="keyword" value="${requestScope.keyword}">
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