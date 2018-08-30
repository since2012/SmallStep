<#include "../common/header.ftl">
<div class="panel panel-danger">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <form method="post" class="form-horizontal">
            <input id="id" name="id" type="hidden" value="">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">权限名称：</label>
                <div class="col-sm-10">
                    <input name="name" class="form-control" id="name" placeholder="请输入权限名称"/>
                </div>
            </div>
            <div class="form-group">
                <label for="permission" class="col-sm-2 control-label">权限permission ：</label>
                <div class="col-sm-10">
                    <input name="permission" class="form-control" id="permission"
                           placeholder="请输入权限 permission 字符串"/>
                </div>
            </div>
            <div class="form-group">
                <label for="url" class="col-sm-2 control-label">权限url ：</label>
                <div class="col-sm-10">
                    <input name="url" class="form-control" id="url" placeholder="请输入权限 url"/>
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
            <div class="col-sm-offset-1 col-sm-10 text-center">
                <button id="submitBtn" type="button" class="btn btn-danger">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </form>
    </div>
</div>
<#include "../common/footer.html">