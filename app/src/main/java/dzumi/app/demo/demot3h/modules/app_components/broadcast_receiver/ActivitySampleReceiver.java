package dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dzumi.app.demo.demot3h.R;


/**
 * Created by dzumi on 10/07/2015.
 */
public class ActivitySampleReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_receiver);
        networkReceiver = new NetworkReceiver();
        batteryChangedReceiver = new BatteryChangedReceiver();
    }

    BatteryChangedReceiver batteryChangedReceiver;
    IntentFilter batteryChangedIntentFilter;

    NetworkReceiver networkReceiver;
    IntentFilter networkIntentFilter;
    @Override
    protected void onResume() {
        super.onResume();

        networkIntentFilter = new IntentFilter(
                "android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkReceiver, networkIntentFilter);

        batteryChangedIntentFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangedReceiver, batteryChangedIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
        unregisterReceiver(batteryChangedReceiver);
    }
}
