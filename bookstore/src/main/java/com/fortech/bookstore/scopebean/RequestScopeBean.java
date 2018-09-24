package com.fortech.bookstore.scopebean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int counter;

	public int increment() {
		counter++;
		return counter;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
}
