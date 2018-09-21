package com.fortech.bookstore.util.annotation.generator;

import java.util.Random;

import javax.annotation.PostConstruct;

import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.annotation.Generator;
import com.fortech.bookstore.util.annotation.Generator.NumberOfDigits;

@Generator(numberOfDigits = NumberOfDigits.THIRTEEN, printed = true)
public class PIsbnGenerator implements NumberGenerator {

	private int postfix;

	@PostConstruct
	private void init() {
		postfix = Math.abs(new Random().nextInt());
	}

	@Override
	public String generateNumber() {
		// TODO Auto-generated method stub
		return "p-13-5677-" + postfix++;
	}

}
