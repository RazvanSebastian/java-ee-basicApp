package com.fortech.bookstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.util.annotation.ChronologicalDates;

public class ChronologicalDatesValdiator implements ConstraintValidator<ChronologicalDates, Book> {

	@Override
	public void initialize(ChronologicalDates constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Book book, ConstraintValidatorContext context) {
		return book.getCreationDate().getTime() < book.getPublicationDate().getTime();
	}

}
