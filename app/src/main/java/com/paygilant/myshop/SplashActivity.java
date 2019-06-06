package com.paygilant.myshop;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ui_appBar_start)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Code Added By Abhishek 12/10/18 For Permission Check
                    checkPermissions();
                }
            }
        };
        timerThread.start();
    }


    void checkPermissions() {
        // GetImage();
            Intent intent = new Intent(SplashActivity.this, ConnectActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

    }



}


