package dzumi.app.demo.demot3h.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by duy on 3/2/2016.
 */
public class CallUtils {
    Context mContext;

    public CallUtils(Context context) {
        mContext = context;
    }

    void call(String numberPhone) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + numberPhone));
        mContext.startActivity(callIntent);
    }
}
