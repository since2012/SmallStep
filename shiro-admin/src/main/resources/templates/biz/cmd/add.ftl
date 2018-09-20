<!DOCTYPE HTML>
<html>
<head>
    <title>指令新增</title>
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

</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="box">
            <!-- /.box-header -->
            <form class="form-horizontal">
                <div class="box-body" id="itemsArea">
                    <input type="hidden" id="id" value="">
                    <div class="form-group">
                        <label class="control-label col-xs-1">指令</label>
                        <div class="col-xs-4">
                            <input class="form-control" id="name" name="name" type="text">
                        </div>
                        <label class="control-label col-xs-1">描述</label>
                        <div class="col-xs-4">
                            <input class="form-control" id="detail" name="detail" type="text">
                        </div>
                        <div class="col-xs-2">
                            <button type="button" class="btn btn-plus" onclick="CommandInfo.addItem()"
                                    id="cancel">
                                <i class="fa fa-plus"></i>&nbsp;增加
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
                <div class="box-footer text-center">
                    <div class="text-center">
                        <button type="button" class="btn btn-success" onclick="CommandInfo.addSubmit()"
                                id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger" onclick="CommandInfo.close()" id="cancel">
                            <i class="fa fa-eraser"></i>&nbsp;取消
                        </button>
                    </div>
                </div>
                <!-- /.box-footer -->
            </form>
        </div>
        <!-- /.box -->
    </div>

</section>

<script type="text/template" id="itemTemplate">
    <div class="form-group" name="contents">
        <label class="col-xs-1 control-label">内容</label>
        <div class="col-xs-9">
            <input class="form-control" type="text" name="content">
        </div>
        <div class="col-xs-2">
            <button type="button" class="btn btn-danger" onclick="CommandInfo.deleteItem(this)" id="cancel">
                <i class="fa fa-remove"></i>&nbsp;删除
            </button>
        </div>
    </div>
</script>
<script src="/static/modular/biz/cmd/cmd_info.js"></script>

</body>
</html>