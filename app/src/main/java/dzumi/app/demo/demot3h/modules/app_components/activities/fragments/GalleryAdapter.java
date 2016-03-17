package dzumi.app.demo.demot3h.modules.app_components.activities.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dzumi.app.demo.demot3h.R;


/**
 * Created by dzumi on 25/05/2015.
 */
public class GalleryAdapter extends FragmentStatePagerAdapter {

    public String mParent;
    String[] mListImages;

    public GalleryAdapter(FragmentManager fragmentManager, String[] listImages, String parent) {
        super(fragmentManager);
        mListImages = listImages;
        mParent = parent;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", mListImages[position]);
        bundle.putString("parent", mParent);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return mListImages.length;
    }

    public static class ImageFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_image_view, null);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

            Bitmap bitmap = BitmapFactory.decodeFile(getArguments().getString("parent") + getArguments().getString("path"));
            imageView.setImageBitmap(bitmap);
            return rootView;
        }
    }
}
