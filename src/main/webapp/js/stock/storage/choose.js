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
    title : '品名',
    width : 100
  });
  columns.push({
    field : 'num',
    title : '库存数量',
    width : 100
  });
  columns.push({
    field : 'cdy-py',
    title : '拼音缩写',
    width : 150
  });
  columns.push({
    field : 'cdy-type',
    title : '型号',
    width : 100
  });
  columns.push({
    field : 'cdy-remark',
    title : '备注',
    width : 200
  });

  var opts = {};
  opts.title = "选择商品";
  opts.singleSelect = true;

  grid("commodity_choose_table", "commodity_choose_oper_table", columns, "/storage/queryjson.action", opts);

  $("#query_btn").click(function() {
    var param = buildSearchData();
    $("#commodity_choose_table").datagrid('load', param);
  });

  $("#commodity_choose_btn").click(function() {
    var selData = $("#commodity_choose_table").datagrid("getSelected");
    if (!selData) {
      alertMsg("请选择您要选择的数据！");
      return;
    }

    var id = selData.id;
    var name = selData.name;
    var callback = $("#commodity_choose_table").data("callback");
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
