package dzumi.demo.app.demoapiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dzumi.demo.app.demoapiservice.model.User;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
