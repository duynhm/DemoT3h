package dzumi.demo.app.demoapiservice;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dzumi.demo.app.demoapiservice.model.Notification;
import dzumi.demo.app.demoapiservice.model.request.BaseRequest;
import dzumi.demo.app.demoapiservice.model.response.BaseResponse;
import dzumi.demo.app.demoapiservice.network.APIServiceBuilder;
import dzumi.demo.app.demoapiservice.network.DemoAPIService;
import dzumi.demo.app.demoapiservice.network.NetworkRequest;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {
    //buoc 1: load data notification tu server
    //buoc 2: load len recyclerView

    Subscription subscription;
    DemoAPIService demoAPIService;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    List<Notification> notificationList;
    RecyclerView recyclerView;
    void setupRecyclerView(){
        //TODO: thực hiện load data lên recyclerView
        //recycler
        //adapter --> data = notificationList
        //setAdapter
        //setLayoutManager
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoAPIService = APIServiceBuilder.buildAPIService(this, Constants.DOMAIN);

        subscription = NetworkRequest.performAsyncRequest(this,
                demoAPIService.getNotifications(new BaseRequest(this)),
                data->{
                    if(data.getStatus() == BaseResponse.STATUS_SUCCESS){
                        notificationList = data.getNotifications();
                    }
                    return data;},
                next->{
                    if(next.getStatus() == BaseResponse.STATUS_SUCCESS){
                        setupRecyclerView();
                    }else{
                        Snackbar.make(recyclerView, next.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
}
