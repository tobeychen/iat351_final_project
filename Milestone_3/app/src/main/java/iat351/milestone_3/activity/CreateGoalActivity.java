package iat351.milestone_3.activity;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import iat351.milestone_3.R;

/**
 * Created by xiaok_000 on 2016-11-12.
 */

public class CreateGoalActivity extends AppCompatActivity {
    Button add_goal_done;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        add_goal_done = (Button)findViewById(R.id.add_goal_done);
        add_goal_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                startActivity(new Intent(this,CreateEventActivity.class));
//                startActivity(new Intent(view.getContext(), CreateEventActivity.class));
                finish();
            }
        });

        ImageButton cancel_btn = (ImageButton)findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

