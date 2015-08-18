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
		field : 'name',
		title : '品名',
		width : 100,
	});
	columns.push({
		field : 'py',
		title : '拼音缩写',
		width : 150
	});
	columns.push({
		field : 'type',
		title : '型号',
		width : 100
	});

	columns.push({
		field : 'remark',
		title : '备注',
		width : 200
	});

	var opts = {};
	opts.title = "商品管理";
	opts.singleSelect = true;

	grid("commodity_table", "oper_table", columns, "/commodity/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#commodity_table").datagrid('load', param);
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

	$("#add_btn").click(function() {
		var option = {};
		option.width = 380;
		option.height = 220;
		var win = loadDialogPage(null, "添加商品", "/commodity/edit.action", [ {
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
		option.width = 380;
		option.height = 220;

		var selData = $("#commodity_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "修改商品", "/commodity/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/commodity/save.action", param, function(data) {
			if (!data.success) {
				alertMsg(data.message);
			} else {
				$(win).dialog("close");
				$("#commodity_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var name = $("#name_").val();
		if (!name) {
			alertMsg("请填写名称！");
			return false;
		}

		var type = $("#type_").val();
		var remark = $("#remark").val();
		var supplierId = $("#supplier_id").val();

		var param = {};
		param["commodity.id"] = $("#commodity_id").val();
		param["commodity.name"] = name;
		param["commodity.type"] = type;
		param["commodity.remark"] = remark;
		param["commodity.supplier.id"] = supplierId;

		return param;
	}

});
