package vote.domain;

public class LoginUser extends PrivateUser {
    private boolean remember;

    public LoginUser() {
    }

    public LoginUser(String account, int userId, String password, int type, boolean remember) {
        super(account, userId, password, type);
        this.remember = remember;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "remember=" + remember +
                ", account='" + account + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
