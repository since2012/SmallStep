/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var StockInfo = {
    stockInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        total: {
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                }
            }
        },
        begintime: {
            validators: {
                notEmpty: {
                    message: '开始时间不能为空'
                }
            }
        },
        endtime: {
            validators: {
                notEmpty: {
                    message: '结束时间不能为空'
                }
            }
        },
        primeprice: {
            validators: {
                notEmpty: {
                    message: '原价不能为空'
                },
                numeric: {
                    message: '原价只能输入数字'
                }
            }
        },
        saleprice: {
            validators: {
                notEmpty: {
                    message: '促销价不能为空'
                },
                numeric: {
                    message: '促销价只能输入数字'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
StockInfo.clearData = function () {
    this.stockInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StockInfo.set = function (key, value) {
    if (typeof value == "undefined") {
        if (typeof $("#" + key).val() == "undefined") {
            var str = "";
            var ids = "";
            $("input[name='" + key + "']:checkbox").each(function () {
                if (true == $(this).is(':checked')) {
                    str += $(this).val() + ",";
                }
            });
            if (str) {
                if (str.substr(str.length - 1) == ',') {
                    ids = str.substr(0, str.length - 1);
                }
            } else {
                $("input[name='" + key + "']:radio").each(function () {
                    if (true == $(this).is(':checked')) {
                        ids = $(this).val()
                    }
                });
            }
            this.stockInfoData[key] = ids;
        } else {
            this.stockInfoData[key] = $("#" + key).val();
        }
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StockInfo.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
StockInfo.close = function () {
    parent.layer.close(window.parent.Stock.layerIndex);
};

/**
 * 收集数据
 */
StockInfo.collectData = function () {
    StockInfo.clearData();
    this.set("id").set('name').set('total').set('begintime').set('endtime');
};


/**
 * 验证数据是否为空
 */
StockInfo.validate = function () {
    $('#stockInfoForm').data("bootstrapValidator").resetForm();
    $('#stockInfoForm').bootstrapValidator('validate');
    return $("#stockInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
StockInfo.addSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stock/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Stock.table.refresh();
        StockInfo.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stockInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
StockInfo.editSubmit = function () {
    if (!this.validate()) {
        return;
    }
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stock/edit", function (data) {
        Feng.success("修改成功!");
        if (window.parent.Stock != undefined) {
            window.parent.Stock.table.refresh();
            StockInfo.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stockInfoData);
    ajax.start();
};


$(function () {
    //表单验证
    Feng.initValidator("stockInfoForm", StockInfo.validateFields);

    $('#beginTime').datetimepicker({
        language: 'zh-CN',
        // weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        // startView: 2,
        // forceParse: 0,
        // showMeridian: 1
        format: 'yyyy-mm-dd hh:ii'
    });
    $('#endTime').datetimepicker({
        language: 'zh-CN',
        // weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        // startView: 2,
        // forceParse: 0,
        // showMeridian: 1
        format: 'yyyy-mm-dd hh:ii'
    });

});
