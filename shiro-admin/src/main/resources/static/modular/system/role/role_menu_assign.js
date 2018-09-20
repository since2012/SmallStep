var MenuAssign = {
    roleId: ""
};

MenuAssign.setMemu = function () {
    var ids = Feng.zTreeCheckedNodes("zTree");
    var ajax = new $ax(Feng.ctxPath + "/role/set_menu", function (data) {
        Feng.success("分配角色成功!");
        window.parent.Role.table.refresh();
        parent.layer.close(index);
    }, function (data) {
        Feng.error("分配角色失败!"
            + data.responseJSON.message + "!");
    });
    ajax.set("roleId", MenuAssign.roleId);
    ajax.set("ids", ids);
    ajax.start();
};

MenuAssign.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

MenuAssign.initZtree = function () {
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "s"}
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid"
            }
        }
    };
    MenuAssign.roleId = $("#roleId").val();
    var ztree = new $ZTree("zTree", "/menu/treeByRoleId/" + MenuAssign.roleId);
    ztree.setSettings(setting);
    ztree.init();
};

$(function () {
    MenuAssign.initZtree();
});

