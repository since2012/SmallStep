<!DOCTYPE HTML>
<html>
<head>
    <title></title>
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
    <script src="/static/modular/system/role/role_menu_assign.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="box box-success">
            <div class="box-header with-border">
            ${roleName}
                <input type="hidden" id="roleId" value="${roleId}">
            </div>
            <div class="box-body">
                <ul id="zTree" class="ztree tree-box">
                </ul>
            </div>
            <div class="box-footer text-center">
                <button class="btn btn-sm btn-info" type="button" onclick="MenuAssign.setMemu()">
                    <i class="ace-icon fa fa-check bigger-110"></i>保存
                </button>
                &nbsp;
                <button class="btn btn-sm btn-danger" type="button" onclick="MenuAssign.close()">
                    <i class="ace-icon fa fa-close bigger-110"></i>关闭
                </button>
            </div>
        </div>
    </div>
</section>
</body>
</html>