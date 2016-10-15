package dzumi.app.demo.demot3h.modules.app_components.services;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dzumi on 13/07/2015.
 */
public class SampleService1 extends BaseServices {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SampleService1", "onStartCommand" + this.getClass().toString());
        return START_STICKY;
    }


}
