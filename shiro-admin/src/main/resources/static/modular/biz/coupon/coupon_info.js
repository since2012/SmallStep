/**
 * 初始化优惠券详情对话框
 */
var CouponInfo = {
    couponInfoData: {},
    sellerZtree: null,
    validateFields: {
        sellername: {
            validators: {
                notEmpty: {
                    message: '卖家不能为空'
                }
            }
        },
        beginDay: {
            validators: {
                notEmpty: {
                    message: '起始时间不能为空'
                }
            }
        },
        endDay: {
            validators: {
                notEmpty: {
                    message: '结束时间不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CouponInfo.clearData = function () {
    this.couponInfoData = {};
}

/**
 * 存储对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfo.set = function (key, val) {
    this.couponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponInfo.close = function () {
    parent.layer.close(window.parent.Coupon.layerIndex);
}

/**
 * 收集数据(要提交的字段)
 */
CouponInfo.collectData = function () {
    this.clearData();
    this.set('sellerid')
        .set('beginday')
        .set('endday')
        .set('total');
}

/**
 * 验证数据是否为空
 */
CouponInfo.validate = function () {
    $('#sellerInfoForm').data("bootstrapValidator").resetForm();
    $('#sellerInfoForm').bootstrapValidator('validate');
    return $("#sellerInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
CouponInfo.addSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/coupon/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Coupon.table.refresh();
        CouponInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.set(this.couponInfoData);
    ajax.start();
}

/**
 * 显示卖家的树
 *
 * @returns
 */
CouponInfo.showSellerNameSelectTree = function () {
    Feng.showInputTree("sellername", "sellerMenu");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
CouponInfo.onClickNode = function (e, treeId, treeNode) {
    $("#sellername").attr("value", CouponInfo.sellerZtree.getSelectedVal());
    $("#sellerid").attr("value", treeNode.id);
};

$(function () {
    Feng.initValidator("sellerInfoForm", CouponInfo.validateFields);

    var sellerTree = new $ZTree("sellerTree", "/seller/tree");
    sellerTree.bindOnClick(CouponInfo.onClickNode);
    sellerTree.init();
    CouponInfo.sellerZtree = sellerTree;

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'yyyy-mm-dd'
    });
});
