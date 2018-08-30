<!DOCTYPE HTML>
<html>
<head>
    <title>用户列表</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">

    <#include "../common/header.ftl">
    <form>
        <table class="table table-striped text-left">
            <thead>
            <tr class="info text-center">
                <th>勾选</th>
                <th>序号</th>
                <th>用户名</th>
                <th>昵称</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
        <#if list?? && list?size gt 0 >
            <#list list as item>
        <tr>
            <@shiro.hasPermission name="/admin/user/**/delete">
                <td><input type="checkbox" name="itemId" class="itemId" value="${item.id}"></td>
            </@shiro.hasPermission>
            <td>${item_index+1}</td>
            <td>${item.username}</td>
            <td>${item.nickname}</td>
            <td>
                【<a class="code" data-id="${item.id}">
                ${item.enable?string("启用","禁用")}
            </a>】
            </td>
            <td><#if item.createTime??>${item.createTime?datetime}</#if></td>
            <td>
                <@shiro.hasPermission name="/admin/user/update">
                <a href="/admin/user/update/${item.id}">修改</a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/admin/user/recources">
                <a href="/admin/user/resources/${item.id}"> 权限</a>
                </@shiro.hasPermission>
            </td>
        </tr>
            </#list>
        </#if>
            </tbody>
        </table>
        用户操作：
        <@shiro.hasPermission name="/admin/user/add">
            <a class="btn btn-danger" type="button" href="/admin/user/add">添加用户</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/user/diable">
            <button class="btn btn-primary" type="button" id="diableBtn">禁用用户</button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/user/delete">
            <button class="btn btn-info" type="button" id="deleteBtn">删除用户</button>
        </@shiro.hasPermission>
    </form>
    <#include "../common/footer.html">
</div>

<script src="/static/js/user/list.js"></script>

</body>
</html>
