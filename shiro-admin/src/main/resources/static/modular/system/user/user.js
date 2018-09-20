/**
 * 系统管理--用户管理的单例对象
 */
var UserMgr = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid: 0,
    queryData: {}
};

/**
 * 获取数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserMgr.get = function (key) {
    return $("#" + key).val();
};

/**
 * 设置收集数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserMgr.set = function (key, val) {
    this.queryData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 清除数据
 */
UserMgr.clearData = function () {
    this.queryData = {};
}

/**
 * 收集数据
 */
UserMgr.collectData = function () {
    this.clearData();
    this.set('deptid')
        .set('name')
        .set('phone')
        .set('beginTime')
        .set('endTime');
};

/**
 * 查询列表
 */
UserMgr.search = function () {
    this.collectData();
    UserMgr.table.refresh({query: this.queryData});
};

/**
 * 重置搜索选项
 */
UserMgr.reset = function () {
    $("form .form-group input").val("");
};

/**
 * 按部门搜索
 * @param e
 * @param treeId
 * @param treeNode
 */
UserMgr.onClickDept = function (e, treeId, treeNode) {
    UserMgr.deptid = treeNode.id;
    UserMgr.search();
};

/**
 * 初始化表格的列
 */
UserMgr.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '账号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'sexName', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '角色', field: 'roleName', align: 'center', valign: 'middle', sortable: true},
        {title: '部门', field: 'deptName', align: 'center', valign: 'middle', sortable: true},
        {title: '邮箱', field: 'email', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime', visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
UserMgr.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        UserMgr.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加管理员
 */
UserMgr.openAddPage = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员',
        area: ['750px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mgr/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
UserMgr.openEditPage = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['750px', '350px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击角色分配
 * @param
 */
UserMgr.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/set_role/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
UserMgr.delUser = function () {
    if (this.check()) {

        var operation = function () {
            var userId = UserMgr.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/mgr/delete", function () {
                Feng.success("删除成功!");
                UserMgr.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", userId);
            ajax.start();
        };

        Feng.confirm("是否删除用户" + UserMgr.seItem.account + "?", operation);
    }
};

/**
 * 冻结用户账户
 * @param userId
 */
UserMgr.freezeAccount = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/freeze", function (data) {
            Feng.success("冻结成功!");
            UserMgr.table.refresh();
        }, function (data) {
            Feng.error("冻结失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userId", userId);
        ajax.start();
    }
};

/**
 * 解除冻结用户账户
 * @param userId
 */
UserMgr.unfreeze = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/unfreeze", function (data) {
            Feng.success("解除冻结成功!");
            UserMgr.table.refresh();
        }, function (data) {
            Feng.error("解除冻结失败!");
        });
        ajax.set("userId", userId);
        ajax.start();
    }
}

/**
 * 重置密码
 */
UserMgr.resetPassword = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为111111？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Feng.ctxPath + "/mgr/reset", function (data) {
                Feng.success("重置密码成功!");
            }, function (data) {
                Feng.error("重置密码失败!");
            });
            ajax.set("userId", userId);
            ajax.start();
        });
    }
};

$(function () {
    //激活菜单
    $("#power").attr("class", "active");
    $("#mgr").attr("class", "active");

    //加载表格
    var defaultColunms = UserMgr.initColumn();
    var table = new BSTable("managerTable", "/mgr/list", defaultColunms);
    table.setPaginationType("client");
    UserMgr.table = table.init();

    //加载部门
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(UserMgr.onClickDept);
    ztree.init();

    //配置日期选择格式
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

});
