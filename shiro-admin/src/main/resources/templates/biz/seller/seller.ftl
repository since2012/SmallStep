<!DOCTYPE HTML>
<html>
<head>
    <title>卖家管理</title>
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

    <script src="/static/modular/biz/seller/seller.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>卖家管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">业务管理</a></li>
        <li class="active">卖家管理</li>
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
                            <label for="name" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px;">名称</label>
                            <div class="col-md-4 col-lg-5 ">
                                <input class="form-control" type="text" id="name" name="name"
                                       placeholder="名称或地址">
                            </div>
                            <label for="status" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px;">状态</label>
                            <div class="col-md-4 col-lg-5 ">
                                <select class="form-control" id="status" name="status">
                                    <option value="">所有</option>
                                    <option value="1">启用</option>
                                    <option value="0">禁用</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <button type="button" onclick="Seller.search()" class="btn btn-primary btn-sm no-margin-top">
                            <i class="fa fa-check"></i> 查询
                        </button>
                        <button type="button" onclick="Seller.reset();" class="btn btn-default btn-sm no-margin-top">
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
                    <div class="hidden-xs" id="sellerTableToolbar" role="group">
                        <button type="button" onclick="Seller.openAddPage()"
                                class="btn btn-success btn-sm no-margin-top">
                            新增
                        </button>
                        <button type="button" onclick="Seller.openEditPage()"
                                class="btn btn-warning btn-sm no-margin-top">
                            编辑
                        </button>
                        <button type="button" onclick="Seller.switch()" class="btn btn-default btn-sm no-margin-top">
                            状态切换
                        </button>
                        <button type="button" onclick="Seller.delete()" class="btn btn-default btn-sm no-margin-top">
                            删除
                        </button>
                    </div>
                    <table id="sellerTable" data-mobile-responsive="true" data-click-to-select="true">
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
