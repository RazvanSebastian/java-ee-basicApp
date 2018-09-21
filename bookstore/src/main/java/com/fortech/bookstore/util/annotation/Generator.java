package com.fortech.bookstore.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Generator {

	/**
	 * Generation strategy
	 * 
	 * @return {@link NumberOfDigits}
	 */
	NumberOfDigits numberOfDigits();

	/**
	 * 
	 * @return true for printed and false for electronic
	 */
	boolean printed();

	public enum NumberOfDigits {
		EIGHT, THIRTEEN
	}
}
