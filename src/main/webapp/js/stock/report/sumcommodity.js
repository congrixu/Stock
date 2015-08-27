$(function() {

	var columns = [];

	columns.push({
		field : 'ck',
		checkbox : true
	});
	columns.push({
		field : 'commodityId',
		title : 'commodityId',
		width : 100,
		hidden : true
	});
	columns.push({
		field : 'commodityName',
		title : '商品品名',
		width : 100,
	});
	columns.push({
		field : 'commodityType',
		title : '商品型号',
		width : 150
	});
	columns.push({
		field : 'totalNum',
		title : '销售总数量',
		width : 100
	});
	columns.push({
		field : 'totalPrice',
		title : '销售总金额',
		width : 120
	});
	columns.push({
		field : 'storageNum',
		title : '库存数量',
		width : 120
	});

	var opts = {};
	opts.title = "商品销售统计";
	opts.singleSelect = true;

	grid("sales_commodity_table", "oper_table", columns, "/report/sumcommodity.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#sales_commodity_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var name = $.trim($("#search_name").val());
		if (name)
			param.name = name;

		param.startDate = $("#start_date").val();
		param.endDate = $("#end_date").val()

		return param;
	}

});
