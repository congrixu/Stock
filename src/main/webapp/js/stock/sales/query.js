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
		field : 'clientName',
		title : '客户名称',
		width : 150,
	});
	columns.push({
		field : 'clientPhone',
		title : '客户电话',
		width : 150
	});
	columns.push({
		field : 'clientAddr',
		title : '客户联系地址',
		width : 200
	});
	columns.push({
		field : 'salesDate',
		title : '售售日期',
		width : 120,
		sortable : true
	});
	columns.push({
		field : 'totalNum',
		title : '数量',
		width : 100
	});
	columns.push({
		field : 'totalPrice',
		title : '总价格',
		width : 120
	});
	columns.push({
		field : 'state',
		title : '状态',
		width : 100,
		hidden : true
	});
	columns.push({
		field : 'stateStr',
		title : '状态',
		width : 100
	});
	columns.push({
		field : 'remark',
		title : '备注',
		width : 250
	});

	var opts = {};
	opts.title = "销售管理";
	opts.singleSelect = true;

	grid("sales_table", "oper_table", columns, "/sales/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#sales_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var startDate = $("#start_date").val();
		var endDate = $("#end_date").val();
		var clientName = $("#client_name").val();
		var clientPhone = $("#client_phone").val();
		param.startDate = startDate;
		param.endDate = endDate;
		param.clientName = clientName;
		param.clientPhone = clientPhone;
		return param;
	}

	$("#delete_btn").click(function() {
		var selData = $("#sales_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return false;
		}
		var state = selData.state;
		if ("2" == state) {
			alertMsg("当前销售单已完成出库,不允许被删除！");
			return false;
		}
		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/sales/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg("操作失败！");
				} else {
					$("#sales_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 290;
		var win = loadDialogPage(null, "添加销售单", "/sales/edit.action", [ {
			text : "确定",
			iconCls : "easyui-icon-save",
			handler : function() {
				var param = buildData();

				if (!param)
					return;

				saveOrModifyData(param, win);
			}
		} ], true, option, true);
	});

	$("#modify_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 290;

		var selData = $("#sales_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "添加销售单", "/sales/edit.action?id=" + selData.id, [ {
			text : "确定",
			iconCls : "easyui-icon-save",
			handler : function() {
				var param = buildData();

				if (!param)
					return;

				saveOrModifyData(param, win);
			}
		} ], true, option, true);
	});

	function saveOrModifyData(param, win) {
		ajax("post", "/sales/save.action", param, function(data) {
			if (!data.success) {
				alertMsg("操作失败！");
			} else {
				$(win).dialog("close");
				$("#sales_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var name = $("#client_name_form").val();
		if (!name) {
			alertMsg("请填写客户名称！");
			return false;
		}

		var clientPhone = $("#client_phone_form").val();
		var clientAddr = $("#client_addr").val();
		var remark = $("#remark").val();

		var param = {};
		param["sales.id"] = $("#sales_id").val();
		param["sales.clientName"] = name;
		param["sales.clientPhone"] = clientPhone;
		param["sales.clientAddr"] = clientAddr;
		param["sales.remark"] = remark;
		return param;
	}

	$("#item_btn").click(function() {
		var selData = $("#sales_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要操作的数据！");
			return false;
		}

		load("/salesitem/query.action", {
			salesId : selData.id
		}, $("#main"), null);
	});

	$("#out_lib_btn").click(function() {
		var selData = $("#sales_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要操作的数据！");
			return false;
		}

		var state = selData.state;
		if ("3" == state) {
			alertMsg("当前销售单已出库！");
			return false;
		}
		confirmMsg("您确定要对当前销售单做出库处理吗？", "请确认", function() {
			ajax("post", "/sales/outlib.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg("操作失败！");
				} else {
					$("#sales_table").datagrid("reload");
				}
			}, null);
		});

	});

});
