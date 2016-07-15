package dzumi.app.demo.demot3h.modules.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dzumi.app.demo.demot3h.R;

public class DemoCounterActivity extends AppCompatActivity {
    //TODO: Thiết lập 3 nút: start, pause, reset
    TextView tvcounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_counter);
        tvcounter = (TextView) findViewById(R.id.tvCounter);
    }

    int count = 0;
    boolean isStart = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tvcounter.setText((++count) + "");
            if(isStart)
               tvcounter.postDelayed(runnable, 3000);

        }
    };
    public void start(View v){
        isStart = true;
        //sau 1s , thực hiên nhiệm vụ đc xử lý trong run()
        tvcounter.postDelayed(runnable, 3000);
    }

    public void stop(View v){
        isStart = false;
        count = 0;
    }
}
