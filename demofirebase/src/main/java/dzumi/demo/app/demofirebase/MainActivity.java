package dzumi.demo.app.demofirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //track screen
        sendEvent("screen", "Man hinh chinh");
    }

    public void onClick(View v){
        //track su kien khi click vao button
        sendEvent("Man hinh chinh", "btn_go_to_detail");
    }
}
