package vote.domain;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import vote.config.JsonDateSerializer;
import vote.config.JsonDecDateSerializer;

import java.util.Date;

public class User extends PrivateUser{
    private String username;
    private String sex;
    private String phone;
    private String email;
    private String whatsUp;
    private Date createTime;
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDecDateSerializer.class)
    private Date birth;
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDecDateSerializer.class)
    private Date lastLogin;

    public User() {
    }

    public User(String account, int userId, String password, int type, String username, String sex, String phone, String email, String whatsUp, Date createTime, Date birth, Date lastLogin) {
        super(account, userId, password, type);
        this.username = username;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.whatsUp = whatsUp;
        this.createTime = createTime;
        this.birth = birth;
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsUp() {
        return whatsUp;
    }

    public void setWhatsUp(String whatsUp) {
        this.whatsUp = whatsUp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", whatsUp='" + whatsUp + '\'' +
                ", createTime=" + createTime +
                ", birth=" + birth +
                ", lastLogin=" + lastLogin +
                ", account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
