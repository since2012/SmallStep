// 批量删除
$("#submitBtn").on("click", function () {
    if ($('input[class="itemId"]:checked').length == 0) {
        alert("请至少选择一项权限");
        return;
    }
    // 这里也可以使用表单提交的方式删除
    $.ajax({
        type: "post",
        url: "/admin/role/resources",
        dataType: "json",
        data: $("form").serialize(),
        success: function (data) {
            alert(data.msg);
            if (data.code == 200) {
                window.location.href = "/admin/role/list";
            }
        },
        error: function () {
            alert("您没有相应的权限,请联系管理员");
        }
    });
});