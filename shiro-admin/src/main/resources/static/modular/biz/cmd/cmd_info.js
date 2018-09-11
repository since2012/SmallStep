/**
 * 初始化字典详情对话框
 */
var CommandInfo = {
    itemTemplate: $("#itemTemplate").html(),
    CommandInfoData: {}
};

/**
 * 关闭此对话框
 */
CommandInfo.close = function () {
    parent.layer.close(window.parent.Command.layerIndex);
};

/**
 * 添加条目
 */
CommandInfo.addItem = function () {
    $("#itemsArea").append(CommandInfo.itemTemplate);
};

/**
 * 删除item
 */
CommandInfo.deleteItem = function (obj) {
    $(obj).parents(".form-group").remove();
};

/**
 * 清除为空的item Dom
 */
CommandInfo.clearNullDom = function () {
    $("[name='contents']").each(function () {
        var content = $(this).find("[name='content']").val();
        if (content == '') {
            $(this).remove();
        }
    });
};

/**
 * 获取数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommandInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 设置收集数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommandInfo.set = function (key, val) {
    this.CommandInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};
/**
 * 收集数据
 */
CommandInfo.collectData = function () {
    CommandInfo.clearNullDom();
    this.set('id')
        .set('name')
        .set('detail');
};

/**
 * 提交添加字典
 */
CommandInfo.addSubmit = function () {
    CommandInfo.clearNullDom();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmd/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Command.table.refresh();
        CommandInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.setData($("form").serialize());
    ajax.start();

};

/**
 * 提交修改
 */
CommandInfo.editSubmit = function () {
    CommandInfo.clearNullDom();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmd/edit", function (data) {
        Feng.success("编辑成功!");
        window.parent.Command.table.refresh();
        CommandInfo.close();
    }, function (data) {
        Feng.error("编辑失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.setData($("form").serialize());
    ajax.start();

};

