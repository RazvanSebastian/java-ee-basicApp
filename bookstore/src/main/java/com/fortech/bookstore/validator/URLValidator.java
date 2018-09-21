package com.fortech.bookstore.validator;

import java.net.MalformedURLException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fortech.bookstore.util.annotation.URL;

public class URLValidator implements ConstraintValidator<URL, String> {

	private String protocol;
	private String host;
	private int port;

	@Override
	public void initialize(URL constraintAnnotation) {
		this.protocol = constraintAnnotation.protocol();
		this.host = constraintAnnotation.host();
		this.port = constraintAnnotation.port();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0)
			return true;

		java.net.URL url = null;
		try {
			url = new java.net.URL(value);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (protocol != null && protocol.length() > 0 && !url.getProtocol().equals(protocol))
			return false;

		if (host != null && host.length() > 0 && !url.getHost().startsWith(host))
			return false;

		if (port != -1 && url.getPort() != port)
			return false;

		return true;
	}

}
