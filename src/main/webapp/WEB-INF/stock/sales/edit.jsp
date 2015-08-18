<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>添加采购单</title-->
</head>
<body>
	<input type="hidden" id="sales_id" value="${requestScope.sales.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="client_name" class="col-sm-1 control-label">客户名称：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="client_name_form" placeholder="客户名称" value="${requestScope.sales.clientName}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="client_phone" class="col-sm-1 control-label">客户电话：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="client_phone_form" placeholder="客户电话" value="${requestScope.sales.clientPhone}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="client_addr" class="col-sm-1 control-label">客户地址：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="client_addr">${requestScope.sales.clientAddr}</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="remark" class="col-sm-1 control-label">备注：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="remark">${requestScope.sales.remark}</textarea>
	    </div>
	  </div>
	</form>
</body>
</html>
