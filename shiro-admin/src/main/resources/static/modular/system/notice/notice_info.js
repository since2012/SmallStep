/**
 * 初始化通知详情对话框
 */
var NoticeInfo = {
    noticeInfoData: {},
    editor: null,
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
NoticeInfo.clearData = function () {
    this.noticeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NoticeInfo.set = function (key, value) {
    this.noticeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NoticeInfo.get = function (key) {
    return $("#" + key).val();
}

//String转义
NoticeInfo.escapeHTML = function (a) {
    a = "" + a;
    return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;");
}

//String 反转义
NoticeInfo.unescapeHTML = function (a) {
    a = "" + a;
    return a.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&apos;/g, "'");
}

/**
 * 收集数据
 */
NoticeInfo.collectData = function () {
    this.clearData();
    var html = NoticeInfo.editor.txt.html();
    this.noticeInfoData['content'] = NoticeInfo.escapeHTML(html);
    this.set('id').set('title');
}

/**
 * 关闭此对话框
 */
NoticeInfo.close = function () {
    parent.layer.close(window.parent.Notice.layerIndex);
}

/**
 * 验证数据是否为空
 */
NoticeInfo.validate = function () {
    $('#noticeInfoForm').data("bootstrapValidator").resetForm();
    $('#noticeInfoForm').bootstrapValidator('validate');
    return $("#noticeInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
NoticeInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Notice.table.refresh();
        NoticeInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NoticeInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Notice.table.refresh();
        NoticeInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("noticeInfoForm", NoticeInfo.validateFields);

    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html(NoticeInfo.unescapeHTML($("#contentVal").val()));
    NoticeInfo.editor = editor;
});
