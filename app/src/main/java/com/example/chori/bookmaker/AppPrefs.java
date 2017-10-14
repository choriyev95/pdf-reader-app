package com.example.chori.bookmaker;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static final String APP_PREFS_NAME = "foreach_soft_psf_reader";

    public static final String KEY_PAGE_NUMBER = "page_number";

    private static AppPrefs instance = null;

    private SharedPreferences preferences;

    private AppPrefs(Context context) {
        preferences = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static AppPrefs getInstance(Context context) {
        if (instance == null) {
            instance = new AppPrefs(context);
        }
        return instance;
    }

    public int getPageNumber() {
        return preferences.getInt(KEY_PAGE_NUMBER, 0);
    }

    public void savePageNumber(int pageNumber) {
        preferences.edit()
                .putInt(KEY_PAGE_NUMBER, pageNumber)
                .apply();
    }
}
