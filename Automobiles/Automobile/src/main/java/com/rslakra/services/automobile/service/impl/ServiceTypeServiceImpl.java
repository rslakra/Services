package com.rslakra.services.automobile.service.impl;

import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.services.automobile.domain.entities.ServiceType;
import com.rslakra.services.automobile.domain.repositories.ServiceTypeRepository;
import com.rslakra.services.automobile.service.ServiceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 4/21/23 12:20 PM
 */
@Service
public class ServiceTypeServiceImpl extends AbstractServiceImpl<ServiceType, Long> implements ServiceTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTypeServiceImpl.class);
    private final ServiceTypeRepository serviceTypeRepository;

    /**
     * @param serviceTypeRepository
     */
    @Autowired
    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        LOGGER.debug("AutoServiceImpl({})", serviceTypeRepository);
        this.serviceTypeRepository = serviceTypeRepository;
    }

    /**
     * @param operation
     * @param serviceType
     * @return
     */
    @Override
    public ServiceType validate(Operation operation, ServiceType serviceType) {
        return null;
    }

    /**
     * @param serviceType
     * @return
     */
    @Override
    public ServiceType create(ServiceType serviceType) {
        serviceType = serviceTypeRepository.save(serviceType);
        return serviceType;
    }

    /**
     * @param serviceTypes
     * @return
     */
    @Override
    public List<ServiceType> create(List<ServiceType> serviceTypes) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ServiceType> getAll() {
        return serviceTypeRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ServiceType getById(Long id) {
        return serviceTypeRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<ServiceType> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<ServiceType> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * @param serviceType
     * @return
     */
    @Override
    public ServiceType update(ServiceType serviceType) {
        return null;
    }

    /**
     * @param serviceTypes
     * @return
     */
    @Override
    public List<ServiceType> update(List<ServiceType> serviceTypes) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ServiceType delete(Long id) {
        return null;
    }
}
