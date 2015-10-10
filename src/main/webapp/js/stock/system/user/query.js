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

	grid("user_table", "oper_table", columns, "/system/user/queryjson.action", opts);

	$("#query_btn").click(function() {
		var param = buildSearchData();
		$("#user_table").datagrid('load', param);
	});

	var buildSearchData = function() {
		var param = {};
		var name = $.trim($("#search_name").val());
		if (name)
			param.name = name;

		return param;
	}

	$("#delete_btn").click(function() {
		var selData = $("#user_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要删除的数据！");
			return;
		}
		confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
			ajax("post", "/system/user/delete.action", {
				id : selData.id
			}, function(data) {
				if (!data.success) {
					alertMsg(data.message);
				} else {
					$("#user_table").datagrid("reload");
				}
			}, null);
		});
	});

	$("#add_btn").click(function() {
		var option = {};
		option.width = 400;
		option.height = 390;
		var win = loadDialogPage(null, "添加用户", "/system/user/edit.action", [ {
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

		var selData = $("#user_table").datagrid("getSelected");
		if (!selData) {
			alertMsg("请选择您要修改的数据！");
			return;
		}

		var win = loadDialogPage(null, "修改用户", "/system/user/edit.action?id=" + selData.id, [ {
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
		ajax("post", "/system/user/save.action", param, function(data) {
			if (!data.success) {
				alertMsg(data.message);
			} else {
				$(win).dialog("close");
				$("#user_table").datagrid("reload");
			}
		}, null);
	}

	var buildData = function() {

		var name = $("#name_").val();
		if (!name) {
			alertMsg("请填写名称！");
			return false;
		}

		var userId = $("#user_id").val();
		if (!userId) {
			alertMsg("请填写登录名！");
			return false;
		}
		var pwd = $("#user_pwd").val();
		if (!pwd) {
			alertMsg("请填写密码！");
			return false;
		}
		var phone = $("#phone").val();
		var addr = $("#addr").val();
		var idCard = $("#id_card").val();
		var birthday = $("#birthday_date").val();
		var pwd = $("#user_pwd").val();

		var param = {};
		param["user.id"] = $("#user_pk_id").val();
		param["user.userId"] = userId;
		param["user.name"] = name;
		param["user.phone"] = phone;
		param["user.addr"] = addr;
		param["user.idCard"] = idCard;
		param["user.birthday"] = birthday;
		param["user.pwd"] = pwd;

		return param;
	}

	var userPkId = $("#user_pk_id").val();
	if (userPkId) {
		$("#user_id").addClass("disabled");
	}

});
