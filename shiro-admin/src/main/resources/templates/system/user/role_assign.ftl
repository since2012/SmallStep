<!DOCTYPE HTML>
<html>
<head>
    <title>修改用户</title>
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
    <script src="/static/modular/system/user/user_role_assign.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="box box-success">
        <input type="hidden" id="userId" value="${userId}">
        <!-- 配置grid -->
        <div class="container"
             style="padding:  0px 10px !important; margin-top: -10px; text-align: center !important;">
            <div class="row">
                <div class="box-header with-border">
                ${userAccount}
                </div>
                <div class="box-body">
                    <ul id="zTree" class="ztree">
                    </ul>
                </div>
                <div class="box-footer text-center">
                    <button class="btn btn-sm btn-info" type="button" onclick="RoleAssign.setRole()">
                        <i class="ace-icon fa fa-check bigger-110"></i> 保存
                    </button>
                    &nbsp;
                    <button class="btn btn-sm btn-danger" type="button" onclick="RoleAssign.close()">
                        <i class="ace-icon fa fa-close bigger-110"></i> 关闭
                    </button>
                </div>
            </div>
        </div>

    </div>
</section>
</body>
</html>