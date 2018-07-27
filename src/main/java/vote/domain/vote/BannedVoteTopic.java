package vote.domain.vote;

import java.util.Date;

public class BannedVoteTopic {
    private String voteName;
    private int banId;
    private int voteId;
    private Date bannedTime;

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public Date getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(Date bannedTime) {
        this.bannedTime = bannedTime;
    }

    @Override
    public String toString() {
        return "BannedVoteTopic{" +
                "voteName='" + voteName + '\'' +
                ", banId=" + banId +
                ", voteId=" + voteId +
                ", bannedTime=" + bannedTime +
                '}';
    }
}
