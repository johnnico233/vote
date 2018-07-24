package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import vote.domain.FollowUser;
import vote.domain.PrivateUser;
import vote.domain.User;
import vote.result.ResultCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    private SqlSessionFactory factory;
    public UserWithResult CheckUserAccountAndPassword(PrivateUser privateUser){
        SqlSession session=factory.openSession();
        try{
            PrivateUser user=session.selectOne("checkUserValid",privateUser);
            if(user!=null)
                return new UserWithResult(ResultCode.SUCCESS,user);
            else{
                ResultCode code=checkUserExist(privateUser,session);
                return new UserWithResult(code==ResultCode.ACCOUNT_EXIST?ResultCode.PASSWORD_ERROR:code,null);
            }
        }finally {
            session.close();
        }
    }
    public ResultCode checkUserExist(PrivateUser privateUser,SqlSession session){
        PrivateUser accountUser=session.selectOne("checkPrivateUserExist",privateUser);
        return accountUser!=null?ResultCode.ACCOUNT_EXIST:ResultCode.ACCOUNT_NOT_EXIST;
    }
    public ResultCode checkUserExist(PrivateUser privateUser){
        SqlSession session=factory.openSession();
        try{
            return checkUserExist(privateUser,session);
        }finally {
            session.close();
        }
    }
    public ResultCode checkUsernameExist(User user){
        SqlSession session=factory.openSession();
        try{
            User tempUser=session.selectOne("checkUsernameExist",user);
            return tempUser!=null?ResultCode.USERNAME_EXIST:ResultCode.USERNAME_NOT_EXIST;
        }finally {
            session.close();
        }
    }
    public ResultCode checkPhoneExist(User user){
        SqlSession session=factory.openSession();
        try{
            User tempUser=session.selectOne("checkUserPhoneExist",user);
            return tempUser!=null?ResultCode.PHONE_EXIST:ResultCode.PHONE_NOT_EXIST;
        }finally {
            session.close();
        }
    }
    public ResultCode checkEmailExist(User user){
        SqlSession session=factory.openSession();
        try{
            User tempUser=session.selectOne("checkUserEmailExist",user);
            return tempUser!=null?ResultCode.EMAIL_EXIST:ResultCode.EMAIL_NOT_EXIST;
        }finally {
            session.close();
        }
    }
    public ResultCode addNewUser(User user){
        SqlSession session=factory.openSession();
        try{
            User tempUser=session.selectOne("checkUsernameExist",user);
            if(tempUser==null){
                tempUser=session.selectOne("checkUserPhoneExist",user);
                if(tempUser==null){
                    tempUser=session.selectOne("checkUserExist",user);
                    if(tempUser==null){
                        tempUser=session.selectOne("checkUserEmailExist",user);
                        if(tempUser==null){
                            int result=session.insert("addNewUserPrivate",user);
                            if(result==0)
                                return ResultCode.ACCOUNT_EXIST;
                            else{
                                PrivateUser privateUser=session.selectOne("getUserIdByAccount",user);
                                user.setUserId(privateUser.getUserId());
                                result=session.insert("addNewUserInfo",user);
                                session.commit();
                                return result>=0?ResultCode.SUCCESS:ResultCode.FAILED;
                            }
                        }else
                            return ResultCode.EMAIL_EXIST;
                    }else
                        return ResultCode.ACCOUNT_EXIST;
                }
                else
                    return ResultCode.PHONE_EXIST;
            }else
                return ResultCode.USERNAME_EXIST;
        }finally {
            session.close();
        }
    }
    public User getPublicUserInfoById(int id){
        SqlSession session=factory.openSession();
        try {
            User user=session.selectOne("getUserPublicInfoById",id);
            return user;
        }finally {
            session.close();
        }
    }
    public static class UserWithResult{
        ResultCode resultCode;
        PrivateUser privateUser;

        public UserWithResult(ResultCode resultCode, PrivateUser privateUser) {
            this.resultCode = resultCode;
            this.privateUser = privateUser;
        }

        public ResultCode getResultCode() {
            return resultCode;
        }

        public PrivateUser getPrivateUser() {
            return privateUser;
        }
    }
    public User getUserAllInfo(int userId){
        SqlSession sqlSession=factory.openSession();
        try{
            User user=sqlSession.selectOne("getUserAllInfo",userId);
            return user;
        }finally {
            sqlSession.close();
        }
    }
    public List<FollowUser> getFollowUserList(int userId,int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("userId",userId);
            map.put("start",start);
            map.put("limit",limit);
            return session.selectList("getFollowUserList",map);
        }finally {
            session.close();
        }
    }
}
