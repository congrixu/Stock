<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!--title>打印采购单</title-->
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
				<td align="center" colspan="2"><h2>采购入库单</h2></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td ><b>采购日期：</b><s:date name="#request.purchase.createDate" format="yyyy-MM-dd HH:mm:ss" /> </td>
				<td ><b>入库日期：</b><s:date name="#request.purchase.inTime" format="yyyy-MM-dd HH:mm:ss" /> </td>
			</tr>
			<tr>
				<td ><b>供应名称：</b>${purchase.supplier.name }</td>
				<td ><b>采购金额：</b> ${purchase.totalPrice}元</td>
			</tr>
			<tr>
				<td><b>供应商电话：</b>${purchase.supplier.phone}</td>
				<td><b>供应商手机：</b>${purchase.supplier.mobile}</td>
			</tr>
			<tr>
				<td ><b>供应商传真：</b>${purchase.supplier.fax}</td>
			</tr>
			<tr>
				<td colspan="2"><b>供应商地址：</b>${purchase.supplier.addr}</td>
			</tr>
			<tr>
				<td colspan="2"><b>银行信息：</b>${purchase.supplier.bankInfo}</td>
			</tr>
		</tbody>	
	</table>
	<table class='print-table'>
		<thead>
			<tr>
				<td align="center" colspan="6"><h3>商品明细</h3></td>
			</tr>
			<tr>
				<th >商品名称</th>
				<th >商品型号</th>
				<th >件数</th>
				<th >采购数量</th>
				<th >单价</th>
				<th >总金额</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="#request.items.size()>0">
			<s:iterator value="#request.items" id="item">
				<tr>
					<td ><s:property value='#item.cdy.name'/></td>
					<td ><s:property value='#item.cdy.type'/></td>
					<td ><s:property value='#item.clothes'/></td>
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
