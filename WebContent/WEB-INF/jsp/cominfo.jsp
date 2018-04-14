<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找工作-互联网招聘求职网-拉勾网</title>
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/register.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/Seeyourresume.css">
     <style type="text/css">
     .re-header a{
     top:20px;
     }
     .re-header {
    position: relative;
    width: 100%;
    height: 90px;
    background-color: #00b38a;
    margin-bottom:60px;
}
.re-content {
    width:700px;
    margin-top:258px;
}
.lg-Seeyourresume{
margin-top:-59px;
}
.info-top p{
height:22px;
}
.item-content{
padding:0px 20px;
line-height:30px;
text-align:left;
border-left:5px solid #00B38A;
}
     </style>
</head>
<body>
<!--拉勾头部header-->
<div class="re-header">
    <a href="${pageContext.request.contextPath}/user/index"></a>
</div>
<!--拉勾content-->
<form action="${pageContext.request.contextPath }/user/changeResume" method="post">
<div class="lg-Seeyourresume">
    <div class="re-header">
        <div class="header-top">

            <img  src="${pageContext.request.contextPath }/files/${sessionScope.com.com_logo}" alt="" style="width: 120px;">
            <img src="//www.lgstatic.com/www/static/mycenter/modules/common/img/tou_c3707c7.png" alt="">
        </div>
        <div class="header-bottom">
            <div class="bottom-name">
                <span>${sessionScope.com.com_name}</span>
                <h3 style="text-align:center;">${sessionScope.com.sign}</h3>
            </div>
            <div class="bottom-introduce">
            </div>
            <div class="bottom-info">
                <div class="info-top">
                    <p><span>${sessionScope.com.com_address}</span>
                    <span>[${sessionScope.com.com_reg}]</span> 
                    </p> 
                    
                     <p><c:forEach var="sm" items="${sessionScope.com.com_type}">
                    <span><c:out value="${sm}"></c:out></span>
                    </c:forEach> </p>
                    
                 <p>  <span>邮箱 : ${sessionScope.com.com_email}</span> </p>
                    
                    
                </div>
                <div class="info-bottom">
                    <span>${requestScope.resume.email }</span>
                </div>
            </div>
        </div>
    </div>
    <div class="re-content">
        <div class="content-item">
            <div class="item-head">
                <span></span>
                <span>公司简介</span>
                <span></span>
            </div>
            <div class="item-content">
               <span>${sessionScope.com.com_desc}</span>
            </div>
        </div>
    </div>
    <div class="re-bottom">
        <a href="${pageContext.request.contextPath }/com/changeComInfo">完善信息</a>
    </div>
</div>
</form>

</body>
</html>