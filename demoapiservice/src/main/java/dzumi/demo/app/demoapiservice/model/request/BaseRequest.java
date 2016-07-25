package dzumi.demo.app.demoapiservice.model.request;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dzumi.demo.app.demoapiservice.utils.CustomSharedPref;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class BaseRequest {
    @SerializedName("token")
    @Expose
    String token;

    public BaseRequest(Context context) {
        token = CustomSharedPref.getToken(context);
    }
}
