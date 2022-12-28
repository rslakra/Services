package com.rslakra.libraryclient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity extends Auditable implements Serializable {

    private Long id;

}
