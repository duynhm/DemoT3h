package dzumi.app.demo.demot3h.modules.app_components;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.modules.HelloWorld;
import dzumi.app.demo.demot3h.modules.app_components.broadcast_receiver.CheckNetworkReceiver;
import dzumi.app.demo.demot3h.modules.app_components.content_provider.ActivitySampleContactList1;

public class OverviewsActivity extends AppCompatActivity {

    Button btnContentProviders, btnServices, btnNotifications;
    CheckNetworkReceiver checkNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overviews);

        btnContentProviders = (Button) findViewById(R.id.btnContentProviders);
        btnServices = (Button) findViewById(R.id.btnServices);
        btnNotifications = (Button) findViewById(R.id.btnNotifications);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotifications();
            }
        });

        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://developer.android.com/design/media/hero-material-design.png");
                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());

                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        });

        btnContentProviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewsActivity.this, ActivitySampleContactList1.class);
                startActivity(intent);
            }
        });

        checkNetworkReceiver = new CheckNetworkReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilterCheckNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(checkNetworkReceiver, intentFilterCheckNetwork);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(checkNetworkReceiver);
    }

    void showNotifications() {
        Intent intent = new Intent(this, HelloWorld.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Demo Notificaion")
                .setContentText("Hello world!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX).build();

        final int notifId = 1337;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifId, notification);
    }
}
