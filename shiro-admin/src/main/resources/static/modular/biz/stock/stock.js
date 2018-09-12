/**
 * 日志管理初始化
 */
var Stock = {
    id: "stockTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    queryData: {}
};

/**
 * 初始化表格的列
 */


Stock.initColumn = function () {
    return [
        {field: 'selectItem', radio: true, checkbox: false},
        {
            title: '序号', field: '', visible: true, align: 'center', valign: 'middle', sortable: false,
            formatter: function (value, row, index) {
                return index + 1;
            }
        },
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {
            title: '促销价', field: 'saleprice', align: 'center', valign: 'middle', sortable: true,
            formatter: function (value, row, index) {
                return '<span class="label label-warning">' + value + '</span>';
            }
        },
        {title: '原价', field: 'primeprice', align: 'center', valign: 'middle', sortable: true},
        {title: '库存量', field: 'total', align: 'center', valign: 'middle', sortable: true},
        {title: '开始时间', field: 'begintime', align: 'center', valign: 'middle', sortable: true},
        {title: '结束时间', field: 'endtime', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime',visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Stock.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Stock.seItem = selected[0];
        return true;
    }
};


/**
 * 获取数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Stock.get = function (key) {
    return $("#" + key).val();
};

/**
 * 设置收集数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Stock.set = function (key, val) {
    this.queryData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 清除数据
 */
Stock.clearData = function () {
    this.queryData = {};
}

/**
 * 收集数据
 */
Stock.collectData = function () {
    this.clearData();
    this.set('name');
};

/**
 * 查询列表
 */
Stock.search = function () {
    this.collectData();
    Stock.table.refresh({query: this.queryData});
};

//高级重置
Stock.reset = function () {
    $("form .form-group input").val("");
};

/**
 * 调用后台批量删除方法
 */
Stock.deleteBatch = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/stock/deleteBatch", function (data) {
            Feng.success("删除成功!");
            Stock.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", this.seItem.id);
        ajax.start();
    }
};
/**
 * 单条记录删除
 */
Stock.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/stock/delete", function (data) {
            Feng.success("删除成功!");
            Stock.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", this.seItem.id);
        ajax.start();
    }
};

/**
 * 新增功能
 */
Stock.openAddPage = function () {
    var index = layer.open({
        type: 2,
        title: '新增指令',
        area: ['800px', '320px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: '/stock/stock_add'
    });
    this.layerIndex = index;
};
/**
 * 编辑
 */
Stock.openEditPage = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑指令',
            area: ['800px', '320px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: '/stock/stock_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 增加排序效果
 */
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
    init();
    var defaultColunms = Stock.initColumn();
    var table = new BSTable(Stock.id, "/stock/list", defaultColunms);
    table.setPaginationType("server");
    Stock.table = table.init();

    $("#biz").attr("class", "active");
    $("#stock").attr("class", "active");
});

