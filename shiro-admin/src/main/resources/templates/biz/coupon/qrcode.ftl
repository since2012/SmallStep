<!DOCTYPE HTML>
<html>
<head>
    <title>新增卖家</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.css">
    <!-- jQuery 3 -->
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/static/plugins/qrcode/qrcode.js"></script>
    <script type="text/javascript" src="/static/plugins/qrcode/jquery.qrcode.js"></script>
    <script src="/static/modular/biz/coupon/qrcode.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="form-horizontal">
        <input type="hidden" id="address" value="${address}">
        <div class="row">
            <div class="panel panel-danger">
                <div class="panel-heading">${address}</div>
                <div class="panel-body">
                    <br/>
                    <div class="text-center">
                        <div id="qrcode"></div>
                        <br/>
                        <div>扫码领取更简单</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>