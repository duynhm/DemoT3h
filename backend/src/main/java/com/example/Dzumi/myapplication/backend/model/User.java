package com.example.Dzumi.myapplication.backend.model;

import com.example.Dzumi.myapplication.backend.lib.JSONException;
import com.example.Dzumi.myapplication.backend.lib.JSONObject;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class User {
    long userID;
    String password;
    String fullName;
    String token;
    String email;
    String userName;

    public User(String email, String fullName, String token, long userID) {
        this.email = email;
        this.fullName = fullName;
        this.token = token;
        this.userID = userID;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userID", userID);
            jsonObject.put("fullName", fullName);
            jsonObject.put("token", token);
            jsonObject.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
