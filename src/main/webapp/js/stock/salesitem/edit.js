$(function() {
	$("#cdy_name").click(function() {

		var option = {};
		option.width = 600;
		option.height = 350;

		var url = "/storage/choose.action";
		var win = loadDialogPage($("#choose_commodity_div"), "选择商品", url, [ {
			text : "确定",
			iconCls : "easyui-icon-save",
			handler : function() {
				var selData = $("#commodity_choose_table").datagrid("getSelected");
				if (!selData) {
					alertMsg("请选择您要选择数据", null);
					return false;
				}
				$("#cdy_name").val(selData.name);
				$("#cdy_id").val(selData.id);
				$(win).dialog("close");
			}
		} ], true, option, false);

	});

	$("#supplier_name").click(function() {

		var option = {};
		option.width = 600;
		option.height = 350;

		var url = "/supplier/choose.action";
		var win = loadDialogPage($("#choose_supplier_div"), "选择供应商", url, [ {
			text : "确定",
			iconCls : "easyui-icon-save",
			handler : function() {
				var selData = $("#supplier_choose_table").datagrid("getSelected");
				if (!selData) {
					alertMsg("请选择您要选择数据", null);
					return false;
				}
				$("#supplier_name").val(selData.name);
				$("#supplier_id").val(selData.id);
				$(win).dialog("close");
			}
		} ], true, option, false);
	});
});
