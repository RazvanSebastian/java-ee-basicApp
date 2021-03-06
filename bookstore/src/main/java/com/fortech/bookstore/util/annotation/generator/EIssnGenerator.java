package com.fortech.bookstore.util.annotation.generator;

import java.util.Random;

import javax.annotation.PostConstruct;

import com.fortech.bookstore.util.annotation.Generator;
import com.fortech.bookstore.util.annotation.Generator.NumberOfDigits;

@Generator(numberOfDigits = NumberOfDigits.EIGHT, printed = false)
public class EIssnGenerator implements NumberGenerator {

	private int postfix;

	@PostConstruct
	private void init() {
		postfix = Math.abs(new Random().nextInt());
	}

	@Override
	public String generateNumber() {
		// TODO Auto-generated method stub
		return "e-8-" + postfix++;
	}

}
