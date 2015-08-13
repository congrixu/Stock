window.onerror = function() {
  if (typeof console == "object" && typeof console.log == "function") {
    console.log(arguments);
  }
}
window.log = function() {
  if (typeof console == "object" && typeof console.log == "function") {
    console.log(arguments);
  }
}

window.loadDialogPage = function(el, title, url, buttons, modal, options, isRemove) {
  var dom;

  if (el) {
    dom = $(el);
  } else {
    dom = $("<div style='display:none;'></div").appendTo(document.body);
  }

  buttons = buttons || [];
  var oper = true;
  $.each(buttons, function(i, obj) {
    if (obj.text == "取消") {
      oper = false;
      return false;
    }
  });
  if (oper) {
    buttons.push({
      text : "取消",
      iconCls : "easyui-icon-cancel",
      handler : function() {
        dom.dialog("close");
      }
    });
  }

  options = options || {};
  if (title)
    options.title = title;
  options.closeText = "";
  options.modal = !!modal;
  options.buttons = buttons;
  options.resizable = true;

  if (!url) {
    dom.dialog(options);
  } else {
    dom.load(CONTEXT_PATH + url, options.data, function(res, status, xhr) {
      if (status !== "success") {
        dom.html(res);
      }
      dom.dialog(options);
    });
  }

  if (isRemove) {
    dom.dialog({
      onClose : function() {
        $(dom.parent()).remove();
      }
    });
  }

  return dom;
};

window.ajax = function(method, url, data, callback, options) {

  options = options || {};

  options.type = method.toUpperCase();
  options.url = CONTEXT_PATH + url;
  options.data = data;
  options.contentType = "application/x-www-form-urlencoded; charset=utf-8";
  options.dataType = "json";
  options.success = callback;
  options.complete = function(xhr, textStatus) {
    closeLoading()
    if (!textStatus == 'success') {
      return;
    }
  }
  openLoading();
  $.ajax(options);
};

window.load = function(url, data, toEl, callback) {
  if (!toEl) {
    toEl = $("<div></div>")
    $(document.body).append(toEl);
  }

  $(toEl).load(CONTEXT_PATH + url, data, function(resp, textStatus, xhr) {
    if (textStatus == 'success') {
      if ((typeof callback) == "function") {
        callback();
      }
    } else {
      $(toEl).html(resp);
    }
  });
};

window.openLoading = function() {
  var loading = $("#loading");
  if (loading.length == 0) {
    var loadingImg = "<img src='" + CONTEXT_PATH + "/img/loading.gif' width='441' height='250'>"
    loading = $("<div id='loading' style='padding:0;'>" + loadingImg + "</div").appendTo(document.body);
    loading.dialog({
      title : "处理中，请等待...",
      closable : false,
      resizable : false,
      modal : true,
      width : 445,
      height : 400,
      onBeforeClose : function(event, ui) {
        loading.data("n", 0);
      }
    });
    loading.data("n", 0);
  }
  loading.data("n", loading.data("n") + 1);
  loading.dialog("open");
};
window.closeLoading = function() {
  var loading = $("#loading");
  if (loading.length == 0) {
    return;
  }
  if (loading.data("n") > 1) {
    loading.data("n", loading.data("n") - 1);
  } else {
    loading.dialog("close");
  }
};

window.grid = function(tableEl, tableToolbar, tableColumns, url, opts) {
  var options = {};
  options.title = "&nbsp;";
  options.collapsible = false;// 可折叠
  options.modal = false;// 遮罩
  options.striped = true;// 显示条纹
  options.nowrap = true;// 截取
  options.rownumbers = true;// 设置为true将显示行数
  options.pagination = true;
  if (!(opts && opts.height)) {
    options.fit = true;
  }
  options.url = CONTEXT_PATH + url;

  options.columns = [ tableColumns ];

  if (tableToolbar && typeof tableToolbar == 'string') {
    options.toolbar = "#" + tableToolbar;
  } else if (tableToolbar && typeof tableToolbar == 'object') {
    options.toolbar = tableToolbar;
  }

  options.onClickRow = function(rowIndex) {
    $('#' + tableEl).datagrid('beginEdit', rowIndex);
  }
  options.onHeaderContextMenu = function(e, field) {
    e.preventDefault();
    if (!$('#tmenu').length) {
      createColumnMenu(tableEl);
    }
    $('#tmenu').menu('show', {
      left : e.pageX,
      top : e.pageY
    });
  }

  if (opts && typeof opts != "undefined") {
    for ( var key in opts) {
      options[key] = opts[key];
    }
  }

  if (options.pagination) {
    options.pageSize = 30
  }

  var dataGrid = $('#' + tableEl).datagrid(options);

  if (tableToolbar && typeof tableToolbar == 'string') {
    $.parser.parse("#" + tableToolbar);
  }

  dataGrid.endAllEdit = function() {
    var rows = $('#' + tableEl).datagrid('getRows');
    for ( var i = 0; i < rows.length; i++) {
      $('#' + tableEl).datagrid('endEdit', i);
    }
  }

  return dataGrid;
}

