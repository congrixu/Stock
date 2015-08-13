<!--
说明：无权限访问页面提示
时间：2011-9-27
作者：倪毅
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bio-Lims</title>
<script type="text/javascript"></script>
<link href="" rel="stylesheet" type="text/css" />
</head>
<body>
<br></br><br></br>
<center>
<h3 style="color:red"><%=(String)request.getAttribute("message")%></h3>
<a onclick="javascript:window.parent.location.href = '${ctx}/main/logOut.action'"style="cursor:hand;color:blue">登录</a>
</center>
</body>
</html>