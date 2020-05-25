package com.revature.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component(value = "loggingAspect")
@Aspect 
public class LoggingAspect {

		//create a logger to use in the program
		public static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
		
		//pointcut to add advice to everything in the controller package
		@Pointcut("within(com.revature.controller.*)") 
		public void allControllerPointCut() {
			
		}
		
		//pointcut to add advice to everything in the service package
		@Pointcut("within(com.revature.service.*)")
		public void allServicePointCut() {
			
		}
		
		
		@Before(value = "allControllerPointCut()") 
		public void logBeforeControl(JoinPoint jp) { 
			
			LOGGER.info("The " + jp.getSignature().getName() + " controller method is going to be invoked!");
		}
		
		@After(value = "allControllerPointCut()") 
		public void logAfterControl(JoinPoint jp) {
			
			LOGGER.info("The " + jp.getSignature().getName() + " controller method has been invoked!");
		}
		
		@Before(value = "allServicePointCut()") 
		public void logBeforeService(JoinPoint jp) { 
			
			LOGGER.info("The " + jp.getSignature().getName() + " service method is going to be invoked!");
		}
		
		@After(value = "allServicePointCut()") 
		public void logAfterService(JoinPoint jp) {
			
			LOGGER.info("The " + jp.getSignature().getName() + " service method has been invoked!");
		}
		
		
}
