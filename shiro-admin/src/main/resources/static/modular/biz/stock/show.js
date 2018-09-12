/**
 * 日志管理初始化
 */
var Show = {
    id: "stockTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    queryData: {}
};

/**
 * 初始化表格的列
 */


Show.initColumn = function () {
    return [
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
        {title: '库存', field: 'total', align: 'center', valign: 'middle', sortable: true},
        {title: '开始时间', field: 'begintime', align: 'center', valign: 'middle', sortable: true},
        {title: '结束时间', field: 'endtime', align: 'center', valign: 'middle', sortable: true},
        {
            title: '操作',
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: false,
            formatter: function (value, row, index) {
                var result = '<a class="btn btn-info" href="/stock/' + value +
                    '/seckill" target="_blank">立刻秒杀</a>';
                return result;
            }
        }
    ];
};

/**
 * 获取数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Show.get = function (key) {
    return $("#" + key).val();
};

/**
 * 设置收集数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Show.set = function (key, val) {
    this.queryData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 清除数据
 */
Show.clearData = function () {
    this.queryData = {};
}

/**
 * 收集数据
 */
Show.collectData = function () {
    this.clearData();
    this.set('name');
};

/**
 * 查询列表
 */
Show.search = function () {
    this.collectData();
    Show.table.refresh({query: this.queryData});
};

//高级重置
Show.reset = function () {
    $("form .form-group input").val("");
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
    var defaultColunms = Show.initColumn();
    var table = new BSTable(Show.id, "/stock/list", defaultColunms);
    table.setPaginationType("server");
    Show.table = table.init();

    $("#biz").attr("class", "active");
    $("#stock_show").attr("class", "active");
});

