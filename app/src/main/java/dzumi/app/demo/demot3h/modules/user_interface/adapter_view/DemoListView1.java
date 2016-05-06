package dzumi.app.demo.demot3h.modules.user_interface.adapter_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

public class DemoListView1 extends AppCompatActivity {

//    @BindView(R.id.listView)
    ListView listView;

    List<String> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list_view1);

//        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.listView);
        initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countries);

        listView.setAdapter(adapter);
    }

    void initData(){
        countries = new ArrayList<>();
        countries.add("Việt Nam");
        countries.add("Lào");
        countries.add("Campuchia");
        countries.add("Hàn Quốc");
        countries.add("Đài Loan");
        countries.add("Nhật Bản");
        countries.add("Singapore");
        countries.add("Indonexia");
    }
}
