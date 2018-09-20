<!DOCTYPE HTML>
<html>
<head>
    <title>通知</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <script src="/static/plugins/common/Feng.js"></script>
    <script src="/static/plugins/common/ajax-object.js"></script>
    <script src="/static/plugins/layer/layer.js"></script>

    <script src="/static/modular/blackboard.js"></script>
</head>
<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>通知
        <small>黑板</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li class="active">通知</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-sm-12">
            <ul class="products-list product-list-in-box">
                <li class="item">
                    <#if list?? && list?size gt 0>
                        <#list list as item>
                        <div class="alert  alert-dismissable">
                            <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
                            <h4>${item.title}</h4>
                            ${item.content}
                        </div>
                            <hr>
                        </#list>
                    </#if>
                </li>
            </ul>
        </div>
    </div><!-- /.row -->
</section><!-- /.content -->
</body>
</html>
