package com.fortech.bookstore.repository;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.fortech.bookstore.model.PurchaseOrder;
import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = false)
public class StatisticsService {

	@Inject
	private Logger logger;

	public void statisticItems(@Observes PurchaseOrder purchaseOrder) {
		logger.info(purchaseOrder.toString());
	}
}
