package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dzumi.app.demo.demot3h.R;

public class DemoFragmentActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        Log.d("lifeCycle-Activity", "onBackPress");
        int count = getFragmentManager().getBackStackEntryCount();
        if(count > 1)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);

        Fragment1 fragment1 = new Fragment1();

        Bundle bundle = new Bundle();
        bundle.putString("title", "fragment 1");
        fragment1.setArguments(bundle);

        pushFragment(fragment1, true);
    }

    public void pushFragment(BaseFragment fragment, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container, fragment, fragment.getTagName());
        if (isAddToBackStack)
            fragmentTransaction.addToBackStack(fragment.getTagName());
        fragmentTransaction.commit();
    }
}
