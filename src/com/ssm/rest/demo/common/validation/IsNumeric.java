package com.ssm.rest.demo.common.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ssm.rest.demo.common.validation.validator.IsNumericValidator;

/**
 * 数字注解
 *
 * @author huangyong
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNumericValidator.class)
public @interface IsNumeric {

    String message() default "is_numeric";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

