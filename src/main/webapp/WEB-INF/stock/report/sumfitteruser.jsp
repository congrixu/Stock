<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>安装人员情况统计</title-->
</head>
<body>
	<div id="oper_table">
		<table width="100%">
			<tr>
				<td style="width:10px">&nbsp;</td>
				<td>
					   安装人员：<input type="text" id="search_name">
					    开始时间：<input class="Wdate" type="text" id="start_date" value="${startDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end_date\')}'})"/>
			  		   结束时间：<input class="Wdate" type="text" id="end_date" value="${endDate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'start_date\')}'})"/>
					<a href="#" id="query_btn" class="easyui-linkbutton" data-options="iconCls:'easyui-icon-search',plain:true">查询</a>
				</td>
				<td style="width:30px">&nbsp;</td>
			</tr>
		</table>
	</div>
	<table id="sales_user_table">
	</table>
    <script src="${CONTEXT_PATH}/js/stock/report/sumfitteruser.js" type="text/javascript"></script>
</body>
</html>