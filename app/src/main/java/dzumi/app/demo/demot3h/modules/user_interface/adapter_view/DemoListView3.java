package dzumi.app.demo.demot3h.modules.user_interface.adapter_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.model.Country;

public class DemoListView3 extends AppCompatActivity {

//    @BindView(R.id.listView)
    ListView listView;

    List<Country> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list_view1);

//        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.listView);
        initData();

        CountryAdapterSimple1 adapter = new CountryAdapterSimple1(this,
                R.layout.item_demo_list_view3, countries);

        listView.setAdapter(adapter);
    }

    void initData(){
        countries = new ArrayList<>();
        countries.add(new Country("Vietnam","Việt Nam"));
        countries.add(new Country("Laos","Lào"));
        countries.add(new Country("Japan","Nhật Bản"));
        countries.add(new Country("USA","Mỹ"));
        countries.add(new Country("Korea","Hàn Quốc"));
        countries.add(new Country("China","Trung Quốc"));
        countries.add(new Country("Mexico","Mê hi cô"));
        countries.add(new Country("India","Ấn Độ"));
        countries.add(new Country("Italia","Ý"));
        countries.add(new Country("French","Pháp"));
        countries.add(new Country("Taiwan","Đài Loan"));
        countries.add(new Country("Thailand","Thái Lan"));
    }
}
