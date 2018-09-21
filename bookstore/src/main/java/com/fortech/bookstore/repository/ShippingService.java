package com.fortech.bookstore.repository;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.fortech.bookstore.model.PurchaseOrder;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = false)
public class ShippingService {

	@Inject
	private Logger logger;

	public void shipItems(@Observes PurchaseOrder purchaseOrder) {
		logger.info(purchaseOrder.toString());
	}
}
