<!DOCTYPE HTML>
<html>
<head>
    <title>新增库存</title>
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

    <!-- bootstrap datetimepicker -->
    <link rel="stylesheet" href="/static/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <script src="/static/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" href="/static/plugins/validate/bootstrapValidator.min.css">
    <script src="/static/plugins/validate/bootstrapValidator.min.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/biz/stock/stock_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="form-horizontal bv-form" id="stockInfoForm" novalidate="novalidate">
                <input type="hidden" id="id" value="">
                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="name" name="name" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">开始时间</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="begintime" name="begintime" type="text" lay-key="1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">原价</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="primeprice" name="primeprice" type="text"
                                       data-bv-field="account">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div id="driverInfoContent">
                            <div class="form-group has-feedback">
                                <label class="col-xs-3 control-label">数量</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="total" name="total" type="number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">结束时间</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="endtime" name="endtime" type="text" lay-key="1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">促销价</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="saleprice" name="saleprice" type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="StockInfo.addSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="StockInfo.close()" id="cancel">
                            <i class="fa fa-eraser"></i>&nbsp;取消
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
</body>
</html>