package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vote.dao.UserDao;
import vote.domain.PrivateUser;
import vote.domain.User;
import vote.result.Result;
import vote.result.ResultCode;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getUrl(){
        return "signUp";
    }
    @RequestMapping(value = "/accountCheck",method = RequestMethod.POST)
    public @ResponseBody SignUpResult checkUserExist(@RequestBody PrivateUser privateUser){
        ResultCode resultCode=userDao.checkUserExist(privateUser);
        return new SignUpResult(resultCode==ResultCode.ACCOUNT_NOT_EXIST?true:false);
    }
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    public @ResponseBody SignUpResult checkUserNameExist(@RequestBody User user){

        return new SignUpResult(false);
    }
    static class SignUpResult{
        boolean result;

        public SignUpResult() {
        }

        public SignUpResult(boolean result) {
            this.result = result;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

}
