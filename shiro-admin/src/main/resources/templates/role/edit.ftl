<#include "../common/header.ftl">
<div class="panel panel-success">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <form class="form-horizontal">
            <input type="hidden" name="id" value="">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">角色名称</label>
                <div class="col-sm-10">
                    <input name="name" class="form-control" id="name"
                           placeholder="请填写角色名称"/>
                </div>
            </div>
            <div class="form-group">
                <label for="sn" class="col-sm-2 control-label">角色表示字符串</label>
                <div class="col-sm-10">
                    <input name="sn" class="form-control" id="sn"
                           placeholder="请填写角色表示字符串"/>
                </div>
            </div>
            <div class="form-group">
                <label for="enable" class="col-sm-2 control-label">启用 ：</label>
                <div class="col-sm-10">
                    <select name="enable" class="form-control">
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="sortCode" class="col-sm-2 control-label">排序码 ：</label>
                <div class="col-sm-10">
                    <input name="sortCode" class="form-control" id="sortCode" placeholder="请输入排序码"/>
                </div>
            </div>
            <div class=" text-center">
                <button id="submitBtn" type="button" class="btn btn-danger">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </form>
    </div>
</div>
<#include "../common/footer.html">