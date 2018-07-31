package vote.domain.vote;
import java.util.Date;
import java.util.Objects;

public class UserVoteHistory {
    private int userId;
    private String voteName;
    private String optionName;
    private int voteId;
    private int optionId;
    private Date sendVoteTime;
    private boolean multi;
    private int historyId;
    private Date startTime;
    private Date endTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public Date getSendVoteTime() {
        return sendVoteTime;
    }

    public void setSendVoteTime(Date sendVoteTime) {
        this.sendVoteTime = sendVoteTime;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
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

    @Override
    public String toString() {
        return "UserVoteHistory{" +
                "userId=" + userId +
                ", voteName='" + voteName + '\'' +
                ", optionName='" + optionName + '\'' +
                ", voteId=" + voteId +
                ", optionId=" + optionId +
                ", sendVoteTime=" + sendVoteTime +
                ", multi=" + multi +
                ", historyId=" + historyId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

}
