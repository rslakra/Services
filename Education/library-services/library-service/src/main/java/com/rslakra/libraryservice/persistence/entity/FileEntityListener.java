package com.rslakra.libraryservice.persistence.entity;

import com.rslakra.libraryservice.service.context.ContextServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:20 PM
 */
public class FileEntityListener extends BaseEntityListener<File> {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(FileEntityListener.class);

    /**
     * @param operation
     * @param baseEntity
     */
    @Transactional(Transactional.TxType.MANDATORY)
    public void persistEntity(Operation operation, File baseEntity) {
        LOGGER.debug("persistEntity({}, {})", operation, baseEntity);
        if (Operation.DELETED != operation) {
            final File file = (File) baseEntity;
            EntityManager entityManager = ContextServiceImpl.getBean(EntityManager.class);
            entityManager.persist(new FileHistory(file, operation));
        }
    }

}
