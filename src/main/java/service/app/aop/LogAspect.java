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
//	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
//	
//	@Pointcut("execution(* service.app.controller.*.*(..))")
//	public void executionController(){}
//	
//	@Before("executionController()")
//	public void beforeLogging(JoinPoint jp){
//		String mn = jp.getSignature().getName();
//		String cn = jp.getSignature().getDeclaringTypeName();
//		RequestData rd = (RequestData) jp.getArgs()[1];
//		logger.info(cn +"." +mn+" username["+rd.getManageruser()+"],token["+rd.getType()+"] in controller ");
//	}

}
