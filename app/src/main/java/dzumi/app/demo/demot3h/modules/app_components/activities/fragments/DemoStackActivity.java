package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dzumi.app.demo.demot3h.R;

public class DemoStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_stack);
    }

    public void onClickNext(View view){
        Toast.makeText(DemoStackActivity.this, "next", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DemoStackActivity.class);
        startActivity(intent);
    }

    public void onClickBack(View view){
        Toast.makeText(DemoStackActivity.this, "back", Toast.LENGTH_SHORT).show();
    }
}
