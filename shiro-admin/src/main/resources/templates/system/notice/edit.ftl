<!DOCTYPE HTML>
<html>
<head>
    <title>新增部门</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- jQuery 3 -->
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/static/plugins/bootstrapValidator/bootstrapValidator.min.css">
    <script src="/static/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>

    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/plugins/wangEditor/wangEditor.js"></script>
    <script src="/static/modular/system/notice/notice_info.js"></script>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="col-xs-12 col-md-12 col-lg-12">
        <div class="box box-success">
            <div class="form-horizontal" id="noticeInfoForm">
                <input type="hidden" id="id" value="${notice.id}">
                <input type="hidden" id="contentVal" value='${notice.content}'>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标题</label>
                            <div class="col-sm-11">
                                <input class="form-control" id="title" name="title" type="text" value="${notice.title}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">内容</label>
                            <div class="col-sm-11">
                                <div id="editor" class="editorHeight">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row btn-group-m-t">
                    <div class="text-center">
                        <button type="button" class="btn btn-info " onclick="NoticeInfo.editSubmit()" id="ensure">
                            <i class="fa fa-check"></i>&nbsp;提交
                        </button>
                        <button type="button" class="btn btn-danger " onclick="NoticeInfo.close()" id="cancel">
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