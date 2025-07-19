package net.kem198.todos_api.infrastructure.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class Examples2Listener implements EntityListener<Examples2> {

    @Override
    public void preInsert(Examples2 entity, PreInsertContext<Examples2> context) {
    }

    @Override
    public void preUpdate(Examples2 entity, PreUpdateContext<Examples2> context) {
    }

    @Override
    public void preDelete(Examples2 entity, PreDeleteContext<Examples2> context) {
    }

    @Override
    public void postInsert(Examples2 entity, PostInsertContext<Examples2> context) {
    }

    @Override
    public void postUpdate(Examples2 entity, PostUpdateContext<Examples2> context) {
    }

    @Override
    public void postDelete(Examples2 entity, PostDeleteContext<Examples2> context) {
    }
}
