package com.fortech.bookstore.modelbean;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.fortech.bookstore.util.annotation.Discount;
import com.fortech.bookstore.util.annotation.JSFTitle;
import com.fortech.bookstore.util.annotation.Vat;

public class NumberProducer {
	
	@Produces
	@Vat
	@Named("vat")
	private Float vatRate = 5.5F;

	@Produces
	@Discount
	@Named("discount")
	private Float discountRate = 2.25F;
	
	@Produces
	@Named("title")
	@JSFTitle
	private String title = "Ordinary computing";
	
	@Produces
	@Named("title")
	@JSFTitle
	@Alternative
	private String titleAlt = "Black Friday Computing";
	
	
	@Produces
	@Vat
	@Named("vat")
	@Alternative
	private Float vatRateAlt = 19.6F;
	
	@Produces
	@Discount
	@Named("discount")
	@Alternative
	private Float discountRateAlt = 4.75F;
	
	
}
