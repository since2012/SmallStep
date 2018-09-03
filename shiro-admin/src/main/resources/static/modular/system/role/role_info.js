/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RoleInfo = {
    roleInfoData: {},
    deptZtree: null,
    pNameZtree: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        },
        tips: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RoleInfo.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfo.set = function (key, value) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfo.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RoleInfo.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RoleInfo.onClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RoleInfo.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};
RoleInfo.onDblClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RoleInfo.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
    $("#deptContent").fadeOut("fast");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RoleInfo.onClickNode = function (e, treeId, treeNode) {
    $("#pName").attr("value", RoleInfo.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
RoleInfo.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};

/**
 * 显示父级菜单的树
 *
 * @returns
 */
RoleInfo.showPNameSelectTree = function () {
    Feng.showInputTree("pName", "pNameContent");
};

/**
 * 收集数据
 */
RoleInfo.collectData = function () {
    this.clearData();
    this.set('id').set('name').set('pid').set('deptid').set('tips').set('num');
};

/**
 * 验证数据是否为空
 */
RoleInfo.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
RoleInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Role.table.refresh();
        RoleInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
RoleInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Role.table.refresh();
        RoleInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

$(function () {
    Feng.initValidator("roleInfoForm", RoleInfo.validateFields);

    var deptTree = new $ZTree("deptTree", "/dept/tree");
    deptTree.bindOnClick(RoleInfo.onClickDept);
    deptTree.bindOnDblClick(RoleInfo.onDblClickDept)
    deptTree.init();
    RoleInfo.deptZtree = deptTree;

    var pNameTree = new $ZTree("pNameTree", "/role/tree");
    pNameTree.bindOnClick(RoleInfo.onClickNode);
    pNameTree.init();
    RoleInfo.pNameZtree = pNameTree;
});
