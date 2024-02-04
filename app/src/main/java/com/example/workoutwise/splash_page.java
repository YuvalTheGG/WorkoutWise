package com.example.workoutwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class splash_page extends AppCompatActivity {
    int DELAY_MS = 750;
    Intent goTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        new Handler().postDelayed(( ) -> {
            goTo = new Intent(this, MainActivity.class);
            startActivity(goTo);
            //DELAYED CODE
        }, DELAY_MS);

    }






//    iv_image.setOnClickListener(new View.OnClickListener() {
//        iv_image.animate().setDuration(2000).
//
//    });
}