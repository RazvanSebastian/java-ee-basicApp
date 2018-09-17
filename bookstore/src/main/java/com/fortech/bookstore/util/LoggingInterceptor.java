package com.fortech.bookstore.util;

import javax.inject.Inject;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fortech.bookstore.util.cdi_qualifier.Loggable;

@Loggable
@Interceptor
public class LoggingInterceptor {
	
	private Logger logger;
	
	@Inject
	public LoggingInterceptor() {
		logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	}

	@AroundInvoke
	private Object intercept(InvocationContext invocationContext) throws Exception {
		logger.info("> Entry {}", invocationContext.getMethod());
		try {
			return invocationContext.proceed();
		} finally {
			logger.info("< Exit {}", invocationContext.getMethod());
		}
	}

}
