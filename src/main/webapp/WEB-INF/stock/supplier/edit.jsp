<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>编辑查询服务</title-->
</head>
<body>
	<input type="hidden" id="supplier_id" value="${requestScope.supplier.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="name_" class="col-sm-1 control-label">名称：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="name_"  placeholder="供应商名称" value="${requestScope.supplier.name}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="phone" class="col-sm-1 control-label">电话：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="phone" placeholder="电话" value="${requestScope.supplier.phone}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="fax" class="col-sm-1 control-label">传真：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="fax" placeholder="传真"  value="${requestScope.supplier.fax}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="mobile" class="col-sm-1 control-label">手机：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="mobile" placeholder="手机" value="${requestScope.supplier.mobile}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="bank_info" class="col-sm-1 control-label">银行信息：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="bank_info">${requestScope.supplier.bankInfo}</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="addr" class="col-sm-1 control-label">地址：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="addr">${requestScope.supplier.addr}</textarea>
	    </div>
	  </div>
	</form>
</body>
</html>