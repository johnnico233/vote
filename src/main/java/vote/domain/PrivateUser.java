package vote.domain;

public class PrivateUser {
    protected String account;
    protected int userId=-1;
    protected String password;

    public PrivateUser() {
    }

    public PrivateUser(String account, int userId, String password) {
        this.account = account;
        this.userId = userId;
        this.password = password;
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

    @Override
    public String toString() {
        return "PrivateUser{" +
                "account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
