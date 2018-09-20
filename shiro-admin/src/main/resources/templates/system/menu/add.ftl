<!DOCTYPE HTML>
<html>
<head>
    <title>新增菜单</title>
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

    <link rel="stylesheet" href="/static/plugins/bootstrapValidator/bootstrapValidator.min.css">
    <script src="/static/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/jqueryZtree/zTreeStyle.css">
    <script src="/static/plugins/jqueryZtree/jquery.ztree.all.min.js"></script>
    <script src="/static/plugins/common/ztree-object.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/system/menu/menu_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <!-- /.box-header -->
            <div class="form-horizontal bv-form" id="menuInfoForm" novalidate="novalidate">
                <button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>
                <input type="hidden" id="id" value="">
                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="name" name="name" type="text" data-bv-field="name">
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">菜单编号</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="code" name="code" type="text">
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">父级编号</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="pcodeName" name="pcodeName" type="text"
                                       readonly="readonly" onclick="MenuInfo.showMenuSelectTree(); return false;"
                                       style="background-color: #ffffff !important;">
                                <input class="form-control" type="hidden" id="pcode" value="">
                                <div id="pcodeTreeDiv" style="display: none; position: absolute; z-index: 200;">
                                    <ul id="pcodeTree" class="ztree tree-box" style="width:244px !important;">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">是否是菜单</label>
                            <div class="col-xs-9">
                                <select class="form-control" id="ismenu" name="ismenu">
                                    <option value="1">是</option>
                                    <option value="0">不是</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">请求地址</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="url" name="url" type="text" data-bv-field="url">
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">排序</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="num" name="num" type="text" data-bv-field="num">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">图标</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="icon" name="icon" type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="MenuInfo.addSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="MenuInfo.close()" id="cancel">
                            <i class="fa fa-eraser"></i>&nbsp;取消
                        </button>
                    </div>
                </div>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
</body>
</html>