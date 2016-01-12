package kitkare.kitkare.app.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// http://stackoverflow.com/questions/12744337/how-to-keep-android-applications-always-be-logged-in-state
public class SaveSharedPreference {
    static final String PREF_USER_NAME = "KitKareUsername";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setKeyValuePair(Context ctx, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getKeyValuePair(Context ctx, String key) {
        return getSharedPreferences(ctx).getString(key, "");
    }

    public static void removeKeyValuePair(Context ctx, String key) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(key);
        editor.commit();
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void removeUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_NAME);
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}
