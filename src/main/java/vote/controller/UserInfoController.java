package vote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vote.domain.user.DeleteFollowInfo;
import vote.domain.user.FollowUser;
import vote.domain.user.User;
import vote.domain.user.UserWithBannedInfo;
import vote.domain.vote.BannedVoteTopic;
import vote.domain.vote.VoteMessage;
import vote.domain.vote.VoteTopic;
import vote.domain.vote.VoteTopicWithOption;
import vote.result.Result;
import vote.result.ResultCode;
import vote.service.UserService;
import vote.service.VoteService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Value("${avatar_path_tmp}")
    private String avatarPathTmp;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteService voteService;

    @RequestMapping("/{id}")
    public String getUserOverview(HttpSession httpSession, Model model, @PathVariable int id) {
        User user = userService.getUserOverviewInfo(id);
        List<FollowUser> followUsers = userService.getFollowList(id, 0, 5);
        if (user != null) {
            model.addAttribute("userInfo", user);
            model.addAttribute("infoType", "overview");
            model.addAttribute("followList", followUsers);
            model.addAttribute("title", "总览");
            return "user";
        }
        return "home";
    }

    @RequestMapping("/{id}/userInfo")
    public String getUserConcreteInfo(HttpSession httpSession, Model model, @PathVariable int id) {
        User user = userService.getUserOverviewInfo(id);
        if (user != null) {
            model.addAttribute("userInfo", user);
            model.addAttribute("infoType", "userInfo");
            model.addAttribute("title", "我的信息");
            return "user";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/{id}/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    Result uploadPhoto(@PathVariable int id, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile != null) {
            String path = multipartFile.getOriginalFilename();
            if (!path.endsWith(".png") && !path.endsWith(".jpg") && !path.endsWith(".jpeg"))
                return new Result(ResultCode.FAILED, "文件不符,只能上传png,jpg,jpeg格式");
            else {
                File file = new File(avatarPathTmp + new Date().getTime() + id + path.substring(path.lastIndexOf(".")));
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    InputStream inputStream = multipartFile.getInputStream();
                    byte[] bytes = new byte[1024];
                    while (inputStream.read(bytes) != -1) {
                        outputStream.write(bytes);
                    }
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return new Result(ResultCode.SUCCESS, "上传成功", file.getName());
            }
        } else {
            return new Result(ResultCode.FAILED, "文件不存在");
        }
    }

    @RequestMapping(value = "/{id}/updateUserInfo", method = RequestMethod.POST)
    public @ResponseBody
    Result updateUserInfo(@PathVariable int id, @RequestBody User user) {
        //wait for modify~
        ResultCode resultCode = userService.updateUserInfo(user);
        return new Result(resultCode, resultCode == ResultCode.SUCCESS ? "修改成功" :
                (resultCode == ResultCode.UPDATE_USER_INFO_FAILED) ? "更新用户信息失败" : "更新用户头像失败");
    }

    @RequestMapping("/**")
    public String getUrl(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("userID") != null) {
            return getUserOverview(httpSession, model, (int) httpSession.getAttribute("userID"));
        }
        return "home";
    }

    @RequestMapping(value = "/checkNameExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkNameExistExceptMe(@RequestBody User user) {
        ResultCode code = userService.checkUsernameNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.USERNAME_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "用户名已存在");
    }

    @RequestMapping(value = "/checkPhoneExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkPhoneExistExceptMe(@RequestBody User user) {
        System.out.println(user);
        ResultCode code = userService.checkPhoneNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.PHONE_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "电话号码已存在");
    }

    @RequestMapping(value = "/checkEmailExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkEmailExistExceptMe(@RequestBody User user) {
        System.out.println(user);
        ResultCode code = userService.checkEmailNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.EMAIL_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "Email已存在");
    }

    @RequestMapping(value = "/checkInfoExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkInfoExceptMe(@RequestBody User user) {
        ResultCode code = userService.checkUserInfoNotExistExceptMe(user);
        String errorWord = "";
        if (code == ResultCode.EMAIL_EXIST)
            errorWord = "Email已经存在";
        else if (code == ResultCode.PHONE_EXIST)
            errorWord = "电话号码已经存在";
        else if (code == ResultCode.USERNAME_EXIST)
            errorWord = "用户名已存在";
        return new Result(code, errorWord);
    }

    @RequestMapping(value = "/{id}/followList")
    public String getFollowList(HttpSession httpSession, Model model, @PathVariable int id,
                                @RequestParam(required = false) String idx) {
        int index = 1;
        if (idx != null)
            index = Integer.valueOf(idx);
        List<FollowUser> followUsers = userService.getFollowList(id, (index - 1) * 5, 5);
        model.addAttribute("infoType", "followList");
        model.addAttribute("followList", followUsers);
        model.addAttribute("title", "关注列表");
        model.addAttribute("index", index);
        int total = userService.getFollowUserCount(id);
        total = Integer.valueOf(((total - 1) / 5)) + 1;
        model.addAttribute("total", total);
        return "user";
    }

    @RequestMapping(value = "/follow/delete", method = RequestMethod.POST)
    public @ResponseBody Result deleteFollowUser(@RequestBody DeleteFollowInfo info) {
        int result = userService.deleteFollowUser(info);
        return new Result(result == 1 ? ResultCode.SUCCESS : ResultCode.DELETE_FAILED, result == 1 ? "取消关注成功" : "取消关注失败");
    }
    @RequestMapping(value="/manage")
    public String getManageUserUrl(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int start=1;
        if(idx!=null)
            start=Integer.valueOf(idx);
        int id=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(id);
        if (valid) {
            User user = userService.getUserOverviewInfo(id);
            model.addAttribute("infoType","userManage");
            model.addAttribute("userInfo",user);
            model.addAttribute("managedUser",userService.getManagedUser((start-1)*5,5));
            model.addAttribute("title", "用户管理");
            model.addAttribute("index", start);
            int total = userService.getManagedUserCount();
            total = Integer.valueOf(((total - 1) / 5)) + 1;
            model.addAttribute("total", total);
            return "user";
        }else{
            //非法操作,返回首页
            return "home";
        }
    }
    @RequestMapping(value = "/manage/ban")
    public @ResponseBody Result banUser(HttpSession httpSession,@RequestBody User user){
        int userId=(int)httpSession.getAttribute("userID");
        int result=userService.banUser(user,userId);
        return new Result(result==1?ResultCode.SUCCESS:ResultCode.FAILED,result==1?"封停用户成功":"封停用户失败");
    }
    @RequestMapping(value="/manage/modify")
    public String returnModifyPage(HttpSession httpSession,Model model,@RequestParam String uid){
        if(uid==null)
            return getManageUserUrl(httpSession,model,"1");
        else{
            int id=Integer.valueOf(uid);
            int myId=(int)httpSession.getAttribute("userID");
            User user=userService.getUserInfoWithEvaluate(model,id,myId);
            if(user==null){
                //404
                return "home";
            }else{
                model.addAttribute("userInfo",user);
                model.addAttribute("title","修改用户信息");
                model.addAttribute("infoType","modifyUser");
                model.addAttribute("returnPage","/user/manage");
                return "user";
            }
        }
    }
    @RequestMapping(value="/dustbinUser")
    public String getDustbinUser(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int index=1;
        if(idx!=null)
            index=Integer.valueOf(idx);
        int userId=(int)httpSession.getAttribute("userID");
        List<UserWithBannedInfo> bannedInfos=userService.getBannedUsers(Integer.valueOf((index-1)*5),5,userId);
        if(bannedInfos!=null){
            model.addAttribute("userList",bannedInfos);
            model.addAttribute("title","回收站");
            model.addAttribute("infoType","dustbinUser");
            model.addAttribute("index",index);
            model.addAttribute("total",userService.getBannedUserCount(5));
            return "user";
        }else{
            //null代表检测到非法操作,返回首页
            return "home";
        }
    }
    @RequestMapping(value = "/dustbinUser/recover",method = RequestMethod.POST)
    public @ResponseBody Result recoverUser(HttpSession httpSession,@RequestBody User user){
        int id=(int)httpSession.getAttribute("userID");
        int result=userService.recoverUser(user,id);
        if(result==-1){
            return new Result(ResultCode.ACCESS_DENIED,"非法操作");
        }else
            return new Result(result==1?ResultCode.SUCCESS:ResultCode.RECOVER_FAILED,result==1?"恢复成功":"恢复失败");
    }
    @RequestMapping(value = "/myVotes")
    public String getMyVotes(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int userId=(int)httpSession.getAttribute("userID");
        int index=1;
        if(idx!=null)
            index=Integer.valueOf(idx);
        model.addAttribute("infoType","userVotes");
        model.addAttribute("title","我的投票");
        List<VoteService.VoteHistory> list=voteService.getVoteHistories(userId,(index-1)*5,5);
        model.addAttribute("voteHistories",list);
        model.addAttribute("index",index);
        model.addAttribute("total",voteService.getVoteHistorySizeByUserId(userId,5));
        return "user";
    }
    @RequestMapping(value="/voteManage")
    public String getVoteManage(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int userId=(int)httpSession.getAttribute("userID");
        boolean isValid=userService.checkUserValid(userId);
        if(!isValid){
            //出现非法操作
            return "home";
        }else{
            int index=1;
            if(idx!=null)
                index=Integer.valueOf(idx);
            model.addAttribute("title","投票管理");
            model.addAttribute("infoType","voteManage");
            model.addAttribute("total",voteService.getVoteListSize(5));
            model.addAttribute("index",index);
            model.addAttribute("voteList",voteService.getVoteList(index,5));
            return "user";
        }
    }
    @RequestMapping(value="/voteManage/ban",method = RequestMethod.POST)
    public @ResponseBody Result banTopic(HttpSession httpSession,@RequestBody VoteTopic voteTopic){
        int userId=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(userId);
        if(!valid){
            //非法操作
            return new Result(ResultCode.ACCESS_DENIED,"非法操作");
        }else{
            int result=voteService.banVoteTopic(voteTopic.getId());
            String resultText=result==1?"结束投票成功":"结束投票失败";
            return new Result(result==1?ResultCode.SUCCESS:ResultCode.FAILED,resultText);
        }
    }
    @RequestMapping(value="/voteManage/modify/{topicId}")
    public String redirectToModifyPage(HttpSession httpSession,Model model,@PathVariable int topicId){
        int userId=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(userId);
        if(!valid){
            //非法操作
            return "home";
        }else{
            model.addAttribute("title","修改投票信息");
            VoteTopicWithOption voteTopic=voteService.getVoteInformation(topicId,model,true);
            if(voteTopic!=null){
                model.addAttribute("voteInfo",voteTopic);
                return "voteUpload";
            }else{
                return getVoteManage(httpSession,model,"1");
            }
        }
    }
    @RequestMapping(value="/dustbinVote")
    public String getDustbinVote(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int userId=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(userId);
        model.addAttribute("infoType","dustbinVote");
        int index=1;
        if(idx!=null)
            index=Integer.valueOf(idx);
        if(valid){
            model.addAttribute("title","回收站");
            model.addAttribute("bannedList",voteService.getBannedVoteList(index,5));
            model.addAttribute("index",index);
            model.addAttribute("total",voteService.getBannedVoteListCount(5));
        }else{
            //非管理员
        }
        return "user";
    }
    @RequestMapping(value = "/dustbinVote/recover")
    public @ResponseBody Result recoverBannedVote(HttpSession httpSession, @RequestBody BannedVoteTopic bannedVoteTopic){
        int userId=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(userId);
        if(!valid){
            //非法用户操作
            return new Result(ResultCode.ACCESS_DENIED,"非法操作");
        }else{
            boolean isRecover=voteService.recoverBannedVote(bannedVoteTopic);
            String resultText=isRecover?"恢复成功":"恢复失败";
            return new Result(isRecover?ResultCode.SUCCESS:ResultCode.RECOVER_FAILED,resultText);
        }
    }
    @RequestMapping("/myVoteMessage")
    public String getMyVoteMessage(HttpSession httpSession,Model model,@RequestParam(required = false) String idx){
        int userId=(int)httpSession.getAttribute("userID");
        int index=1;
        if(idx!=null)
            index=Integer.valueOf(idx);
        model.addAttribute("voteMessageList",voteService.getMyVoteMessageList(userId,index,10));
        model.addAttribute("index",index);
        model.addAttribute("total",voteService.getMyVoteMessageSize(userId,10));
        model.addAttribute("infoType","myVoteMessage");
        model.addAttribute("title","我的投票");
        return "user";
    }
    @RequestMapping("/voteModify")
    public String modifyVoteMessage(HttpSession httpSession,Model model,@RequestParam(value = "mes") String messageId,
                                    @RequestParam(value = "topic") String topicId){
        int userId=(int)httpSession.getAttribute("userID");
        boolean valid=userService.checkUserValid(userId);
        if(!valid){
            //非法操作
            return "home";
        }else{
            VoteTopicWithOption voteTopic=voteService.getVoteInformation(Integer.valueOf(topicId),model,false);
            if(voteTopic!=null){
                voteService.getVoteMessages(model,Integer.valueOf(topicId),1,5);
                model.addAttribute("index",1);
                model.addAttribute("topicUser",userService.getUserOverviewInfo(userId));
                model.addAttribute("step",5);
                VoteMessage voteMessage=voteService.getVoteMessageById(Integer.valueOf(messageId));
                System.out.println(voteMessage);
                model.addAttribute("myVoteMessage",voteMessage);
                return "voteSubject";
            }else{
                return "home";
            }
        }
    }
}
