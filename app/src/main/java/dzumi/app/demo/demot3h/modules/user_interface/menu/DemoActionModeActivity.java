package dzumi.app.demo.demot3h.modules.user_interface.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;

public class DemoActionModeActivity extends AppCompatActivity {

    private static final String TAG = "DemoActionModeActivity";
    ListView listView;
    List<String> nameEnList;
    ArrayAdapter<String> adapter;
    int mCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_floating_context_menu);
        listView = (ListView) findViewById(R.id.listView);

        initData();
        adapter = new ArrayAdapter<String>(this,R.layout.item_demo_action_mode_color, nameEnList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                if(checked)
                    mCount++;
                else
                    mCount--;
                mode.setTitle("count: " + mCount);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.demo_floating_context_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                //phuong thức này được gọi khi action mode bắt đầu đc kích hoạt
                //TODO: về nhà xem lại
                Log.d(TAG, "onPrepareActionMode:");
                mCount = 0;
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                //phương thức này được gọi khi bấm vào item menu: del, update
                int id = item.getItemId();
                if(id == R.id.mnDel){
                    //xoa thì cần list position
                    //lấy ở đâu???
                    //TODO: xem lại hàm xóa item
                    //Vị trí chắc chắn đúng.
                    SparseBooleanArray checkedArray = listView.getCheckedItemPositions();
                    for(int i = 0; i < checkedArray.size(); i++){
//                        Log.d(TAG, "onActionItemClicked "  + i + ":"+ checkedArray.valueAt(i));
//                        Log.d(TAG, "onActionItemClicked: " + checkedArray.keyAt(i));
                        nameEnList.remove(checkedArray.keyAt(i));
                    }
                    adapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.d(TAG, "onDestroyActionMode:");
                mCount = 0;
            }
        });
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
