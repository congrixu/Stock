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
		field : 'cdy-name',
		title : '商品名称',
		width : 200,
	});
	columns.push({
		field : 'clothes',
		title : '件数',
		width : 100,
		sortable : true
	});
	columns.push({
		field : 'num',
		title : '数量',
		width : 100
	});
	columns.push({
		field : 'price',
		title : '价格',
		width : 100
	});
	columns.push({
		field : 'totalPrice',
		title : '总价格',
		width : 120
	});
	columns.push({
		field : 'remark',
		title : '备注',
		width : 200
	});

	var opts = {};
	opts.title = "采购明细";
	opts.singleSelect = true;

	grid("purchase_item_table", "oper_table", columns, "/purchaseitem/queryjson.action?purchaseId="
			+ $("#purchase_id").val(), opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#purchase_item_table").datagrid('load', param);
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
		var selData = $("#purchase_item_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return false;
		}

		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/purchaseitem/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg(data.message);
				} else {
					$("#purchase_item_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 310;
		var win = loadDialogPage(null, "添加采购单", "/purchaseitem/edit.action", [ {
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
		option.height = 310;

		var selData = $("#purchase_item_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "修改采购单", "/purchaseitem/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/purchaseitem/save.action", param, function(data) {
			if (!data.success) {
				alertMsg("操作失败！");
			} else {
				$(win).dialog("close");
				$("#purchase_item_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var cdyName = $("#cdy_name").val();
		if (!cdyName) {
			alertMsg("请选择商品！");
			return false;
		}
		var cdyId = $("#cdy_id").val();
		var clothes = $("#clothes").val();
		if (isNaN(clothes)) {
			alertMsg("填写的件数不正确，请填写数值！");
			return false;
		}

		var num = $("#num").val();
		if (isNaN(num)) {
			alertMsg("填写的数量不正确，请填写数值！");
			return false;
		}

		var price = $("#price").val();
		if (isNaN(price)) {
			alertMsg("填写的价格不正确，请填写数值！");
			return false;
		}
		var param = {};
		param["item.purchaseOrder.id"] = $("#purchase_id").val();
		param["item.cdy.id"] = cdyId;
		param["item.clothes"] = clothes;
		param["item.num"] = num;
		param["item.price"] = price;
		param["item.id"] = $("#supplier_item_id").val();
		param["item.remark"] = $("#remark").val();

		return param;
	}

	$("#return_btn").click(function() {
		load("/purchase/query.action", null, $("#main"), null);
	});

	// 如果是已入库商品，采购明细不允许被 操作
	var purchaseState = $("#purchase_state").val();
	if (purchaseState == '2') {
		$("#oper_btn_div").hide();
	}

});
