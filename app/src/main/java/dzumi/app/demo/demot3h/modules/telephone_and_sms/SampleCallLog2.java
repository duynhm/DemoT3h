package dzumi.app.demo.demot3h.modules.telephone_and_sms;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.utils.logger.Log;
import dzumi.app.demo.demot3h.utils.logger.LogFragment;
import dzumi.app.demo.demot3h.utils.logger.LogWrapper;
import dzumi.app.demo.demot3h.utils.logger.MessageOnlyLogFilter;

public class SampleCallLog2 extends AppCompatActivity {

    public static final String TAG_LOG = "SampleCallLog";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 17;
    BroadcastReceiver callLogReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String msg = "Phone state changed to " + state;

            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                msg += ". Incoming number is " + incomingNumber;

                // TODO This would be a good place to "Do something when the phone rings" ;-)

            }

//            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Log.i(TAG_LOG, msg);
        }
    };
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_call_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                if (ActivityCompat.checkSelfPermission(SampleCallLog2.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    // Should we show an explanation?
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(SampleCallLog2.this,
                            Manifest.permission.CALL_PHONE)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        showMessageOKCancel("You need to allow access to Call log",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(SampleCallLog2.this,
                                                new String[]{Manifest.permission.CALL_PHONE},
                                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                    }
                                });
                        return;
                    }

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(SampleCallLog2.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.


                    return;
                }
                call();

            }
        });

        IntentFilter intentFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(callLogReceiver, intentFilter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    call();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                       /* showMessageOKCancel("You need to allow access to Call",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(SampleCallLog.this,
                                                new String[]{Manifest.permission.CALL_PHONE},
                                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                    }
                                });*/
                    } else {
                        //never
                        Snackbar snackbar = Snackbar.make(fab, "You should allow acess to Call", Snackbar.LENGTH_LONG);
                        snackbar.setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        });
                        snackbar.show();
                    }

                    Toast.makeText(SampleCallLog2.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:01265620879"));
        startActivity(callIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(callLogReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeLogging();
    }

    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG_LOG, "Ready");
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
