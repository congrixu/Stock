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
		field : 'commodity-name',
		title : '商品名称',
		width : 150,
	});
	columns.push({
		field : 'commodity-type',
		title : '商品型号',
		width : 150
	});
	columns.push({
		field : 'sup-name',
		title : '供应商',
		width : 200
	});
	columns.push({
		field : 'num',
		title : '销售数量',
		width : 120
	});
	columns.push({
		field : 'price',
		title : '数量',
		width : 100
	});
	columns.push({
		field : 'totalPrice',
		title : '总价格',
		width : 120
	});

	var opts = {};
	opts.title = "销售明细管理";
	opts.singleSelect = true;

	grid("sales_item_table", "oper_table", columns, "/salesitem/queryjson.action?salesId=" + $("#sales_id").val(), opts);

	$("#delete_btn").click(function() {
		var selData = $("#sales_item_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return false;
		}

		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/salesitem/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg("操作失败！");
				} else {
					$("#sales_item_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 240;
		var win = loadDialogPage(null, "添加销售单", "/salesitem/edit.action", [ {
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
		option.height = 240;

		var selData = $("#sales_item_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "添加销售单", "/salesitem/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/salesitem/save.action", param, function(data) {
			if (!data.success) {
				alertMsg("操作失败！");
			} else {
				$(win).dialog("close");
				$("#sales_item_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var cdyId = $("#cdy_id").val();
		if (!cdyId) {
			alertMsg("请选择商品！");
			return false;
		}

		var supId = $("#supplier_id").val();
		if (!supId) {
			alertMsg("请选择商品供应商！");
			return false;
		}

		var num = $("#num").val();
		var price = $("#price").val();

		var param = {};
		param["salesItem.id"] = $("#sales_item_id").val();
		param["salesItem.commodity.id"] = cdyId;
		param["salesItem.sup.id"] = supId;
		param["salesItem.num"] = num;
		param["salesItem.price"] = price;
		param["salesItem.salesOrder.id"] = $("#sales_id").val();
		return param;
	}
	$("#return_btn").click(function() {
		load("/sales/query.action", null, $("#main"), null);
	});

	// 如果是已出库商品，销售明细不允许被 操作
	var salesState = $("#sales_state").val();
	if (salesState == '3') {
		$("#oper_btn_div").hide();
	}
});
