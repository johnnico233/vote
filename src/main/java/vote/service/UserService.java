package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vote.controller.UserInfoController;
import vote.dao.UserDao;
import vote.domain.DeleteFollowInfo;
import vote.domain.FollowUser;
import vote.domain.User;
import vote.result.ResultCode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class UserService {
    @Value("${avatar_path_tmp}")
    private String avatarPathTmp;
    @Value("${avatar_path_root}")
    private String avatarPath;
    @Autowired
    private UserDao userDao;
    public User getUserOverviewInfo(int userId){
        return userDao.getUserAllInfo(userId);
    }
    public List<FollowUser> getFollowList(int userId,int start,int limit){
        return userDao.getFollowUserList(userId,start,limit);
    }
    public ResultCode updateUserInfo(User user){
        User tempUser=userDao.checkUserChangeAvatar(user);
        if(!tempUser.getAvatar().equals(user.getAvatar())){
            ResultCode resultCode;
            boolean switchSuccess=switchAvatar(user.getAvatar(),tempUser.getAvatar());
            System.out.println(tempUser);
            if(switchSuccess){
                int result=userDao.updateUserInfo(user);
                resultCode=result>=1?ResultCode.SUCCESS:ResultCode.UPDATE_USER_INFO_FAILED;
            }
            else
                resultCode=ResultCode.SWITCH_PHOTO_LOCAL_FAILED;
            return resultCode;
        }else{
            int result=userDao.updateUserInfo(user);
            return result>=1?ResultCode.SUCCESS:ResultCode.UPDATE_USER_INFO_FAILED;
        }
    }
    public boolean switchAvatar(String filename,String oldFileName){
        File tempFile=new File(avatarPathTmp+filename);
        File file=new File(avatarPath+filename);
        long result=-1;
        try(BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file))){
            result=Files.copy(tempFile.toPath(),outputStream);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        tempFile.delete();
        File oldFile=new File(avatarPath+oldFileName);
        System.out.println(oldFile.getName());
        if(oldFile.exists()&&!oldFileName.equals("default.jpg"))
            oldFile.delete();
        return result>0;
    }
    public boolean checkUsernameNotExistExceptMe(User user){
        return userDao.selectUsernameExceptMe(user)==null;
    }
    public boolean checkPhoneNotExistExceptMe(User user){
        User temp=userDao.selectPhoneExceptMe(user);
        System.out.println(temp==null);
        return temp==null;
    }
    public boolean checkEmailNotExistExceptMe(User user){
        return userDao.selectEmailExceptMe(user)==null;
    }
    public ResultCode checkUserInfoNotExistExceptMe(User user){
        return userDao.selectAllInfoException(user);
    }

    public int getFollowUserCount(int userId){
        return userDao.getFollowUserCount(userId);
    }
    public int deleteFollowUser(DeleteFollowInfo info){
        return userDao.deleteFollowUser(info);
    }
}