function createColumnMenu(tableEl) {
  var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo(document.body);
  var fields = $('#' + tableEl).datagrid('getColumnFields');
  for ( var i = 0; i < fields.length; i++) {
    var fieldVals = $('#' + tableEl).datagrid('getColumnOption', fields[i]);
    // console.log(fields[i] + " " + fieldVals.hidden)
    if (fieldVals.hidden) {
      $('<div iconCls="easyui-icon-empty" name="' + fields[i] + '"/>').html(fieldVals.title).appendTo(tmenu);
    } else {
      $('<div iconCls="easyui-icon-ok" name="' + fields[i] + '"/>').html(fieldVals.title).appendTo(tmenu);
    }
  }
  tmenu.menu({
    onClick : function(item) {
      if (item.iconCls == 'easyui-icon-ok') {
        $('#' + tableEl).datagrid('hideColumn', item.name);
        tmenu.menu('setIcon', {
          target : item.target,
          iconCls : 'easyui-icon-empty'
        });
      } else {
        $('#' + tableEl).datagrid('showColumn', item.name);
        tmenu.menu('setIcon', {
          target : item.target,
          iconCls : 'easyui-icon-ok'
        });
      }
    }
  });
}

window.gridTree = function(tableEl, tableToolbar, tableColumns, url, opts) {
  var options = {};
  options.title = "&nbsp;";
  options.collapsible = false;// 可折叠
  options.modal = false;// 遮罩
  options.striped = true;// 显示条纹
  options.nowrap = true;// 截取
  options.rownumbers = true;// 设置为true将显示行数
  options.pagination = false;
  if (!(opts && opts.height)) {
    options.fit = true;
  }
  options.url = CONTEXT_PATH + url;

  options.columns = [ tableColumns ];

  if (tableToolbar && typeof tableToolbar == 'string') {
    options.toolbar = "#" + tableToolbar;
  }

  if (opts && typeof opts != "undefined") {
    for ( var key in opts) {
      options[key] = opts[key];
    }
  }
  var dataGridTree = $('#' + tableEl).treegrid(options);

  if (tableToolbar && typeof tableToolbar == 'string') {
    $.parser.parse("#" + tableToolbar);
  }

  return dataGridTree;
}

/**
 * 替换字符
 */
String.prototype.replaceAll = function(s1, s2) {
  return this.replace(new RegExp(s1, "gm"), s2);
}

/**
 * 是否以指定字符结束
 * 
 */
String.prototype.endWith = function(str) {
  if (str == null || str == "" || this.length == 0 || str.length > this.length)
    return false;
  if (this.substring(this.length - str.length) == str)
    return true;
  else
    return false;
  return true;
}

/**
 * 是否以指定字符开始
 * 
 */
String.prototype.startWith = function(str) {
  if (str == null || str == "" || this.length == 0 || str.length > this.length)
    return false;
  if (this.substr(0, str.length) == str)
    return true;
  else
    return false;
  return true;
}

window.alertMsg = function(msg, title) {
  if (!title)
    title = "提示";
  $.messager.alert(title, msg);
}

window.confirmMsg = function(msg, title, callback) {
  var oper = false;
  if (!title)
    title = "请确认";
  $.messager.confirm(title, msg, function(r) {
    if (r) {
      callback();
    }
  });
}