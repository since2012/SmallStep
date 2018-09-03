/**
 * 初始化部门详情对话框
 */
var DeptInfo = {
    deptInfoData: {},
    zTreeInstance: null,
    validateFields: {
        simplename: {
            validators: {
                notEmpty: {
                    message: '部门名称不能为空'
                }
            }
        },
        fullname: {
            validators: {
                notEmpty: {
                    message: '部门全称不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '上级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DeptInfo.clearData = function () {
    this.deptInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeptInfo.set = function (key, val) {
    this.deptInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeptInfo.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DeptInfo.close = function () {
    parent.layer.close(window.parent.Dept.layerIndex);
}

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
DeptInfo.onClickDept = function (e, treeId, treeNode) {
    $("#pName").attr("value", DeptInfo.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
DeptInfo.showDeptSelectTree = function () {
    Feng.showInputTree("pName", "parentDeptMenu");
}

/**
 * 收集数据
 */
DeptInfo.collectData = function () {
    DeptInfo.clearData();
    this.set('id').set('simplename').set('fullname').set('tips').set('num').set('pid');
}

/**
 * 验证数据是否为空
 */
DeptInfo.validate = function () {
    $('#deptInfoForm').data("bootstrapValidator").resetForm();
    $('#deptInfoForm').bootstrapValidator('validate');
    return $("#deptInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加部门
 */
DeptInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dept/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Dept.table.refresh();
        DeptInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deptInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DeptInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dept/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Dept.table.refresh();
        DeptInfo.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deptInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("deptInfoForm", DeptInfo.validateFields);

    var ztree = new $ZTree("parentDeptMenuTree", "/dept/tree");
    ztree.bindOnClick(DeptInfo.onClickDept);
    ztree.init();
    DeptInfo.zTreeInstance = ztree;
});
