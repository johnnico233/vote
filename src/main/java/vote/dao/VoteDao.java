package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vote.controller.VoteSubjectController;
import vote.domain.TopicOption;
import vote.domain.UploadVoteTopic;
import vote.domain.VoteMessage;
import vote.domain.VoteTopic;
import vote.result.ResultCode;

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
}
