package dzumi.app.demo.demot3h.modules.user_interface.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;

public class DemoSearchView extends AppCompatActivity {
    ListView listView;
    AutoCompleteTextView autoCompleteTextView;
    private List<String> nameEnList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_search_view);
        initData();
        listView = (ListView) findViewById(R.id.listView);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.actvSearch);
        //TODO: sử dụng myAdapter cho autoCompleteTextView để search fulltext
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameEnList);
        autoCompleteTextView.setAdapter(adapter);

        myAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1, nameEnList);
        listView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_option_menu, menu);
        //Mục tiêu: tìm SearchView để bắt sự kiện khi search
        //==> SearchView nằm trong menuItem
        //==> findItem --> getActionView ==> SearchView

        MenuItem mnSearch = menu.findItem(R.id.mnSearch);
        SearchView searchView = (SearchView) mnSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //dc goi khi bam button submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //dc goi khi go phim
            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    void initData(){
        nameEnList = new ArrayList<>();
        nameEnList.add("Vietnam");
        nameEnList.add("China");
        nameEnList.add("Japan");
        nameEnList.add("Korea");
        nameEnList.add("Thailand");
        nameEnList.add("Laos");
        nameEnList.add("Cambodia");
        nameEnList.add("Canada");
        nameEnList.add("Spain");
        nameEnList.add("England");
        nameEnList.add("Italy");
    }
}
