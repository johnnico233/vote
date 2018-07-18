package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import vote.domain.PrivateUser;
import vote.domain.User;
import vote.result.ResultCode;

public class UserDao {
    @Autowired
    private SqlSessionFactory factory;
    public ResultCode getPrivateUser(PrivateUser privateUser){
        SqlSession session=factory.openSession();
        try{
            PrivateUser user=session.selectOne("checkUserValid",privateUser);
            if(user!=null)
                return ResultCode.SUCCESS;
            else{
                ResultCode code=checkUserExist(privateUser,session);
                return code==ResultCode.ACCOUNT_EXIST?ResultCode.PASSWORD_ERROR:code;
            }
        }finally {
            session.close();
            System.out.println("session close");
        }
    }
    public ResultCode checkUserExist(PrivateUser privateUser,SqlSession session){
        PrivateUser accountUser=session.selectOne("checkUserExist",privateUser);
        return accountUser!=null?ResultCode.ACCOUNT_EXIST:ResultCode.ACCOUNT_NOT_EXIST;
    }
    public ResultCode checkUserExist(PrivateUser privateUser){
        SqlSession session=factory.openSession();
        try{
            return checkUserExist(privateUser,session);
        }finally {
            session.close();
            System.out.println("session close");
        }
    }
    public ResultCode checkUsernameExist(User user){
        SqlSession session=factory.openSession();
        try{

        }finally {
            session.close();
        }
    }
}
