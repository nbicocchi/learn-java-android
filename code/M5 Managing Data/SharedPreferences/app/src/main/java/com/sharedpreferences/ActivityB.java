package com.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class ActivityB extends AppCompatActivity {

    public final static  String DEFAULT="N/A";
    Button backBtn,load;
    //SharedPreferences sharedPreferences;
    EditText username,password;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        backBtn=findViewById(R.id.back_button);
        load=findViewById(R.id.load_button);
        relativeLayout=findViewById(R.id.relative_layoutB);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load(relativeLayout);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActivityB.this,ActivityA.class);
                startActivity(i);
            }
        });


    }

    public void load(RelativeLayout relativeLayout)
    {

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", MODE_PRIVATE);
        String name=sharedPreferences.getString("username",DEFAULT);
        String pass=sharedPreferences.getString("password",DEFAULT);


        if(name.equals(DEFAULT) || pass.equals(DEFAULT))
        {
            Snackbar.make(relativeLayout,"No data was found",Snackbar.LENGTH_SHORT).show();

        }
        else
        {
            Snackbar.make(relativeLayout,"Data loaded successfully",Snackbar.LENGTH_SHORT).show();
            username.setText(name);
            password.setText(pass);
        }


    }
}