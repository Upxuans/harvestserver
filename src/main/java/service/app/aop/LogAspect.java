package service.app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
