package vote.domain.vote;

import java.util.Arrays;
import java.util.Date;

public class UploadVoteTopic extends VoteTopic{

    private String[] options;


    public UploadVoteTopic() {
    }

    public UploadVoteTopic(int id, int userId, String topic, String content, Date startTime, Date endTime, boolean isMulti, boolean usable, String[] options) {
        super(id, userId, topic, content, startTime, endTime, isMulti, usable);
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }


    @Override
    public String toString() {
        return "UploadVoteTopic{" +
                "options=" + Arrays.toString(options) +
                ", totalVoteCount=" + totalVoteCount +
                ", id=" + id +
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
