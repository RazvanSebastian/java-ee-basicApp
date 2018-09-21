package com.fortech.bookstore.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


public class LoggingProducer {

	@Produces
	public Logger producer(InjectionPoint injectionPoint) {
		return LogManager.getLogManager().getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

}
