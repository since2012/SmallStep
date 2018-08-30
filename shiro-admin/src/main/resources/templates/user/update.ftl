<!DOCTYPE HTML>
<html>
<head>
    <title>修改用户信息</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "../common/header.ftl">
    <#include "edit.ftl">
    <#include "../common/footer.html">

    <script type="text/javascript">
        jQuery(document).ready(function () {

            jQuery('.panel-heading').text("修改信息");
            jQuery('input:hidden').val("${entity.id}");
            jQuery('#username').val("${entity.username}");
            jQuery('#nickname').val("${entity.nickname}");
            jQuery('#password').val("${entity.password}");
            jQuery("#enable").val("${entity.enable?string("true","false")}");

            <#if hasRole?? && hasRole?size gt 0 >
                <#list hasRole as rid>
                jQuery('#${rid}').attr("checked", true);
                </#list>
            </#if>
        });
    </script>

    <script type="text/javascript" src="/static/js/user/update.js"></script>
</body>
</html>
