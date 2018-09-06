/**
 * 初始化字典详情对话框
 */
var MaintainInfo = {
    itemTemplate: $("#itemTemplate").html(),
    CommandInfoData: {}
};

/**
 * 关闭此对话框
 */
MaintainInfo.close = function () {
    parent.layer.close(window.parent.Maintain.layerIndex);
};

/**
 * 添加条目
 */
MaintainInfo.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
};

/**
 * 删除item
 */
MaintainInfo.deleteItem = function (obj) {
    $(obj).parents(".form-group").remove();
};

/**
 * 清除为空的item Dom
 */
MaintainInfo.clearNullDom = function () {
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
MaintainInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 设置收集数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaintainInfo.set = function (key, val) {
    this.CommandInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};
/**
 * 收集数据
 */
MaintainInfo.collectData = function () {
    MaintainInfo.clearNullDom();
    this.set('id')
        .set('name')
        .set('description');
};

/**
 * 提交添加字典
 */
MaintainInfo.addSubmit = function () {
    MaintainInfo.clearNullDom();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/maintain/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Maintain.table.refresh();
        MaintainInfo.close();
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
MaintainInfo.editSubmit = function () {
    MaintainInfo.clearNullDom();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/maintain/edit", function (data) {
        Feng.success("编辑成功!");
        window.parent.Maintain.table.refresh();
        MaintainInfo.close();
    }, function (data) {
        Feng.error("编辑失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.setData($("form").serialize());
    ajax.start();

};

