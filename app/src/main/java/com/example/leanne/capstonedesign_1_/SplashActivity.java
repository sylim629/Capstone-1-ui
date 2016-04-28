package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by imsuyeon on 16. 4. 2..
 */
public class SplashActivity extends Activity {

    private boolean skipLure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();

        final Intent lure = new Intent(this, LureActivity.class);
        final Intent login = new Intent(this, LoginActivity.class);

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                skipLure = DataHolder.getInstance().getSkipLure();
                if(skipLure == false)
                    startActivity(lure);
                else
                    startActivity(login);
                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다
    }
}
