package org.tc.shiro.core.shiroext.vo;

import com.stylefeng.guns.core.ztree.MenuNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:26:43
 */
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer id;          // 主键ID
    public String account;      // 账号
    public String name;         // 姓名
    public Integer deptid;      // 部门id
    public String deptName;        // 部门名称
    public String avatar;
    public List<Integer> roleList; // 角色集
    public List<String> roleNames; // 角色名称集
    public List<MenuNode> menuList;  //左侧边栏

}
