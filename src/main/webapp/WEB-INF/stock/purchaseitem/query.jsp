<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>采购单明细管理</title-->
</head>
<body>
	<div id="oper_table">
		<table width="100%">
			<tr>
				<td style="width:10px">&nbsp;</td>
				<td align="right">
					<span>
						<a href="#" id="return_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-back',plain:true">返回</a>
					</span>
					<span id="oper_btn_div">
						<a href="#" id="add_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-add',plain:true">添加</a>
						<a href="#" id="modify_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-edit',plain:true">修改</a>
						<a href="#" id="delete_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-remove',plain:true">删除</a>
					</sapn>
				</td>
				<td style="width:30px">&nbsp;</td>
			</tr>
		</table>
		<input type="hidden" id="purchase_id" value="${requestScope.purchase.id}">
		<input type="hidden" id="purchase_state" value="${requestScope.purchase.state}">
	</div>
	<table id="purchase_item_table">
	</table>
    <script src="${CONTEXT_PATH}/js/stock/purchaseitem/query.js" type="text/javascript"></script>
</body>
</html>