<!DOCTYPE HTML>
<html>
<head>
    <title>错误</title>
    <#include "common/common-lib.html">
    <style type="text/css">
        .loginTitle {
            text-align: center;
            font-family: 微软雅黑;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="loginTitle">
        <h1>发生了未知的错误</h1>
    </div>
    <hr>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">测试账号</h3>
        </div>
        <div class="panel-body">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>权限说明</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>admin</td>
                    <td>123456</td>
                    <td>超级管理员,可以执行任何操作</td>
                </tr>

                <tr>
                    <td>dev</td>
                    <td>123456</td>
                    <td>开发人员,可以操作角色和资源,有用户查看权限,无删除权限</td>
                </tr>

                <tr>
                    <td>test</td>
                    <td>123456</td>
                    <td>测试人员,比开发人员多了删除权限</td>
                </tr>

                <tr>
                    <td>guest</td>
                    <td>123456</td>
                    <td>游客,只有查看权限</td>
                </tr>
                </tbody>
            </table>
            登录：<a href="http://localhost:8080/login">http://localhost:8080/login</a>
        </div>
    </div>
</div>
</body>
</html>
