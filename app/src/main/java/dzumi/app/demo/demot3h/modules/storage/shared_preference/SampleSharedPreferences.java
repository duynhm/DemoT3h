package dzumi.app.demo.demot3h.modules.storage.shared_preference;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import dzumi.app.demo.demot3h.R;

public class SampleSharedPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_shared_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(SharedPreferenceUtil.getString(this, "sampleSharedPref", getString(R.string.sample_sharedPref_default)));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceUtil.putString(SampleSharedPreferences.this, "sampleSharedPref", editText.getText().toString());
            }
        });

    }

}
