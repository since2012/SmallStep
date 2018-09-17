<!DOCTYPE html>
<!--优惠券领取页面-->
<html>
<head>
    <title>优惠券领取页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- jQuery 3 -->
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/static/modular/biz/coupon/reception.css">

    <link rel="stylesheet" href="/static/plugins/bootstrapValidator/bootstrapValidator.min.css">
    <script src="/static/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>

    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/layer/layer.js"></script>

    <script type="text/javascript" src="/static/modular/biz/coupon/reception.js"></script>
    <script>
        var pageWidth = 320;
        var phoneScale = parseInt(window.screen.width) / pageWidth;
        document.write('<meta name="viewport" content="width=' + pageWidth + ', initial-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi, user-scalable=0" />');
    </script>
</head>
<body>
<div style='background-color:#F0F0F0;color:#000;margin-right:auto;margin-left:auto;width:320px;height:565px;border:1px #FFF solid'>
    <div class="containser">
        <div class="row">
            <div id="couponDiv">
                <span class="text-danger congratulate">优惠券领取</span>
            </div>
            <!-- 表单数据 -->
            <form id="defaultForm" class="form-horizontal text-center">
                <div class="form-group">
                    <label for="usertel">手机号：</label>
                    <input type="text" name="usertel" id="usertel"/>
                </div>
                <div class="form-group">
                    <label for="couponId">优惠券：</label>
                    <input type="text" name="couponId" id="couponId" value="${couponId}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <button class="btn btn-sm btn-danger btn-inline submitBtn" onclick="Reception.receptSubmit()"
                            type="button">立即领取
                    </button>
                    <button class="btn btn-sm btn-danger btn-inline submitBtn" onclick="Reception.mylist()"
                            type="button">我的优惠券
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
