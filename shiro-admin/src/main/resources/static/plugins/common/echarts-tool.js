function EcharsFun() {
    this.cfg = {
        echartsInitDom: null, //初始化DOM节点,jquery对象类型 （必选）
        ajaxUrl: null, //ajax 提交路径（必选）
        ajaxParam: {}, //ajax 参数（必选）
        ajaxSuccess: null, //ajax请求成功回调函数
        option: null,//option 对象参数
        isResize: true //是否根据窗口大小改变图表大小
    }
}

EcharsFun.prototype = $.extend({}, {
    doAjax: function () {
        var that = this;
        $.ajax({
            url: that.cfg.ajaxUrl,
            type: "POST",
            data: that.cfg.ajaxParam,
            dataType: "json",
            success: function (data) {
                that._success(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                that.error(XMLHttpRequest, textStatus, errorThrown);
            },
            beforeSend: function () {//请求前回调函数
                that.beforeSend();
            },
            complete: function (XMLHttpRequest, textStatus) { //请求完成回调函数
                that.complete(XMLHttpRequest, textStatus);
            }
        });
    },
    beforeSend: function () {
        var that = this;
        that._myChart.clear();
        that._myChart.showLoading({
            text: '正在努力的读取数据中...'   //loading话术
        })
    },
    complete: function () {
        var that = this;
        that._myChart.hideLoading();
    },
    _success: function (data) {
        var that = this;
        //设置ajax回调函数
        that.cfg.ajaxSuccess && that.cfg.ajaxSuccess(data);
        //设置option
        that._myChart.setOption(that.cfg.option, true);
    },
    error: function (error) {
        console.error("图表请求数据失败!");
    },
    initEcharts: function () {
        var that = this;
        that._myChart = echarts.init(that.cfg.echartsInitDom[0]);
    },
    bindEvent: function () { //绑定UI事件
        var that = this;
        if (that.cfg.isResize) {
            window.onresize = that._myChart.resize;
        }
    },
    init: function (cfg) {
        var that = this;
        that._myChart = null;
        $.extend(that.cfg, cfg);
        that.initEcharts();
        that.doAjax();
        that.bindEvent();
    }
});
