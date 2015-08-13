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
    field : 'type',
    title : '型号',
    width : 150
  });
  columns.push({
    field : 'py',
    title : '拼音缩写',
    width : 150
  });
  columns.push({
    field : 'remark',
    title : '备注',
    width : 400
  });
  
  var opts = {};
  opts.title = "供应商";
  opts.singleSelect = true;

  grid("search_service_table", "oper_table", columns, "/serviceinterface/search/queryJson.action", opts);

  $("#search_sel_tree").combotree({
    url : CONTEXT_PATH + '/serviceinterface/catalog/queryCatalogTree.action'
  });

  $("#query_btn").click(function() {
    var param = buildSearchData();
    $("#commodity_table").datagrid('load', param);
  });

  var buildSearchData = function() {
    var param = {};
    var name = $.trim($("#search_name").val());
    if (name)
      param.name = name;

    return param;
  }

  $("#delete_btn").click(function() {
    var selData = $("#search_service_table").datagrid("getSelected");
    if (!selData) {
      alertMsg("请选择您要删除的数据！");
      return;
    }
    confirmMsg("您确定要删除选择中的数据吗？", "请确认", function() {
      ajax("post", "/serviceinterface/search/delete.action", {
        id : selData.id
      }, function(data) {
        if (!data.success) {
          alertMsg(data.message);
        } else {
          $("#search_service_table").datagrid("reload");
        }
      }, null);
    });
  });

  $("#add_btn").click(function() {
    var option = {};
    option.width = 550;
    option.height = 605;
    var win = loadDialogPage(null, "添加接口", "/serviceinterface/search/edit.action", [ {
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

  $("#modify_btn").click(
      function() {
        var option = {};
        option.width = 550;
        option.height = 605;

        var selData = $("#search_service_table").datagrid("getSelected");
        if (!selData) {
          alertMsg("请选择您要修改的数据！");
          return;
        }

        var win = loadDialogPage(null, "修改服务", "/serviceinterface/search/edit.action?id=" + selData.id
            + "&interfaceId=" + selData.id, [ {
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
    ajax("post", "/serviceinterface/search/save.action", param, function(data) {
      if (!data.success) {
        alertMsg(data.message);
      } else {
        $(win).dialog("close");
        $("#search_service_table").datagrid("reload");
      }
    }, null);
  }

  var buildData = function() {

    var name = $("#name").val();
    if (!name) {
      alertMsg("请填写接口名称！");
      return false;
    }

    var datasource = $("#sevice_data_source_id").val();
    if (!datasource) {
      alertMsg("请选择数据源！");
      return false;
    }

    var selTypeTree = $("#sel_tree").combotree("tree").tree("getSelected");
    if (!selTypeTree) {
      alertMsg("请选择服务分类！");
      return false;
    }
    var selType = selTypeTree.id;

    var exeTypeRadio = $("input[name=exeType]:checked");
    if (!exeTypeRadio || exeTypeRadio.length==0) {
      alertMsg("请选择执行类型！");
      return false;
    }

    var exeType = exeTypeRadio.val();

    var showColumn = $("#show_column").val();

    var exeContent = $("#execute_content").val();
    if (!exeContent) {
      alertMsg("请填写执行内容！");
      return false;
    }

    var groupSortSql = $("#group_sort_sql").val();

    var paramNote = $("#param_note").val();

    var returnResultNote = $("#return_result_note").val();

    var note = $("#note").val();

    var param = {
      id : $("#service_search_config_id").val(),
      name : name,
      datasource : datasource,
      selType : selType,
      exeType : exeType,
      showColumn : showColumn,
      exeContent : exeContent,
      groupSortSql : groupSortSql,
      paramNote : paramNote,
      returnResultNote : returnResultNote,
      note : note
    };

    return param;
  }

  $("#test_run_btn").click(
      function() {
        var option = {};
        option.width = 550;
        option.height = 550;

        var selData = $("#search_service_table").datagrid("getSelected");
        if (!selData) {
          alertMsg("请选择您要测试的数据！");
          return;
        }

        loadDialogPage(null, "测试服务", "/serviceinterface/search/runView.action?interfaceId="
            + selData["serviceInterface-serviceInterfaceId"], null, true, option, true);
      });

});
