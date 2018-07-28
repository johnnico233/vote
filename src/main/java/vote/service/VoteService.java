package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import vote.controller.VoteSubjectController;
import vote.dao.UserDao;
import vote.dao.VoteDao;
import vote.domain.user.User;
import vote.domain.vote.*;
import vote.result.ResultCode;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VoteService {
    @Autowired
    private VoteDao voteDao;
    @Autowired
    private UserDao userDao;
    //主要获取整个投票信息,包括用户信息
    public VoteTopicWithOption getVoteInformation(int topicId, Model model,boolean isEdit){
        List<TopicOption> options=voteDao.getTopicOptions(topicId);
        int maxCount=0;
        for(TopicOption option:options)
            maxCount+=option.getCount();
        VoteTopic voteTopic=voteDao.getVoteTopic(topicId);
        if(voteTopic!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String startTime=format.format(voteTopic.getStartTime());
            String endTime=format.format(voteTopic.getEndTime());
            if(isEdit){
                startTime=startTime.substring(0,startTime.indexOf(" "))+"T"
                    +startTime.substring(startTime.indexOf(" ")+1);
                endTime=endTime.substring(0,endTime.indexOf(" "))+"T"
                        +endTime.substring(endTime .indexOf(" ")+1);

            }
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
    public List<VoteHistory> getVoteHistories(int userId, int start, int end){
        List<UserVoteHistory> list=voteDao.getUserVoteHistories(userId,start,end);
        Map<Integer,VoteHistory> map=new HashMap<>();
        for(UserVoteHistory userVoteHistory:list){
            if(map.get(userVoteHistory.getVoteId())==null){
                Date date=new Date();
                VoteHistory voteHistory=new VoteHistory();
                voteHistory.setFinished(date.compareTo(userVoteHistory.getEndTime())>0);
                voteHistory.setMulti(userVoteHistory.isMulti());
                voteHistory.addOptionName(userVoteHistory.getOptionName());
                voteHistory.setSendDate(userVoteHistory.getSendVoteTime());
                voteHistory.setTopicId(userVoteHistory.getVoteId());
                voteHistory.setTopicName(userVoteHistory.getVoteName());
                voteHistory.setUserId(userVoteHistory.getUserId());
                map.put(voteHistory.getTopicId(),voteHistory);
            }else{
                map.get(userVoteHistory.getVoteId()).addOptionName(userVoteHistory.getOptionName());
            }
        }
        return new ArrayList<>(map.values());
    }
    public int getVoteHistorySizeByUserId(int userId,int step){
        return Integer.valueOf((voteDao.getVoteHistorySizeByUserId(userId)-1)/step)+1;
    }
    public List<VoteTopic> getVoteList(int start,int limit){
        List<VoteTopic> voteTopics=voteDao.getVoteList((start-1)*limit,limit,1);
        for(int i=0;i<voteTopics.size();i++){
            Date now=new Date();
            Date startTime=voteTopics.get(i).getStartTime();
            voteTopics.get(i).setModifiable(now.compareTo(startTime)<0);
        }
        System.out.println(voteTopics);
        return voteTopics;
    }
    public int getVoteListSize(int step){
        return Integer.valueOf((voteDao.getVoteListSize(1)-1)/step)+1;
    }
    public int banVoteTopic(int topicId){
        return voteDao.banVoteTopic(topicId);
    }
    public VoteTopic getVoteTopicByVoteId(int voteId){
        return voteDao.getVoteTopic(voteId);
    }
    public ResultCode updateVoteTopic(UploadVoteTopic uploadVoteTopic){
        boolean result=voteDao.updateVoteTopic(uploadVoteTopic)==1;
        if(result){
            result=voteDao.updateVoteTopicOption(uploadVoteTopic);
            return result?ResultCode.SUCCESS:ResultCode.INSERT_TOPIC_OPTION_FAILED;
        }
        return ResultCode.UPDATE_VOTE_TOPIC_FAILED;
    }
    public List<BannedVoteTopic> getBannedVoteList(int start,int limit){
        List<BannedVoteTopic> voteTopics=voteDao.getBannedVoteTopicList((start-1)*limit,limit);
        return voteTopics;
    }
    public int getBannedVoteListCount(int step){
        return Integer.valueOf((voteDao.getBannedVoteTopicSize()-1)/step)+1;
    }
    public boolean recoverBannedVote(BannedVoteTopic bannedVoteTopic){
        return voteDao.recoverBannedVote(bannedVoteTopic)==1;
    }
    public List<VoteMessage> getMyVoteMessageList(int userId,int start,int limit){
        return voteDao.getMyVoteMessageList(userId,(start-1)*limit,limit);
    }
    public int getMyVoteMessageSize(int userId,int step){
        return Integer.valueOf((voteDao.getMyVoteMessageSize(userId)-1)/step)+1;
    }
    public VoteMessage getVoteMessageById(int messageId){
        return voteDao.getVoteMessageById(messageId);
    }
    public ResultCode updateVoteMessage(VoteMessage voteMessage){
        int result=voteDao.updateVoteMessageById(voteMessage);
        return result==1?ResultCode.SUCCESS:ResultCode.FAILED;
    }
    public ResultCode banVoteMessage(VoteMessage voteMessage){
        int result=voteDao.banVoteMessage(voteMessage);
        return result==1?ResultCode.SUCCESS:ResultCode.FAILED;
    }
    public List<VoteMessage> getAllVoteMessageList(int start,int limit){
        return voteDao.getAllVoteMessageList((start-1)*limit,limit);
    }
    public int getAllVoteMessageSize(int step){
        return Integer.valueOf((voteDao.getAllVoteMessageSize()-1)/step)+1;
    }
    public List<BannedVoteMessage> getBannedVoteMessageList(int start,int limit){
        return voteDao.getBannedVoteMessageList((start-1)*limit,limit);
    }
    public int getBannedVoteMessageSize(int limit){
        return Integer.valueOf((voteDao.getBannedVoteMessageSize() -1)/limit)+1;
    }
    public ResultCode recoverVoteMessage(BannedVoteMessage bannedVoteMessage){
        int result=voteDao.recoverVoteMessage(bannedVoteMessage);
        return result==1?ResultCode.SUCCESS:ResultCode.RECOVER_FAILED;
    }
    public ResultCode deleteVoteMessage(BannedVoteMessage bannedVoteMessage){
        int result=voteDao.delVoteMessage(bannedVoteMessage);
        return result==1?ResultCode.SUCCESS:ResultCode.RECOVER_FAILED;
    }
    public List<BannedVoteMessage> getBannedVoteMessageListById(int userId,int start,int limit){
        return voteDao.getBannedVoteMessageListById(userId,(start-1)*limit,limit);
    }
    public int getBannedVoteMessageSizeById(int userId,int limit){
        return Integer.valueOf((voteDao.getBannedVoteMessageSizeById(userId) -1)/limit)+1;
    }
    public static class VoteHistory{
        private int topicId;
        private int userId;
        private String topicName;
        private List<String> optionNames;
        private Date sendDate;
        private boolean finished;
        private boolean multi;

        public VoteHistory() {
            optionNames=new ArrayList<>();
        }


        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public List<String> getOptionNames() {
            return optionNames;
        }

        public void setOptionNames(List<String> optionNames) {
            this.optionNames = optionNames;
        }

        public Date getSendDate() {
            return sendDate;
        }

        public void setSendDate(Date sendDate) {
            this.sendDate = sendDate;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public boolean isMulti() {
            return multi;
        }

        public void setMulti(boolean multi) {
            this.multi = multi;
        }
        public void addOptionName(String optionName){
            optionNames.add(optionName);
        }

        @Override
        public String toString() {
            return "VoteHistory{" +
                    "topicId=" + topicId +
                    ", userId=" + userId +
                    ", topicName='" + topicName + '\'' +
                    ", optionNames=" + optionNames +
                    ", sendDate=" + sendDate +
                    ", finished=" + finished +
                    ", multi=" + multi +
                    '}';
        }
    }
}
