package dzumi.app.demo.demot3h.modules.intent;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

public class MainIntentActivity extends AppCompatActivity {
    public final static int REQUEST_EDIT = 1;
    @BindView(R.id.btnEdit)
    Button btnEdit;

    @BindView(R.id.tvHello)
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);
        ButterKnife.bind(this);
        //lay du lieu tu intent login
        String email = getIntent().getStringExtra("email");

        //set text?
        tvHello.setText(email);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainIntentActivity.this,
                    EditContentActivity.class);
            //truyen du lieu qua man hinh edit --> dư lieu???
            intent.putExtra("email", tvHello.getText().toString());
            startActivityForResult(intent, REQUEST_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EDIT) {
            if (resultCode == RESULT_OK) {
                //thah cong
                tvHello.setText(data.getStringExtra("email"));
            } else {
                //that bai
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
