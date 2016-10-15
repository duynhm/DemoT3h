package dzumi.app.demo.demot3h.modules.telephone_and_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Timer;

import dzumi.app.demo.demot3h.utils.logger.Log;

/**
 * Created by duy on 3/4/2016.
 */
public class OutCallLogger extends BroadcastReceiver {
    TelephonyManager Tm;
    Class c = null;
    Method methodGetInstance = null;
    Method methodGetActiveFgCallState = null;
    String TAG = "Tag";
    Object objectCallManager = null;
    Context context1;
    Class<?> classCallManager;
    Class telephonyClass;
    Class telephonyStubClass;
    Class serviceManagerClass;
    Class serviceManagerStubClass;
    Class serviceManagerNativeClass;
    Class serviceManagerNativeStubClass;
    Method telephonyCall;
    Method telephonyEndCall;
    Method telephonyAnswerCall;
    Method getDefault;
    Method[] temps;
    Constructor[] serviceManagerConstructor;
    // Method getService;
    Object telephonyObject;
    Object serviceManagerObject;
    private Timer timer = null;

    public OutCallLogger() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        this.context1 = context;
        try {
            //String serviceManagerName = "android.os.IServiceManager";
            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";


            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);

            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);

            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod(
                    "asInterface", IBinder.class);

            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");

            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            Method[] aClassMethods = telephonyClass.getDeclaredMethods();
            for (Method m : aClassMethods) {
//                Log.e("CallState", "method:" + m.getName());
            }
            telephonyObject = serviceMethod.invoke(null, retbinder);
            //telephonyCall = telephonyClass.getMethod("call", String.class);
            telephonyEndCall = telephonyClass.getMethod("getPreciseCallState");
            //telephonyAnswerCall = telephonyClass.getMethod("answerRingingCall");
            Log.e("CallState", telephonyEndCall.invoke(telephonyObject).toString());
            Toast.makeText(context1, " " + telephonyEndCall.invoke(telephonyObject).toString(), Toast.LENGTH_LONG).show();

            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
