<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>库存管理</title-->
</head>
<body>
	<div id="oper_table">
		<table width="100%">
			<tr>
				<td style="width:10px">&nbsp;</td>
				<td>
					名称：<input type="text" id="search_name">
					拼音：<input type="text" id="search_py">
					<a href="#" id="query_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-search',plain:true">查询</a>
				</td>
				<td align="right">
					<a href="#" id="inventory_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-sum',plain:true">盘点</a>
				</td>
				<td style="width:30px">&nbsp;</td>
			</tr>
		</table>
	</div>
	<table id="storage_table">
	</table>
    <script src="${CONTEXT_PATH}/js/stock/storage/query.js" type="text/javascript"></script>
</body>
</html>