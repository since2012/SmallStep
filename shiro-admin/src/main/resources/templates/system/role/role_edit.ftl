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

    <link rel="stylesheet" href="/static/plugins/validate/bootstrapValidator.min.css">
    <script src="/static/plugins/validate/bootstrapValidator.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/ztree/zTreeStyle.css">
    <script src="/static/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="/static/plugins/common/ztree-object.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/system/role/role_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="form-horizontal bv-form" id="roleInfoForm" novalidate="novalidate">
                <button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>

                <input type="hidden" id="id" value="${role.id}">
                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">角色名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="name" name="name" value="${role.name}" type="text"
                                       data-bv-field="name">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">上级名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="pName" name="pName" value="${pName}" type="text"
                                       readonly="readonly" onclick="RoleInfo.showPNameSelectTree(); return false;"
                                       style="background-color: #ffffff !important;" data-bv-field="pName">
                                <input class="form-control" type="hidden" id="pid" value="${role.pid}">
                                <!-- 这是父级菜单下拉框 -->
                                <div id="pNameContent" class="menuContent"
                                     style="display: none; position: absolute; z-index: 200;">
                                    <ul id="pNameTree" class="ztree tree-box" style="width: 250px !important;">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">部门名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="deptName" name="deptName" value="${deptName}"
                                       type="text"
                                       readonly="readonly" onclick="RoleInfo.showDeptSelectTree(); return false;"
                                       style="background-color: #ffffff !important;">
                                <input class="form-control" type="hidden" id="deptid" value="${role.deptid}">
                                <!-- 这是部门下拉框 -->
                                <div id="deptContent" class="menuContent"
                                     style="display: none; position: absolute; z-index: 200;">
                                    <ul id="deptTree" class="ztree tree-box" style="width: 250px !important;">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">别名</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="tips" name="tips" value="${role.tips}" type="text"
                                       data-bv-field="tips">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">排序</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="num" name="num" value="${role.num}" type="text">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="RoleInfo.editSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="RoleInfo.close()" id="cancel">
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