package com.lcxbs.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.lcxbs.model.MetaVo;
import com.lcxbs.model.RouterVo;
import com.lcxbs.sys.model.SysMenu;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 资源对象工具类
 */
public class SysMenuUtil {

    /**
     * 构建前端所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public static List<RouterVo> buildMenuTree(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setPath(replacePath(menu));//路径地址
            router.setName(menu.getComponentName()==null?"":menu.getComponentName());//路由名称
            router.setComponent(menu.getComponentPath()==null?"":menu.getComponentPath());//组件路径
            router.setMeta(getMetaVo(menu));//获取Meta对象
            router.setNodeType(menu.getMenuType());//节点类型
            List<SysMenu> cMenus = menu.getChildren();
            List<RouterVo> children = buildMenuTree(cMenus);
            router.setChildren(children);
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public static List<RouterVo> buildRouterList(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setPath(menu.getMenuUrl()==null?"":menu.getMenuUrl());//路径地址
            router.setName(menu.getComponentName()==null?"":menu.getComponentName());//路由名称
            router.setComponent(menu.getComponentPath()==null?"":menu.getComponentPath());//组件路径
            router.setMeta(getMetaVo(menu));//获取Meta对象
            routers.add(router);
        }
        return routers;
    }

    /**
     * 主框架404路由
     * @return
     */
    private static RouterVo init404Router(){
        RouterVo main404Vo = new RouterVo();
        main404Vo.setPath("/:pathMatch(.*)*");
        main404Vo.setName("MainNotFound");
        main404Vo.setRedirect("/404");
        main404Vo.setNodeType(2L);
        main404Vo.setChildren(new ArrayList<>());
        MetaVo main404Meta=new MetaVo();
        main404Vo.setMeta(main404Meta);
        main404Meta.setMenuId(main404Vo.getName());
        return main404Vo;
    }

    /**
     * 主框架Iframe路由
     * @return
     */
    private static RouterVo initIframeRouter(){
        RouterVo iframeRouter = new RouterVo();
        iframeRouter.setPath("/iframe/view");
        iframeRouter.setName("iframe_view");
        iframeRouter.setComponent("@/views/vab/iframe/view");
        iframeRouter.setNodeType(2L);
        iframeRouter.setChildren(new ArrayList<>());
        MetaVo iframeMeta=new MetaVo();
        iframeRouter.setMeta(iframeMeta);
        iframeMeta.setMenuId(iframeRouter.getName());
        return iframeRouter;
    }

    /**
     * 组装元数据
     * @param menu 菜单
     * @return
     */
    public static MetaVo getMetaVo(SysMenu menu){
        MetaVo metaVo=new MetaVo();
        metaVo.setMenuId(menu.getMenuId());
        metaVo.setIsLink(menu.getIsLink());
        metaVo.setIsHide(menu.getIsHide()==1L);
        metaVo.setIsKeepAlive(menu.getIsKeepAlive()==1L);
        metaVo.setIsAffix(menu.getIsAffix()==1L);
        metaVo.setTitle(menu.getMenuName());
        metaVo.setIsIframe(menu.getIsIframe()==1L);
        metaVo.setDynamicNewTab(true);
        metaVo.setTitle(menu.getMenuName());
        metaVo.setIcon(menu.getMenuIcon());
        metaVo.setParams(menu.getMenuParams());
        metaVo.setAuth(menu.getIsAuth()==1L?true:false);
        metaVo.setInherit(menu.getIsInherit()==1L?true:false);
        return metaVo;
    }


    /**
     * 获取权限字符串
     * @param sysResourceList
     * @return
     */
    public static List<String> getPermissions(List<SysMenu> sysResourceList){
        List<String> list=new ArrayList<>();
        for (SysMenu item:sysResourceList){
            if(null!=item.getPermCode()&&!list.contains(item.getPermCode())) {
                list.add(item.getPermCode());
            }
        }
        return list;
    }

    /**
     * 新增或更新对象处理
     * @param sysMenu
     */
    public static void saveOrUpdateHandle(SysMenu sysMenu){
        if(StringUtils.isBlank(sysMenu.getParentId())) {
            sysMenu.setParentId("0");
        }
        if(StringUtils.isBlank(sysMenu.getComponentName())){
            sysMenu.setComponentName(StringUtils.capitalize(PinyinUtil.hanziToPinyin(sysMenu.getMenuName()))+ DateUtil.format(new Date(),"yyMMddHHmmss"));
        }
        if(StringUtils.isBlank(sysMenu.getComponentPath())){
            sysMenu.setComponentPath("layout");
        }
        if(null==sysMenu.getNid()) {
            sysMenu.setMenuId(IdUtil.simpleUUID());
        }
        //如果url不存在，则默认使用ID（防止前端菜单错乱）
        if(StringUtils.isEmpty(sysMenu.getMenuUrl())){
            sysMenu.setMenuUrl(sysMenu.getMenuId());
        }
        if(!sysMenu.getMenuUrl().startsWith("/")){
            sysMenu.setMenuUrl("/"+sysMenu.getMenuUrl());//如果路由URL不是以斜杠开头则自动添加
        }
    }

    /**
     * 将菜单转为菜单编码列表
     *
     * @param menuList
     * @return
     */
    public static List<String> convertToList(List<SysMenu> menuList) {
        List<String> menuCodeList = new ArrayList<>();
        for (SysMenu secuMenu : menuList) {
            String objValue = secuMenu.getPermCode();
            if (null != objValue && objValue.length() > 0) {
                if (!menuCodeList.contains(objValue)) {
                    menuCodeList.add(objValue);
                }
            }
        }
        return menuCodeList;
    }

    /**
     * 获取第一个菜单
     * @param menuList
     * @return
     */
    public static String getFirstPath(List<SysMenu> menuList) {
        for (SysMenu item : menuList) {
            //如果是菜单
            if (item.getMenuType() == 2L) {
                return replacePath(item);
            } else if (item.getMenuType() == 1 && item.getChildren().size() > 0) {
                return getFirstPath(item.getChildren());
            }
        }
        return "";
    }

    /**
     * 替换url中的参数
     * @param item
     * @return
     */
    public static String replacePath(SysMenu item) {
        //如果是菜单
        if (item.getMenuType() == 2L) {
            String menuUrl = item.getMenuUrl();
            //判断地址中是否包含参数
            if (Objects.nonNull(item.getMeta())&&!item.getMeta().getIsHide()&&Objects.nonNull(menuUrl) && menuUrl.contains(":")) {
                String params = item.getMeta().getParams();
                Map map = JSONUtil.toBean(params, Map.class);
                if (Objects.nonNull(map) && map.size() > 0) {
                    Iterator<String> it = map.keySet().iterator();
                    //循环参数
                    while (it.hasNext()) {
                        String key = it.next();
                        Object value = map.get(key);
                        if (Objects.nonNull(value)) {
                            menuUrl = menuUrl.replace(":" + key, value.toString());//替换参数为具体的值
                        }
                    }
                }
            }
            return menuUrl;
        }
        return item.getMenuUrl();
    }
}
