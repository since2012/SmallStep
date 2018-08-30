var edit = {
    init: function () {
        $('form').submit(function (e) {
            e.preventDefault();
            $.ajax({
                url: "/admin/user/add",
                type: 'post',
                dataType: 'json',
                data: $('form').serialize(),
                success: function (result) {
                    alert(result.msg);
                    if (result.code == 200) {
                        window.location.href = "/admin/user/list";
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        });
    }
}

jQuery(document).ready(function () {
    edit.init();
});

