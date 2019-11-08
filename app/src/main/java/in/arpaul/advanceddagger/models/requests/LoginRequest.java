package in.arpaul.advanceddagger.models.requests;

import in.arpaul.advanceddagger.models.BaseDO;

public class LoginRequest extends BaseDO {

    private String userId = "";
    private String password = "";

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
        return "LoginRequest{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
