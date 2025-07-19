package net.kem198.todos_api.infrastructure.dao;

import net.kem198.todos_api.infrastructure.entity.Examples2;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 */
@Dao
public interface Examples2Dao {

    /**
     * @param id
     * @return the Examples2 entity
     */
    @Select
    Examples2 selectById(Integer id);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Examples2 entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Examples2 entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Examples2 entity);
}
