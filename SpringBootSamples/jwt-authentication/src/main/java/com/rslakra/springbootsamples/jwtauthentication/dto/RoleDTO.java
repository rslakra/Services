package com.rslakra.springbootsamples.jwtauthentication.dto;

import com.rslakra.springbootsamples.jwtauthentication.persistence.model.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO extends AbstractDTO {

    @NaturalId
    @Enumerated(EnumType.STRING)
    private RoleType name;

}
