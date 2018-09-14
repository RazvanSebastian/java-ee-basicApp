package com.fortech.bookstore.util;

public class TextUtil {
	
	public String sanitize(String text) {
		return text.replaceAll("(\\t)|(\\s{2,})", " ");
	}
}
