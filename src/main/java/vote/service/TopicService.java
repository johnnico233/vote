package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.dao.VoteDao;
import vote.domain.TopicOption;
import vote.domain.VoteTopicWithOption;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private VoteDao voteDao;
    //主要获取整个投票信息
    public VoteTopicWithOption getVoteInfomation(int topicId){
        List<TopicOption> options=voteDao.getTopicOptions(topicId);

        return null;
    }
}
