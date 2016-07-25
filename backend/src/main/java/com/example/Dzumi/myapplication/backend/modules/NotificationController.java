/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.Dzumi.myapplication.backend.modules;

import com.example.Dzumi.myapplication.backend.lib.JSONArray;
import com.example.Dzumi.myapplication.backend.lib.JSONException;
import com.example.Dzumi.myapplication.backend.lib.JSONObject;
import com.example.Dzumi.myapplication.backend.model.Notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotificationController extends HttpServlet implements Base {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Làm ơn chuyển sang xài POST");
    }

    List<Notification> notifications;
    public  JSONObject getJson(String mess, int codeStatus, Object object){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", codeStatus);
            jsonObject.put("message",mess);
            jsonObject.put("data", object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    void initNotifications(){
       notifications = new ArrayList<>();
        notifications.add(new Notification(1, "Thong bao nghi hoc", 1469441874822L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));
        notifications.add(new Notification(2, "Thong bao nghi hoc", 1469441874822L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));
        notifications.add(new Notification(3, "Thong bao nghi hoc", 1469441942861L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));
        notifications.add(new Notification(4, "Thong bao nghi hoc", 1469441874822L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));
        notifications.add(new Notification(5, "Thong bao nghi hoc", 1469441952204L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));
        notifications.add(new Notification(6, "Thong bao nghi hoc", 1469441874822L, "Lop android module 4 se duoc nghi hoc ngay 25/7", "duynhm" ));

    }

    JSONArray getNotifications(){
        JSONArray jsonArray = new JSONArray();
        for(Notification notification : notifications){
            jsonArray.put(notification.toJsonObject());
        }
        return jsonArray;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
        }
        JSONObject jsonObject = null;
        resp.setHeader("Content-type", "application/json");
        try {
            jsonObject = new JSONObject(jb.toString());
            String token = jsonObject.getString("token");
            if(token != null && token.equals(Base.TOKEN)){
                initNotifications();
                resp.getWriter().print(getJson("Success", STATUS_SUCCESS, getNotifications() ));
            }else
                resp.getWriter().print(getJson("Khong co quyen truy cap", STATUS_ERROR, null ));
        } catch (JSONException e) {
            e.printStackTrace();
            resp.getWriter().print(getJson("Loi he thong: " + jsonObject.toString(), STATUS_ERROR, null ));
        }
    }
}
