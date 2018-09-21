package com.fortech.bookstore.util.annotation.generator;

import java.util.Random;

import javax.annotation.PostConstruct;

import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.annotation.EightDigits;

@EightDigits
public class IssnGenerator implements NumberGenerator{

	private int postfix;

	@PostConstruct
	private void init() {
		postfix = Math.abs(new Random().nextInt());
	}

	@Override
	public String generateNumber() {
		// TODO Auto-generated method stub
		return "8-" + postfix++;
	}

}
