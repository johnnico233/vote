package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.dao.UserDao;
import vote.domain.FollowUser;
import vote.domain.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getUserOverviewInfo(int userId){
        return userDao.getUserAllInfo(userId);
    }
    public List<FollowUser> getFollowList(int userId,int start,int limit){
        return userDao.getFollowUserList(userId,start,limit);
    }
    public int updateUserInfo(User user){
        return userDao.updateUserInfo(user);
    }
}
