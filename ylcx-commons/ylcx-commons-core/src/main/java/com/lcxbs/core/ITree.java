package com.lcxbs.core;

import java.util.List;

/**
 * 树接口
 */
public interface ITree {

    /**
     * 获取节点ID
     * @return
     */
    String getTreeId();

    /**
     * 获取上级节点ID
     * @return
     */
    String getParentTreeId() ;

    /**
     * 获取子节点列表
     * @return
     */
    List getChildren();

    /**
     * 设置子节点列表
     * @param children
     */
    void setChildren(List children) ;
}
