package com.fortech.bookstore.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fortech.bookstore.validator.ChronologicalDatesValdiator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ChronologicalDatesValdiator.class)
public @interface ChronologicalDates {

	String message() default "Date has to be in chronological order";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
