package com.rslakra.swaggerservice.persistence.entity.listener;

import com.rslakra.swaggerservice.persistence.entity.BaseEntity;
import com.rslakra.swaggerservice.service.AppContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Aug 08, 2021 14:10:33
 */
public class EntityPersistenceListener {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(EntityPersistenceListener.class);

    public enum Operation {
        INSERTED,
        UPDATED,
        DELETED;
    }

    private final EntityManager entityManager;

    public EntityPersistenceListener() {
        entityManager = AppContextAware.getBean(EntityManager.class);
        LOGGER.debug("EntityPersistenceListener()");
    }

    /**
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param baseEntity
     */
    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        persistEntity(Operation.INSERTED, baseEntity);
    }

    /**
     * @param baseEntity
     */
    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        persistEntity(Operation.UPDATED, baseEntity);
    }

    /**
     * @param baseEntity
     */
    @PreRemove
    public void preRemove(BaseEntity baseEntity) {
        persistEntity(Operation.DELETED, baseEntity);
    }

    /**
     * @param operation
     * @param baseEntity
     */
    @Transactional(Transactional.TxType.MANDATORY)
    void persistEntity(final Operation operation, BaseEntity baseEntity) {
        LOGGER.debug("persistEntity({}, {})", operation, baseEntity);
        getEntityManager().persist(baseEntity);
    }
}
