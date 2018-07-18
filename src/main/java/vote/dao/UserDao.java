package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import vote.domain.PrivateUser;
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
                PrivateUser accountUser=session.selectOne("checkUserExist",privateUser);
                return accountUser!=null?ResultCode.PASSWORD_ERROR:ResultCode.ACCOUNT_NOT_EXIST;
            }
        }finally {
            session.close();
        }
    }
}
