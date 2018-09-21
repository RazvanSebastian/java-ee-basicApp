package com.fortech.bookstore.util.interceptor;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.fortech.bookstore.util.annotation.Loggable;

@Loggable(debug = true)
@Interceptor
public class LoggingDebugInterceptor {

	@Inject
	private Logger logger;

	@AroundInvoke
	private Object intercept(InvocationContext invocationContext) throws Exception {
		logger.info("> Entry {} " + invocationContext.getMethod());
		try {
			return invocationContext.proceed();
		} finally {
			logger.info("< Exit {} " + invocationContext.getMethod());
		}
	}
}
