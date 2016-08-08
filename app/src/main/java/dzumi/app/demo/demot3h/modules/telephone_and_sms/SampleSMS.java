package dzumi.app.demo.demot3h.modules.telephone_and_sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.utils.logger.Log;


public class SampleSMS extends AppCompatActivity implements SMSListener{
    final static String ACTION_SEND_SMS = "action_send_sms";
    final static String ACTION_DELIVERY_SMS = "action_delivery_sms";
    Button btnSend;
    EditText edtPhoneNumber;
    EditText edtContent;
    TextView tvContent;
    SMSListener smsListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        smsListener = this;
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send sms
                String content = edtContent.getText().toString();
                String destination = edtPhoneNumber.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();

                //destinationAddress: dia chi may nhan
                //scAddress: tong dai nhan tin - default = null
                //text: noi dung tin nhan
                //sentIntent: l� m?t pendingIntent nhan BR khi tin nhan da dc gui
                //DeliveryIntent: l� m?t pendingIntent nhan BR khi tin nhan da dc nhan

//                getResources().getIdentifier("vietnam.png", "drawable", getPackageName());
                Intent intent = new Intent(ACTION_SEND_SMS);
                PendingIntent sentIntent = PendingIntent.getBroadcast(
                        SampleSMS.this, 0, intent, 0);

                Intent intentDelivery = new Intent(ACTION_DELIVERY_SMS);
                PendingIntent deliveryIntent = PendingIntent.getBroadcast(
                        SampleSMS.this, 0, intentDelivery, 0);

                ArrayList<String> parts = smsManager.divideMessage(content);
                if(parts.size()  > 1)
                {
                    smsManager.sendMultipartTextMessage(destination, null,
                            parts, null, null);
                }else {
                    smsManager.sendTextMessage(destination, null,
                            content, sentIntent, deliveryIntent);
                }
            }
        });
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtContent = (EditText) findViewById(R.id.edtContent);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void onReceived(String... text) {
        tvContent.setText(text[0]);
    }

    //de lang nghe event sent --> da gui thanh cong
    public class SentReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "onReceive-SentReceiver", Toast.LENGTH_SHORT).show();
        }

    }

    //de lang nghe event delivered --> da nhan
    public class DeliveryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "onReceive-DeliveryReceiver", Toast.LENGTH_SHORT).show();
        }

    }

    public class ReceiveReceiver extends BroadcastReceiver{

        private static final String ACTION =
                "android.provider.Telephony.SMS_RECEIVED";
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if(intent != null && intent.getAction() != null &&
                    ACTION.compareToIgnoreCase(intent.getAction()) == 0)
            {
                Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
                SmsMessage[] messages = new SmsMessage[pduArray.length];
                String body = "";
                for(int i = 0; i < pduArray.length; i++)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
                    Toast.makeText(context, "From: " + messages[i].getOriginatingAddress(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "MSG: " + messages[i].getMessageBody(), Toast.LENGTH_SHORT).show();
                    Log.d("ANDROID", "From: " + messages[i].getOriginatingAddress());
                    Log.d("ANDROID", "MSG: " + messages[i].getMessageBody());
                    body+= messages[i].getMessageBody();
                }

                smsListener.onReceived(body);

            }
        }

    }
    SentReceiver sentReceiver;
    DeliveryReceiver deliveryReceiver;
    ReceiveReceiver receiver;
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        sentReceiver = new SentReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SEND_SMS);
        registerReceiver(sentReceiver, filter);

        deliveryReceiver = new DeliveryReceiver();
        IntentFilter filter2 = new IntentFilter(ACTION_DELIVERY_SMS);
        registerReceiver(deliveryReceiver, filter2);

        receiver = new ReceiveReceiver();
        IntentFilter filter3 = new IntentFilter(ReceiveReceiver.ACTION);
        registerReceiver(receiver, filter3);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterReceiver(sentReceiver);
        unregisterReceiver(deliveryReceiver);
        unregisterReceiver(receiver);
    }


}
