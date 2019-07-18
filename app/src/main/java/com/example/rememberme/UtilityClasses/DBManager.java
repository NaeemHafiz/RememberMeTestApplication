package com.example.rememberme.UtilityClasses;

import android.content.Context;


import static com.example.rememberme.UtilityClasses.Tags.APP_PREFS;


public class DBManager {

    public static void setStringPrefs(Context context, String key, String value) {
        PreferenceUtility.setStringPreference(context, APP_PREFS, key, value);
    }

    public static void setIntPrefs(Context context, String key, int value) {
        PreferenceUtility.setIntPreference(context, APP_PREFS, key, value);
    }

    public static void setBoolPrefs(Context context, String key, boolean value) {
        PreferenceUtility.setBoolPreference(context, APP_PREFS, key, value);
    }

    public static String getStringPrefs(Context context, String key) {
        return PreferenceUtility.getStringPreference(context, APP_PREFS, key);
    }

    public static int getIntPrefs(Context context, String key) {
        return PreferenceUtility.getIntPreference(context, APP_PREFS, key);
    }

    public static boolean getBoolPrefs(Context context, String key) {
        return PreferenceUtility.getBoolPreference(context, APP_PREFS, key);
    }

    public static void removeAllPreferencesData(Context context) {
        PreferenceUtility.removeAllPreferences(context, APP_PREFS);
    }

}
