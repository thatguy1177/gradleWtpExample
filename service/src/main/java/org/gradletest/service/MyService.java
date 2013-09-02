package org.gradletest.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyService.class);

	public void logSomething(String stringtoLog) {
		logger.warn(stringtoLog);
	}
}