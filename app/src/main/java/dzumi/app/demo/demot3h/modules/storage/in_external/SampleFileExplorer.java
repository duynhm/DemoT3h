package dzumi.app.demo.demot3h.modules.storage.in_external;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.modules.app_components.activities.fragments.ActivityGalleryScreen;
import dzumi.app.demo.demot3h.utils.ExtensionsNameFilter;

public class SampleFileExplorer extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    TextView tvPath;
    String mPath;

    ArrayList<String> mFileNames;
    ArrayList<String> mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_file_explorer);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        mPath = Environment.getExternalStorageDirectory().getPath();
        tvPath = (TextView) findViewById(R.id.textPath);

        getDirectory(mPath);
    }

    public void getDirectory(String pathDir) {

        tvPath.setText(pathDir);

        mFileNames = new ArrayList<>();
        mFilePath = new ArrayList<>();

        File dir = new File(pathDir);

        mFileNames.add("../");
        mFilePath.add(dir.getParent());

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                mFileNames.add(files[i].getName());
                mFilePath.add(files[i].getAbsolutePath());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mFileNames);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        File file = new File(mFilePath.get(i));
        if (file.isDirectory() && !file.isHidden() && file.canRead()) {
            getDirectory(file.getAbsolutePath());
        } else {
            //xem image
            if (file.getAbsolutePath().contains(".jpg")) {
                //lay danh sach file hinh anh
                String[] files = file.getParentFile().list(new ExtensionsNameFilter(ExtensionsNameFilter.IMAGE_FILTER));
                Intent intent = new Intent(this, ActivityGalleryScreen.class);
                intent.putExtra("listImage", files);
                intent.putExtra("parent", file.getParent() + "/");
                startActivity(intent);
            } else
                Toast.makeText(this, file.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
