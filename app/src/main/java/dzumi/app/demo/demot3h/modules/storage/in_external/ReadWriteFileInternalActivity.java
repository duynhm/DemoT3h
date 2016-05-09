package dzumi.app.demo.demot3h.modules.storage.in_external;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

public class ReadWriteFileInternalActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.btnLoad)
    Button btnLoad;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.edtContent)
    EditText edtContent;

    @BindView(R.id.tvContent)
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_file_internal);

        ButterKnife.bind(this);

        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSave){
            try {
                FileOutputStream fileOutputStream = openFileOutput("country", MODE_PRIVATE);
                fileOutputStream.write(edtContent.getText().toString().getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(id == R.id.btnLoad){
            try {
                File dir = getFilesDir();
                Log.d("file", dir.getAbsolutePath());
                FileReader fileReader =
                        new FileReader(dir.getAbsolutePath() + "/country");

                char[] buffer = new char[512];
                int length = 0;
                String content = "";
                while ((length = fileReader.read(buffer)) != -1){
                    content += new String(buffer,0,length);
                }

                fileReader.close();
                tvContent.setText(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
