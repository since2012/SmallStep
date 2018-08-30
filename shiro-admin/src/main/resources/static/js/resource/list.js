$(function () {

    //切换状态
    $(".code").on("click", function () {
        var oper = $(this);
        var res_id = oper.attr("data-id");
        $.ajax({
            url: "/admin/resource/switch",
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

    // 批量删除
    $("#diableBtn").on("click", function () {
        var size = $('input[class="itemId"]:checked').length;
        if (size == 0) {
            alert("您还没有选择要删除的内容!");
            return;
        }
        $.ajax({
            type: "post",
            url: "/admin/resource/diable",
            dataType: "json",
            data: $("form").serialize(),
            success: function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    window.location.reload();
                } else {
                }
            },
            error: function () {
                alert("您没有相应的权限,请联系管理员");
            }
        });
    });

    // 批量删除
    $("#deleteBtn").on("click", function () {
        var size = $('input[class="itemId"]:checked').length;
        if (size == 0) {
            alert("您还没有选择要删除的内容!");
            return;
        }
        $.ajax({
            type: "post",
            url: "/admin/resource/delete",
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