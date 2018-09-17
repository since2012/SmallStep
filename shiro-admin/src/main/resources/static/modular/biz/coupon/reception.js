/**
 * 初始化优惠券详情对话框
 */
var Reception = {
    receptionData: {},
    validateFields: {
        usertel: {
            validators: {
                notEmpty: {
                    message: '手机号码不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
Reception.clearData = function () {
    this.receptionData = {};
}

/**
 * 存储对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Reception.set = function (key, val) {
    this.receptionData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Reception.get = function (key) {
    return $("#" + key).val();
}

/**
 * 收集数据(要提交的字段)
 */
Reception.collectData = function () {
    this.clearData();
    this.set('usertel')
        .set('couponId');
}

/**
 * 验证数据是否为空
 */
Reception.validate = function () {
    $('#defaultForm').data("bootstrapValidator").resetForm();
    $('#defaultForm').bootstrapValidator('validate');
    return $("#defaultForm").data('bootstrapValidator').isValid();
};

/**
 * 页面跳转
 */
Reception.receptSubmit = function () {
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/coupon/reception", function (result) {
        Feng.info("领取成功!请保存好使用码：" + result.data);
    }, function (data) {
        Feng.error("领取失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(Reception.receptionData);
    ajax.start();
}

/**
 * 查询
 */
Reception.mylist = function () {
    var usertel = Reception.get("usertel");
    if (!usertel) {
        Feng.error("未填写手机号码");
    } else {
        window.location.href = "/coupon/mylist/" + usertel;
    }
}

$(function () {
    Feng.initValidator("defaultForm", Reception.validateFields);
});
