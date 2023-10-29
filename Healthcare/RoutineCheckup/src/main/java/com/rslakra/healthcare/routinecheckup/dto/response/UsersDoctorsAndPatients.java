package com.rslakra.healthcare.routinecheckup.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:10 PM
 */
@Getter
@Setter
@AllArgsConstructor
public class UsersDoctorsAndPatients {

    private List<UserDoctorsAndPatients> userDoctorsAndPatients;

}
