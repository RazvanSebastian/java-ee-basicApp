package com.fortech.bookstore.util;

import java.util.Random;

import javax.annotation.PostConstruct;

import com.fortech.bookstore.util.annotation.ThirteenDigits;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

	private int postfix;

	@PostConstruct
	private void init() {
		postfix = Math.abs(new Random().nextInt());
	}

	@Override
	public String generateNumber() {
		// TODO Auto-generated method stub
		return "13-5677-" + postfix++;
	}

}
