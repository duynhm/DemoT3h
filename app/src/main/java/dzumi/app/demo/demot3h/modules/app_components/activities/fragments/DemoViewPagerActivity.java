package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import dzumi.app.demo.demot3h.R;

public class DemoViewPagerActivity extends AppCompatActivity {
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_view_pager);
        ButterKnife.bind(this);

        viewPager.setAdapter(new MyAdapter(getFragmentManager()));
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Fragment1 fragment1 = new Fragment1();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "frag1");
                    fragment1.setArguments(bundle);
                    return fragment1;
                case 1:

                Fragment1 fragment2 = new Fragment1();
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "frag2");
                fragment2.setArguments(bundle2);
                return fragment2;

                case 2:

                Fragment1 fragment3 = new Fragment1();
                Bundle bundle3 = new Bundle();
                bundle3.putString("title", "frag3");
                fragment3.setArguments(bundle3);
                return fragment3;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
