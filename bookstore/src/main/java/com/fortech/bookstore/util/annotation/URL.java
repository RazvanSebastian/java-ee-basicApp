package com.fortech.bookstore.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fortech.bookstore.validator.URLValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { URLValidator.class })
public @interface URL {

	String message() default "Invalid URL";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	
	String protocol() default "";
	String host() default "";
	int port() default -1;

}
