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
  opts.title = "选择供应商";
  opts.singleSelect = true;

  grid("supplier_choose_table", "supplier_choose_oper_table", columns, "/supplier/queryjson.action", opts);

  $("#query_btn").click(function() {
    var param = buildSearchData();
    $("#supplier_choose_table").datagrid('load', param);
  });

  $("#choose_supplier_btn").click(function() {
    var selData = $("#supplier_choose_table").datagrid("getSelected");
    if (!selData) {
      alertMsg("请选择您要选择的数据！");
      return;
    }

    var id = selData.id;
    var name = selData.name;
    var callback = $("#supplier_choose_table").data("callback");
    if (typeof callback == "function") {
      callback(id, name);
    }
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

});
