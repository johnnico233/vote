package vote.domain.vote;

import java.util.Date;

public class VoteMessage {
    //用于数据库存储的数据
    protected int id;
    protected String content;
    protected int voteId;
    protected Date sendTime;
    protected boolean available;
    protected int userId;
    //用于网页显示的数据
    protected Date lastLogin;
    protected String whatsUp;
    protected String userName;
    protected String stringLastLogin;
    protected String stringSendTime;
    protected String voteTitle;
    protected String avatar;

    public VoteMessage(int id, String content, int voteId, Date sendTime, boolean available, int userId, Date lastLogin) {
        this.id = id;
        this.content = content;
        this.voteId = voteId;
        this.sendTime = sendTime;
        this.available = available;
        this.userId = userId;
        this.lastLogin = lastLogin;
    }

    public VoteMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getWhatsUp() {
        return whatsUp;
    }

    public void setWhatsUp(String whatsUp) {
        this.whatsUp = whatsUp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStringLastLogin() {
        return stringLastLogin;
    }

    public void setStringLastLogin(String stringLastLogin) {
        this.stringLastLogin = stringLastLogin;
    }

    public String getStringSendTime() {
        return stringSendTime;
    }

    public void setStringSendTime(String stringSendTime) {
        this.stringSendTime = stringSendTime;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "VoteMessage{" +
                "id=" + id +
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
                '}';
    }
}
