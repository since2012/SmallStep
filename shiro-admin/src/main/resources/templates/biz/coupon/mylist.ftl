<!-- 我的优惠券展示页面 -->
<!DOCTYPE html>
<html>
<head>
    <title>我的优惠券</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/static/favicon.ico">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- jQuery 3 -->
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/modular/biz/coupon/card.css">
    <script>
        var pageWidth = 320;
        var phoneScale = parseInt(window.screen.width) / pageWidth;
        document.write('<meta name="viewport" content="width=' + pageWidth + ', initial-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi, user-scalable=0" />');
    </script>
</head>
<body>
<div style='background-color:#F0F0F0;color:#000;margin-right:auto;margin-left:auto;width:320px;min-height:565px;border:1px #FFF solid'>
    <div id="container" class="container">
        <#if list?? && list?size gt 0>
            <#list list as item>
                <div class="card-box">
                    <div class="card-detail">
                        <div class="card-detail-top ${item.bgclass}">
                            <div>${item.sellername}</div>
                            <div><span>1</span>张</div>
                        </div>
                        <div class="card-detail-bottom">
                            <span>领取时间：${item.receptday?datetime}</span>
                            <span>使用期限：${item.beginday?date}~${item.endday?date}</span>
                            <span>${item.status}</span>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
</body>
</html>


