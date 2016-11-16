package iat351.milestone_3.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import java.util.Timer;
import java.util.TimerTask;

import iat351.milestone_3.R;

/**
 * Created by xiaok_000 on 2016-11-13.
 */

public class LoadingActivity extends FragmentActivity{

    Activity loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        loading();
    }

    private void loading() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FrameLayout loging = (FrameLayout) findViewById(R.id.login_frame);
                        loging.setVisibility(FrameLayout.VISIBLE);
                    }
                });
            }
        }, 3000);
    }

    public void Logined(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    private void ShowLoginButton(){
//        Button login_btn = (Button) findViewById(R.id.login_btn);
//        login_btn.setVisibility(FrameLayout.VISIBLE);
//    }


}
