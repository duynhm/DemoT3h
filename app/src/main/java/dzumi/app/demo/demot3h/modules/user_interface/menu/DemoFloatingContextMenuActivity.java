package dzumi.app.demo.demot3h.modules.user_interface.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;

public class DemoFloatingContextMenuActivity extends AppCompatActivity {

    ListView listView;
    List<String> nameEnList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_floating_context_menu);
        listView = (ListView) findViewById(R.id.listView);

        initData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nameEnList);
        listView.setAdapter(adapter);

        //buoc 2: ddang ki su dung context menu cho listview
        registerForContextMenu(listView);
    }

    //buoc 1: tao floating context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.demo_floating_context_menu, menu);
    }

    //buoc 3: bat su kien
    // cần lấy ra được position của item listview tương tác với contextMenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo contextMenuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int pos = contextMenuInfo.position;

        if(id == R.id.mnUpdate){
            Toast.makeText(DemoFloatingContextMenuActivity.this, "Update", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.mnDel){
            Toast.makeText(DemoFloatingContextMenuActivity.this, "Delete", Toast.LENGTH_SHORT).show();
            nameEnList.remove(pos);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
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
