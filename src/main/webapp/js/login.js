$(function() {
  $("#user_id").focus();
  
  var success = $("#error_hid").val();
  if (success === "false") {
    alert("用户名或密码错误，请重新登录！")
  }
});
// 调整页面布局
(function() {
  $("#login-form li.padding").css("padding-left", $("#login-form li label:first").width());

  var dWidth = $(document).width();
  var dHeight = $(document).height();
  var cWidth = $("#login-container").width();
  var cHeight = $("#login-container").height();
  $("#login-container").css("width", cWidth).css("height", cHeight);
  $("#login-container").offset({
    left : (dWidth - cWidth) / 2,
    top : (dHeight - cHeight) / 2
  });
  $("#login-container").css("visibility", "visible");
})();
