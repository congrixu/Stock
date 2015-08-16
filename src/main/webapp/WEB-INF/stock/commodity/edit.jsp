<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>添加商品</title-->
</head>
<body>
	<input type="hidden" id="commodity_id" value="${requestScope.commodity.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="name_" class="col-sm-1 control-label">名称：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="name_"  placeholder="供应商名称" value="${requestScope.commodity.name}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="type_" class="col-sm-1 control-label">型号：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="type_" placeholder="型号" value="${requestScope.commodity.type}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="supplier_name" class="col-sm-1 control-label">供应商：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="supplier_name" placeholder="供应商" value="${requestScope.commodity.supplier.name}">
	      <input type="hidden" id="supplier_id" vlaue="${requestScope.commodity.supplier.id}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="remark" class="col-sm-1 control-label">备注：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="remark">${requestScope.commodity.remark}</textarea>
	    </div>
	  </div>
	</form>
	<div id="choose_supplier_div"></div>
</body>
</html>
<script src="${CONTEXT_PATH}/js/stock/commodity/edit.js" type="text/javascript"></script>
