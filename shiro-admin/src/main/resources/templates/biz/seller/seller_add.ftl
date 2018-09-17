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

    <link rel="stylesheet" href="/static/plugins/validate/bootstrapValidator.min.css">
    <script src="/static/plugins/validate/bootstrapValidator.min.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/biz/seller/seller_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box">
            <div class="form-horizontal bv-form" id="sellerInfoForm" novalidate="novalidate">
                <button type="submit" class="bv-hidden-submit" disabled="disabled"
                        style="display: none; width: 0px; height: 0px;"></button>

                <input type="hidden" id="id" value="">
                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">卖家名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="name" name="name" type="text">
                            </div>
                        </div>
                        <div class="form-group has-feedback has-error">
                            <label class="col-xs-3 control-label">卖家地址</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="addr" name="addr" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div id="driverInfoContent">
                            <div class="form-group">
                                <label class="col-xs-3 control-label">状态</label>
                                <div class="col-xs-9">
                                    <select class="form-control" id="status" name="status">
                                        <option value="1">启用</option>
                                        <option value="0">禁用</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="SellerInfo.addSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="SellerInfo.close()" id="cancel">
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