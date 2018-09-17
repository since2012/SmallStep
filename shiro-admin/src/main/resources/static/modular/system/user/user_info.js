/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfo = {
    userInfoData: {},
    ztree: null,
    validateFields: {
        account: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        citySel: {
            validators: {
                notEmpty: {
                    message: '部门不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        }
    }
};

/**
 * 清除数据
 */
UserInfo.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfo.set = function (key, value) {
    if (typeof value == "undefined") {
        if (typeof $("#" + key).val() == "undefined") {
            var str = "";
            var ids = "";
            $("input[name='" + key + "']:checkbox").each(function () {
                if (true == $(this).is(':checked')) {
                    str += $(this).val() + ",";
                }
            });
            if (str) {
                if (str.substr(str.length - 1) == ',') {
                    ids = str.substr(0, str.length - 1);
                }
            } else {
                $("input[name='" + key + "']:radio").each(function () {
                    if (true == $(this).is(':checked')) {
                        ids = $(this).val()
                    }
                });
            }
            this.userInfoData[key] = ids;
        } else {
            this.userInfoData[key] = $("#" + key).val();
        }
    }

    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfo.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfo.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
UserInfo.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", UserInfo.ztree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
UserInfo.showDeptSelectTree = function () {
    Feng.showInputTree("citySel", "menuContent");
};

/**
 * 收集数据
 */
UserInfo.collectData = function () {
    UserInfo.clearData();
    this.set('id').set('sex').set('password').set('email')
        .set('name').set('birthday').set('deptid').set('phone');
};

/**
 * 验证两个密码是否一致
 */
UserInfo.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
UserInfo.validate = function () {
    $('#userProfileForm').data("bootstrapValidator").resetForm();
    $('#userProfileForm').bootstrapValidator('validate');
    return $("#userProfileForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/add", function (data) {
        Feng.success("添加成功!");
        window.parent.MgrUser.table.refresh();
        UserInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
        Feng.success("修改成功!");
        if (window.parent.MgrUser != undefined) {
            window.parent.MgrUser.table.refresh();
            UserInfo.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 编辑个人资料
 */
UserInfo.editProfile = function () {
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
        Feng.success("修改成功!");
        window.location.reload();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.setData($("#userProfileForm").serialize());
    ajax.start();
};

/**
 * 修改密码
 */
UserInfo.chPwd = function () {
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
        Feng.success("修改成功!");
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.setData($("#pwdForm").serialize())
    ajax.start();
};

$(function () {

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'yyyy-mm-dd'
    });

    //表单验证
    Feng.initValidator("userProfileForm", UserInfo.validateFields);
    $("#sex").val($("#sexValue").val());

    //部门树
    var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(UserInfo.onClickDept);
    ztree.init();
    UserInfo.ztree = ztree;

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
