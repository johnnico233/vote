package vote.domain;

public class PrivateUser {
    protected String account;
    protected int userId=-1;
    protected String password;
    protected int type;

    public PrivateUser() {
    }

    public PrivateUser(String account, int userId, String password, int type) {
        this.account = account;
        this.userId = userId;
        this.password = password;
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PrivateUser{" +
                "account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
