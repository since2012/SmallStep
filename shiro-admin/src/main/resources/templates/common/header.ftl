<br>
<div style="float: left">

    <@shiro.hasPermission name="/admin/resource/list">
    <a class="btn btn-danger" role="button" href="/admin/resource/list">权限管理</a>
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/admin/role/list">
    <a class="btn btn-success" role="button" href="/admin/role/list">角色管理</a>
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/admin/user/list">
    <a class="btn btn-primary" role="button" href="/admin/user/list">用户管理</a>
    </@shiro.hasPermission>

</div>
<div style="float: right">
    欢迎您,【<@shiro.principal property="username"/> 】
    <a class="btn btn-warning" role="button"
       href="/logout">退出登录</a>
</div>
<hr style="clear: both;margin-top: 50px">
