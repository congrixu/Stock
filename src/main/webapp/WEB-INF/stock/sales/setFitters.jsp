<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>出库设置安装人员</title-->
</head>
<body>
	<input type="hidden" id="sales_id" value="${requestScope.id }">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="fitters_user" class="col-sm-4 control-label">安装工：</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="fitters_user" placeholder="安装工">
	      <input type="hidden" class="form-control" id="fitters_user_id">
	    </div>
	  </div>
	</form>
</body>
</html>	
<div id="choose_user_div"></div>
<script src="${CONTEXT_PATH}/js/stock/sales/setFitters.js" type="text/javascript"></script>

