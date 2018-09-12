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

    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <script src="/static/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
    <script src="/static/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/validate/bootstrapValidator.min.css">
    <script src="/static/plugins/validate/bootstrapValidator.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/ztree/zTreeStyle.css">
    <script src="/static/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="/static/plugins/common/ztree-object.js"></script>

    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
    <script src="/static/plugins/webuploader/webuploader.js"></script>
    <script src="/static/plugins/common/web-upload-object.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/system/user/user_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="form-horizontal bv-form" id="userProfileForm" novalidate="novalidate">
        <button type="submit" class="bv-hidden-submit"
                style="display: none; width: 0px; height: 0px;"></button>
        <input type="hidden" id="id" value="${user.id}">
        <input type="hidden" id="sexValue" value="${user.sex}">
        <div class="row">
            <div class="col-xs-6 b-r">
                <div class="hr-line-dashed"></div>
                <div class="form-group has-feedback">
                    <label class="col-xs-3 control-label">账户</label>
                    <div class="col-xs-9">
                        <input class="form-control" id="account" name="account"
                               value="${user.account}" type="text" disabled="disabled"
                               data-bv-field="account">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">角色</label>
                    <div class="col-xs-9">
                        <input class="form-control" id="roleid" name="roleid"
                               <#if user.roleid??>
                                    value="${roleName}"
                               <#else >
                                    value=""
                               </#if>
                               type="text" disabled="disabled">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">性别</label>
                    <div class="col-xs-9">
                        <select class="form-control" id="sex" name="sex">
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                </div>

                <div class="hr-line-dashed"></div>
                <div class="form-group has-feedback">
                    <label class="col-xs-3 control-label">邮箱</label>
                    <div class="col-xs-9">
                        <input class="form-control" id="email" name="email" value="${user.email}"
                               type="email" data-bv-field="email">
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div id="driverInfoContent">
                    <div class="form-group has-feedback">
                        <label class="col-xs-3 control-label">姓名</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="name" name="name" value="${user.name}"
                                   type="text" data-bv-field="name">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">出生日期</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="birthday" name="birthday"
                                   value="${user.birthday?date}" type="text" lay-key="1">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group has-feedback">
                        <label class="col-xs-3 control-label">部门</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="citySel" name="citySel"
                                   value="总公司" type="text" readonly="readonly"
                                   onclick="UserInfo.showDeptSelectTree(); return false;"
                                   style="background-color: #ffffff !important;"
                                   data-bv-field="citySel">
                            <input class="form-control" type="hidden" id="deptid"
                                   value="24">
                            <div id="menuContent"
                                 style="display: none; position: absolute; z-index: 200;">
                                <ul id="treeDemo" class="ztree tree-box"
                                    style="width:250px !important;">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">电话</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="phone" name="phone"
                                   value="${user.phone}" type="text">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row btn-group-m-t">
            <div class="text-center">
                <button type="button" class="btn btn-info " onclick="UserInfo.editSubmit()" id="ensure">
                    <i class="fa fa-check"></i>&nbsp;提交
                </button>
                <button type="button" class="btn btn-danger " onclick="UserInfo.close()" id="cancel">
                    <i class="fa fa-eraser"></i>&nbsp;取消
                </button>

            </div>
        </div>
    </div>
</section>
</body>
</html>