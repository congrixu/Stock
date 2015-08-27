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
		field : 'cdy-id',
		title : '商品ID',
		width : 100,
		hidden : true
	});
	columns.push({
		field : 'cdy-name',
		title : '商品名称',
		width : 200,
	});
	columns.push({
		field : 'cdy-type',
		title : '型号',
		width : 150
	});
	columns.push({
		field : 'num',
		title : '数量',
		width : 100
	});

	var opts = {};
	opts.title = "库存管理";
	opts.singleSelect = true;

	grid("storage_table", "oper_table", columns, "/storage/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#storage_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var name = $.trim($("#search_name").val());
		var py = $.trim($("#search_py").val());
		if (name)
			param.name = name;
		if (py)
			param.py = py;

		return param;
	}

	$("#inventory_btn").click(function() {

		var selData = $("#storage_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要盘点的数据", null);
			return false;
		}

		var option = {};
		option.width = 380;
		option.height = 200;
		var win = loadDialogPage(null, "盘点商品", "/storage/inventoryview.action", [ {
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
		ajax("post", "/storage/inventory.action", param, function(data) {
			if (!data.success) {
				alertMsg("盘点操作执行错误！");
			} else {
				$(win).dialog("close");
				$("#storage_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {
		var selData = $("#storage_table").datagrid("getSelected");
		var inventoryNum = $("#inventory_num").val();
		if (!inventoryNum) {
			alertMsg("请填写盘点数量！");
			return false;
		}

		if (isNaN(inventoryNum)) {
			alertMsg("请填写正确的盘点数量！");
			return false;
		}

		var param = {};
		param["inventory.num"] = inventoryNum;
		param["inventory.firstNum"] = selData.num;
		param["inventory.cdy.id"] = selData["cdy-id"];
		param["inventory.remark"] = $("#inventory_remark").val();
		return param;
	}

});
