package vote.domain.vote;

import java.util.Date;

public class BannedVoteMessage extends VoteMessage {
    private int banId;
    private Date banTime;

    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
    }

    @Override
    public String toString() {
        return "BannedVoteMessage{" +
                "banId=" + banId +
                ", banTime=" + banTime +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", voteId=" + voteId +
                ", sendTime=" + sendTime +
                ", available=" + available +
                ", userId=" + userId +
                ", lastLogin=" + lastLogin +
                ", whatsUp='" + whatsUp + '\'' +
                ", userName='" + userName + '\'' +
                ", stringLastLogin='" + stringLastLogin + '\'' +
                ", stringSendTime='" + stringSendTime + '\'' +
                ", voteTitle='" + voteTitle + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
