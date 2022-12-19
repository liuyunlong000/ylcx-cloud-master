package com.lcxbs.core;

import java.io.Serializable;
import java.util.List;

/**
 * DAO接口
 * @author fanlianwei
 * @param <T> 实体对象
 * @param <PK> 实体对象主键类型
 */
public interface IEntityDao<T, PK extends Serializable> {

    /**
     * 插入信息
     *
     * @param model 信息内容
     * @return
     */
    int insert(T model);

    /**
     * 删除信息
     *
     * @param modelPK 信息主键
     * @return
     */
    int delete(PK modelPK);

    /**
     * 条件删除
     *
     * @param statement Mapper中定义的ID
     * @param model 参数
     * @return
     */
    int delete(String statement, T model);

    /**
     * 根据主键获取信息
     *
     * @param modelPK 信息主键
     * @return
     */
    T getModelByKey(PK modelPK);

    /**
     * 获取实体信息
     *
     * @param statement Mapper中定义的ID
     * @param model 参数
     * @return
     */
    T getModel(String statement, T model);

    /**
     * 根据条件获取实体信息
     *
     * @param model 条件
     * @return
     */
    T getModelByWhere(T model);

    /**
     * 获取列表信息
     *
     * @param statement Mapper中定义的ID
     * @param model 参数
     * @return
     */
    List<T> getList(String statement, T model);

    /**
     * 获取对象信息
     *
     * @param statement Mapper中定义的ID
     * @param model 参数
     * @return
     */
    Object getObject(String statement, T model);

    /**
     * 更新所有列
     *
     * @param model 需更新的信息
     * @return
     */
    int update(T model);

    /**
     * 自定义更新
     *
     * @param statement 更新执行SQL名称
     * @param model 参数
     * @return
     */
    int update(String statement, T model);

    /**
     * 精确更新
     *
     * @param model 需更新的信息
     * @return
     */
    int updateSelective(T model);

    /**
     * 根据Map集合的条件更新
     * @param model
     * @return
     */
    int updateByMap(T model);

    /**
     * 获取记录数
     *
     * @param model
     * @return
     */
    Long countAll(T model);

    /**
     * 获取所有主键
     *
     * @param model 条件参数
     * @return
     */
    List<PK> getAllIds(T model);

    /**
     * 分页获取数据
     *
     * @param model 条件参数
     * @return
     */
    List<T> getListByPage(T model);
}
