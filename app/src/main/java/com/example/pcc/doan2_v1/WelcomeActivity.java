package com.example.pcc.doan2_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}
