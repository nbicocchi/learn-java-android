package com.example.togglebutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener {

    ToggleButton toggleButton;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton=findViewById(R.id.toggleButton);
        constraintLayout=findViewById(R.id.layout);

        toggleButton.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked)
        {
            constraintLayout.setBackgroundColor(Color.BLACK);

        }
        else
        {
            constraintLayout.setBackgroundColor(Color.WHITE);
        }
    }
}