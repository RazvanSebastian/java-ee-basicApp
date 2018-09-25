package com.fortech.bookstore.decorator;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.util.annotation.Discount;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = true)
@Decorator
public abstract class PurchaseOrderDecorator implements Computable {

	@Inject
	@Discount
	private Float discountRate;

	@Inject
	@Delegate
	Computable purchaseOrderService;

	@Override
	public float compute(List<Book> books) {
		float subtotal = purchaseOrderService.compute(books);
		return subtotal - subtotal * discountRate/100;
	}

}
