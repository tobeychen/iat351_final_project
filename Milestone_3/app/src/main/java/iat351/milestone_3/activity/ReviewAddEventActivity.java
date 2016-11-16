package iat351.milestone_3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import iat351.milestone_3.R;
import iat351.milestone_3.activity.MainActivity;

/**
 * Created by xiaok_000 on 2016-11-14.
 */

public class ReviewAddEventActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add_event);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Button item_finished = (Button)findViewById(R.id.item_finished);
        item_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AfterAddedEventMainActivity.class));
            }
        });

        //This is the same for all the screen, so can copy and paste
        //What is does is to close the current activity and go back to the previous one
        ImageButton cancel_btn = (ImageButton)findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

