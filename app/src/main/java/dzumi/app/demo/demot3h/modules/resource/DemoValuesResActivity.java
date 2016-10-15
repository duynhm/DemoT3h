package dzumi.app.demo.demot3h.modules.resource;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dzumi.app.demo.demot3h.R;

public class DemoValuesResActivity extends AppCompatActivity {

    TextView tvStringFormat;
    TextView tvStringPlurals;
    EditText edtCount;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_values_res);

        tvStringFormat = (TextView) findViewById(R.id.stringFormat);
        tvStringPlurals = (TextView) findViewById(R.id.stringPlurals);
        edtCount = (EditText) findViewById(R.id.edtCount);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getResources();

                int count = Integer.parseInt(edtCount.getText().toString());
                String format = getString(R.string.res_string_format,"midu", count);
                tvStringFormat.setText(format);

                String plurals = resources.getQuantityString(R.plurals.res_plurals_format, count, count);
                tvStringPlurals.setText(plurals);
            }
        });

//        tvStringPlurals.setText(R.plurals.res_plurals_format);

    }
}
