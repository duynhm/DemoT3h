package dzumi.app.demo.demoencrypteddatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dzumi.app.demo.demoencrypteddatabases.adapter.CountryAdapter;
import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.db.DBContext;
import dzumi.app.demo.demoencrypteddatabases.provider.DataProvider;
import dzumi.app.demo.demoencrypteddatabases.provider.DataProviderEncrypt;
import dzumi.app.demo.demoencrypteddatabases.provider.IDataProvider;

/**
 * See more: https://devcentral.f5.com/articles/android-encrypted-databases
 * load data duoc encryp, demo nay data dc encrypt content luu vao sqlite db binh thuong
 */
public class Main1Activity extends AppCompatActivity implements View.OnClickListener {

    IDataProvider iDataProvider;

    @Bind(R.id.btnDBEncryptContent)
    Button btnDBEncryptContent;
    @Bind(R.id.btnDBEncryptFile)
    Button btnDBEncryptFile;

    @Bind(R.id.btnInsert)
    Button btnInsert;

    @Bind(R.id.btnLoad)
    Button btnLoad;


    @Bind(R.id.btnSearch)
    Button btnSearch;


    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        //initView
        initView();

    }

    void initView(){
        ButterKnife.bind(this);
        btnDBEncryptContent.setOnClickListener(this);
        btnDBEncryptFile.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    void setAdapter(List<Country> countries){
        CountryAdapter countryAdapter = new CountryAdapter(this, R.layout.item_list_view_countries, countries);
        listView.setAdapter(countryAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnInsert){
            try {
                if(iDataProvider.insert(new Country(editText.getText().toString(),editText.getText().toString())) != -1)
                    Toast.makeText(this, "Insert success",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Insert fail",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(id == R.id.btnLoad){
            setAdapter(iDataProvider.get());
        }else if(id == R.id.btnDBEncryptContent){
            iDataProvider = new DataProvider(this);
            Toast.makeText(this, "DB Encrypt Content",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.btnDBEncryptFile){
            iDataProvider = new DataProviderEncrypt(this);
            Toast.makeText(this, "DB Encrypt File",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.btnSearch){
            setAdapter(iDataProvider.get(editText.getText().toString()));
        }
    }
}
