var Login = {
    loginData: {},
    validateFields: {
        username: {
            validators: {
                notEmpty: {
                    message: '账号不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                }
            }
        },
        kaptcha: {
            validators: {
                notEmpty: {
                    message: '验证码不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
Login.clearData = function () {
    Login.loginData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Login.set = function (key, value) {
    Login.loginData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return Login;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Login.get = function (key) {
    return $("#" + key).val();
};

/**
 * 收集数据
 */
Login.collectData = function () {
    Login.clearData();
    Login.set('username').set('password').set('kaptcha');
};

/**
 * 验证数据是否为空
 */
Login.validate = function () {
    $('form').data("bootstrapValidator").resetForm();
    $('form').bootstrapValidator('validate');
    return $("form").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
Login.login = function () {

    Login.collectData();

    if (!Login.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/login", function (data) {
        window.location.href = "/"
    }, function (data) {
        Feng.error("登录失败!" + data.responseJSON.message + "!");
    });
    ajax.set(Login.loginData);
    ajax.start();
};


$(function () {
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' /* optional */
    });
    Feng.initValidator("loginForm", Login.validateFields);

    $("#kaptchaimg").on('click', function () {
        $("#kaptchaimg").attr('src', '/kaptcha?' + Math.floor(Math.random() * 100)).fadeIn();
    });
});
