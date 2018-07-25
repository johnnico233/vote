package vote.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Aspect
public class VoteAspect {
    @Before("execution(* vote.controller..*.*(..))")
    public void checkSession(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        if(args!=null&&args.length>=2&&args[0] instanceof HttpSession &&args[1] instanceof Model){
            HttpSession httpSession=(HttpSession)args[0];
            Model model=(Model)args[1];
            if(httpSession.getAttribute("userID")!=null)
                model.addAttribute("userID",httpSession.getAttribute("userID"));
        }
    }
}
