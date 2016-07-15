package dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dzumi on 10/07/2015.
 */
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info != null)
        {
            Log.d("ANDROID", "Co mang");
            Toast.makeText(context, "Co mang", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("ANDROID", "Mat mang");
            Toast.makeText(context, "Mat mang", Toast.LENGTH_SHORT).show();
        }

    }
}
