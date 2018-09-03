<!DOCTYPE HTML>
<html>
<head>
    <title>角色管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">
<#--bootstrap table-->
    <link rel="stylesheet" href="/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <script src="/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<#--国际化-->
    <script src="/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/static/plugins/common/ajax-object.js"></script>
<#--弹出层-->
    <script src="/static/plugins/layer/layer.js"></script>
<#--自定义封装-->
    <script src="/static/plugins/common/bootstrap-table-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
<#--本页JS-->
    <script src="/static/modular/system/role/role.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>角色管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">角色管理</li>
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
                            <label for="tips" class="col-md-2 col-lg-1 control-label"
                                   style="padding-right:0px">别名</label>
                            <div class="col-md-4 col-lg-5 ">
                                <input class="form-control" type="text" id="tips"
                                       name="tips"
                                       placeholder="别名">
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <button type="button" onclick="Role.search();" class="btn btn-primary btn-sm no-margin-top">
                            <i class="fa fa-check"></i> 查询
                        </button>
                        <button type="button" onclick="Role.reset();"
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
                    <div class="hidden-xs" id="roleTableToolbar" role="group">
                        <@shiro.hasPermission name="/role/add">
                            <button type="button" onclick="Role.openAddPage();"
                                    class="btn btn-success btn-sm no-margin-top">
                                添加
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/role/edit">
                            <button type="button" onclick="Role.openEditPage();"
                                    class="btn btn-success btn-sm no-margin-top">
                                修改
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/role/remove">
                            <button type="button" onclick="Role.delRole();"
                                    class="btn btn-success btn-sm no-margin-top">
                                删除
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/role/setAuthority">
                            <button type="button" onclick="Role.assign();"
                                    class="btn btn-success btn-sm no-margin-top">
                                权限配置
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="roleTable" data-mobile-responsive="true" data-click-to-select="true">
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
