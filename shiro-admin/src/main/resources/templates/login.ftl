<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>权限管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <#include "common/common-lib.html">
    <#include "common/jquery-plugin.html">

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="/static/css/login/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/login/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/boostrap/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="/static/css/login/select2.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/login/select2-bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGINS -->

    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="/static/css/login/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
    <!-- END THEME GLOBAL STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="/static/css/login/login-2.min.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->

    <style type="text/css">
        .loginTitle {
            text-align: center;
            font-family: 微软雅黑;
        }
    </style>

</head>

<body class="login">
<div class="container">
    <div class="loginTitle"><h1>开源后台管理系统</h1></div>
    <hr>

    <!-- BEGIN LOGIN -->
    <div class="content">
        <!-- BEGIN LOGIN FORM -->
        <form class="login-form">
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <div id="input-error">
                    <input class="form-control form-control-solid placeholder-no-fix"
                           type="text" autocomplete="off" placeholder="用户名" name="username"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <div id="input-error">
                    <input class="form-control form-control-solid placeholder-no-fix"
                           type="password" autocomplete="off" placeholder="密码"
                           name="password"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <div id="input-error">
                    <input class="form-control form-control-solid placeholder-no-fix form-group"
                           type="text" autocomplete="off" placeholder="验证码" name="kaptcha"
                           required/>
                    <a> <img id="kaptcha" alt="验证码" src="/kaptcha"
                             data-src="/kaptcha?time=" style="width: 94.5px; height: 35px;"/>
                    </a>
                </div>
            </div>
            <div class="form-actions" style="padding: 0 30px 15px;">
                <button type="submit" class="btn red btn-block uppercase">登 录
                </button>
            </div>
            <div class="create-account">
                <p>
                    <a href="javascript:;" class="btn-primary btn" id="register-btn">注 册 用 户</a>
                </p>
            </div>
        </form>
        <!-- END LOGIN FORM -->
        <!-- BEGIN REGISTRATION FORM -->
        <form class="register-form">
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <div id="input-error">
                    <input class="form-control placeholder-no-fix" type="text"
                           placeholder="用户名" name="username"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <div id="input-error">
                    <input class="form-control placeholder-no-fix" type="text"
                           placeholder="密码" name="password"/>
                </div>
            </div>
            <div class="form-actions">
                <button type="button" id="register-back-btn" class="btn btn-default">返回
                </button>
                <button type="submit" id="register-submit-btn"
                        class="btn red uppercase pull-right">注 册
                </button>
            </div>
        </form>
        <!-- END REGISTRATION FORM -->
    </div>
    <#include "common/footer.html">
</div>
<script src="/static/js/login.js" type="text/javascript"></script>

</body>
</html>
