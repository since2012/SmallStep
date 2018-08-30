<!DOCTYPE HTML>
<html>
<#--xmlns:shiro="http://www.pollix.at/thymeleaf/shiro"-->
<head>
    <title>用户权限列表</title>
    <#include "../common/common-lib.html">
</head>
<body>
<div class="container">
    <#include "../common/header.ftl">
    用户名：${entity.username}，昵称：${entity.nickname}
    <hr>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>资源名称</th>
            <th>资源 url</th>
            <th>资源权限字符串</th>
        </tr>
        </thead>
        <tbody>
        <#if list?? && list?size gt 0 >
            <#list list as item >
               <tr>
                   <td>${item.name}</td>
                   <td>${item.url}</td>
                   <td>${item.permission}</td>
               </tr>
            </#list>
        </#if>
        </tbody>
    </table>
    <#include "../common/footer.html">
</div>
</body>
</html>
