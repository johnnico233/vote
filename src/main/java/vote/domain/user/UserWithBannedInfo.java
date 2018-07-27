package vote.domain.user;

import java.util.Date;

public class UserWithBannedInfo extends User {
    private Date bannedTime;

    public Date getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(Date bannedTime) {
        this.bannedTime = bannedTime;
    }

    @Override
    public String toString() {
        return "UserWithBannedInfo{" +
                "bannedTime=" + bannedTime +
                ", account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", loginable=" + loginable +
                '}';
    }
}
