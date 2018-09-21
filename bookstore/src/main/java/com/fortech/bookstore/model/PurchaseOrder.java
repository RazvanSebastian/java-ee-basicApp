package com.fortech.bookstore.model;

import java.util.Date;
import java.util.List;

public class PurchaseOrder {

	private Date date;

	private List<Book> cart;

	private float total;

	public PurchaseOrder() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Book> getCart() {
		return cart;
	}

	public void setCart(List<Book> cart) {
		this.cart = cart;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [date=" + date + ", cart=" + cart + ", total=" + total + "]";
	}

}
