<!DOCTYPE HTML>
<html>
<head>
    <title>优惠券管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <script src="/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/static/plugins/common/bootstrap-table-object.js"></script>

    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/layer/layer.js"></script>

    <script src="/static/modular/biz/coupon/coupon.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>优惠券管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">业务管理</a></li>
        <li class="active">优惠券管理</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-12 col-lg-12">
            <!-- Horizontal Form -->
            <div class="box box-success">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-search"></i> 查询条件</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="usertel" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px;">手机号码</label>
                            <div class="col-md-4 col-lg-5 ">
                                <input class="form-control" type="text" id="usertel" name="usertel"
                                       placeholder="手机号码">
                            </div>
                            <label for="valid" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px;">状态</label>
                            <div class="col-md-4 col-lg-5 ">
                                <select class="form-control" id="valid" name="valid">
                                    <option value="">所有</option>
                                    <option value="1">有效</option>
                                    <option value="0">无效</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <button type="button" onclick="Coupon.search()" class="btn btn-primary btn-sm no-margin-top">
                            <i class="fa fa-check"></i> 查询
                        </button>
                        <button type="button" onclick="Coupon.reset();" class="btn btn-default btn-sm no-margin-top">
                            <i class="fa fa-refresh"></i>重置
                        </button>
                    </div>
                    <!-- /.box-footer -->
                </form>
            </div>
            <!-- /.box -->
        </div>
    </div><!-- /.row -->
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-success">
                <div class="box-body">
                    <div class="hidden-xs" id="couponTableToolbar" role="group">
                        <button type="button" onclick="Coupon.openGenPage()"
                                class="btn btn-success btn-sm no-margin-top">
                            生成优惠券
                        </button>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal-default">
                            使用
                        </button>
                        <button type="button" onclick="Coupon.showQrcode()"
                                class="btn btn-warning btn-sm no-margin-top">
                            二维码
                        </button>
                        <button type="button" onclick="Coupon.delete()" class="btn btn-default btn-sm no-margin-top">
                            删除
                        </button>
                    </div>

                    <div class="modal fade" id="modal-default">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header modal-info">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">使用优惠券</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label for="tel" class="col-xs-2 control-label"
                                                   style="padding-right:0px;">手机号码</label>
                                            <div class="col-xs-4 ">
                                                <input class="form-control" type="text" id="tel" name="tel"
                                                       placeholder="手机号码">
                                            </div>
                                            <label for="code" class="col-xs-2 control-label"
                                                   style="padding-right:0px;">验证码</label>
                                            <div class="col-xs-4">
                                                <input class="form-control" type="text" id="code" name="code"
                                                       placeholder="验证码">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭
                                    </button>
                                    <button type="button" onclick="Coupon.useCoupon();" class="btn btn-primary">确认
                                    </button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    <!-- /.modal -->

                    <table id="couponTable" data-mobile-responsive="true" data-click-to-select="true">
                        <thead>
                        <tr>
                            <th data-field="selectItem" data-checkbox="true"></th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section><!-- /.content -->
</body>
</html>
