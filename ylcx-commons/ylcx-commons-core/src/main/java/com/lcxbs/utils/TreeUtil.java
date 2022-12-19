package com.lcxbs.utils;

import com.lcxbs.core.ITree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树工具类
 */
public class TreeUtil {

    /**
     * 构建树形列表
     *
     * @param list 数据列表
     * @return
     */
    public static List buildTree(List list) {
        if (null == list || list.size() == 0) {
            return list;
        }
        if (!(list.get(0) instanceof ITree)) {
            return list;
        }
        List<ITree> rootNodeList = getRootNode(list);
        List treeMenus = new ArrayList<>();
        for (Object rootNode : rootNodeList) {
            ITree treeNode = (ITree) rootNode;
            rootNode = buildChildTree(list, treeNode);
            treeMenus.add(rootNode);
        }
        return treeMenus;
    }

    /**
     * 将资源列表构建树形列表
     *
     * @param list   数据列表
     * @param rootId 当前数据列表根节点ID
     * @return
     */
    public static List buildTree(List list, String rootId) {
        //获取根节点
        List<ITree> rootNodeList = getRootNode(list, rootId);
        if (rootNodeList == null) {//如果沒有实现ITree接口，直接返回
            return list;
        }
        List<ITree> treeMenus = new ArrayList<>();
        for (ITree rootNode : rootNodeList) {
            rootNode = buildChildTree(list, rootNode);
            treeMenus.add(rootNode);
        }
        return treeMenus;
    }

    /**
     * 递归，建立子树形结构
     *
     * @param pNode
     * @return
     */
    private static ITree buildChildTree(List<ITree> list, ITree pNode) {
        List childMenus = new ArrayList<>();
        for (ITree menuNode : list) {
            if (menuNode.getParentTreeId().equals(pNode.getTreeId())) {
                childMenus.add(buildChildTree(list, menuNode));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    /**
     * 获取根节点
     *
     * @param list 所有节点
     * @return
     */
    private static List getRootNode(List list, String rootId) {
        List rootMenuLists = new ArrayList<>();
        for (Object menuNode : list) {
            if (menuNode instanceof ITree) {
                ITree tree = (ITree) menuNode;
                if (tree.getParentTreeId().equals(rootId)) {
                    rootMenuLists.add(menuNode);
                }
            } else {
                return null;
            }
        }
        return rootMenuLists;
    }

    /**
     * 获取根节点（没有子节点的自动认为是根节点）
     *
     * @param list 所有节点
     * @return
     */
    private static List getRootNode(List list) {
        List rootMenuLists = new ArrayList<>();
        for (Object menuNode : list) {
            if (menuNode instanceof ITree) {
                ITree tree = (ITree) menuNode;
                List collect = (List) list.stream().filter(t -> ((ITree) t).getTreeId().equals(tree.getParentTreeId())).collect(Collectors.toList());
                if(collect==null||collect.size()==0){
                    rootMenuLists.add(menuNode);
                }
            } else {
                return null;
            }
        }
        return rootMenuLists;
    }

    /**
     * 构建树形列表
     *
     * @param list 数据列表
     * @param idCode 节点属性名
     * @param pidCode 上级节点属性名
     * @return
     */
    public static List buildTreeFromMap(List list, String idCode, String pidCode) {
        if (null == list || list.size() == 0) {
            return list;
        }
        if (!(list.get(0) instanceof Map)) {
            return list;
        }
        List rootNodeList = getRootNodeFromMap(list, idCode, pidCode);
        List treeMenus = new ArrayList<>();
        for (Object rootNode : rootNodeList) {
            Map treeNode = (Map) rootNode;
            rootNode = buildChildTreeFromMap(list, treeNode, idCode, pidCode);
            treeMenus.add(rootNode);
        }
        return treeMenus;
    }

    /**
     * 将资源列表构建树形列表
     *
     * @param list   数据列表
     * @param rootId 当前数据列表根节点ID
     * @param idCode 节点属性名
     * @param pidCode 上级节点属性名
     * @return
     */
    public static List buildTreeFromMap(List list, String rootId, String idCode, String pidCode) {
        //获取根节点
        List rootNodeList = getRootNodeFromMap(list, rootId, idCode, pidCode);
        if (rootNodeList == null) {//如果沒有实现ITree接口，直接返回
            return list;
        }
        List treeMenus = new ArrayList<>();
        for (Object node : rootNodeList) {
            Map rootNode = (Map) node;
            rootNode = buildChildTreeFromMap(list, rootNode, idCode, pidCode);
            treeMenus.add(rootNode);
        }
        return treeMenus;
    }

    /**
     * 递归，建立子树形结构
     *
     * @param list 数据集合
     * @param pNode  上级节点
     * @param idCode 节点属性名
     * @param pidCode 上级节点属性名
     * @return
     */
    private static Map buildChildTreeFromMap(List list, Map pNode, String idCode, String pidCode) {
        List childMenus = new ArrayList<>();
        String idCodeValue = String.valueOf(pNode.get(idCode));
        for (Object node : list) {
            Map menuNode = (Map) node;
            String pidCodeValue = String.valueOf(menuNode.get(pidCode));
            if (pidCodeValue.equals(idCodeValue)) {
                childMenus.add(buildChildTreeFromMap(list, menuNode, idCode, pidCode));
            }
        }
        pNode.put("children", childMenus);
        return pNode;
    }

    /**
     * 获取根节点
     *
     * @param list 所有节点
     * @param rootId 根节点ID
     * @param idCode 节点属性名
     * @param pidCode 上级节点属性名
     * @return
     */
    private static List getRootNodeFromMap(List list, String rootId, String idCode, String pidCode) {
        List rootMenuLists = new ArrayList<>();
        for (Object menuNode : list) {
            Map tree = (Map) menuNode;
            String pidCodeValue = String.valueOf(tree.get(pidCode));
            if (pidCodeValue.equals(rootId)) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    /**
     * 获取根节点（没有子节点的自动认为是根节点）
     *
     * @param list 所有节点
     * @param idCode 节点属性名
     * @param pidCode 上级节点属性名
     * @return
     */
    private static List getRootNodeFromMap(List list, String idCode, String pidCode) {
        List rootMenuLists = new ArrayList<>();
        for (Object menuNode : list) {
            Map tree = (Map) menuNode;
            String pidCodeValue = String.valueOf(tree.get(pidCode));
            boolean hasTreeNode = false;
            for (Object subNode : list) {
                Map subTree = (Map) subNode;
                String idCodeValue = String.valueOf(subTree.get(idCode));
                if (pidCodeValue.equals(idCodeValue)) {
                    hasTreeNode = true;
                    break;
                }
            }
            if (!hasTreeNode) {//说明没有子节点
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
