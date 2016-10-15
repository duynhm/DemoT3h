package dzumi.app.demo.demot3h.modules.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

public class EditContentActivity extends AppCompatActivity {
    @BindView(R.id.edtEdit)
    EditText edtEdit;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_content);
        ButterKnife.bind(this);

        //set du lieu email tu man hinh truoc
        String email = getIntent().getStringExtra("email");
        edtEdit.setText(email);

        btnSubmit.setOnClickListener(v -> {
            //lay du lieu tu edit text --> truyen ket qua tra ve cho activity truoc
            String content = edtEdit.getText().toString();

            Intent intent = getIntent();
            intent.putExtra("email", content);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
