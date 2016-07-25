package com.example.Dzumi.myapplication.backend.model;

import com.example.Dzumi.myapplication.backend.lib.JSONException;
import com.example.Dzumi.myapplication.backend.lib.JSONObject;

import java.util.Map;

/**
 * Created by Dzumi on 7/25/2016.
 */
public class Notification {
    int id;
    long date;
    String description;
    String sender;
    String title;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("date", date);
            jsonObject.put("description", description);
            jsonObject.put("sender", sender);
            jsonObject.put("title", title);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Notification(int id, String title, long date, String description,  String sender) {
        this.date = date;
        this.description = description;
        this.id = id;
        this.sender = sender;
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
