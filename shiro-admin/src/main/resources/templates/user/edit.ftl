<div class="panel panel-primary">
    <div class="panel-heading">添加用户</div>
    <div class="panel-body">
        <form class="form-horizontal">
            <input type="hidden" name="id" value="">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label"> 用户名 </label>
                <div class="col-sm-10">
                    <input name="username" class="form-control" id="username" placeholder="请输入用户名"/>
                </div>
            </div>
            <div class="form-group">
                <label for="nickname" class="col-sm-2 control-label"> 昵称 </label>
                <div class="col-sm-10">
                    <input name="nickname" class="form-control" id="nickname" placeholder="请输入昵称"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label"> 密码 </label>
                <div class="col-sm-10">
                    <input name="password" type="password" class="form-control"
                           id="password" placeholder="请输入密码"/>
                </div>
            </div>
            <div class="form-group">
                <label for="enable" class="col-sm-2 control-label"> 状态
                </label>
                <div class="col-sm-10">
                    <select name="enable" id="enable" class="form-control">
                        <option value="true">启用</option>
                        <option value="false">停用</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label"> 角色 </label>
                <div class="col-sm-10" id="idBox">
                <#if list?? && list?size gt 0>
                    <#list list as item>
                        <div class="checkbox"><label>
                            <input id="${item.id}" type="checkbox" name="roleId"
                                   value="${item.id}"/>${item.name}
                        </label></div>
                    </#list>
                </#if>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-danger">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </form>
    </div>
</div>
<#include "../common/footer.html">