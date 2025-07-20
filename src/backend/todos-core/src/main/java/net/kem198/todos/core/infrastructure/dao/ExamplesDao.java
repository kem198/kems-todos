package net.kem198.todos.core.infrastructure.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import net.kem198.todos.core.infrastructure.entity.Examples;

/**
 */
@Dao
@ConfigAutowireable
public interface ExamplesDao {

    /**
     * @param id
     * @return the Examples entity
     */
    @Select
    Examples selectById(Integer id);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Examples entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Examples entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Examples entity);
}
