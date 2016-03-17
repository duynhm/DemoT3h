package dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.utils.logger.Log;

/**
 * Created by duy on 2/24/2016.
 */
public class CheckNetworkReceiver extends BroadcastReceiver {
    private final String TAG_LOG = "CheckNetworkReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null) {
            Log.d(TAG_LOG, context.getString(R.string.notice_connected_network));
            Toast.makeText(context, R.string.notice_connected_network, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG_LOG, context.getString(R.string.notice_disconnect_network));
            Toast.makeText(context, R.string.notice_disconnect_network, Toast.LENGTH_SHORT).show();
        }
    }
}
