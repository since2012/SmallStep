$(function () {
    $("#submitBtn").on("click", function () {
        $.ajax({
            type: "post",
            url: "/admin/resource/add",
            dataType: "json",
            data: $("form").serialize(),
            success: function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    window.location.href = "/admin/resource/list";
                }
            },
            error: function () {
                alert("您没有相应的权限,请联系管理员");
            }
        });
    });

});