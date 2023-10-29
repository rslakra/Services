package com.rslakra.springservices.thymeleaflayout.framework.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;

@NoRepositoryBean
@Transactional
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {

}
