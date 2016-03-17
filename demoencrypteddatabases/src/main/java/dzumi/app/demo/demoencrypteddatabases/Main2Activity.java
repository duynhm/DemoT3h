package dzumi.app.demo.demoencrypteddatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import dzumi.app.demo.demoencrypteddatabases.main.Encryptor;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.btnEncode)
    Button btnEncode;
    @Bind(R.id.btnDecode)
    Button btnDecode;
    @Bind(R.id.edtDes)
    EditText edtDes;
    @Bind(R.id.edtSrc)
    EditText edtSrc;

    @Bind(R.id.edtKey)
    EditText edtKey;
    private Encryptor encryptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        btnEncode.setOnClickListener(this);
        btnDecode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String key = edtKey.getText().toString();
        encryptor = new Encryptor(key);
        if(id == R.id.btnEncode){
            String text = edtSrc.getText().toString();
            try {
                String encryptText = encryptor.encrypt(text);
                edtDes.setText(encryptText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(id == R.id.btnDecode){
            String text = edtDes.getText().toString();
            try {
                String decryptText = encryptor.decrypt(text);
                edtSrc.setText(decryptText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
