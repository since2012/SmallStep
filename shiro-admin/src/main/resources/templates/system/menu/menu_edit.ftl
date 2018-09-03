<!DOCTYPE HTML>
<html>
<head>
    <title>修改菜单</title>
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
                <input type="hidden" id="id" value="${menu.id}">
                <input type="hidden" id="ismenuValue" value="${menu.ismenu}">
                <div class="row">
                    <div class="col-xs-6 b-r">
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">名称</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="name" name="name" value="${menu.name}" type="text"
                                       data-bv-field="name"><i class="form-control-feedback" data-bv-icon-for="name"
                                                               style="display: none;"></i>
                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="name"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">菜单名称不能为空
                                </small>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">菜单编号</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="code" name="code" value="${menu.code}" type="text"
                                       data-bv-field="code"><i class="form-control-feedback" data-bv-icon-for="code"
                                                               style="display: none;"></i>
                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="code"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">菜单编号不能为空
                                </small>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">父级编号</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="pcodeName" name="pcodeName" value="${pcodeName}"
                                       type="text"
                                       readonly="readonly" onclick="MenuInfo.showMenuSelectTree(); return false;"
                                       style="background-color: #ffffff !important;" data-bv-field="pcodeName"><i
                                    class="form-control-feedback" data-bv-icon-for="pcodeName"
                                    style="display: none;"></i>
                                <input class="form-control" type="hidden" id="pcode" value="${menu.pcode}">
                                <div id="pcodeTreeDiv" style="display: none; position: absolute; z-index: 200;">
                                    <ul id="pcodeTree" class="ztree tree-box" style="width:244px !important;">

                                    </ul>
                                </div>
                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="pcodeName"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">父菜单不能为空
                                </small>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
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
                                <input class="form-control" id="url" name="url" value="${menu.url}" type="text"
                                       data-bv-field="url"><i class="form-control-feedback" data-bv-icon-for="url"
                                                              style="display: none;"></i>
                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="url"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">请求地址不能为空
                                </small>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group has-feedback">
                            <label class="col-xs-3 control-label">排序</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="num" name="num" value="${menu.num}" type="text"
                                       data-bv-field="num"><i class="form-control-feedback" data-bv-icon-for="num"
                                                              style="display: none;"></i>
                                <small class="help-block" data-bv-validator="notEmpty" data-bv-for="num"
                                       data-bv-result="NOT_VALIDATED" style="display: none;">序号不能为空
                                </small>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">图标</label>
                            <div class="col-xs-9">
                                <input class="form-control" id="icon" name="icon"
                                       <#if menu.icon??> value="${menu.icon}" </#if> >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="MenuInfo.editSubmit()" id="ensure">
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