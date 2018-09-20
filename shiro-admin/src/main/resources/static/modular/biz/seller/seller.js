/**
 * 卖家信息管理初始化
 */
var Seller = {
    id: "sellerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    queryData: {}
};

/**
 * 清除数据
 */
Seller.clearData = function () {
    this.queryData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Seller.set = function (key, value) {
    if (typeof value == "undefined") {
        if (typeof $("#" + key).val() == "undefined") {
            var str = "";
            var ids = "";
            $("input[name='" + key + "']:checkbox").each(function () {
                if (true == $(this).is(':checked')) {
                    str += $(this).val() + ",";
                }
            });
            if (str) {
                if (str.substr(str.length - 1) == ',') {
                    ids = str.substr(0, str.length - 1);
                }
            } else {
                $("input[name='" + key + "']:radio").each(function () {
                    if (true == $(this).is(':checked')) {
                        ids = $(this).val()
                    }
                });
            }
            this.queryData[key] = ids;
        } else {
            this.queryData[key] = $("#" + key).val();
        }
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Seller.get = function (key) {
    return $("#" + key).val();
};

/**
 * 收集数据
 */
Seller.collectData = function () {
    Seller.clearData();
    this.set("name").set('status');
};

/**
 * 查询卖家信息列表
 */
Seller.search = function () {
    Seller.collectData();
    Seller.table.refresh({query: Seller.queryData});
};

//高级重置
Seller.reset = function () {
    $("form .form-group input").val("");
};

/**
 * 初始化表格的列
 */
Seller.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'addr', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Seller.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Seller.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加卖家信息
 */
Seller.openAddPage = function () {
    var index = layer.open({
        type: 2,
        title: '添加卖家信息',
        area: ['600px', '320px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/seller/add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看卖家信息详情
 */
Seller.openEditPage = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '卖家信息详情',
            area: ['600px', '320px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/seller/edit/' + Seller.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 状态切换
 */
Seller.switch = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/seller/switch", function (data) {
            Feng.success("切换成功!");
            Seller.table.refresh();
        }, function (data) {
            Feng.error("切换失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sellerId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除卖家
 */
Seller.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/seller/delete", function (data) {
            Feng.success("删除成功!");
            Seller.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sellerId", this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = Seller.initColumn();
    var table = new BSTable(Seller.id, "/seller/list", defaultColunms);
    table.setPaginationType("server");
    Seller.table = table.init();

    $("#biz").attr("class", "active");
    $("#seller").attr("class", "active");
});
