package dzumi.demo.app.demoapiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dzumi.demo.app.demoapiservice.model.Notification;

/**
 * Created by Dzumi on 7/25/2016.
 */
public class GetNotificationResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    List<Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
