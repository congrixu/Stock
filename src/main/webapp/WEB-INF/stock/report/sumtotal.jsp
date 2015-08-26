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
		<div class="form-group">
		    <label class="col-sm-2 control-label">销售总单数：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalSalesCount}</p>
		    </div>
		 </div>
		<div class="form-group">
		    <label class="col-sm-2 control-label">销售总数量：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalSalesNum}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">销售总价格：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalSalesPrice}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">采购总单数：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalPurchaseCount}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">采购总数量：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalPurchaseNum}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">采购总价格：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalPurchasePrice}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">赠送总单数：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.giftCount}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">赠送总数量：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.giftNum}</p>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">赠送总价格：</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${tb.totalGiftPurchasePrice}</p>
		    </div>
		 </div>
	</form>
	<script src="${CONTEXT_PATH}/js/stock/report/sumtotal.js" type="text/javascript"></script>
</body>
</html>