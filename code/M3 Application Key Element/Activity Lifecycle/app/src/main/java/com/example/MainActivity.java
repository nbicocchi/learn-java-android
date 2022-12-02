package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast=Toast.makeText(this, "onCreate MainActivity", Toast.LENGTH_SHORT);
        toast.show();
        Log.d(TAG, "onCreate MainActivity");

    }

    public void onClick(View view)
    {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {

        Toast.makeText(this, "onStart MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart MainActivity");

        super.onStart();
    }
    @Override
    protected void onResume() {

        Toast.makeText(this, "onResume MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume MainActivity");
        super.onResume();

    }

    @Override
    protected void onPause() {

        Toast.makeText(this, "onPause MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause MainActivity");

        super.onPause();
    }

    @Override
    protected void onStop() {

        Toast.makeText(this, "onStop MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop MainActivity");

        super.onStop();
    }

    @Override
    protected void onRestart() {

        Toast.makeText(this, "onRestart MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart MainActivity");

        super.onRestart();
    }

    protected void onDestroy() {

        Toast.makeText(this, "onDestroy MainActivity", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy MainActivity");

        super.onDestroy();
    }
}