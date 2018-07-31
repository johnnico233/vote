package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vote.dao.UserDao;
import vote.domain.user.LoginUser;
import vote.result.Result;
import vote.result.ResultCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {
    @Value("${project_local}")
    private String path;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    public String getSignInUrl(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("userID")!=null){
            return "redirect:/user";
        }else{
            return "signIn";
        }
    }

    @RequestMapping(value="/signIn",method = RequestMethod.POST)
    public @ResponseBody Result check( HttpSession httpSession,@RequestBody LoginUser user, HttpServletResponse response){
        UserDao.UserWithResult userWithResult=userDao.CheckUserAccountAndPassword(user);
        ResultCode code=userWithResult.getResultCode();
        if(code!=ResultCode.SUCCESS)
            return new Result(code,(code==ResultCode.ACCOUNT_NOT_EXIST?"账号不存在":"密码错误"));
        else if(!userWithResult.getPrivateUser().isLoginable()){
            return new Result(ResultCode.USER_IS_BANNED,"该账号已经被封停,具体原因请联系管理员");
        }else{
            httpSession.setAttribute("userID",userWithResult.getPrivateUser().getUserId());
            if(user.isRemember()) {
                Cookie cookie=new Cookie("userID",String.valueOf(userWithResult.getPrivateUser().getUserId()));
                cookie.setMaxAge(3600*7*24);
                cookie.setPath("/"+path+"/");
                response.addCookie(cookie);
            }
            return new Result(code,"登陆成功",userWithResult.getPrivateUser().getUserId());
        }
    }

}
