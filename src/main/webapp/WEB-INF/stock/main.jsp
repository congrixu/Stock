<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>铭家灯饰库存管理系统</title>
    <link href="${CONTEXT_PATH}/js/lib/jquery-easyui/1.3.1/css/icon.css?version=${BUILD_TIME}" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/js/lib/jquery-easyui/1.3.1/css/defaule/easyui.css?version=${BUILD_TIME}" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/js/lib/bootstrap/css/bootstrap.css?version=${BUILD_TIME}" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/js/lib/bootstrap/css/bootstrap-responsive.css?version=${BUILD_TIME}" rel="stylesheet" type="text/css">
    <link href="${CONTEXT_PATH}/css/common.css?version=${BUILD_TIME}" rel="stylesheet" type="text/css">
    <script type="text/javascript">
	    window.CONTEXT_PATH = '${CONTEXT_PATH}';
	    window.LOGIN_TYPE='${LOGIN_TYPE}'
    </script>
</head>
<body class="easyui-layout">
 	<div id="head" data-options="region:'north',border:false" style="height:60px;overflow:hidden;">
 	  <div class="ui-widget-header ui-state-active" style="width: 100%;height:56px; padding:0px 10px 0px 10px;">&nbsp;
	 	  <h1 style="margin-top:-5px;padding:0px 0 5px 10px;font-weight: bold; color:#FFFFFF;">铭家灯饰库存管理系统</h1>
	      <div style="position:absolute;right:30px;bottom:10px;">
	        <span>
	         <input type="button" id="logout_btn" class="btn" value="退出">
	        </span>
	      </div>
 	  </div>
    </div>
    <div id="menu" data-options="region:'west',title:'&nbsp;',split:false" style="width:200px;">
      <ul>
         <li><a href="/sales/query.action">销售管理</a></li>
         <li><a href="/purchase/query.action">采购管理</a></li>
         <li><a href="/storage/query.action">库存管理</a></li>
         <li><a href="/commodity/query.action">商品管理</a></li>
         <li><a href="/supplier/query.action">供应商管理</a></li>
         <%-- <li>
         	<span>统计</span>
         	<ul>
         		<li><a href="/report/sumtotal.action">总量统计</a></li>
         		<li><a href="#">商品统计</a></li>
         	</ul>
         </li> --%>
      </ul>
    </div>
    <div id="main" data-options="region:'center'">
      <div style="line-height:50px;">&nbsp;</div>
    </div>
    <div id="foot" data-options="region:'south',border:false" style="height:18px;background:#DDDDDD;padding:1px; overflow: hidden;">
      <p style="text-align:center;font-weight:normal;">版权所有 © 铭家灯饰 2015。保留所有权利。技术支持: rixuv5@126.com</p>
    </div>
    <script src="${CONTEXT_PATH}/js/lib/json2.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/jquery/jquery-1.8.0.min.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/bootstrap/js/bootstrap.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/jquery-easyui/1.3.1/jquery.easyui.min.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/jquery-easyui/1.3.1/easyui-lang-zh_CN.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/jstree/pre1.0_fix_1/jquery.jstree.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/lib/My97DatePicker/WdatePicker.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/common.js?version=${BUILD_TIME}" type="text/javascript"></script>
    <script src="${CONTEXT_PATH}/js/main.js?version=${BUILD_TIME}" type="text/javascript"></script>
</body>
</html>