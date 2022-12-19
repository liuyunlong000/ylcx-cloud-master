package com.lcxbs.utils;

import com.lcxbs.sys.model.SysRoleMenu;

import java.util.ArrayList;
import java.util.List;

public class SysRoleUtil {

    /**
     * 获取选中的菜单ID
     * @param sysRoleMenuList
     * @return
     */
    public static List getCheckedKeys(List<SysRoleMenu> sysRoleMenuList){
        List list=new ArrayList();
        for (SysRoleMenu sysRoleResource:sysRoleMenuList){
            list.add(sysRoleResource.getMenuId());
        }
        return list;
    }
}
