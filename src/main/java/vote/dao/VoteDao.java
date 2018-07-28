package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vote.controller.VoteSubjectController;
import vote.domain.vote.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VoteDao {
    @Autowired
    private SqlSessionFactory factory;
    public int addNewVoteTopic(UploadVoteTopic uploadVoteTopic){
        SqlSession session=factory.openSession();
        try{
            Map<String,Object> map=new HashMap<>();
            String[] names={"topic","content","startTime","endTime","isMulti","userId","id","totalVoteCount"};
            Object[] params={uploadVoteTopic.getTopic(), uploadVoteTopic.getContent(), uploadVoteTopic.getStartTime(), uploadVoteTopic.getEndTime(),
                    uploadVoteTopic.getIsMulti(), uploadVoteTopic.getUserId(),-1,uploadVoteTopic.getTotalVoteCount()};
            for(int i=0;i<names.length;i++)
                map.put(names[i],params[i]);
            session.selectOne("addNewVoteTopic",map);
            Integer id=(Integer) map.get("id");
            int insertCount=0;
            for(String option:uploadVoteTopic.getOptions())
                insertCount+=session.insert("addTopicOptions",new TopicOption(option,id));
            session.commit();
            return insertCount==uploadVoteTopic.getOptions().length?id:-1;
        }finally {
            session.close();
        }
    }
    public List<TopicOption> getTopicOptions(int id){
        SqlSession session=factory.openSession();
        try{
            List<TopicOption> list=session.selectList("getVoteOptions",id);
            return list;
        }finally {
            session.close();
        }
    }
    public VoteTopic getVoteTopic(int id){
        SqlSession session=factory.openSession();
        try{
            VoteTopic voteTopic=session.selectOne("getVoteTopic",id);
            return voteTopic;
        }finally {
            session.close();
        }
    }
    /*
        计算公式:(index-1)*step,index代表当前位置,1代表第一页，step表示一页显示多少个
    * */
    public List<VoteMessage> getVoteMessages(int id,int index,int step){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("id",id);
            map.put("index",(index-1)*step);
            map.put("step",step);
            List<VoteMessage> list=session.selectList("getVoteMessage",map);
            return list;
        }finally {
            session.close();
        }
    }

    public int getVoteMessageCount(int id){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getVoteMessageCount",id);
        }finally {
            session.close();
        }
    }
    public void ModifyVoteCountAndHistory(VoteSubjectController.UserVote userVote){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("topicId",userVote.getTopicId());
            map.put("userId",userVote.getUserId());
            List<Integer> voteList=userVote.getVoteOptions();
            for(int i=0;i<voteList.size();i++){
                System.out.println(userVote.getTopicId()+"\t"+userVote.getUserId()+"\t"+voteList.get(i));
                map.put("id",voteList.get(i));
                session.selectOne("uploadVoteItem",map);
            }
        }finally {
            session.close();
        }
    }

    public int addNewVoteMessage(VoteMessage voteMessage){
        SqlSession session=factory.openSession();
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("content",voteMessage.getContent());
            map.put("voteId",voteMessage.getVoteId());
            map.put("userId",voteMessage.getUserId());
            map.put("total",-1);
            session.selectOne("addVoteMessage",map);
            return (Integer) map.get("total");
        }finally {
            session.close();
        }
    }
    public List<UserVoteHistory> getUserVoteHistories(int userId, int start, int end){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("userId",userId);
            map.put("start",start);
            map.put("end",end);
            return session.selectList("getMyVoteList",map);
        }finally {
            session.close();
        }
    }
    public int getVoteHistorySizeByUserId(int userId){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("myVoteListCount",userId);
        }finally {
            session.close();
        }
    }
    public List<VoteTopic> getVoteList(int start,int end,int notBanned){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("start",start);
            map.put("end",end);
            map.put("usable",notBanned);
            return session.selectList("getVoteTopicList",map);
        }finally {
            session.close();
        }
    }
    public int getVoteListSize(int notBanned){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getVoteTopicListSize",notBanned);
        }finally {
            session.close();
        }
    }
    public int banVoteTopic(int topicId){
        SqlSession session=factory.openSession();
        try{
            int updateResult=session.update("banVoteTopic",topicId);
            int banResult=session.update("addBanVoteTopicInfo",topicId);
            session.commit();
            return updateResult==1&&banResult==1?1:0;
        }finally {
            session.close();
        }
    }
    public int updateVoteTopic(UploadVoteTopic uploadVoteTopic){
        SqlSession session=factory.openSession();
        try{
            String[] names={"topic","content","startTime","endTime","isMulti","userId","id","totalVoteCount","result"};
            Object[] params={uploadVoteTopic.getTopic(), uploadVoteTopic.getContent(), uploadVoteTopic.getStartTime(), uploadVoteTopic.getEndTime(),
                    uploadVoteTopic.getIsMulti(), uploadVoteTopic.getUserId(),uploadVoteTopic.getId(),uploadVoteTopic.getTotalVoteCount(),0};
            Map<String,Object> map=new HashMap<>();
            for(int i=0;i<names.length;i++){
                map.put(names[i],params[i]);
            }
            session.selectOne("updateVoteTopic",map);
            return (int)map.get("result");
        }finally {
            session.close();
        }
    }
    public boolean updateVoteTopicOption(UploadVoteTopic uploadVoteTopic){
        SqlSession session=factory.openSession();
        try{
            int insertCount=0;
            for(String option:uploadVoteTopic.getOptions())
                insertCount+=session.insert("addTopicOptions",new TopicOption(option,uploadVoteTopic.getId()));
            session.commit();
            return insertCount==uploadVoteTopic.getOptions().length;
        }finally {
            session.close();
        }
    }
    public List<BannedVoteTopic> getBannedVoteTopicList(int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("start",start);
            map.put("limit",limit);
            return session.selectList("getBannedVoteTopicList",map);
        }finally {
            session.close();
        }
    }
    public int getBannedVoteTopicSize(){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getBannedVoteTopicListSize");
        }finally {
            session.close();
        }
    }
    public int recoverBannedVote(BannedVoteTopic bannedVoteTopic){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("banId",bannedVoteTopic.getBanId());
            map.put("topicId",bannedVoteTopic.getVoteId());
            map.put("result",0);
            session.selectOne("recoverBanVote",map);
            return map.get("result");
        }finally {
            session.close();
        }
    }
    public int getMyVoteMessageSize(int userId){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getMyVoteMessageSize",userId);
        }finally {
            session.close();
        }
    }
    public List<VoteMessage> getMyVoteMessageList(int userId,int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("id",userId);
            map.put("start",start);
            map.put("limit",limit);
            return session.selectList("getMyVoteMessages",map);
        }finally {
            session.close();
        }
    }
    public int getAllVoteMessageSize(){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getAllVoteMessageSize");
        }finally {
            session.close();
        }
    }
    public List<VoteMessage> getAllVoteMessageList(int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("start",start);
            map.put("limit",limit);
            return session.selectList("getAllVoteMessages",map);
        }finally {
            session.close();
        }
    }
    public VoteMessage getVoteMessageById(int messageId){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getVoteMessageById",messageId);
        }finally {
            session.close();
        }
    }

    public int updateVoteMessageById(VoteMessage voteMessage){
        SqlSession session=factory.openSession();
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("messageId",voteMessage.getId());
            map.put("content",voteMessage.getContent());
            int result=session.update("updateVoteMessage",map);
            session.commit();
            return result;
        }finally {
            session.close();
        }
    }
    public int banVoteMessage(VoteMessage voteMessage){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("id",voteMessage.getId());
            map.put("result",0);
            session.selectOne("banVoteMessage",map);
            return map.get("result");
        }finally {
            session.close();
        }
    }
    public List<BannedVoteMessage> getBannedVoteMessageList(int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("start",start);
            map.put("limit",limit);
            return session.selectList("getBanVoteMessageList",map);
        }finally {
            session.close();
        }
    }
    public int getBannedVoteMessageSize(){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getBanVoteMessageSize");
        }finally {
            session.close();
        }
    }
    public int recoverVoteMessage(BannedVoteMessage bannedVoteMessage){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("banId",bannedVoteMessage.getBanId());
            map.put("messageId",bannedVoteMessage.getId());
            map.put("result",0);
            session.selectOne("recoverVoteMessage",map);
            return map.get("result");
        }finally {
            session.close();
        }
    }
    public int delVoteMessage(BannedVoteMessage bannedVoteMessage){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("banId",bannedVoteMessage.getBanId());
            map.put("messageId",bannedVoteMessage.getId());
            map.put("result",0);
            session.selectOne("delVoteMessage",map);
            return map.get("result");
        }finally {
            session.close();
        }
    }
    public List<BannedVoteMessage> getBannedVoteMessageListById(int userId,int start,int limit){
        SqlSession session=factory.openSession();
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("start",start);
            map.put("limit",limit);
            map.put("id",userId);
            return session.selectList("getBanVoteMessageListById",map);
        }finally {
            session.close();
        }
    }
    public int getBannedVoteMessageSizeById(int userId){
        SqlSession session=factory.openSession();
        try{
            return session.selectOne("getBanVoteMessageSizeById",userId);
        }finally {
            session.close();
        }
    }
}
