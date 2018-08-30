jQuery(document).ready(function () {
    $(".code").on("click", function () {
        var oper = $(this);
        var user_id = oper.attr("data-id");
        $.ajax({
            type: "post",
            url: "/admin/user/switch",
            dataType: "json",
            data: {id: user_id},
            success: hanleSuccess,
            error: hanleError
        });
    });

    // 批量禁用
    $("#diableBtn").on("click", function () {
        if ($('input.itemId:checked').length == 0) {
            alert("您还没有选择要删除的内容!");
            return;
        }
        $.ajax({
            type: "post",
            url: "/admin/user/disable",
            dataType: "json",
            data: $("form").serialize(),
            success: hanleSuccess,
            error: hanleError
        });
    });

    // 批量删除
    $("#deleteBtn").on("click", function () {
        if ($('input.itemId:checked').length == 0) {
            alert("您还没有选择要删除的内容!");
            return;
        }
        // 这里也可以使用表单提交的方式删除
        $.ajax({
            type: "post",
            url: "/admin/user/delete",
            dataType: "json",
            data: $("form").serialize(),
            success: hanleSuccess,
            error: hanleError
        });
    });

    function hanleSuccess(data) {
        alert(data.msg);
        if (data.code == 200) {
            window.location.reload();
        }
    }

    function hanleError() {
        alert("您没有相应的权限,请联系管理员");
    }
});
