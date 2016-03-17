package dzumi.app.demo.demot3h.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by duy on 3/2/2016.
 */
public class PermissionsUtils {
    public static void requestPermissionsCallPhone(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CALL_PHONE},
                requestCode);
    }

    public static boolean shouldShowRequestPermissionRationaleCallPhone(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CALL_PHONE);
    }

    public static boolean checkSelfPermissionCallPhone(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED;
    }

    public static void checkPermission(final Activity activity, final String permission, final int requestCode, final String messageAccessPermissions) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showMessageOKCancel(activity, messageAccessPermissions,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{permission},
                                        requestCode);
                            }
                        });
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        requestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

            return;
        }
    }

    private static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
