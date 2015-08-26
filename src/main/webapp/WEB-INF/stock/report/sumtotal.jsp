<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!--title>汇总信息</title-->
</head>
<body>
	<form class="form-horizontal" method="post" action="${ctx}/report/sumtotal.action">
		<div class="row" style="margin-left: 0px;">
			     开始时间：<input class="Wdate" type="text" id="start_date" value="${startDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end_date\')}'})"/>
			     结束时间：<input class="Wdate" type="text" id="end_date" value="${endDate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'start_date\')}'})"/>
			 <button type="button" id="sub_btn">查询</button>
		</div>
		<p></p>
		<table class="table table-bordered table-hover">
			<colgroup>
				<col width="100px">
				<col width="100px">
				<col width="">
			</colgroup>
			<tbody>
				<tr>
					<td>销售总单数</td>
					<td>${tb.totalSalesCount}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>销售总数量</td>
					<td>${tb.totalSalesNum}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>销售总价格</td>
					<td>${tb.totalSalesPrice}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>采购总单数</td>
					<td>${tb.totalPurchaseCount}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>采购总数量</td>
					<td>${tb.totalPurchaseNum}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>采购总价格</td>
					<td>${tb.totalPurchasePrice}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>赠送总单数</td>
					<td>${tb.giftCount}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>赠送总数量</td>
					<td>${tb.giftNum}</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>赠送总价格</td>
					<td>${tb.totalGiftPurchasePrice}</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</form>
	<script src="${CONTEXT_PATH}/js/stock/report/sumtotal.js" type="text/javascript"></script>
</body>
</html>