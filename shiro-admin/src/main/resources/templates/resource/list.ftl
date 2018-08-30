<!DOCTYPE HTML>
<html>
<head>
    <title>资源查看列表</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "../common/header.ftl">
    <div style="height: 450px;overflow: auto">
        <table class="table table-striped table-hover">
            <thead>
            <tr class="danger">
                <th>勾选</th>
                <th>序号</th>
                <th>名称</th>
                <th>权限</th>
                <th>url</th>
                <th>状态</th>
                <th>排序码</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <form>
        <#if list?? && list?size gt 0 >
            <#list list as item>
            <tr>
                <td><input type="checkbox" name="itemId" class="itemId" value="${item.id}"></td>
                <td>${item_index+1}</td>
                <td>${item.name}</td>
                <td>${item.permission}</td>
                <td>${item.url}</td>
                <td>
                    【<a class="code" data-id="${item.id}"> ${item.enable?string("启用","禁用")} </a>】
                </td>
                <td><#if item.sortCode??>${item.sortCode}</#if></td>
                <td><a href="/admin/resource/update/${item.id}">修改</a></td>
            </tr>
            </#list>
        </#if>
            </form>
            </tbody>
        </table>
    </div>

    <div>
        权限操作：
        <@shiro.hasPermission name="/admin/resource/add">
        <a class="btn btn-danger" href="/admin/resource/add">新增权限</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/resource/diable">
        <button class="btn btn-primary" id="diableBtn">批量禁用</button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/resource/delete">
        <button class="btn btn-info" id="deleteBtn">批量删除</button>
        </@shiro.hasPermission>
    </div>
    <#include "../common/footer.html">
</div>
<script type="text/javascript" src="/static/js/resource/list.js"></script>
</body>
</html>
