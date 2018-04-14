<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找工作-互联网招聘求职网-拉勾网</title>
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/postJobInterface.css">
    <script src="${pageContext.request.contextPath }/jq/jquery-1.11.1.min.js"></script>
</head>
<body>
<!--拉勾头部header-->
<div class="re-header">
    <a href="http://www.lagou.com"></a>
</div>
<!--拉勾content-->
<div class="re-content-box">
     <h3>发布职位</h3>
    <form action="${pageContext.request.contextPath }/com/postJobInterfaceDo" method='post'>
         <input type="text"  name="pname" placeholder="职位名称"><br/>

         <input type="text"  name="pdesc" placeholder="职位简介"><br/>

         <input type="text"  name="psalary" placeholder="职位薪资"><br/>

         <input type="text"  name="pexperience" placeholder="要求经验"><br/>

         <input type="text"  name="peducation" placeholder="要求学历"><br/>

         <input type="text"  name="ppersonNum" placeholder="招聘人数"><br/>

         <input type="text"  name="pcategory" placeholder="职位类型"><br/>
        <input type="submit"value="发布">
    </form>
</div>

<script>
    $(function () {
        $(".re-content-box-left-head-ul > li").on("click",function () {
            $(this).addClass("li-active").siblings().removeClass("li-active");
            if($(".re-content-box-left-head-triangle").css("left") == "0px"){
                $(".re-content-box-left-head-triangle").css("left","145px");
            }else {
                $(".re-content-box-left-head-triangle").css("left","0");
            }
        })
    })
</script>
</body>
</html>