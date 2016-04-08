package dzumi.android.demo.readnews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dzumi on 4/4/2016.
 */
public class Channel {
    String title;@SerializedName("item")
    public List<News> items;
}
