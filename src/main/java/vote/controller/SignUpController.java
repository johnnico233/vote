package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vote.dao.UserDao;
import vote.domain.user.PrivateUser;
import vote.domain.user.User;
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
    public @ResponseBody SignUpResult checkUserNotExist(@RequestBody PrivateUser privateUser){
        ResultCode resultCode=userDao.checkUserExist(privateUser);
        return new SignUpResult(resultCode==ResultCode.ACCOUNT_NOT_EXIST?true:false);
    }
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    public @ResponseBody SignUpResult checkUserNameNotExist(@RequestBody User user){
        ResultCode code=userDao.checkUsernameExist(user);
        return new SignUpResult(code!=ResultCode.USERNAME_EXIST?true:false);
    }
    @RequestMapping(value = "/checkUserPhone",method = RequestMethod.POST)
    public @ResponseBody SignUpResult checkPhoneNotExist(@RequestBody User user){
        ResultCode code=userDao.checkPhoneExist(user);
        return new SignUpResult(code==ResultCode.PHONE_EXIST?false:true);
    }
    @RequestMapping(value="/checkUserEmail",method = RequestMethod.POST)
    public @ResponseBody SignUpResult checkEmailNotExist(@RequestBody User user){
        ResultCode code=userDao.checkEmailExist(user);
        return new SignUpResult(code==ResultCode.EMAIL_EXIST?false:true);
    }
    @RequestMapping(value="/addNewUser",method = RequestMethod.POST)
    public @ResponseBody AddResult addNewUser(@RequestBody User user){
        ResultCode code=userDao.addNewUser(user);
        System.out.println(user);
        return new AddResult(code);
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
    static class AddResult{
        ResultCode code;

        public AddResult() {
        }

        public AddResult(ResultCode code) {
            this.code = code;
        }

        public ResultCode getCode() {
            return code;
        }

        public void setCode(ResultCode code) {
            this.code = code;
        }
    }
}
