package dzumi.demo.app.demoapiservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dzumi on 7/25/2016.
 */
public class Notification {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     * The id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The date
     */
    public Long getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The sender
     */
    public String getSender() {
        return sender;
    }

    /**
     *
     * @param sender
     * The sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
