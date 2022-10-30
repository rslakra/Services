package com.rslakra.auditingservice.persistence.entity;

import com.rslakra.auditingservice.service.AppContextAware;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:20 PM
 */
public class FileEntityListener {

    @PrePersist
    public void prePersist(File target) {
        persistEntity(target, Operation.INSERTED);
    }

    @PreUpdate
    public void preUpdate(File target) {
        persistEntity(target, Operation.UPDATED);
    }

    @PreRemove
    public void preRemove(File target) {
        persistEntity(target, Operation.DELETED);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    void persistEntity(File file, Operation operation) {
        EntityManager entityManager = AppContextAware.getBean(EntityManager.class);
        entityManager.persist(new FileHistory(file, operation));
    }

}
