package com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.repository;

import com.rslakra.springservices.thymeleafsidebarlayouts.framework.persistence.repository.AbstractRepository;
import com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.entity.Tutorial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TutorialRepository extends AbstractRepository<Tutorial, Long> {

    /**
     * @param keyword
     * @return
     */
    List<Tutorial> findByTitleContainingIgnoreCase(String keyword);

    /**
     * @param id
     * @param isPublished
     */
    @Query("UPDATE Tutorial t SET t.published = :isPublished WHERE t.id = :id")
    @Modifying
    public void updatePublishedStatus(Long id, boolean isPublished);
}
