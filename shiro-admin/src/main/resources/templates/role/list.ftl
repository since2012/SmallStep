<!DOCTYPE HTML>
<html>
<head>
    <title>角色列表查询</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "../common/header.ftl">

    <table class="table table-striped">
        <thead>
        <tr class="success">
            <th>勾选</th>
            <th>序号</th>
            <th>角色名称</th>
            <th>角色字符串</th>
            <th>状态</th>
            <th>上级ID</th>
        <#--<th>创建时间</th>-->
            <th>排序码</th>
            <th> 操作</th>
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
            <td>${item.sn}</td>
            <td>
                【<a class="code" data-id="${item.id}"> ${item.enable?string("启用","禁用")} </a>】
            </td>
            <td><#if item.topId??>${item.topId}</#if></td>
        <#--<td><#if item.createTime??>${item.createTime?datetime}</#if></td>-->
            <td><#if item.sortCode??>${item.sortCode}</#if></td>
            <td>
                <a href="/admin/role/update/${item.id}">修改</a>
                <a href="/admin/role/resources/${item.id}">权限</a>
            </td>
        </tr>
          </#list>
      </#if>
        </form>
        </tbody>
    </table>
    角色操作：
    <@shiro.hasPermission name="/admin/role/add">
    <a class="btn btn-danger" href="/admin/resource/add">新增角色</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/role/diable">
    <button class="btn btn-primary" id="diableBtn">批量禁用</button>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/role/delete">
    <button class="btn btn-info" id="deleteBtn">批量删除</button>
    </@shiro.hasPermission>

    <#include "../common/footer.html">
</div>

<script type="text/javascript" src="/static/js/role/list.js"></script>
</body>
</html>
