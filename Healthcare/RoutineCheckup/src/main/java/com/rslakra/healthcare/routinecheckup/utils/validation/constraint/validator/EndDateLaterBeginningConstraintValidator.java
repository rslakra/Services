package com.rslakra.healthcare.routinecheckup.utils.validation.constraint.validator;

import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.annotation.EndDateLaterBeginning;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:42 PM
 */
public class EndDateLaterBeginningConstraintValidator
    implements ConstraintValidator<EndDateLaterBeginning, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        EndDateLaterBeginning annotation = getAnnotation(value);
        if (annotation == null) {
            return true;
        }

        String dateBeforeFieldName = annotation.dateBeforeFieldName();
        String dateAfterFieldName = annotation.dateAfterFieldName();

        Date dateBefore = getDateField(dateBeforeFieldName, value);
        Date dateAfter = getDateField(dateAfterFieldName, value);

        boolean beforeIsNull = dateBefore == null;
        boolean afterIsNull = dateAfter == null;

        if (beforeIsNull || afterIsNull) {
            return true;
        }

        return dateBefore.before(dateAfter);
    }

    private EndDateLaterBeginning getAnnotation(Object value) {
        Class<?> objectClass = value.getClass();
        EndDateLaterBeginning annotation
            = objectClass.getAnnotation(EndDateLaterBeginning.class);

        return annotation;
    }

    private Date getDateField(String fieldName, Object value) {
        Class<?> carrierClass = value.getClass();
        Field field = ReflectionUtils.findField(carrierClass, fieldName);
        if (field == null) {
            String message = String.format(
                "Field '%s' not found!",
                fieldName
            );
            throw new RuntimeException(message);
        }

        field.setAccessible(true);
        Date date = (Date) ReflectionUtils.getField(field, value);
        return date;
    }

}
