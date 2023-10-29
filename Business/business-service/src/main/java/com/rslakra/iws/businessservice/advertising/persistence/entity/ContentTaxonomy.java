package com.rslakra.iws.businessservice.advertising.persistence.entity;

import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 4/4/23 4:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "content_taxonomies")
public class ContentTaxonomy extends AbstractEntity<Long> {

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "tier1")
    private String tier1;

    @Column(name = "tier2")
    private String tier2;

    @Column(name = "tier3")
    private String tier3;

    @Column(name = "tier4")
    private String tier4;

    @Column(name = "extension")
    private String extension;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "confident")
    private Boolean confident;

}
