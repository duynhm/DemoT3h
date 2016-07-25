package dzumi.demo.app.demoapiservice.network;

import dzumi.demo.app.demoapiservice.model.User;
import dzumi.demo.app.demoapiservice.model.request.BaseRequest;
import dzumi.demo.app.demoapiservice.model.response.GetEmailResponse;
import dzumi.demo.app.demoapiservice.model.response.GetNotificationResponse;
import dzumi.demo.app.demoapiservice.model.response.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Dzumi on 7/24/2016.
 */
public interface DemoAPIService {
    //danh sách các api
    //login
    @POST("login")
    Observable<LoginResponse> login(@Body User user);

    @POST("getEmails")
    Observable<GetEmailResponse> getEmails(@Body BaseRequest baseRequest);

    @POST("getNotifications")
    Observable<GetNotificationResponse> getNotifications(@Body BaseRequest baseRequest);
}
