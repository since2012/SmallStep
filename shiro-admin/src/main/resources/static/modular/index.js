var AdminIndex = {
    loginChart: null,
    browserChart: null
};

/**
 * 初始化登录图表
 */
AdminIndex.initLoginLogChart = function () {
    //异步加载echart
    var echartfun = new EcharsFun();
    echartfun.init({
        echartsInitDom: $('#login-chart'),
        ajaxUrl: Feng.ctxPath + "/login_log/line", //ajax 提交路径（必选）
        ajaxSuccess: function (data) {
            if (data) {
                echartfun.cfg.option.legend.data = ["登录次数"];
                echartfun.cfg.option.xAxis = [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: data.dateList
                    }
                ];
                echartfun.cfg.option.series = [{
                    name: '登录次数',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {},
                    data: data.numList
                }];
            }
            return true;
        },
        option: {
            // title: {
            //     text: '堆叠区域图'
            // },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data: []
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: []
        },
        isResize: true //是否根据窗口大小改变图表大小
    });
    AdminIndex.loginChart = echartfun._myChart;
};

/**
 * 初始化浏览器图表
 */
AdminIndex.initBrowserChart = function () {
    AdminIndex.browserChart = echarts.init($("#browser-chart")[0]);
    AdminIndex.browserChart.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['Chrome', 'FireFox', 'IE', 'Opera', 'Edge']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    {value: 335, name: 'Chrome'},
                    {value: 310, name: 'FireFox'},
                    {value: 234, name: 'IE'},
                    {value: 135, name: 'Opera'},
                    {value: 1548, name: 'Edge'}
                ]
            }
        ]
    });
};

$(function () {
    $("#index").attr("class", "active");

    // Make the dashboard widgets sortable Using jquery UI
    $('.connectedSortable').sortable({
        placeholder: 'sort-highlight',
        connectWith: '.connectedSortable',
        handle: '.box-header, .nav-tabs',
        forcePlaceholderSize: true,
        zIndex: 999999
    });
    $('.connectedSortable .box-header, .connectedSortable .nav-tabs-custom').css('cursor', 'move');

    AdminIndex.initLoginLogChart();
    AdminIndex.initBrowserChart();

});
