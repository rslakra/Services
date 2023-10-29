package com.rslakra.iws.businessservice.marketing.persistence.entity;

import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 3/16/23 2:37 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "marketing")
public class Marketing extends AbstractEntity<Long> {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

}
