<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>打印销售单</title-->
	<link href="${CONTEXT_PATH}/css/print.css" rel="stylesheet" type="text/css">
	<link href="${CONTEXT_PATH}/js/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div align="center">
		<button type="button" id="print_btn" class="btn btn-primary">打印</button>
	</div>
<!--startprint-->
	<table class='print-table'>
		<thead>
			<tr>
				<td align="center" colspan="2"><h2>销售单</h2></td>
			</tr>
		</thead>	
		<tr>
			<td ><b>客户名称：</b>${sales.clientName }</td>
			<td ><b>客户电话：</b>${sales.clientPhone }</td>
		</tr>
		<tr>
			<td ><b>销售日期：</b><s:date name="#request.sales.salesDate" format="yyyy-MM-dd HH:mm:ss" /> </td>
			<td ><b>销售金额</b>： ${sales.totalPrice }元</td>
		</tr>
		<tr>
			<td colspan="2" >
				<b>客户地址：</b>${sales.clientAddr}
			</td>
		</tr>
	</table>
	<table class='print-table'>
		<thead>
			<tr>
				<td align="center" colspan="5"><h3>商品明细</h3></td>
			</tr>
			<tr>
				<th >商品名称</th>
				<th >商品型号</th>
				<th >数量</th>
				<th >单价</th>
				<th >总金额</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="#request.items.size()>0">
			<s:iterator value="#request.items" id="item">
				<tr>
					<td ><s:property value='#item.commodity.name'/></td>
					<td ><s:property value='#item.commodity.type'/></td>
					<td ><s:property value='#item.num'/></td>
					<td ><s:property value='#item.price'/></td>
					<td ><s:property value='#item.totalPrice'/></td>
				</tr>
			</s:iterator>
			</s:if>
		</tbody>
	</table>
	<div style="padding: 10px;">
		<table style="width:90%;  margin: auto;">
			<tr>
				<td colspan="4" align="right">
					打印日期：<s:date name="#request.currentDate" format="yyyy-MM-dd HH:mm:ss" /> 
				</td>
			</tr>
		</table>
	</div>
<!--endprint-->
</body>
<script src="${CONTEXT_PATH}/js/lib/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		function pagesetup_null(){                
            var hkey_root,hkey_path,hkey_key;
            hkey_root="HKEY_CURRENT_USER"
            hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
            try{
                var RegWsh = new ActiveXObject("WScript.Shell");
                hkey_key="header";
                RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
                //hkey_key="footer";
                //RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
            }catch(e){}
        }
		$("#print_btn").click(function(){
			var bdhtml=window.document.body.innerHTML; 
			var sprnstr="<!--startprint-->"; 
			var eprnstr="<!--endprint-->"; 
			var prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
		    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
			window.document.body.innerHTML=prnhtml; 
			pagesetup_null();
			window.print(); 
			window.document.body.innerHTML=bdhtml;
		});
	});
</script>
</html>
