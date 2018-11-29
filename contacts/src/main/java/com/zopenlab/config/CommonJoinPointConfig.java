package com.zopenlab.config;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

	@Pointcut("execution(* com.zopenlab.services.*.*(..))")
	public void businessLayerExecution(){}
	
	/*@Pointcut("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
	public void businessLayerExecution(){}*/
}
