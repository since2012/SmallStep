/**
 * 菜单详情对话框
 */
var MenuInfo = {
    menuInfoData: {},
    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '菜单编号不能为空'
                }
            }
        },
        pcodeName: {
            validators: {
                notEmpty: {
                    message: '父菜单不能为空'
                }
            }
        },
        url: {
            validators: {
                notEmpty: {
                    message: '请求地址不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '序号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
MenuInfo.clearData = function () {
    this.menuInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfo.set = function (key, value) {
    this.menuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MenuInfo.close = function () {
    parent.layer.close(window.parent.Menu.layerIndex);
}

/**
 * 收集数据
 */
MenuInfo.collectData = function () {
    MenuInfo.clearData();
    this.set('id').set('name').set('code').set('pcode')
        .set('url').set('num').set('levels').set('icon').set("ismenu");
}

/**
 * 验证数据是否为空
 */
MenuInfo.validate = function () {
    $('#menuInfoForm').data("bootstrapValidator").resetForm();
    $('#menuInfoForm').bootstrapValidator('validate');
    return $("#menuInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加用户
 */
MenuInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/menu/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Menu.table.refresh();
        MenuInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.menuInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MenuInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/menu/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Menu.table.refresh();
        MenuInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.menuInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
MenuInfo.onClickDept = function (e, treeId, treeNode) {
    $("#pcodeName").attr("value", treeNode.name);
    $("#pcode").attr("value", treeNode.code);
};

/**
 * 显示父级菜单选择的树
 */
MenuInfo.showMenuSelectTree = function () {
    Feng.showInputTree("pcodeName", "pcodeTreeDiv", 15, 34);
};

$(function () {
    Feng.initValidator("menuInfoForm", MenuInfo.validateFields);

    var ztree = new $ZTree("pcodeTree", "/menu/tree");
    ztree.bindOnClick(MenuInfo.onClickDept);
    ztree.init();
    MenuInfo.ztreeInstance = ztree;

    //初始化是否是菜单
    if ($("#ismenuValue").val() == undefined) {
        $("#ismenu").val(0);
    } else {
        $("#ismenu").val($("#ismenuValue").val());
    }
});
