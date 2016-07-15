package dzumi.app.demo.demot3h.modules.app_components.services;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dzumi.app.demo.demot3h.R;


public class ActivityDemoService extends AppCompatActivity implements View.OnClickListener{

    Button btnStart1, btnStop1;
    Button btnStart2, btnStop2;
    Button btnStart3, btnStop3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_service);
        btnStart1 = (Button) findViewById(R.id.btnStart1);
        btnStop1 = (Button) findViewById(R.id.btnStop1);

        btnStart2 = (Button) findViewById(R.id.btnStart2);
        btnStop2 = (Button) findViewById(R.id.btnStop2);

        btnStart3 = (Button) findViewById(R.id.btnStart3);
        btnStop3 = (Button) findViewById(R.id.btnStop3);

        btnStart1.setOnClickListener(this);
        btnStart2.setOnClickListener(this);
        btnStart3.setOnClickListener(this);

        btnStop1.setOnClickListener(this);
        btnStop2.setOnClickListener(this);
        btnStop3.setOnClickListener(this);
    }
    Intent intentService2;
    Intent intentService3;
    @Override
    public void onClick(View v) {
      int id = v.getId();
        if(id == R.id.btnStart1)
        {
            startService(new Intent(this, SampleService1.class));
        }
        else if(id == R.id.btnStop1)
        {
            stopService(new Intent(this, SampleService1.class));
        }else if(id == R.id.btnStart2)
        {
            intentService2 = new Intent(this, SampleService2.class);
            startService(intentService2);
        }
        else if(id == R.id.btnStop2)
        {
            stopService(intentService2);
        }else if(id == R.id.btnStart3)
        {
            intentService3 = new Intent(this, SampleService3.class);
            startService(intentService3);
        }
        else if(id == R.id.btnStop3)
        {
            stopService(intentService3);
        }
    }



}
