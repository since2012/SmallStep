<!DOCTYPE HTML>
<html>
<head>
    <title>菜单管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">
<#--bootstrap table-->
    <link rel="stylesheet" href="/static/plugins/bootstrap-treetable/bootstrap-treetable.css">
    <script src="/static/plugins/bootstrap-treetable/bootstrap-treetable.js"></script>
    <script src="/static/plugins/common/ajax-object.js"></script>
<#--弹出层-->
    <script src="/static/plugins/layer/layer.js"></script>
<#--自定义封装-->
    <script src="/static/plugins/common/tree-table-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
<#--本页JS-->
    <script src="/static/modular/system/menu/menu.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>菜单管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">菜单管理</li>
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
                                       placeholder="名称">
                            </div>
                            <label for="level" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px">层级</label>
                            <div class="col-md-4 col-lg-5 ">
                                <input class="form-control" type="text" id="level"
                                       name="level"
                                       placeholder="层级">
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <button type="button" onclick="Menu.search();" class="btn btn-primary btn-sm no-margin-top">
                            <i class="fa fa-check"></i> 查询
                        </button>
                        <button type="button" onclick="Menu.reset();"
                                class="btn btn-default btn-sm no-margin-top">
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
                    <div class="hidden-xs" id="menuTableToolbar" role="group">
                        <@shiro.hasPermission name="/menu/add">
                            <button type="button" onclick="Menu.openAddPage();"
                                    class="btn btn-success btn-sm no-margin-top">
                                添加
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/menu/edit">
                            <button type="button" onclick="Menu.openEditPage();"
                                    class="btn btn-success btn-sm no-margin-top">
                                修改
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/menu/switch">
                            <button type="button" onclick="Menu.statusSwitch();"
                                    class="btn btn-success btn-sm no-margin-top">
                                状态切换
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/menu/delete">
                            <button type="button" onclick="Menu.delMenu();"
                                    class="btn btn-success btn-sm no-margin-top">
                                删除
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="menuTable" data-mobile-responsive="true" data-click-to-select="true">
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
