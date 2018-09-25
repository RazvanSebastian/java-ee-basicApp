package com.fortech.bookstore.decorator;

import java.util.List;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = true)
public class PurchaseOrderService implements Computable {

	@Override
	public float compute(List<Book> books) {
		float sum = 0;
		for (Book book : books) {
			sum += book.getUnitCost();
		}
		return sum;
	}

}
