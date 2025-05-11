package com.guillaumegasnier.education.recherche.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UaiValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUai {
    String message() default "UAI invalide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
