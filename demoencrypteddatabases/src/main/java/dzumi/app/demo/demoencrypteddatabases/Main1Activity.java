package dzumi.app.demo.demoencrypteddatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.db.DBContext;

/**
 * See more: https://devcentral.f5.com/articles/android-encrypted-databases
 */
public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ListView listView = (ListView) findViewById(R.id.listView);

        final DBContext dbContext = new DBContext(this, null);
        final EncryptedNameAdapter encryptedNameAdapter = new EncryptedNameAdapter(this,0,dbContext.getCursor(),false);
        listView.setAdapter(encryptedNameAdapter);
        final EditText editText = (EditText) findViewById(R.id.editText);

        Button btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                Country country = null;
                try {
                    country = new Country(s,s);
                    dbContext.insert(country);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptedNameAdapter.notifyDataSetChanged();
            }
        });
    }
}
