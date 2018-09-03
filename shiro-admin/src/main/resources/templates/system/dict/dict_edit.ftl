<!DOCTYPE HTML>
<html>
<head>
    <title>修改字典</title>
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

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/modular/system/dict/dict_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="form-horizontal">
                <input type="hidden" id="id" value="">
                <div class="row">
                    <div class="col-xs-12" id="itemsArea">
                        <input type="hidden" id="itemSize" value="3">
                        <div class="form-group">
                            <label class="col-xs-2 control-label">类型名称</label>
                            <div class="col-xs-3">
                                <input class="form-control" id="dictName" type="text" value="账号状态">
                                <input type="hidden" id="dictId" value="${dict.id}">
                            </div>
                            <label class="col-xs-2 control-label">类型编码</label>
                            <div class="col-xs-3">
                                <input class="form-control" id="dictCode" type="text" value="${dict.code}">
                            </div>
                            <div class="col-xs-2">
                                <button type="button" class="btn btn-info " onclick="DictInfo.addItem()">
                                    <i class="fa fa-plus"></i>&nbsp;增加
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-2 control-label">备注</label>
                            <div class="col-xs-8">
                                <input class="form-control" id="dictTips" type="text" value="${dict.tips}">
                            </div>
                        </div>
                        <#if subDicts?? && subDicts?size gt 0>
                            <#list subDicts as item>
                            <div class="form-group" name="dictItem" id="dictItem${item.num}">
                                <label class="col-xs-1 control-label">名称</label>
                                <div class="col-xs-3">
                                    <input class="form-control" type="text" name="itemName" value="${item.name}">
                                </div>
                                <label class="col-xs-1 control-label">值</label>
                                <div class="col-xs-2">
                                    <input class="form-control" type="text" name="itemCode" value="${item.code}">
                                </div>
                                <label class="col-xs-1 control-label">序号</label>
                                <div class="col-xs-2">
                                    <input class="form-control" type="text" name="itemNum" value="${item.num}">
                                </div>
                                <div class="col-xs-2">
                                    <button type="button" class="btn btn-danger " onclick="DictInfo.deleteItem(event)"
                                            id="cancel">
                                        <i class="fa fa-remove"></i>&nbsp;删除
                                    </button>
                                </div>
                            </div>
                            </#list>
                        </#if>
                    </div>
                    <div class="row btn-group-m-t">
                        <div class="text-center">
                            <button type="button" class="btn btn-info " onclick="DictInfo.editSubmit()" id="ensure">
                                <i class="fa fa-check"></i>&nbsp;提交
                            </button>
                            <button type="button" class="btn btn-danger " onclick="DictInfo.close()" id="cancel">
                                <i class="fa fa-eraser"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/template" id="itemTemplate">
    <div class="form-group" name="dictItem" id="">
        <label class="col-xs-1 control-label">名称</label>
        <div class="col-xs-3">
            <input class="form-control" type="text" name="itemName">
        </div>
        <label class="col-xs-1 control-label">值</label>
        <div class="col-xs-2">
            <input class="form-control" type="text" name="itemCode">
        </div>
        <label class="col-xs-1 control-label">序号</label>
        <div class="col-xs-2">
            <input class="form-control" type="text" name="itemNum">
        </div>
        <div class="col-xs-2">
            <button type="button" class="btn btn-danger " onclick="DictInfo.deleteItem(event)" id="cancel">
                <i class="fa fa-remove"></i>&nbsp;删除
            </button>
        </div>
    </div>
</script>

</body>
</html>