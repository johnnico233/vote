package vote.domain.vote;

import java.util.Date;
import java.util.List;

public class VoteTopicWithOption extends VoteTopic{
    private List<TopicOption> topicOptionList;
    private int maxCount;

    public VoteTopicWithOption() {
    }

    public VoteTopicWithOption(int id, int userId, String topic, String content, Date startTime, Date endTime, boolean isMulti, boolean usable, List<TopicOption> topicOptionList, int maxCount) {
        super(id, userId, topic, content, startTime, endTime, isMulti, usable);
        this.topicOptionList = topicOptionList;
        this.maxCount = maxCount;
    }

    public VoteTopicWithOption(VoteTopic voteTopic, List<TopicOption> topicOptionList, int maxCount) {
        super(voteTopic);
        this.topicOptionList = topicOptionList;
        this.maxCount = maxCount;
    }

    public VoteTopicWithOption(VoteTopic voteTopic, List<TopicOption> topicOptionList){
        super(voteTopic);
        this.topicOptionList=topicOptionList;
    }

    public List<TopicOption> getTopicOptionList() {
        return topicOptionList;
    }

    public void setTopicOptionList(List<TopicOption> topicOptionList) {
        this.topicOptionList = topicOptionList;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return "VoteTopicWithOption{" +
                "topicOptionList=" + topicOptionList +
                ", maxCount=" + maxCount +
                ", id=" + id +
                ", userId=" + userId +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isMulti=" + isMulti +
                ", usable=" + usable +
                ", totalVoteCount=" + totalVoteCount +
                '}';
    }

}
