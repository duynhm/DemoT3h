package dzumi.android.demo.readnews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dzumi on 4/1/2016.
 */
public class News implements Parcelable {
    public static final String TYPE_THE_THAO = "//thethao";
    public static final String TYPE_GAME = "//diendan.gamethu.net/";
    public static final String TYPE_THOI_SU = "/thoi-su/";
    public static final String TYPE_NHAC = "/nhac/";
    public static final String TYPE_TRUYEN_HINH = "/truyen-hinh/";
    public static final String TYPE_MOBILE = "/mobile/";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String LINK = "link";
    public static final String PUB_DATE = "pubDate";
    public static final String COMMENTS = "slash:comments";
    protected News(Parcel in) {
        title = in.readString();
        description = in.readString();
        link = in.readString();
        imgUrl = in.readString();
        pubDate = in.readString();
        type = in.readString();
        comment = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public News() {

    }

    public News(String title, String description, String link, String imgUrl, String pubDate, String type, int comment) {

        this.title = title;
        this.description = description;
        this.link = link;
        this.imgUrl = imgUrl;
        this.pubDate = pubDate;
        this.type = type;
        this.comment = comment;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }


    String title;
    String description;
    String link;
    String imgUrl;
    String pubDate;
    String type;
    @SerializedName("slash:comments")
    int comment;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(link);
        dest.writeString(imgUrl);
        dest.writeString(pubDate);
        dest.writeString(type);
        dest.writeInt(comment);
    }
}
