$(function () {

    $(".code").on("click", function () {
        var oper = $(this);
        var res_id = oper.attr("data-id");
        $.ajax({
            url: "/admin/role/switch",
            type: "post",
            dataType: "json",
            data: {id: res_id},
            success: function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    window.location.reload();
                }
            },
            error: function () {
                alert("您没有相应的权限,请联系管理员");
            }
        });
    });

    // 批量禁用
    $("#deleteBtn").on("click", function () {
        if ($('input[class="itemId"]:checked').length == 0) {
            alert("请至少选择一个角色");
            return;
        }
        // 这里也可以使用表单提交的方式删除
        $.ajax({
            type: "post",
            url: "/admin/role/diable",
            dataType: "json",
            data: $("form").serialize(),
            success: function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    window.location.reload();
                }
            },
            error: function () {
                alert("您没有相应的权限,请联系管理员");
            }
        });
    });

    // 批量删除
    $("#deleteBtn").on("click", function () {
        if ($('input[class="itemId"]:checked').length == 0) {
            alert("请至少选择一个角色");
            return;
        }
        // 这里也可以使用表单提交的方式删除
        $.ajax({
            type: "post",
            url: "/admin/role/delete",
            dataType: "json",
            data: $("form").serialize(),
            success: function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    window.location.reload();
                }
            },
            error: function () {
                alert("您没有相应的权限,请联系管理员");
            }
        });
    });

});
