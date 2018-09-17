/**
 * 优惠券管理初始化
 */
var Coupon = {
    id: "couponTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 清除数据
 */
Coupon.clearData = function () {
    this.queryData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Coupon.set = function (key, value) {
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
Coupon.get = function (key) {
    return $("#" + key).val();
};

/**
 * 收集数据
 */
Coupon.collectData = function () {
    Coupon.clearData();
    this.set("usertel").set('valid');
};

//高级重置
Coupon.reset = function () {
    $("form .form-group input").val("");
};
/**
 * 查询优惠券列表
 */
Coupon.search = function () {
    Coupon.collectData();
    Coupon.table.refresh({query: Coupon.queryData});
};
/**
 * 初始化表格的列
 */
Coupon.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '商家', field: 'sellername', visible: true, align: 'center', valign: 'middle'},
        {title: '起始日期', field: 'beginday', visible: true, align: 'center', valign: 'middle'},
        {title: '结束日期', field: 'endday', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号码', field: 'usertel', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '领取时间', field: 'receptday', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Coupon.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Coupon.seItem = selected[0];
        return true;
    }
};
/**
 * 点击添加优惠券
 */
Coupon.openGenPage = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠券',
        area: ['700px', '350px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/coupon/coupon_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码
 */
Coupon.showQrcode = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码',
            area: ['680px', '380px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/coupon/qrcode/' + Coupon.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 使用优惠券
 */
Coupon.useCoupon = function () {
    var tel = Coupon.get("tel");
    var code = Coupon.get("code");
    if (!$.trim(code) || !$.trim(code)) {
        Feng.error("数据不完整，请重新填写");
    } else {
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/coupon/use", function (data) {
            Feng.success("操作成功!");
            Coupon.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        //配置提交的数据
        ajax.set("tel", tel);
        ajax.set("code", code);
        ajax.start();

    }
};

/**
 * 删除优惠券
 */
Coupon.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/coupon/delete", function (data) {
            Feng.success("删除成功!");
            Coupon.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponId", this.seItem.id);
        ajax.start();
    }
};

function init() {

    var BootstrapTable = $.fn.bootstrapTable.Constructor;
    BootstrapTable.prototype.onSort = function (event) {
        var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
            $this_ = this.$header.find('th').eq($this.index()),
            sortName = this.header.sortNames[$this.index()];

        this.$header.add(this.$header_).find('span.order').remove();

        if (this.options.sortName === $this.data('field')) {
            this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            this.options.sortName = sortName || $this.data('field');
            this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
        }
        this.trigger('sort', this.options.sortName, this.options.sortOrder);

        $this.add($this_).data('order', this.options.sortOrder);

        // Assign the correct sortable arrow
        this.getCaret();

        if (this.options.sidePagination === 'server') {
            this.initServer(this.options.silentSort);
            return;
        }

        this.initSort();
        this.initBody();
    };
    BootstrapTable.prototype.getCaret = function () {
        var that = this;

        $.each(this.$header.find('th'), function (i, th) {
            var sortName = that.header.sortNames[i];
            $(th).find('.sortable').removeClass('desc asc').addClass((sortName || $(th).data('field')) === that.options.sortName ? that.options.sortOrder : 'both');
        });
    };
}

$(function () {
    var defaultColunms = Coupon.initColumn();
    var table = new BSTable(Coupon.id, "/coupon/list", defaultColunms);
    table.setPaginationType("server");
    Coupon.table = table.init();

    $("#biz").attr("class", "active");
    $("#coupon").attr("class", "active");
});