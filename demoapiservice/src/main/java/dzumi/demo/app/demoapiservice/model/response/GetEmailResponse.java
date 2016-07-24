package dzumi.demo.app.demoapiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dzumi.demo.app.demoapiservice.model.Email;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class GetEmailResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    List<Email> emails;
}
