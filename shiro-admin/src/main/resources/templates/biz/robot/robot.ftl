<!DOCTYPE HTML>
<html>
<head>
    <title>微信公众号</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="UTF-8">

    <script type="text/javascript" src="/static/modular/biz/robot/robot.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        仿公众号
        <small>自动回复</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">业务管理</a></li>
        <li class="active">自动回复</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Direct Chat -->
    <div class="row ">
        <div class="col-md-12">
            <div class="box box-success">
                <div class="col-md-6 col-md-offset-3">
                    <!-- DIRECT CHAT WARNING -->
                    <div class="box box-success direct-chat direct-chat-success">
                        <div class="box-header with-border">
                            <h3 class="box-title">自动回复机器人</h3>

                            <div class="box-tools pull-right">
                                <span data-toggle="tooltip" title="3 New Messages" class="badge bg-yellow">3</span>
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="Contacts"
                                        data-widget="chat-pane-toggle">
                                    <i class="fa fa-comments"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <!-- Conversations are loaded here -->
                            <div class="direct-chat-messages">
                                <!-- Message. Default to the left -->
                                <div class="direct-chat-msg">
                                    <div class="direct-chat-info clearfix">
                                        <span class="direct-chat-name pull-left">机器人</span>
                                        <span class="direct-chat-timestamp-right pull-right">23 Jan 2:00 pm</span>
                                    </div>
                                    <!-- /.direct-chat-info -->
                                    <img class="direct-chat-img" src="/static/dist/img/user1-128x128.jpg"
                                         alt="Message User Image"><!-- /.direct-chat-img -->
                                    <div class="direct-chat-text">
                                        Is this template really for free? That's unbelievable!
                                    </div>
                                    <!-- /.direct-chat-text -->
                                </div>
                                <!-- /.direct-chat-msg -->

                                <!-- Message to the right -->
                                <div class="direct-chat-msg right">
                                    <div class="direct-chat-info clearfix">
                                        <span class="direct-chat-name pull-right">mek</span>
                                        <span class="direct-chat-timestamp-left pull-left">23 Jan 2:05 pm</span>
                                    </div>
                                    <!-- /.direct-chat-info -->
                                    <img class="direct-chat-img" src="/static/dist/img/user3-128x128.jpg"
                                         alt="Message User Image"><!-- /.direct-chat-img -->
                                    <div class="direct-chat-text">
                                        You better believe it!
                                    </div>
                                    <!-- /.direct-chat-text -->
                                </div>
                                <!-- /.direct-chat-msg -->
                            </div>
                            <!--/.direct-chat-messages-->

                            <!-- Contacts are loaded here -->
                            <div class="direct-chat-contacts">
                                <ul class="contacts-list">
                                    <li>
                                        <a href="#">
                                            <img class="contacts-list-img" src="/static/dist/img/user1-128x128.jpg"
                                                 alt="User Image">

                                            <div class="contacts-list-info">
                                                <span class="contacts-list-name">
                                                  Count Dracula
                                                  <small class="contacts-list-date pull-right">2/28/2015</small>
                                                </span>
                                                <span class="contacts-list-msg">How have you been? I was...</span>
                                            </div>
                                            <!-- /.contacts-list-info -->
                                        </a>
                                    </li>
                                    <!-- End Contact Item -->
                                </ul>
                                <!-- /.contatcts-list -->
                            </div>
                            <!-- /.direct-chat-pane -->
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <form action="#" method="post">
                                <div class="input-group">
                                    <input type="text" name="message" placeholder="Type Message ..."
                                           class="form-control">
                                    <span class="input-group-btn">
                                        <button onclick="Robot.showData()" type="button"
                                                class="btn btn-success btn-flat">Send</button>
                                      </span>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-footer-->
                    </div>
                    <!--/.direct-chat -->
                </div>
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->
</body>
</html>