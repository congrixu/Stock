<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>铭家灯饰库存管理系统</title>
 	<link href="${CONTEXT_PATH}/js/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/js/lib/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/css/common.css" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="login-container" class="ui-state-default">
	    <div id="login-title" class="ui-state-active">铭家灯饰库存管理系统</div>
	    <div id="login-form">
	      <form action="${CONTEXT_PATH}/login.action" id="login_form" method="post">
		      <table>
		        <colgroup>
		          <col width="40">
		          <col width="">
		        </colgroup>
		        <tbody>
		          <tr><td><span>账号：</span></td><td><input type="text" name="userId" id="user_id" style="width: 100px;"></td></tr>
		          <tr><td><span>密码：</span></td><td><input type="password" name="pwd" id="pwd" style="width: 100px;"></td></tr>
		          <tr>
		          	<td colspan="2" align="center" style="padding-top: 20px;">
		          		<input type="submit" value="登录">
	          		</td>
	        	  </tr>
		        </tbody>
		      </table>
	      </form>
	    </div>
	</div>
	<script src="${CONTEXT_PATH}/js/lib/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/login.js" type="text/javascript"></script>
</body>
</html>