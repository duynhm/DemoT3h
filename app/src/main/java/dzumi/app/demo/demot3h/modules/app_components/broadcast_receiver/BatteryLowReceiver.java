package dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by dzumi on 10/07/2015.
 */
public class BatteryLowReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "het pin", Toast.LENGTH_SHORT).show();
    }
}
