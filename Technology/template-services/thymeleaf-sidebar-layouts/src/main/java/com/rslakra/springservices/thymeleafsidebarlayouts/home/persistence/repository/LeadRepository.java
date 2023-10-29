package com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.repository;

import com.rslakra.springservices.thymeleafsidebarlayouts.framework.persistence.repository.AbstractRepository;
import com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.entity.Lead;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LeadRepository extends AbstractRepository<Lead, Long> {

    /**
     * @param email
     * @return
     */
    Optional<Lead> findByEmail(String email);
}
