/**
 * 初始化卖家信息详情对话框
 */
var SellerInfo = {
    sellerInfoData: {}
};

/**
 * 清除数据
 */
SellerInfo.clearData = function () {
    this.sellerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SellerInfo.set = function (key, val) {
    this.sellerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SellerInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 收集数据
 */
SellerInfo.collectData = function () {
    SellerInfo.clearData();
    this
        .set('id')
        .set('name')
        .set('addr')
        .set('status')
}

/**
 * 提交添加
 */
SellerInfo.addSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seller/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Seller.table.refresh();
        SellerInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sellerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SellerInfo.editSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seller/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Seller.table.refresh();
        SellerInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sellerInfoData);
    ajax.start();
}

/**
 * 关闭此对话框
 */
SellerInfo.close = function () {
    parent.layer.close(window.parent.Seller.layerIndex);
}

$(function () {
    $("#status").val($("#statusVal").val());
});
