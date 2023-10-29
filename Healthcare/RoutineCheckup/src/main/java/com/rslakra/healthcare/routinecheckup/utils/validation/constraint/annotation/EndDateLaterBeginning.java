package com.rslakra.healthcare.routinecheckup.utils.validation.constraint.annotation;

import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.validator.EndDateLaterBeginningConstraintValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:40 PM
 */
@Documented
@Constraint(validatedBy = EndDateLaterBeginningConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndDateLaterBeginning {

    String dateBeforeFieldName();

    String dateAfterFieldName();

    String message()
        default "The start date must not be later than the end date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
