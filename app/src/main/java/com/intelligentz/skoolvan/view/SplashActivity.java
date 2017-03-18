package com.intelligentz.skoolvan.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.intelligentz.skoolvan.R;
import com.nineoldandroids.animation.Animator;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logoview);
        int duration = 2000;
        YoYo.with(Techniques.Shake)
                .duration(duration).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startNextActivity();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(logo);

    }

    private void startNextActivity() {
        SharedPreferences mPrefs = getSharedPreferences("skoolvan.username", Context.MODE_PRIVATE);
        String username = mPrefs.getString("username", null);
        if (username == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }
    }
}
