package dzumi.app.demo.demot3h.main.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.utils.Constants;
import dzumi.app.demo.demot3h.utils.logger.LogWrapper;

/**
 * Created by duy on 2/18/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FrameLayout mFrameContainer;
    Toolbar mToolbar;

    protected void initView() {
        mFragmentManager = getFragmentManager();

        Fragment currentFragment = initFragment();
        if (currentFragment != null)
            mFragmentManager.beginTransaction().replace(R.id.frameContainer, initFragment()).commit();
        else {
            View view = initContentView();
            mFrameContainer = (FrameLayout) findViewById(R.id.frameContainer);
            if (view == null) {
                throw new RuntimeException("You must init fragment or contentView.");
            }
            mFrameContainer.addView(view);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLogLifeCycle())
            Log.d(getLogTag(), "onCreate - " + getClass().toString());


    }

    //region init method

    /**
     * Khoi tao fragment cho activity, fragment nay se dc gan vao frameContainer
     *
     * @return
     */
    protected abstract Fragment initFragment();

    /**
     * Khoi tao contentView neu ko chua fragment
     *
     * @return
     */
    protected abstract View initContentView();

    /**
     * Lay TAG LOG de thuc hien Log
     *
     * @return
     */
    protected abstract String getLogTag();

    /**
     * Co log LifeCycle cua Activity khong?
     *
     * @return
     */
    protected abstract boolean isLogLifeCycle();

    protected String initTitleToolbar() {
        String path = getIntent().getStringExtra(Constants.TITLE_ACTIVITY);
        if (path == null)
            return "";
        return path;
    }
    //endregion

    //region Life cycle
    @Override
    protected void onStart() {
        super.onStart();
//            initializeLogging();
        if (isLogLifeCycle())
            Log.d(getLogTag(), "OnStart - " + getClass().toString());
    }


    /**
     * Set up targets to receive log data
     */
    public void initializeLogging() {
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        dzumi.app.demo.demot3h.utils.logger.Log.setLogNode(logWrapper);

        dzumi.app.demo.demot3h.utils.logger.Log.i("dzumi", "Ready");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLogLifeCycle())
            Log.d(getLogTag(), "onResume - " + getClass().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isLogLifeCycle())
            Log.d(getLogTag(), "onPause - " + getClass().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isLogLifeCycle())
            Log.d(getLogTag(), "onStop - " + getClass().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLogLifeCycle())
            Log.d(getLogTag(), "onDestroy - " + getClass().toString());
    }
    //endregion
}
