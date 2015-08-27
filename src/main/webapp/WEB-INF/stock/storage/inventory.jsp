<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>盘点</title-->
</head>
<body>
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="inventory_num" class="col-sm-4 control-label">盘点数量</label>
	    <div class="col-sm-7">
	      <input type="text" class="form-control" id="inventory_num" placeholder="盘点数量" >
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inventory_remark" class="col-sm-4 control-label">备注</label>
	    <div class="col-sm-7">
	      <textarea class="form-control" id="inventory_remark"></textarea>
	    </div>
	  </div>
	</form>
</body>
</html>
