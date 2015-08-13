$(function() {
  // $("#menu, #main").css("overflow", "auto").css("padding", "10px");

  // 初始化菜单
  $("#menu").bind("loaded.jstree", function(event, data) {
    data.inst.open_all();
  }).bind("select_node.jstree", function(event, data) {
    var url = data.rslt.obj.children("a").attr("href");
    load(url, null, $("#main"));
  }).jstree({
    plugins : [ "themes", "html_data", "ui" ],
    themes : {
      theme : "classic"
    },
    ui : {
      select_limit : 1
    }
  });
  $("#logout_btn").click(function() {
    window.location = CONTEXT_PATH + "/logout.action";
  });
});
