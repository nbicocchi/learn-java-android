package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Second Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this, "onCreate Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate Second Activity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart Second Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart Second Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume Second Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause Second Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop Second Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy Second Activity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy Second Activity");
    }
}