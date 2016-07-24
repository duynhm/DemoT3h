package dzumi.demo.app.demoapiservice.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class BaseRequest {
    @SerializedName("token")
    @Expose
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
