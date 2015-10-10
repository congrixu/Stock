<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>用户管理</title-->
</head>
<body>
	<div id="oper_table">
		<table width="100%">
			<tr>
				<td style="width:10px">&nbsp;</td>
				<td>
					名称：<input type="text" id="search_name">
					<a href="#" id="query_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-search',plain:true">查询</a>
				</td>
				<td align="right">
					<a href="#" id="add_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-add',plain:true">添加</a>
					<a href="#" id="modify_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-edit',plain:true">修改</a>
					<a href="#" id="delete_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-remove',plain:true">删除</a>
				</td>
				<td style="width:30px">&nbsp;</td>
			</tr>
		</table>
	</div>
	<table id="user_table">
	</table>
    <script src="${CONTEXT_PATH}/js/stock/system/user/query.js" type="text/javascript"></script>
</body>
</html>