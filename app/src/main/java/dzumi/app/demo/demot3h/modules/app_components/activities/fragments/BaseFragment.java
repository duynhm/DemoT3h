package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Dzumi on 5/15/2016.
 */
public abstract class BaseFragment extends Fragment {
    public abstract String getTagName();

    public BaseFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifeCycle", "onCreate");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("lifeCycle", "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("lifeCycle", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifeCycle", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifeCycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
       Log.d("lifeCycle", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
       Log.d("lifeCycle", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      Log.d("lifeCycle", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       Log.d("lifeCycle", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
       Log.d("lifeCycle", "onDetach");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("lifeCycle", "onAttach");
    }

}
