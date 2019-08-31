package com.example.anniversary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import android.os.Build;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;







public class FullscreenActivity extends Activity {
    SharedPreferences sharedPreferences;
    Thread splashTread;
    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.FullscreenTheme);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }




            startAnimations();


    }

    private void splashTimer() {
        /*new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {*/
        if (!isFinishing() && FullscreenActivity.this != null) {
            Intent i = null;

//                startActivity(i);
                finish();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }






    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }


    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.ll_logo);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.tv_gameon);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    splashTimer();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    FullscreenActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
