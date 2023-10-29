package com.rslakra.liquibaseservice.persistence.entity;

import com.rslakra.liquibaseservice.component.AppContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:20 PM
 */
public class EntityOperationListener<E> {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityOperationListener.class);

    /**
     * @param target
     */
    @PrePersist
    public void prePersist(E target) {
        updateEntity(EntityOperation.INSERTED, target);
    }

    /**
     * @param target
     */
    @PreUpdate
    public void preUpdate(E target) {
        updateEntity(EntityOperation.UPDATED, target);
    }

    /**
     * @param target
     */
    @PreRemove
    public void preRemove(E target) {
        updateEntity(EntityOperation.DELETED, target);
    }

    /**
     * @param auditable
     */
    private void setLastUpdated(Auditable auditable) {
        auditable.setLastUpdatedAt(new Date());
        auditable.setLastUpdatedOn(auditable.getLastUpdatedAt().getTime());
        auditable.setLastUpdatedBy("rslakra");
    }

    /**
     * @param entity
     */
    @PostLoad
    public void postLoad(E entity) {
        updateEntity(EntityOperation.LOADED, entity);
    }

    /**
     * @param entityOperation
     * @param entity
     */
    @Transactional(Transactional.TxType.MANDATORY)
    void updateEntity(EntityOperation entityOperation, E entity) {
        LOGGER.debug("updateEntity({}, {})", entityOperation, entity);
        EntityManager entityManager = AppContextAware.getBean(EntityManager.class);
        if (entity instanceof Auditable && EntityOperation.LOADED != entityOperation) {
            Auditable auditable = (Auditable) entity;
            switch (entityOperation) {
                case INSERTED:
                    auditable.setCreatedAt(new Date());
                    auditable.setCreatedOn(auditable.getCreatedAt().getTime());
                    auditable.setCreatedBy("rslakra");
                    setLastUpdated(auditable);
                    break;

                case UPDATED:
                    setLastUpdated(auditable);
                    break;
            }

            entityManager.persist(entity);
        }
    }

}
