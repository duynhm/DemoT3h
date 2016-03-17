package dzumi.app.demo.demot3h.modules.storage.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by duynhm on 02/12/2014.
 */
public class SharedPreferenceUtil {
    final static String sharedName = "demot3hpref";

    //<editor-fold desc="normal sharedPref">
    public static void putString(Context context, String key, String value) {
        putString(context, sharedName, key, value);
    }

    public static void putInt(Context context, String key, int value) {
        putInt(context, sharedName, key, value);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        putBoolean(context, sharedName, key, value);

    }

    public static void putFloat(Context context, String key, float value) {
        putFloat(context, sharedName, key, value);

    }

    public static void putLong(Context context, String key, long value) {
        putLong(context, sharedName, key, value);
    }

    public static void putStringSet(Context context, String key, Set<String> values) {
        putStringSet(context, sharedName, key, values);
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getString(context, sharedName, key, defaultValue);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getInt(context, sharedName, key, defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getLong(context, sharedName, key, defaultValue);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getFloat(context, sharedName, key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getBoolean(context, sharedName, key, defaultValue);
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValues) {

        return getStringSet(context, sharedName, key, defaultValues);
    }

    public static void clear(Context context) {
        clear(context, sharedName);
    }

    public static void remove(Context context, String key) {
        remove(context, sharedName, key);
    }

    public static void putString(Context context, String prefName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void putInt(Context context, String prefName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().putInt(key, value).commit();
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static void putBoolean(Context context, String prefName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().putBoolean(key, value).commit();
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static void putFloat(Context context, String prefName, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().putFloat(key, value).commit();
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void putLong(Context context, String prefName, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().putLong(key, value).commit();
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static void putStringSet(Context context, String prefName, String key, Set<String> values) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().putStringSet(key, values).commit();
        sharedPreferences.edit().putStringSet(key, values).apply();
    }

    public static String getString(Context context, String prefName, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static int getInt(Context context, String prefName, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static long getLong(Context context, String prefName, String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static float getFloat(Context context, String prefName, String key, float defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String prefName, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static Set<String> getStringSet(Context context, String prefName, String key, Set<String> defaultValues) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.getStringSet(key, defaultValues);
    }

    public static void clear(Context context, String prefName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().clear().commit();
        sharedPreferences.edit().clear().apply();
    }

    public static void remove(Context context, String prefName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
//        sharedPreferences.edit().remove(key).commit();
        sharedPreferences.edit().remove(key).apply();
    }
    //</editor-fold>

    public static SharedPreferences.Editor getEdit(Context context, String prefName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, 0);
        return sharedPreferences.edit();
    }

    public static SharedPreferences getSharedPreferences(Context context, String prefName) {
        return context.getSharedPreferences(prefName, 0);
    }
}
