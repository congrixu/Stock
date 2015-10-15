$(function() {

	var columns = [];

	columns.push({
		field : 'ck',
		checkbox : true
	});
	columns.push({
		field : 'salName',
		title : '商品',
		width : 100
	});
	columns.push({
		field : 'salType',
		title : '型号',
		width : 100
	});
	columns.push({
		field : 'count',
		title : '销售总单数',
		width : 100
	});
	columns.push({
		field : 'total',
		title : '销售总价',
		width : 100
	});
	columns.push({
		field : 'userName',
		title : '销售人员',
		width : 100,
	});
	columns.push({
		field : 'userId',
		title : '销售人员账号',
		width : 100,
	});

	var opts = {};
	opts.title = "员工商品销售统计";
	opts.singleSelect = true;

	grid("sales_user_table", "oper_table", columns, "/report/sumsaluser.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#sales_user_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var name = $.trim($("#search_name").val());
		if (name)
			param.userName = name;

		param.startDate = $("#start_date").val();
		param.endDate = $("#end_date").val()

		return param;
	}

});
