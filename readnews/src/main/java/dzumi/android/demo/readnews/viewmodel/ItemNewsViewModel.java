package dzumi.android.demo.readnews.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dzumi.android.demo.readnews.R;
import dzumi.android.demo.readnews.model.News;
import dzumi.android.demo.readnews.utils.DateTimeUtils;

/**
 * Created by Dzumi on 4/1/2016.
 */
public class ItemNewsViewModel extends BaseObservable implements ViewModel {

    private News mNews;
    private Context mContext;

    public ItemNewsViewModel(Context context, News news) {
        this.mNews = news;
        this.mContext = context;
    }

    public String getComment() {
        return mContext.getString(R.string.news_comment, mNews.getComment());
    }

    public String getType() {
        return mNews.getType();
    }

    public String getTitle() {
        return mNews.getTitle();
    }

    public String getDescription() {
        return mNews.getDescription();
    }

    public String getPubDate() {
        return DateTimeUtils.getPrettyTime(mNews.getPubDate());
    }

    public String getImgUrl() {
        return mNews.getImgUrl();
    }

    /* public int typeColor(){
         Resources resources = mContext.getResources();
         switch (mNews.getType()){
             case News.TYPE_GAME: return resources.getColor();
         }
     }*/
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public void onItemClick(View view) {
    }

    @Override
    public void destroy() {

    }
}
