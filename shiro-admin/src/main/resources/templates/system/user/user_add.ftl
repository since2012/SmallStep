<!DOCTYPE HTML>
<html>
<head>
    <title>新增用户</title>
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
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="form-horizontal bv-form" id="userInfoForm" novalidate="novalidate">
                <button type="submit" class="bv-hidden-submit" disabled="disabled"
                        style="display: none; width: 0px; height: 0px;"></button>

                <input type="hidden" id="id" value="">

                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">账户</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="account" name="account" type="text"
                                       data-bv-field="account"><i class="form-control-feedback"
                                                                  data-bv-icon-for="account" style="display: none;"></i>

                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="account"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">账户不能为空
                                </small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">性别</label>
                            <div class="col-xs-9">
                                <select class="form-control" id="sex" name="sex">
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group has-feedback has-error">
                            <label class="col-xs-3 control-label">密码</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="password" name="password" type="password"
                                       data-bv-field="password"><i
                                    class="form-control-feedback glyphicon glyphicon-remove" data-bv-icon-for="password"
                                    style=""></i>

                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="password"
                                       data-bv-result="VALID" style="display: none;">密码不能为空
                                </small>
                                <small class="help-block" data-bv-validator="identical" data-bv-for="password"
                                       data-bv-result="INVALID" style="">两次密码不一致
                                </small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">角色</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="roleid" name="roleid" type="text" disabled="disabled">

                            </div>
                        </div>
                        <div class="form-group has-feedback has-error">
                            <label class="col-xs-3 control-label">邮箱</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="email" name="email" type="email"
                                       data-bv-field="email"><i class="form-control-feedback glyphicon glyphicon-remove"
                                                                data-bv-icon-for="email" style=""></i>

                                <small class="help-block" data-bv-validator="emailAddress" data-bv-for="email"
                                       data-bv-result="INVALID" style="">请输入有效的邮件地址
                                </small>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div id="driverInfoContent">
                            <div class="form-group has-feedback">
                                <label class="col-xs-3 control-label">姓名</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="name" name="name" type="text"
                                           data-bv-field="name"><i class="form-control-feedback" data-bv-icon-for="name"
                                                                   style="display: none;"></i>

                                    <small class="help-block" data-bv-validator="notEmpty" data-bv-for="name"
                                           data-bv-result="NOT_VALIDATED" style="display: none;">姓名不能为空
                                    </small>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">出生日期</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="birthday" name="birthday" type="text" lay-key="1">
                                </div>
                            </div>
                            <div class="form-group has-feedback">
                                <label class="col-xs-3 control-label">确认密码</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="rePassword" name="rePassword" type="password"
                                           data-bv-field="rePassword"><i class="form-control-feedback"
                                                                         data-bv-icon-for="rePassword"
                                                                         style="display: none;"></i>

                                    <small class="help-block" data-bv-validator="notEmpty" data-bv-for="rePassword"
                                           data-bv-result="NOT_VALIDATED" style="display: none;">密码不能为空
                                    </small>
                                    <small class="help-block" data-bv-validator="identical" data-bv-for="rePassword"
                                           data-bv-result="NOT_VALIDATED" style="display: none;">两次密码不一致
                                    </small>
                                </div>
                            </div>
                            <div class="form-group has-feedback">
                                <label class="col-xs-3 control-label">部门</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="citySel" name="citySel" type="text"
                                           readonly="readonly" onclick="UserInfo.showDeptSelectTree(); return false;"
                                           style="background-color: #ffffff !important;" data-bv-field="citySel">
                                    <i class="form-control-feedback" data-bv-icon-for="citySel"
                                       style="display: none;"></i>
                                    <input class="form-control" type="hidden" id="deptid" value="">
                                    <div id="menuContent"
                                         style="display: none; position: absolute; z-index: 200;">
                                        <ul id="treeDemo" class="ztree tree-box"
                                            style="width:250px !important;">
                                        </ul>
                                    </div>
                                    <small class="help-block" data-bv-validator="notEmpty" data-bv-for="citySel"
                                           data-bv-result="NOT_VALIDATED" style="display: none;">部门不能为空
                                    </small>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">电话</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="phone" name="phone" type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row btn-group-m-t">
                    <div class="text-center">

                        <button type="button" class="btn btn-info " onclick="UserInfo.addSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="UserInfo.close()" id="cancel">
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