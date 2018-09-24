package com.fortech.bookstore.modelbean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fortech.bookstore.util.annotation.Discount;
import com.fortech.bookstore.util.annotation.JSFTitle;
import com.fortech.bookstore.util.annotation.Vat;

@Named("order")
@ViewScoped
public class PurchaseOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Float subtotal = 0F;
	private Float total = 0F;

	@Inject
	@Vat
	private Float discountRate;

	@Inject
	@Discount
	private Float vatRate;

	@Inject
	@JSFTitle
	private String title;

	public PurchaseOrderBean() {
		super();
	}

	public String compute() {
		Float vat = subtotal * (vatRate / 100);
		Float discount = subtotal * (discountRate / 100);
		this.total = subtotal + vat - discount;

		return null;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Float getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Float discountRate) {
		this.discountRate = discountRate;
	}

	public Float getVatRate() {
		return vatRate;
	}

	public void setVatRate(Float vatRate) {
		this.vatRate = vatRate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
