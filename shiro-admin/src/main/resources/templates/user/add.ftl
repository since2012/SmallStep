<!DOCTYPE HTML>
<html>
<head>
    <title>添加用户</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "../common/header.ftl">
    <#include "edit.ftl">
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('.panel-heading').text("新增用户");
            $("button:reset").click();
        });
    </script>
    <script type="text/javascript" src="/static/js/user/add.js"></script>
</div>
</body>
</html>
