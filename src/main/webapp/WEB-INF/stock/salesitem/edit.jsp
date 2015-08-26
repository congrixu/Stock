<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>编辑采购明细</title-->
</head>
<body>
	<input type="hidden" id="sales_item_id" value="${requestScope.item.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="name_" class="col-sm-1 control-label">商品名称：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="cdy_name"  placeholder="商品名称" value="${requestScope.item.commodity.name}">
	  	   <input type="hidden" id="cdy_id" value="${requestScope.item.commodity.id}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="supplier_name" class="col-sm-1 control-label">供应商：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="supplier_name" placeholder="供应商" value="${requestScope.item.sup.name}">
	      <input type="hidden" id="supplier_id" value="${requestScope.item.sup.id}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="num" class="col-sm-1 control-label">销售数量：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="num" placeholder="销售数量"  value="${requestScope.item.num}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="price" class="col-sm-1 control-label">销售单价：</label>
	    <div class="col-sm-11">
	      <input type="text" class="form-control" id="price" placeholder="销售单价" value="${requestScope.item.price}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="remark" class="col-sm-1 control-label">备注：</label>
	    <div class="col-sm-11">
	      <textarea rows="" cols="" id="remark">${requestScope.item.remark}</textarea>
	    </div>
	  </div>
	</form>
	<div id="choose_commodity_div"></div>
	<div id="choose_supplier_div"></div>
</body>
</html>
<script src="${CONTEXT_PATH}/js/stock/salesitem/edit.js" type="text/javascript"></script>
