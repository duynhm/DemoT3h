package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

/**
 * Created by Dzumi on 5/15/2016.
 */
public class Fragment1 extends BaseFragment {
    @BindView(R.id.btnNext)
    Button btnNext;

    @BindView(R.id.edtContent)
    EditText edtContent;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("fragment", "oncreateView");
        View root = inflater.inflate(R.layout.layout_demo_fragment1,container, false);
        ButterKnife.bind(this, root);

        //set title
        Bundle bundle = getArguments();
        tvTitle.setText(bundle.getString("title"));

        btnNext.setOnClickListener(v -> {
            Fragment1 fragment2 = new Fragment1();

            Bundle argument = new Bundle();
            argument.putString("title", edtContent.getText().toString());
            fragment2.setArguments(argument);

            //
            ((DemoFragmentActivity)getActivity()).pushFragment(fragment2, false);
        });

        return root;
    }

    @Override
    public String getTagName() {
        return "Fragment1";
    }
}
