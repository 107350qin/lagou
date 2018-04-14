<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司主页</title>
  <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/img/favicon_faed927.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/content.css">
    <script src="${pageContext.request.contextPath }/js/lg-header.js"></script>
    <style type="text/css">
    .lg-content .content-body-clearfix-tabmodulelist-hotjob-libtm{
    margin-top:10px;
    }
    </style>
</head>
<body>
<div class="lg-tbar">
        <div class="inner">
            <div class="lg-tbar-l">
                <a class="lg-tbar-l-lgapp" href="https://www.lagou.com/app/download.html" target="_blank">拉勾APP</a>
                <a class="lg-tbar-l-lgos" href="${pageContext.request.contextPath }/com/cominfo"
                   target="_blank">公司信息</a>
                <a class="lg-tbar-l-lgos" href="${pageContext.request.contextPath }/com/postJob"
                   target="_blank">发布职位</a>
                <a class="lg-tbar-l-lgos" href="${pageContext.request.contextPath }/com/cancel"
                   target="_blank">退出登陆</a>
            </div>
        </div>
    </div>
<div class="lg-content">
    <div class="searchview">
        <div class="search-box">
            <input type="text" class="search-box-ui" tabindex="1" maxlength="64" name="KeyCode" value="" placeholder="搜索简历！">
            <input type="submit" value="搜索" class="search-box-button">
        </div>
          </div>
    <div class="content-body">
        <div class="content-body-clearfix">
            <ul class="content-body-clearfix-tabmodule" id="content-body-clearfix-tabmodule">
                <li class="clickli-current">已发布职位</li>
            </ul>
            <div class="content-body-clearfix-tabmodulelist">
                <div class="content-body-clearfix-tabmodulelist-hotjob">
                    <ol>
                       <c:forEach items="${requestScope.position}" var="position">
                        <li>
                            <div class="content-body-clearfix-tabmodulelist-hotjob-litop">
                                <div class="content-body-clearfix-tabmodulelist-hotjob-litop-one">
                                    <span><a href="${pageContext.request.contextPath}/com/seePositionDetail?p_id=${position.p_id}">${position.p_name }</a></span>
                                    <span>[${position.p_release  }发布]</span>
                                    <span></span>
                                    <span>${position.p_salary}</span>
                                </div>
                                <div class="content-body-clearfix-tabmodulelist-hotjob-litop-two">
                                    <span>经验${position.p_experience  }年 / ${position.p_education   }</span>
                                </div>
                                <div class="content-body-clearfix-tabmodulelist-hotjob-litop-three">
                                	<c:forEach items="${position.p_category}" var="mm">
                                    <span>${mm}</span>
                                	</c:forEach>
                                </div>
                            </div>
                            <div class="content-body-clearfix-tabmodulelist-hotjob-libtm">
                                <a href=""><img src="${pageContext.request.contextPath}/files/${sessionScope.com.com_logo}" alt="" width="40" height="40"></a>
                                <div class="content-body-clearfix-tabmodulelist-hotjob-libtm-bottom">
                                    <span>${sessionScope.com.com_name}</span>
                                    <span>
                                    
                                    <c:forEach items="${sessionScope.com.com_type }" var="nm">
                                    ${nm} / 
                                	</c:forEach>
                                      ${sessionScope.com.com_address  }</span>
                                </div>
                            </div>
                        </li>
                         </c:forEach>
                    </ol>
                   
                    <!--  <a href="" class="content-body-clearfix-tabmodulelist-more">查看更多</a> -->
                </div>
            </div>
            <ul class="content-body-clearfix-tabmodule" >
                <li class="clickli-current">收到的简历</li>
            </ul>
            
            <ul class="lg-content-body-adcompany">
              <c:forEach items="${requestScope.resume}" var="resume">
               <li class="lg-content-body-adcompany-companyitem">
              		
	                    <div class="lg-content-body-adcompany-companyitem-top">
	                        <p><a href="${pageContext.request.contextPath}/user/resumeDetail?r_id=${resume.r_id}"><img src="${pageContext.request.contextPath }/files/${resume.r_header }" alt="简历的头像" width="80" height="80"></a></p>
	                        <p class="company-name"><a href="${pageContext.request.contextPath }/com/resumeDetails?rid=${resume.r_id}">${resume.r_name}</a></p>
	                        <p>${resume.email}</p>
	                    </div>
	                    <div class="lg-content-body-adcompany-companyitem-bottom">
	                        <a href=""><span>性别</span><span>${resume.sex  }</span></a>
	                    </div>
	                    <div class="lg-content-body-adcompany-companyitem-bottom">
	                        <a href=""><span>学历</span><span>${resume.r_degree  }</span></a>
	                    </div>
	                    <div class="lg-content-body-adcompany-companyitem-bottom">
	                        <a href=""><span>求职意向</span><span>${resume.r_category  }</span></a>
	                    </div>
               		
                </li>
                </c:forEach>
            </ul>
            
            
        </div>
    </div>
</div>

</body>
</html>