var login = {
    checkForm: function () {
        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                kaptcha: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: "用户名不能为空."
                },
                password: {
                    required: "密码不能为空."
                },
                kaptcha: {
                    required: "验证码不能为空."
                }
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('#input-error'));
            },

            submitHandler: function (form) {
                $.ajax({
                    url: "/login",
                    type: 'post',
                    dataType: 'json',
                    data: $(form).serialize(),
                    success: function (data) {
                        // 登录成功或者失败的提示信息
                        if (data.code == 200) {
                            window.location.href = "/admin/user/list";
                        } else {
                            alert(data.msg);
                        }
                    },
                    error: function () {
                        alert();
                    }
                });

            }
        });
    },
    listenKey: function () {
        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    },
    panelSwitch: function () {
        // 构建登录对象
        var handleLogin = function () {

            $('#register-btn').click(function () {
                $('.register-form').show();
                $('.login-form').hide();
            });

            $('#register-back-btn').click(function () {
                $('.register-form').hide();
                $('.login-form').show();
            });
        }
    },
    init: function () {
        login.checkForm();
        login.listenKey();
    }
}

var regist = {
    checkForm: function () {
        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: "用户名不能为空."
                },
                password: {
                    required: "密码不能为空."
                }
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('#input-error'));
            },

            submitHandler: function (form) {
                $.ajax({
                    url: "/regist",
                    type: 'post',
                    dataType: 'json',
                    data: $(form).serialize(),
                    success: function (data) {
                        // 登录成功或者失败的提示信息
                        if (data.code == 200 && data.msg == "OK") {
                            alert("注册成功");
                        } else {
                            alert(data.msg);
                        }
                    },
                    error: function () {
                        alert();
                    }
                });
            }

        });
    },
    listenKey: function () {
        $('.register-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    },
    init: function () {
        regist.checkForm();
        regist.listenKey();
    }
}


jQuery(document).ready(function () {
    login.init();
    regist.init();
    // 验证码
    $("#kaptcha").click(function () {
        var $this = $(this);
        var url = $this.data("src") + new Date().getTime();
        $this.attr("src", url);
    });
});