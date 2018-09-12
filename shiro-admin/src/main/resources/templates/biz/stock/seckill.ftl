<!DOCTYPE HTML>
<html>
<head>
    <title>秒杀</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <script src="/static/plugins/countdown/jquery.countdown.js"></script>
    <script src="/static/modular/biz/stock/seckill.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>秒杀
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">业务管理</a></li>
        <li class="active">秒杀</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <input type="hidden" id="stockId" value="${stock.id?c}">
        <div class="col-md-12 col-lg-12">
            <!-- Horizontal Form -->
            <div class="box box-success">
                <div class="box-header with-border">
                    <h2>${stock.primeprice}秒杀${stock.name}</h2>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <div class="box-body">
                    <h2 class="text-danger text-center">
                        <span class="glyphicon" id="seckill-box"></span>
                    </h2>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div><!-- /.row -->
</section><!-- /.content -->
</body>
</html>
