package com.fortech.bookstore.repository;

import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.model.PurchaseOrder;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = false)
public class PurchaseOrderService {

	@Inject
	private Event<PurchaseOrder> event;

	public void addItem(List<Book> list) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setDate(new Date());
		purchaseOrder.setCart(list);

		float amount = 0;
		for (Book book : list) {
			amount += book.getUnitCost();
		}

		purchaseOrder.setTotal(amount);

		event.fire(purchaseOrder);
	}

}
