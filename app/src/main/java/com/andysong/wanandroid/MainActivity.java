package com.andysong.wanandroid;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Spark mSpark;
    private ConstraintLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = findViewById(R.id.frameLayout);
        mSpark = new Spark.Builder()
                .setView(mFrameLayout)
                .setDuration(4000)
                .setAnimList(Spark.ANIM_BLUE_PURPLE)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSpark.startAnimation();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSpark.stopAnimation();
    }
}
