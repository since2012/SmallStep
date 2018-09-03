/**
 * 初始化字典详情对话框
 */
var DictInfo = {
    count: $("#itemSize").val(),
    dictName: '',//字典的名称
    dictCode: '',//字典类型编码
    dictTips: '',//字典备注
    mutiString: ''		//拼接字符串内容(拼接字典条目)
};

/**
 * item获取新的id
 */
DictInfo.newId = function () {
    if (this.count == undefined) {
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
DictInfo.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
DictInfo.addItem = function () {
    $("#itemsArea").append($("#itemTemplate").html());
    $("#itemsArea .form-group:last").attr("id", DictInfo.newId());
};

/**
 * 删除item
 */
DictInfo.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DictInfo.clearNullDom = function () {
    $("[name='dictItem']").each(function () {
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        if (num == '' || name == '') {
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
DictInfo.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function () {
        var code = $(this).find("[name='itemCode']").val();
        var name = $(this).find("[name='itemName']").val();
        var num = $(this).find("[name='itemNum']").val();
        mutiString = mutiString + (code + ":" + name + ":" + num + ";");
    });
    this.dictName = $("#dictName").val();
    this.dictCode = $("#dictCode").val();
    this.dictTips = $("#dictTips").val();
    this.mutiString = mutiString;
};


/**
 * 提交添加字典
 */
DictInfo.addSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dict/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Dict.table.refresh();
        DictInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictName', this.dictName);
    ajax.set('dictCode', this.dictCode);
    ajax.set('dictTips', this.dictTips);
    ajax.set('dictValues', this.mutiString);
    ajax.start();
};

/**
 * 提交修改
 */
DictInfo.editSubmit = function () {
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/dict/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Dict.table.refresh();
        DictInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictId', $("#dictId").val());
    ajax.set('dictName', this.dictName);
    ajax.set('dictCode', this.dictCode);
    ajax.set('dictTips', this.dictTips);
    ajax.set('dictValues', this.mutiString);
    ajax.start();
};
