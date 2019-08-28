package dao;

import bean.Page;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * Dao层操作工具类
 *
 * @param <T>数据映射实体类型
 */
public interface BaseDao<T> {
    /**
     * 根据ID加载实体
     *
     * @param entityClass 对应实体类
     * @param id          对应实体类ID
     * @return 对应实体
     */
    T getById(@NotNull Class<T> entityClass, @NotNull Object id);

    /**
     * 保存实体
     *
     * @param entity 对应实体
     * @return 可序列化对象，即新增的实体的ID
     */
    Serializable save(@NotNull T entity);

    /**
     * 更新实体
     *
     * @param entity 对应实体
     */
    boolean update(@NotNull T entity);

    /**
     * 保存或更新实体
     *
     * @param entity 对应实体
     */
    void saveOrUpdate(@NotNull T entity);

    /**
     * @param entity 对应实体
     */
    void delete(@NotNull T entity);

    /**
     * 根据ID删除
     *
     * @param entityClass 对应实体类
     * @param id          对应实体类ID
     */
    Boolean delete(@NotNull Class<T> entityClass, @NotNull Object id);

    /**
     * 获得所有实体
     *
     * @param entityClass 对应实体类
     * @return 实体列表
     */
    List<T> getAll(@NotNull Class<T> entityClass);

    /**
     * 根据页号获取实体对象列表
     *
     * @param entityClass 对应实体类
     * @param pageNumber  页号
     * @param pageSize    页面大小
     * @return 页对象
     */
    Page getAllByPage(@NotNull Class<T> entityClass, int pageNumber, int pageSize);

    /**
     * 获得所有实体行数
     *
     * @param entityClass 对应实体类
     * @return 实体行数
     */
    long count(@NotNull Class<T> entityClass);

    /**
     * 批量保存
     *
     * @param entities 对应实体对象列表
     */
    boolean batchToSave(@NotNull List<T> entities);

    /**
     * 批量更新
     *
     * @param entities 对应实体对象列表
     */
    boolean batchToUpdate(@NotNull List<T> entities);

    /**
     * 根据主键字段进行批量更新
     *
     * @param entities 对应实体对象列表
     */
    boolean batchToUpdateByIdentifierProperty(@NotNull List<T> entities);

    /**
     * 批量删除
     *
     * @param entityClass 对应实体类
     * @param id          对应实体ID列表
     */
    boolean batchToDelete(@NotNull Class<T> entityClass, @NotNull List<Object> id);

    /**
     * 根据ID字段进行批量删除
     *
     * @param entityClass 对应实体类
     * @param id          对应实体ID列表
     */
    boolean batchToDeleteIdentifierProperty(Class<T> entityClass, List<Object> id);
}
