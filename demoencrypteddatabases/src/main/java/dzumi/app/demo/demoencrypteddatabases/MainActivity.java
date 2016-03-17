package dzumi.app.demo.demoencrypteddatabases;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements MainFragment.IMainActivityCallback {
    public final static String APIS_PATH = "dzumi.app.demo.demoencrypteddatabases.Path";
    public final static String TITLE_ACTIVITY = "title_activity";
    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get(TITLE_ACTIVITY), map2.get(TITLE_ACTIVITY));
                }
            };
    //endregion
    String mPath;

    //region Lay danh sach item tu manifest theo label de show len listview
    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("dzumi.encrypt.intent.category.DEMO");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra(APIS_PATH, path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put(TITLE_ACTIVITY, name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = getIntent();
        mPath = intent.getStringExtra(APIS_PATH);

        if (mPath == null) {
            mPath = "";
        }

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
    }

    @Override
    public List<Map<String, Object>> initData() {
        return getData(mPath);
    }
}
