<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>用户管理</title-->
</head>
<body>
	<div id="user_choose_oper_table">
		<table width="100%">
			<tr>
				<td style="width:10px">&nbsp;</td>
				<td>
					名称：<input type="text" id="search_name">
					<a href="#" id="choose_user_query_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-search',plain:true">查询</a>
				</td>
				<td align="right">
					&nbsp;
				</td>
				<td style="width:30px">&nbsp;</td>
			</tr>
		</table>
	</div>
	<table id="user_choose_user_table">
	</table>
    <script src="${CONTEXT_PATH}/js/stock/system/user/choose.js" type="text/javascript"></script>
</body>
</html>