package com.rslakra.libraryservice.persistence.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:20 PM
 */
public class BaseEntityListener<T> {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntityListener.class);

    /**
     * @param entity
     */
    @PrePersist
    public void prePersist(Object entity) {
        persistEntity(Operation.INSERTED, entity);
    }

    /**
     * @param entity
     */
    @PreUpdate
    public void preUpdate(Object entity) {
        persistEntity(Operation.UPDATED, entity);
    }

    /**
     * @param entity
     */
    @PreRemove
    public void preRemove(Object entity) {
        persistEntity(Operation.DELETED, entity);
    }

    /**
     * @param operation
     * @param entity
     */
    @Transactional(Transactional.TxType.MANDATORY)
    public void persistEntity(Operation operation, Object entity) {
        LOGGER.info("persistEntity({}, {})", operation, entity);
//        EntityManager entityManager = AppContextAware.getBean(EntityManager.class);
//        entityManager.persist(new FileHistory(file, operation));
    }

}
