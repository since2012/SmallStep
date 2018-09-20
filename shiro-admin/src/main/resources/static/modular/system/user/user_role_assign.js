var RoleAssign = {
    userId: ""
};

/**
 * 初始化角色树
 */
RoleAssign.initZtree = function () {
    var setting = {
        check: {
            enable: true,
            chkboxType: {
                "Y": "",
                "N": ""
            }
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid"
            }
        }
    };

    RoleAssign.userId = $("#userId").val();
    var ztree = new $ZTree("zTree", "/role/treeByUserId/" + RoleAssign.userId);
    ztree.setSettings(setting);
    ztree.init();
};

/**
 * 保存配置数据
 */
RoleAssign.setRole = function () {
    var ids = Feng.zTreeCheckedNodes("zTree");
    var ajax = new $ax(Feng.ctxPath + "/mgr/set_role", function (data) {
        Feng.success("分配角色成功!");
        window.parent.UserMgr.table.refresh();
        RoleAssign.close();
    }, function (data) {
        Feng.error("分配角色失败!" + data.responseJSON.message + "!");
    });
    ajax.set("roleIds", ids);
    ajax.set("userId", RoleAssign.userId);
    ajax.start();
};

/**
 * 关闭弹窗
 */
RoleAssign.close = function () {
    parent.layer.close(window.parent.UserMgr.layerIndex);
};


$(function () {
    RoleAssign.initZtree();
});

