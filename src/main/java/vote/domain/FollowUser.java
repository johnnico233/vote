package vote.domain;

import java.util.Date;

public class FollowUser extends User {
    private Date followTime;

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    @Override
    public String toString() {
        return "FollowUser{" +
                "followTime=" + followTime +
                ", account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
