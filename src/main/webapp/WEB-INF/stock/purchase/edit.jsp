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
	<input type="hidden" id="purchase_id" value="${requestScope.purchase.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="supplier_name" class="col-sm-4 control-label">供应商：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="supplier_name" placeholder="供应商" value="${requestScope.purchase.supplier.name}">
	      <input type="hidden" id="supplier_id" value="${requestScope.purchase.supplier.id}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="remark" class="col-sm-4 control-label">备注：</label>
	    <div class="col-sm-7">
	      <textarea class="form-control" id="remark">${requestScope.purchase.remark}</textarea>
	    </div>
	  </div>
	</form>
	<div id="choose_supplier_div"></div>
</body>
</html>
<script src="${CONTEXT_PATH}/js/stock/purchase/edit.js" type="text/javascript"></script>
