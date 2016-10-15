package dzumi.app.demo.demot3h.modules.app_components.services;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dzumi on 13/07/2015.
 */
public class SampleService2 extends BaseServices {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //services chay o main thread --> khong dc thuc hien mot
        //thao ta doi hoi xu ly qua nhieu trong services
        //neu can --> tao thread
        try {
            Thread.sleep(10000);
            stopSelf();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("SampleService2", "onStartCommand" + this.getClass().toString());
        return START_NOT_STICKY;
    }


}
