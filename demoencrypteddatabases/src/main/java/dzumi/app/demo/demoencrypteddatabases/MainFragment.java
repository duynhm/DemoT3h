package dzumi.app.demo.demoencrypteddatabases;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;



/**
 * Created by dzumi on 07/03/2015.
 */
public class MainFragment extends ListFragment {

    IMainActivityCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (IMainActivityCallback) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        setListAdapter(new SimpleAdapter(getActivity(), callback.initData(),
                android.R.layout.simple_list_item_1, new String[]{MainActivity.TITLE_ACTIVITY
        },
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = new Intent((Intent) map.get("intent"));
        intent.putExtra(MainActivity.TITLE_ACTIVITY, map.get(MainActivity.TITLE_ACTIVITY).toString());
        intent.addCategory(BuildConfig.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    public interface IMainActivityCallback {
        List<Map<String, Object>> initData();
    }

}
