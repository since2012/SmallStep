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
    <script src="/static/modular/system/role/role_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="row">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>开发工程师</h5>
                    </div>
                    <div class="ibox-content">
                        <ul id="zTree" class="ztree">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button class="btn btn-sm btn-info" type="button" id="btn_save">
                        <i class="ace-icon fa fa-check bigger-110"></i>保存
                    </button>
                    &nbsp;
                    <button class="btn btn-sm btn-danger" type="button" id="btn_close">
                        <i class="ace-icon fa fa-close bigger-110"></i>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
    $(function () {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        $("#btn_close").bind("click", function () {
            parent.layer.close(index);
        });

        $("#btn_save").bind("click", function () {
            var ids = Feng.zTreeCheckedNodes("zTree");
            var ajax = new $ax(Feng.ctxPath + "/role/setAuthority", function (data) {
                Feng.success("分配角色成功!");
                window.parent.Role.table.refresh();
                parent.layer.close(index);
            }, function (data) {
                Feng.error("分配角色失败!"
                        + data.responseJSON.message + "!");
            });
            ajax.set("roleId", "6");
            ajax.set("ids", ids);
            ajax.start();
        });

        initZtree();
    });

    function initZtree() {
        var setting = {
            check: {
                enable: true,
                chkboxType: {"Y": "p", "N": "s"}
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid"
            }
        };
        var ztree = new $ZTree("zTree", "/menu/treeByRoleId/"
                + "6");
        ztree.setSettings(setting);
        ztree.init();
    }
</script>
</body>
</html>