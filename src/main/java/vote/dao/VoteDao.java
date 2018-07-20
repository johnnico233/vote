package vote.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vote.domain.TopicOption;
import vote.domain.UploadVoteTopic;
import vote.domain.VoteTopic;

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
            String[] names={"topic","content","startTime","endTime","isMulti","userId","id"};
            Object[] params={uploadVoteTopic.getTopic(), uploadVoteTopic.getContent(), uploadVoteTopic.getStartTime(), uploadVoteTopic.getEndTime(),
                    uploadVoteTopic.isMulti(), uploadVoteTopic.getUserId(),-1};
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

            //wait to modify

            return null;
        }finally {
            session.close();
        }
    }
}
