package service.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import service.app.tramodel.RequestData;

@Aspect
@Component
@Order(value = 1)
public class LogAspect {
	//AOP操作，用于登陆验证
//	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
//	
//	@Pointcut("execution(* service.app.controller.*.*(..))")
//	public void executionController(){}
//	
//	@Before("executionController()")
//	public void beforeLogging(JoinPoint jp){
//	}

}
