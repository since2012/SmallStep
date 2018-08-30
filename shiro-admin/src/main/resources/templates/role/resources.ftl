<!DOCTYPE HTML>
<html>
<#--xmlns:shiro="http://www.pollix.at/thymeleaf/shiro"-->
<head>
    <head>
        <title>角色资源列表</title>
	 <#include "../common/common-lib.html">
    </head>
<body>
<div class="container">
    <#include "../common/header.ftl">
    <span>${entity.name}（${entity.sn}）的拥有的权限有：</span>
    <form action="" method="">
        <input type="hidden" name="roleId" value="">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>权限名称</th>
                <th>权限 url</th>
                <th>资源权限字符串</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
        <#if list?? && list?size gt 0 >
            <#list list as item>
            <tr>
                <td>${item.name}</td>
                <td>${item.url}</td>
                <td>${item.permission}</td>
                <td><input type="checkbox" id="${item.id}" name="itemIds" class="itemId" value="${item.id}"></td>
            </tr>
            </#list>
        </#if>
            </tbody>
        </table>
        <div class="text-center">
            <input class="btn btn-danger" id="submitBtn" type="button" value="提交"/>
            <input class="btn btn-default" type="reset"/>
        </div>
    </form>
    <#include "../common/footer.html">
</div>

<script type="text/javascript">
    jQuery(document).ready(function () {

        jQuery('input:hidden').val("${entity.id}");
        <#if hasResourceIds?? && hasResourceIds?size gt 0 >
            <#list hasResourceIds as rid>
            jQuery('#${rid}').attr("checked", true);
            </#list>
        </#if>
    });
</script>
<script type="text/javascript" src="/static/js/role/resources.js"></script>
</body>
</html>
