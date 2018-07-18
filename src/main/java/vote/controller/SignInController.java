package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vote.dao.UserDao;
import vote.domain.PrivateUser;
import vote.result.Result;
import vote.result.ResultCode;

@Controller
public class SignInController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    public String getSignInUrl(){
        return "signIn";
    }

    @RequestMapping(value="/signIn",method = RequestMethod.POST)
    public @ResponseBody Result check(@RequestBody PrivateUser user){
        ResultCode code=userDao.getPrivateUser(user);
        if(code!=ResultCode.SUCCESS)
            return new Result(code,(code==ResultCode.ACCOUNT_NOT_EXIST?"账号不存在":"密码错误"));
        else
            return new Result(code,"登陆成功");
    }
}
