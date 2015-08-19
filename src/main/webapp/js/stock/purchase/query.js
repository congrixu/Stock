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
		field : 'supplier-name',
		title : '供应商名称',
		width : 200,
	});
	columns.push({
		field : 'createDate',
		title : '采购日期',
		width : 150,
		sortable : true
	});
	columns.push({
		field : 'totalPrice',
		title : '总价钱',
		width : 150
	});
	columns.push({
		field : 'state',
		title : '状态值',
		width : 100,
		hidden : true
	});
	columns.push({
		field : 'stateStr',
		title : '状态',
		width : 100
	});
	columns.push({
		field : 'inTime',
		title : '入库时间',
		width : 120
	});
	columns.push({
		field : 'remark',
		title : '备注',
		width : 250
	});
	/*
	 * columns.push({ field : 'oper', title : '操作', width : 250, formatter :
	 * function(value, row, index) { var dom = []; dom.push("<a href='#'
	 * id='item_manage' purchaseId='" + row.id + "'>"); dom.push("明细管理");
	 * dom.push("</a>"); return dom.join(""); } });
	 */

	var opts = {};
	opts.title = "采购管理";
	opts.singleSelect = true;

	grid("purchase_table", "oper_table", columns, "/purchase/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#purchase_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var startDate = $("#start_date").val();
		var endDate = $("#end_date").val();
		param.startDate = startDate;
		param.endDate = endDate;
		return param;
	}

	$("#delete_btn").click(function() {
		var selData = $("#purchase_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return false;
		}

		var state = selData.state;
		if ("2" == state) {
			alertMsg("已入库采购订单不允许被删除！");
			return false;
		}
		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/purchase/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg(data.message);
				} else {
					$("#purchase_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 200;
		var win = loadDialogPage(null, "添加采购单", "/purchase/edit.action", [ {
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
		option.height = 200;

		var selData = $("#purchase_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}
		var state = selData.state;
		if ("2" == state) {
			alertMsg("已入库采购订单不允许被删除！");
			return false;
		}
		var win = loadDialogPage(null, "修改采购单", "/purchase/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/purchase/save.action", param, function(data) {
			if (!data.success) {
				alertMsg("操作失败！");
			} else {
				$(win).dialog("close");
				$("#purchase_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var name = $("#supplier_name").val();
		if (!name) {
			alertMsg("请选择供应商！");
			return false;
		}

		var supplierId = $("#supplier_id").val();

		var param = {};
		param["purchase.id"] = $("#purchase_id").val();
		param["purchase.supplier.id"] = supplierId;
		param["purchase.remark"] = $("#remark").val();

		return param;
	}

	$("#item_btn").click(function() {
		var selData = $("#purchase_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要操作的数据！");
			return false;
		}

		load("/purchaseitem/query.action", {
			purchaseId : selData.id
		}, $("#main"), null);
	});

	$("#in_lib_btn").click(function() {
		var selData = $("#purchase_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要操作的数据！");
			return false;
		}

		var state = selData.state;
		if ("2" == state) {
			alertMsg("当前采购单已入库！");
			return false;
		}
		confirmMsg("您确定要对当前采购单做入库处理吗？", "请确认", function() {
			ajax("post", "/purchase/inlib.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg("操作失败！");
				} else {
					$("#purchase_table").datagrid("reload");
				}
			}, null);
		});

	});

});
