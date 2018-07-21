package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import vote.controller.VoteSubjectController;
import vote.dao.UserDao;
import vote.dao.VoteDao;
import vote.domain.*;
import vote.result.ResultCode;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteDao voteDao;
    @Autowired
    private UserDao userDao;
    //主要获取整个投票信息,包括用户信息
    public VoteTopicWithOption getVoteInformation(int topicId, Model model){
        List<TopicOption> options=voteDao.getTopicOptions(topicId);
        int maxCount=0;
        for(TopicOption option:options)
            maxCount+=option.getCount();
        VoteTopic voteTopic=voteDao.getVoteTopic(topicId);
        if(voteTopic!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String startTime=format.format(voteTopic.getStartTime());
            String endTime=format.format(voteTopic.getEndTime());
            model.addAttribute("startTime",startTime);
            model.addAttribute("endTime",endTime);
            VoteTopicWithOption topicWithOption=new VoteTopicWithOption(voteTopic,options,maxCount);
            model.addAttribute("vote",topicWithOption);
            User publicUserInfo=userDao.getPublicUserInfoById(voteTopic.getUserId());
            System.out.println(publicUserInfo);
            model.addAttribute("userInfo",publicUserInfo);
            model.addAttribute("lastLogin",format.format(publicUserInfo.getLastLogin()));
            return topicWithOption;
        }else
            return null;
    }
    //获取投票主题对应的评论
    public void getVoteMessages(Model model,int topicId,int index,int step){
        List<VoteMessage> list=voteDao.getVoteMessages(topicId,index,step);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i=0;i<list.size();i++){
            String sendTime=format.format(list.get(i).getSendTime());
            String lastLogin=format.format(list.get(i).getLastLogin());
            list.get(i).setStringSendTime(sendTime);
            list.get(i).setStringLastLogin(lastLogin);
        }
        model.addAttribute("voteMessages",list);
        model.addAttribute("messageCount",voteDao.getVoteMessageCount(topicId));
    }
    public ResultCode addNewVoteHistoryAndCount(VoteSubjectController.UserVote userVote){
        voteDao.ModifyVoteCountAndHistory(userVote);
        return ResultCode.SUCCESS;
    }
    public int addNewVoteMessage(VoteMessage voteMessage){
        return voteDao.addNewVoteMessage(voteMessage);
    }
}
