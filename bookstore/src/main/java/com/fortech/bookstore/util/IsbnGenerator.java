package com.fortech.bookstore.util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {

	@Override
	public String generateNumber() {
		// TODO Auto-generated method stub
		return "13-5677-" + Math.abs(new Random().nextInt());
	}

}
