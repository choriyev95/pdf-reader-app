package com.example.chori.bookmaker;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // Starter ishlatishni tavsiya qilinadi
                        ReaderActivity.start(LauncherActivity.this);
                        finish();
                    }
                },
                Constants.SHOW_LOGO_DELAY
        );
    }
}



