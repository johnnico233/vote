package vote.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import vote.config.JsonDateSerializer;
import vote.config.JsonDecDateSerializer;

import java.util.Date;

public class VoteTopic {
    protected int id;
    protected int userId;
    protected String topic;
    protected String content;
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDecDateSerializer.class)
    protected Date startTime;
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDecDateSerializer.class)
    protected Date endTime;
    protected boolean isMulti;
    protected boolean usable;

    public VoteTopic(int id, int userId, String topic, String content, Date startTime, Date endTime, boolean isMulti, boolean usable) {
        this.id = id;
        this.userId = userId;
        this.topic = topic;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isMulti = isMulti;
        this.usable = usable;
    }

    public VoteTopic(VoteTopic voteTopic){
        id=voteTopic.id;
        userId=voteTopic.userId;
        topic=voteTopic.topic;
        content=voteTopic.content;
        startTime=voteTopic.startTime;
        endTime=voteTopic.endTime;
        isMulti=voteTopic.isMulti;
        usable=voteTopic.usable;
    }
    public VoteTopic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isMulti() {
        return isMulti;
    }

    public void setIsMulti(boolean multi) {
        isMulti = multi;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    @Override
    public String toString() {
        return "VoteTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isMulti=" + isMulti +
                ", usable=" + usable +
                '}';
    }
}
