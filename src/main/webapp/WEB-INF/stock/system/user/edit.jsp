<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>编辑用户</title-->
</head>
<body>
	<form class="form-horizontal">
	  <input type="hidden" id="user_pk_id" value="${requestScope.user.id }">
	  <div class="form-group">
	    <label for="user_id" class="col-sm-4 control-label">登录名：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="user_id"  placeholder="登录名" value="${requestScope.user.userId}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="user_pwd" class="col-sm-4 control-label">密码：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="user_pwd"  placeholder="登录密码" value="${requestScope.user.pwd}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="name_" class="col-sm-4 control-label">名称：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="name_"  placeholder="用户名称" value="${requestScope.user.name}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="phone" class="col-sm-4 control-label">电话：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="phone" placeholder="电话" value="${requestScope.user.phone}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="phone" class="col-sm-4 control-label">出生日期：</label>
	    <div class="col-sm-7">
	      	<input class="form-control" type="text" id="birthday_date" value="${requestScope.user.birthday}" onFocus="WdatePicker()" readonly="readonly"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_card" class="col-sm-4 control-label">身份证号：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="id_card" placeholder="身份证号"  value="${requestScope.user.idCard}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="addr" class="col-sm-4 control-label">联系地址：</label>
	    <div class="col-sm-7">
	      <textarea class="form-control" id="addr">${requestScope.user.addr}</textarea>
	    </div>
	  </div>
	</form>
</body>
</html>