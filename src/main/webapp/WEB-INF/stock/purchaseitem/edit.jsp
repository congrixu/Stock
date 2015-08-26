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
	<input type="hidden" id="supplier_item_id" value="${requestScope.item.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="name_" class="col-sm-4 control-label">商品名称：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="cdy_name"  placeholder="商品名称" value="${requestScope.item.cdy.name}">
	  	   <input type="hidden" id="cdy_id" value="${requestScope.item.cdy.id}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="clothes" class="col-sm-4 control-label">件数：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="clothes" placeholder="件数" value="${requestScope.item.clothes}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="num" class="col-sm-4 control-label">采购数量：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="num" placeholder="采购数量"  value="${requestScope.item.num}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="price" class="col-sm-4 control-label">单价：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="price" placeholder="单价" value="${requestScope.item.price}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="remark" class="col-sm-4 control-label">备注：</label>
	    <div class="col-sm-7">
	      <textarea class="form-control" id="remark">${requestScope.item.remark}</textarea>
	    </div>
	  </div>
	</form>
	<div id="choose_commodity_div"></div>
</body>
</html>
<script src="${CONTEXT_PATH}/js/stock/purchaseitem/edit.js" type="text/javascript"></script>
