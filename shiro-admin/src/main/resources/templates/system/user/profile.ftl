<!DOCTYPE HTML>
<html>
<head>
    <title>个人资料</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">
    <!-- bootstrap datetimepicker -->
    <link rel="stylesheet" href="/static/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <script src="/static/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" href="/static/plugins/bootstrapValidator/bootstrapValidator.min.css">
    <script src="/static/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
    <script src="/static/plugins/webuploader/webuploader.js"></script>
    <script src="/static/plugins/common/web-upload-object.js"></script>

    <link rel="stylesheet" href="/static/plugins/jqueryZtree/zTreeStyle.css">
    <script src="/static/plugins/jqueryZtree/jquery.ztree.all.min.js"></script>
    <script src="/static/plugins/common/ztree-object.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/system/user/user_info.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1> 个人资料 </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">用户管理</a></li>
        <li class="active">个人资料</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-md-3">
            <!-- Profile Image -->
            <div class="box box-primary">
                <div class="box-body box-profile">
                    <img id="avatarPreId" class="profile-user-img img-responsive img-circle"
                         src="/mgr/img/${user.avatar}" alt="User profile picture">
                    <div class="head-scu-btn upload-btn text-center" id="avatarBtnId">
                        <i class="fa fa-upload"></i>&nbsp;上传
                    </div>
                    <input type="hidden" id="avatar" value="${user.avatar}">
                    <div class="progress progress-striped" id="progressTipArea"
                         style="margin-top: 20px;">
                        <div id="progressBar" style="width: 0%" aria-valuemax="100" aria-valuemin="0"
                             aria-valuenow="0" role="progressbar"
                             class="progress-bar progress-bar-info">
                        </div>
                    </div>
                    <h3 class="profile-username text-center">TC</h3>
                    <p class="text-muted text-center">软件工程师</p>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
            <!-- About Me Box -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">关于我</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <strong><i class="fa fa-book margin-r-5"></i> 教育</strong>

                    <p class="text-muted">
                        211 and Communicating Engineering
                    </p>
                    <hr>
                    <strong><i class="fa fa-pencil margin-r-5"></i> 技能</strong>
                    <p>
                        <span class="label label-danger">JAVA</span>
                        <span class="label label-success">SpringBoot</span>
                        <span class="label label-info">Mybatis</span>
                        <span class="label label-warning">MySQL</span>
                        <span class="label label-primary">Freemarker</span>
                    </p>
                    <hr>
                    <strong><i class="fa fa-file-text-o margin-r-5"></i> 说明</strong>
                    <p>小试牛刀</p>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#settings" data-toggle="tab">设置</a></li>
                    <li><a href="#activity" data-toggle="tab">重置密码</a></li>
                </ul>
                <div class="tab-content">
                    <div class="active tab-pane" id="settings">
                        <form class="form-horizontal bv-form" id="userProfileForm" novalidate="novalidate">
                            <button type="submit" class="bv-hidden-submit"
                                    style="display: none; width: 0px; height: 0px;"></button>
                            <input type="hidden" id="id" value="${user.id}">
                            <div class="row">
                                <div class="col-sm-6 b-r">

                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group has-feedback">
                                        <label class="col-sm-3 control-label">账户</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" id="account" name="account"
                                                   value="${user.account}" type="text" disabled="disabled"
                                                   data-bv-field="account">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">角色</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" value="${roleName}"
                                                   type="text" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">性别</label>
                                        <div class="col-sm-9">
                                            <input type="hidden" value="${user.sex}" id="sexValue">
                                            <select class="form-control" id="sex" name="sex">
                                                <option value="1">男</option>
                                                <option value="2">女</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group has-feedback">
                                        <label class="col-sm-3 control-label">邮箱</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" id="email" name="email" value="${user.email}"
                                                   type="email" data-bv-field="email">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div id="driverInfoContent">
                                        <div class="form-group has-feedback">
                                            <label class="col-sm-3 control-label">姓名</label>
                                            <div class="col-sm-9">
                                                <input class="form-control" id="name" name="name" value="${user.name}"
                                                       type="text" data-bv-field="name">
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">出生日期</label>
                                            <div class="col-sm-9">
                                                <div class="input-group date form_date" data-date=""
                                                     data-date-format="yyyy-mm-dd" data-link-field="birthday"
                                                     data-link-format="yyyy-mm-dd">
                                                    <input class="form-control" size="16" type="text"
                                                           value="${user.birthday?date}" readonly>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-remove"></span></span>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                                <input type="hidden" id="birthday" value="${user.birthday?date}"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group has-feedback">
                                            <label class="col-sm-3 control-label">部门</label>
                                            <div class="col-sm-9">
                                                <input class="form-control" id="citySel" name="citySel"
                                                       value="${deptName}" type="text" readonly="readonly"
                                                       onclick="UserInfo.showDeptSelectTree(); return false;"
                                                       style="background-color: #ffffff !important;"
                                                       data-bv-field="citySel">
                                                <input class="form-control" type="hidden" id="deptid"
                                                       value="${user.deptid}">
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
                                            <label class="col-sm-3 control-label">电话</label>
                                            <div class="col-sm-9">
                                                <input class="form-control" id="phone" name="phone"
                                                       value="${user.phone}" type="text">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row btn-group-m-t">
                                <div class="text-center">
                                    <button type="button" class="btn btn-danger "
                                            onclick="UserInfo.editProfile()" id="ensure">
                                        <i class="fa fa-check"></i>&nbsp;提交
                                    </button>

                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="activity">
                        <form id="pwdForm" class="form-horizontal">
                            <div class="row">
                                <div class="form-group">
                                    <label for="oldPwd" class="col-sm-3 control-label">原密码</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" id="oldPwd" placeholder="原密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="newPwd" class="col-sm-3 control-label">新密码</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" id="newPwd"
                                               placeholder="新密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="rePwd" class="col-sm-3 control-label">验证</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" id="rePwd" placeholder="再次新密码">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="text-center">
                                    <button type="button" class="btn btn-danger " onclick="UserInfo.chPwd()"
                                            id="ensure">
                                        <i class="fa fa-check"></i>&nbsp;提交
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.tab-pane -->
                </div>
                <!-- /.tab-content -->
            </div>
            <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->

</section>
<!-- /.content -->
</body>
</html>