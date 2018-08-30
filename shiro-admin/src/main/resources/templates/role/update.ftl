<!DOCTYPE HTML>
<html>
<#--xmlns:shiro="http://www.pollix.at/thymeleaf/shiro"-->
<head>
    <title>修改角色</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "edit.ftl">
</div>
<script type="text/javascript">

    $(".panel-heading").text("修改角色信息");
    $("input:hidden").val("${entity.id}");
    $("#name").val("${entity.name}");
    $("#sn").val("${entity.sn}");
    $("#enable").val("${entity.enable?string("true","false")}");
    <#if entity.sortCode??>
        $("#sortCode").val("${entity.sortCode}");
    </#if>
</script>
<script src="/static/js/role/update.js"></script>
</body>
</html>
