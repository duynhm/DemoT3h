package dzumi.demo.app.demoapiservice.utils;

import android.content.Context;

import dzumi.demo.app.demoapiservice.model.User;

/**
 * Created by Dzumi on 7/25/2016.
 */
public class CustomSharedPref {
    /**
     * Luu thong tin user
     * @param context
     * @param user
     */
    public static void saveUser(Context context, User user){
        //TODO: hoan thanh phuong thuc nay
        SharedPreferenceUtil.putString(context, "fullName", user.getFullName());
        SharedPreferenceUtil.putString(context, "token", user.getToken());
    }

    public static User getUserInfo(Context context){
        //TODO: hoan thanh phuong thuc
        User user = new User();
        return user;
    }

    public static String getToken(Context context){
        return SharedPreferenceUtil.getString(context, "token", "");
    }
}
