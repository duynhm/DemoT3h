package dzumi.app.demo.demot3h.modules.app_components.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import dzumi.app.demo.demot3h.utils.logger.Log;


/**
 * Created by dzumi on 13/07/2015.
 */
public class BaseServices extends Service {
    final String TAG = "sampleServices";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BaseServices", "onCreate - " + this.getClass().toString());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BaseServices","onStartCommand" + this.getClass().toString());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BaseServices","onDestroy" + this.getClass().toString());
    }
}
