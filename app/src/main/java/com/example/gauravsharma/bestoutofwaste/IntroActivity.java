package com.example.gauravsharma.bestoutofwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class IntroActivity extends MenuActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("why","why");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        super.onCreateDraw();
        //
    }
}
