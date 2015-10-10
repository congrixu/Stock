$(function() {

	var columns = [];

	columns.push({
		field : 'ck',
		checkbox : true
	});
	columns.push({
		field : 'id',
		title : 'ID',
		width : 100,
		hidden : true
	});
	columns.push({
		field : 'userId',
		title : '用户账号',
		width : 100,
	});
	columns.push({
		field : 'name',
		title : '用户名称',
		width : 100
	});
	columns.push({
		field : 'birthday',
		title : '出生日期',
		width : 100
	});
	columns.push({
		field : 'phone',
		title : '电话',
		width : 100
	});
	columns.push({
		field : 'stateStr',
		title : '是否在职',
		width : 100
	});
	columns.push({
		field : 'addr',
		title : '地址',
		width : 150
	});
	columns.push({
		field : 'idCard',
		title : '身份证号',
		width : 100
	});
	columns.push({
		field : 'entryDate',
		title : '入职日期',
		width : 100
	});
	columns.push({
		field : 'resignDate',
		title : '离职日期',
		width : 100
	});

	var opts = {};
	opts.title = "用户管理";
	opts.singleSelect = true;

	grid("user_choose_user_table", "user_choose_oper_table", columns, "/system/user/queryjson.action", opts);

	$("#choose_user_query_btn").click(function() {
		var param = buildSearchData();
		$("#user_choose_user_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var name = $.trim($("#search_name").val());
		if (name)
			param.name = name;

		return param;
	}

	$("#choose_user_btn").click(function() {
		var selData = $("#user_choose_user_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要选择的数据！");
			return;
		}

		var id = selData.id;
		var name = selData.name;
		var callback = $("#user_choose_user_table").data("callback");
		if (typeof callback == "function") {
			callback(id, name);
		}
	});

});
