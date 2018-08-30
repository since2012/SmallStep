<!DOCTYPE HTML>
<html>
<head>
    <title>未授权</title>
        <#include "common/common-lib.html">
</head>
<body>
<div class="container">
    <div style="margin-top: 20px;" class="alert alert-danger" role="alert">
        <span style="font-size: 24px;">很遗憾,您不具备访问该网页的权限。</span>
    </div>
    <button class="btn btn-default" type="button" onclick="history.go(-1)">返回上一页</button>
    <a class="btn btn-default" href="/login">请更换账号登录</a>
</div>
</body>
</html>
