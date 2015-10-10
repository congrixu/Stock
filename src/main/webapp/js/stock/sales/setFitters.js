$(function() {
	$("#fitters_user").click(function() {
		var option = {};
		option.width = 600;
		option.height = 350;

		var url = "/system/user/choose.action";
		var win = loadDialogPage($("#choose_user_div"), "选择安装工", url, [ {
			text : "确定",
			iconCls : "easyui-icon-save",
			handler : function() {
				var selData = $("#user_choose_user_table").datagrid("getSelected");
				if (!selData) {
					alertMsg("请选择您要选择数据", null);
					return false;
				}
				$("#fitters_user").val(selData.name);
				$("#fitters_user_id").val(selData.id);
				$(win).dialog("close");
			}
		} ], true, option, false);
	});
});
