//存放主要交互逻辑JS代码
//javaScripts 模块化

var Seckill = {
    //封装秒杀相关的ajax的url地址
    URL: {
        now: function (stockId) {
            return '/stock/' + stockId + '/time';
        },
        exposer: function (stockId) {
            return '/stock/' + stockId + '/exposer';
        },
        killUrl: function (stockId, md5) {
            return '/stock/' + stockId + '/' + md5 + '/execution';
        }
    },
    handleSeckill: function (stockId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');//按钮
        $.post(Seckill.URL.exposer(stockId), {}, function (result) {
            //在回调函数中，执行交互流程
            if (result) {
                var exposer = result.data;
                //如果exposed为true，就显示开始秒杀按钮，并绑定点击事件
                if (exposer.exposed) {
                    //获取秒杀MD5
                    var md5 = exposer.md5;
                    //组装秒杀地址
                    var killUrl = Seckill.URL.killUrl(stockId, md5);
                    console.log('killUrl:' + killUrl);
                    //绑定一次点击事件，回调函数内容为执行秒杀
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disable');
                        //执行秒杀请求
                        $.post(killUrl, {}, Seckill.handleSubmit);
                    });
                    node.show();
                } else {
                    //未开启秒杀
                    var now = exposer.now;
                    var start = exposer.startTime;
                    var end = exposer.endTime;
                    //重新计算计时逻辑
                    Seckill.countDown(stockId, now, start, end);
                }
            } else {
                console.log('result:' + result);
            }
        })
    },
    handleSubmit: function (result) {
        if (result) {
            var killResult = result.data;
            var state = killResult.state;
            var stateInfo = killResult.stateInfo;
            //显示秒杀结果
            $('#seckill-box').html('<span class="label label-success">' + stateInfo + '</span>');
        }
    },
    countDown: function (stockId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            //秒杀未开始
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //控制时间格式
                var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //时间完成后回调事件
            }).on('finish.countdown', function () {
                //获取秒杀地址，控制显示逻辑，执行秒杀
                Seckill.handleSeckill(stockId, seckillBox);
            });
        } else {
            Seckill.handleSeckill(stockId, seckillBox);
        }

    },
    //详情页初始化
    init: function (stockId) {
        $.get(Seckill.URL.now(stockId), {}, function (result) {
            if (result) {
                var data = result.data;
                var startTime = parseInt(data.startTime);
                var endTime = parseInt(data.endTime);
                var nowTime = parseInt(data.nowTime);
                Seckill.countDown(stockId, nowTime, startTime, endTime);
            } else {
                console.log('result:' + result);
            }
        });
    }
};

$(function () {
    Seckill.init($("#stockId").val());

    $("#biz").attr("class", "active");
    $("#stock").attr("class", "active");
});