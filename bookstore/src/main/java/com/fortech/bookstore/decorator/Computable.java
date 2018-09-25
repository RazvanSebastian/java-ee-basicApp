package com.fortech.bookstore.decorator;

import java.util.List;

import com.fortech.bookstore.model.Book;

public interface Computable {
	
	float compute(List<Book> books);

}
