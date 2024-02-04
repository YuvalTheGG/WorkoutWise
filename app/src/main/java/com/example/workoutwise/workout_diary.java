package com.example.workoutwise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class workout_diary extends AppCompatActivity {

    private CalendarView calendarView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_diary);

        calendarView = findViewById(R.id.calendarView);
        handler = new Handler(Looper.getMainLooper());

        // Set initial date
        calendarView.setDate(System.currentTimeMillis());

        // Update calendar view every second
        handler.postDelayed(updateCalendarRunnable, 1000);

        // Set listener for date selection
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle date selection
                showToast("Selected Date: " + (month + 1) + "/" + dayOfMonth + "/" + year);
            }
        });
    }

    private Runnable updateCalendarRunnable = new Runnable() {
        @Override
        public void run() {
            // Update calendar view to the current time
            calendarView.setDate(System.currentTimeMillis());

            // Schedule the next update after 1 second
            handler.postDelayed(this, 1000);
        }
    };

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        // Remove the update runnable when the activity is destroyed
        handler.removeCallbacks(updateCalendarRunnable);
        super.onDestroy();
    }
}