<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bio-LIMS</title>
<script type="text/javascript"></script>
<link href="" rel="stylesheet" type="text/css" />
</head>
<body>
<h2>抱歉，系统出现错误！</h2>
<p>

    请您与管理员联系！
    <ul>
    	<li>电话：	</li>
    	<li>邮箱：	</li>
    </ul>
</p>
<div id="systemFailed" style="text-align:left; padding-left:80px; padding-right:80px;">
        异常类型："${exception }"
    </div>
    
    <%
    java.lang.Throwable ex  = (java.lang.Throwable)request.getAttribute("exception");
    	//记录日志
    	Logger logger = LoggerFactory.getLogger("500.jsp");
    	logger.error(ex.getMessage(), ex);
    	ex.printStackTrace();
    %>
</body>
</html>