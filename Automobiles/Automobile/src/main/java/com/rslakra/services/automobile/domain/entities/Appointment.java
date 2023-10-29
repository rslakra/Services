package com.rslakra.services.automobile.domain.entities;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import com.rslakra.services.automobile.domain.entities.converter.LocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:38:47 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AutoUser user;

    @Embedded
    private Vehicle vehicle;

    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "appointment_on")
    private LocalDate appointmentOn;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "services", joinColumns = {@JoinColumn(name = "appointment_id")})
    @Column(name = "name")
    private List<String> services = new ArrayList<>();

    @Column(name = "status")
    private String status;

    /**
     * @param service
     */
    public void addService(String service) {
        if (BeanUtils.isNull(services)) {
            services = new ArrayList<>();
        }

        getServices().add(service);
    }

}
