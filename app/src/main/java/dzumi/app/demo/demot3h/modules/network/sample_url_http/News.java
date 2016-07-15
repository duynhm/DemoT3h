package dzumi.app.demo.demot3h.modules.network.sample_url_http;

/**
 * Created by Dzumi on 7/15/2016.
 */
public class News {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PUB_DATE = "pubDate";
    public static final String LINK = "link";

    String title;
    String description;
    String link;
    String imageLink;
    String pubDate;

    @Override
    public String toString() {
        return title + " / " + link + "  / " + pubDate ;
    }

    public News() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
