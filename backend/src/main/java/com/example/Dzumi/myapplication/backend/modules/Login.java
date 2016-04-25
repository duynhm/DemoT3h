/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.Dzumi.myapplication.backend.modules;

import com.example.Dzumi.myapplication.backend.lib.JSONException;
import com.example.Dzumi.myapplication.backend.lib.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Làm ơn chuyển sang xài POST");
    }

    final String TOKEN = "SADfivfjvlieyverygbwcgwheywe#kfha313124r##@B3r";
    final int STATUS_SUCCESS = 1;
    final int STATUS_ERROR = 2;

    JSONObject getJson(String mess, int codeStatus, String accessToken){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", codeStatus);
            jsonObject.put("message",mess);
            jsonObject.put("accessToken", accessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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
        try {
            jsonObject = new JSONObject(jb.toString());
            String userName = jsonObject.getString("user_name");
            String pass = jsonObject.getString("pass");

            if(pass.equals("t3h") && userName.equals("t3h")){
                resp.getWriter().print(getJson(null, STATUS_SUCCESS, TOKEN ));
            }else
                resp.getWriter().print(getJson("Tai khoan hoac mat khau khong hop le", STATUS_ERROR, null ));
        } catch (JSONException e) {
            e.printStackTrace();
            resp.getWriter().print(getJson("Loi he thong: " + jsonObject.toString(), STATUS_ERROR, null ));
        }
    }
}
