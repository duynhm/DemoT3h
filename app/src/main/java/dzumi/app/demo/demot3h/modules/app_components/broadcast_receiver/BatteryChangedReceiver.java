package dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Created by dzumi on 10/07/2015.
 */
public class BatteryChangedReceiver extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        Toast.makeText(context, "" + level, Toast.LENGTH_SHORT).show();
    }
}
