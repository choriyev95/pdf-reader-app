package com.example.chori.bookmaker;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
    //bular kalitlar tushundim buni
    private static final String APP_PREFS_NAME = "foreach_soft_psf_reader";

    public static final String KEY_PAGE_NUMBER = "page_number";

    // Appda nastroykalar bir nechta joydan turib o'zgartiriladi
    // Hamma joyda bitta Preferences bo'lishi uchun singleton yasaymiz
    // Static o'zgaruvchi butun klas uchun yagona bo'ladi
    // Mana shu o'zgaruvchida yasaymiz settingsni
    private static AppPrefs instance = null;

    private SharedPreferences preferences;

    private AppPrefs(Context context) {
        preferences = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Mana bu yerda uni so'raladi
    public static AppPrefs getInstance(Context context) {

        // Agar oldin yasalmagan bo'lsa yangi yasaymiz
        if (instance == null) {
            instance = new AppPrefs(context);
        }
        // Agar tayyori bor bo'lsa o'shani o'zini qaytaramiz
        // Natijada appda bitta shunaqa obyekt bo'ladi
        return instance;
    }


    //beqda faqat 1 ta narsa saqlasa buladimi??
    // Bu yerda shunaqa
    // Lekin kompozit tiplar kelsa ularni har bir maydonini alohida saqlasa bo'ladi
    // Bu metod kod tushunarli chiqishi uchun
    //yana qandeydir raqamni saqlash uchun yana alohida yasaymizmi??
    // Bu narsa majburiymas
    // Lekin tavsiya qilinadi
    //bu saqlayobdi tugrimi yoki malumotni qaytarib berobdimi??

    // nomiga mos holda bu qaytaradi
    public int getPageNumber() {
        return preferences.getInt(KEY_PAGE_NUMBER, 0);
    }

    // bunisi saqlaydi
    public void savePageNumber(int pageNumber) {
        preferences.edit()
                .putInt(KEY_PAGE_NUMBER, pageNumber)
                .apply();
    }
}
