package com.example.sampleproject.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlbumValidator.class)
public @interface ValidateAlbums {
    String message() default "Please insert at least one album!";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
