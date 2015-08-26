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
		title : '供应商名称',
		width : 100,
	});
	columns.push({
		field : 'py',
		title : '拼音缩写',
		width : 150
	});
	columns.push({
		field : 'mobile',
		title : '手机',
		width : 100
	});
	columns.push({
		field : 'phone',
		title : '电话',
		width : 100
	});
	columns.push({
		field : 'fax',
		title : '传真',
		width : 100
	});
	columns.push({
		field : 'addr',
		title : '地址',
		width : 150
	});
	columns.push({
		field : 'bankInfo',
		title : '银行信息',
		width : 400
	});

	var opts = {};
	opts.title = "供应商管理";
	opts.singleSelect = true;

	grid("supplier_table", "oper_table", columns, "/supplier/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#supplier_table").datagrid('load', param);
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

	$("#delete_btn").click(function() {
		var selData = $("#supplier_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return;
		}
		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/supplier/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg(data.message);
				} else {
					$("#supplier_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 390;
		var win = loadDialogPage(null, "添加供应商", "/supplier/edit.action", [ {
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
		option.height = 390;

		var selData = $("#supplier_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "修改供应商", "/supplier/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/supplier/save.action", param, function(data) {
			if (!data.success) {
				alertMsg(data.message);
			} else {
				$(win).dialog("close");
				$("#supplier_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var name = $("#name_").val();
		if (!name) {
			alertMsg("请填写名称！");
			return false;
		}

		var phone = $("#phone").val();
		var addr = $("#addr").val();
		var fax = $("#fax").val();
		var mobile = $("#mobile").val();
		var bankInfo = $("#bank_info").val();

		var param = {};
		param["supplier.id"] = $("#supplier_id").val();
		param["supplier.name"] = name;
		param["supplier.phone"] = phone;
		param["supplier.addr"] = addr;
		param["supplier.fax"] = fax;
		param["supplier.mobile"] = mobile;
		param["supplier.bankInfo"] = bankInfo;

		return param;
	}

});
