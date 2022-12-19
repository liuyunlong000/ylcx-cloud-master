package com.lcxbs.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象
 *
 * @author fanlianwei
 */
public class RouterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路由路径(注意根路由（第一条数据）是斜线，第一级路由必须带斜线，第二级路由开始不能，path名不可重复)
     */
    private String path;
    /**
     * 名称(首字母大写，一定要与vue文件的name对应起来，name名不可重复，用于noKeepAlive缓存控制（该项特别重要）)
     */
    private String name;
    /**
     * 路由组件(第一级路由是为Layout，其余为层级为vue的文件路径)
     */
    private String component;
    /**
     * 重定向到子路由，格式从第一级路由开始拼接
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redirect;
    /**
     * 元信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MetaVo meta;

    /**
     * 节点类型：1目录，2菜单，3操作
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long nodeType;

    /**
     * @return
     * @see RouterVo#nodeType
     */
    public Long getNodeType() {
        return nodeType;
    }

    /**
     * @return
     * @see RouterVo#nodeType
     */
    public void setNodeType(Long nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RouterVo> children = new ArrayList<>();

//    /**
//     * @return
//     * @see RouterVo#childrenNameList
//     */
//    public List getChildrenNameList() {
//        List list = new ArrayList();
//        if (null != this.getChildren()) {
//            convertIdToList(list, this.children);
//        }
//        return list;
//    }
//
//    /**
//     * 递归查找所有菜单的子菜单
//     *
//     * @param childrenNameList
//     * @param children
//     */
//    private void convertIdToList(List childrenNameList, List<RouterVo> children) {
//        children.forEach((item) -> {
//            childrenNameList.add(item.meta.getMenuId());
//            if (item.children != null && item.children.size() > 0) {
//                convertIdToList(childrenNameList, item.children);
//            }
//        });
//    }

    /**
     * @return
     * @see RouterVo#path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return
     * @see RouterVo#path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return
     * @see RouterVo#name
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     * @see RouterVo#name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @see RouterVo#component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @return
     * @see RouterVo#component
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * @return
     * @see RouterVo#redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * @return
     * @see RouterVo#redirect
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     * @return
     * @see RouterVo#meta
     */
    public MetaVo getMeta() {
        return meta;
    }

    /**
     * @return
     * @see RouterVo#meta
     */
    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }

    /**
     * @return
     * @see RouterVo#children
     */
    public List<RouterVo> getChildren() {
        return children;
    }

    /**
     * @return
     * @see RouterVo#children
     */
    public void setChildren(List<RouterVo> children) {
        this.children = children;
    }
}
