<!DOCTYPE HTML>
<html>
<#--xmlns:shiro="http://www.pollix.at/thymeleaf/shiro"-->
<head>
    <title>更改权限页面</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
<#include "edit.ftl">
</div>
<script type="text/javascript">
    $(".panel-heading").text("修改权限");
    $("#id").val("${entity.id}");
    $("#name").val("${entity.name}");
    $("#permission").val("${entity.permission}");
    $("#url").val("${entity.url}");
    $("#enable").val("${entity.enable?string("true","false")}");
    <#if entity.sortCode??>
        $("#sortCode").val("${entity.sortCode}");
    </#if>
</script>
<script src="/static/js/resource/update.js"></script>
</body>
</html>
