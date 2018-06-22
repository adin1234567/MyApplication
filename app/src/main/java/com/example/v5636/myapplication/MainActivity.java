package com.example.v5636.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "MainActivity";
    Button mInstant_button, mHistory_button, mToday_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstant_button = findViewById(R.id.Instant);
        mHistory_button = findViewById(R.id.History);
        mToday_button = findViewById(R.id.Today);

        mInstant_button.setOnClickListener(this);
        mHistory_button.setOnClickListener(this);
        mToday_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "View ID:" + v.getId());
        switch (v.getId()) {

            case R.id.Instant: {
                startActivity(new Intent(MainActivity.this, InstantActivity.class));
                finish();
                break;
            }
            case R.id.History: {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                finish();
                break;
            }
            case R.id.Today: {
                startActivity(new Intent(MainActivity.this, TodayActivity.class));
                finish();
                break;
            }
            default: {
                return;
            }
        }


    }
}








