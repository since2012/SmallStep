var Robot = {};

/**
 * 向聊天记录中添加聊天内容
 * @param myClass 添内容的样式
 * @param name 发送消息的账号名称
 * @param content 发送的内容
 */
Robot.appendDialog = function (isRight, name, content) {

    var chattext = '<div class="direct-chat-msg';
    if (isRight) {
        chattext += ' right';
    }
    chattext += '"><div class="direct-chat-info clearfix">';
    if (isRight) {
        chattext += '<span class="direct-chat-name pull-right">' + name +
            '</span><span class="direct-chat-timestamp-left pull-left">';
    } else {
        chattext += '<span class="direct-chat-name pull-left">' + name +
            '</span><span class="direct-chat-timestamp-right pull-right">';
    }
    chattext += Robot.getCurrentDate() + '</span></div><img class="direct-chat-img" ' +
        'src="/static/dist/img/user3-128x128.jpg"alt="Message User Image">' +
        '<div class="direct-chat-text">' + content + '</div></div>';
    $(".direct-chat-messages").append(chattext);
}

/**
 * 获取当前系统时间
 * @returns {String} 当前系统时间
 */
Robot.getCurrentDate = function () {
    var date = new Date();
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
};

Robot.showData = function () {
    var message;
    $(".direct-chat form input").each(function () {
        var val = $(this).val();
        if (val) {
            message = val;
        }
    });
    //0,"",null,undefine
    if (!message) {
        alert("请输入内容！");
        return;
    }
    $.ajax({
        url: "/robot/reply",
        type: "POST",
        dataType: "json",
        timeout: 3000,
        data: {"message": encodeURI(message)},
        success: function (result) {
            Robot.appendDialog(true, "Sarah Bullock", message);
            Robot.appendDialog(false, "Alexander Pierce", result.data);
            $(".direct-chat form input").each(function () {
                $(this).val("");
            });
        }
    });
};

/**
 * 页面加载
 */
$(function () {
    var content = "回复[查看]收取更多精彩内容。";
    content += "<br/>回复[帮助]可以查看所有可用的指令。";
    // 添加公众号的开场白
    Robot.appendDialog(false, "Alexander Pierce", content);
});

