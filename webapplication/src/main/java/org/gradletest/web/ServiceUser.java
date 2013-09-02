package org.gradletest.web;

import org.gradletest.service.MyService;


public class ServiceUser {

	public void doLog(String str) {
		MyService service = new MyService(); 
		service.logSomething(str);
	}
}
