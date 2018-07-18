package vote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OperationRecorder {
    @Before("execution(* vote.controller.HomeController.getHomeUrl(..))")
    public void Hello(){
        System.out.println("hello!");
    }
}
