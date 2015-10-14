$(function() {

	var columns = [];

	columns.push({
		field : 'ck',
		checkbox : true
	});
	columns.push({
		field : 'count',
		title : '安装总个数',
		width : 100
	});

	columns.push({
		field : 'userName',
		title : '安装人员',
		width : 100,
	});
	columns.push({
		field : 'userId',
		title : '安装人员账号',
		width : 100,
	});

	var opts = {};
	opts.title = "安装人员统计";
	opts.singleSelect = true;

	grid("sales_user_table", "oper_table", columns, "/report/sumfitteruser.action", opts);

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
