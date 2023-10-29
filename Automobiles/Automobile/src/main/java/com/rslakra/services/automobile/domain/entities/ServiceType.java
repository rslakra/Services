/**
 *
 */
package com.rslakra.services.automobile.domain.entities;

import com.devamatre.framework.spring.persistence.entity.NamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 01-03-2020 2:07:39 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "service_types")
public class ServiceType extends NamedEntity {

}
