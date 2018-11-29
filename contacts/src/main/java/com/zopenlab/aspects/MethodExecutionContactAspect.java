package com.zopenlab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.zopenlab.otherservices.IEmailService;

@Aspect
@Configuration
public class MethodExecutionContactAspect {
	
	@Autowired
	IEmailService emailService;
	
	@Before("com.zopenlab.config.CommonJoinPointConfig.businessLayerExecution()")
	public void before(JoinPoint joinPoint){
		//emailService.sendSimpleMessage("remidiar@hotmail.com", joinPoint.toShortString(), "Execution avant la methode");
		System.out.println("execution du before"+joinPoint.toShortString());
	}
	
	@AfterReturning(value="com.zopenlab.config.CommonJoinPointConfig.businessLayerExecution()",
			returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result){
		/*emailService.sendSimpleMessage("remidiar@hotmail.com"
				, joinPoint.toShortString(), "Execution après la methode");
		*/
		System.out.println("execution du After"+joinPoint.toShortString());
		
	}
}
