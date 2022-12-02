package com.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.text.BreakIterator;

public class ActivityA extends AppCompatActivity {



    Button buttonB,savebtn;
    RelativeLayout relativeLayout;
    private  EditText username,password;
    private SharedPreferences sharedPreferences;
    boolean insert=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        buttonB=findViewById(R.id.buttonB);
        savebtn=findViewById(R.id.save_button);
        relativeLayout=findViewById(R.id.relative_layoutA);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences= getSharedPreferences("MyData", MODE_PRIVATE);
               saveData(sharedPreferences);

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(insert==true) {
                    Intent i = new Intent(ActivityA.this, ActivityB.class);
                    startActivity(i);
                }
                else
                {
                    Snackbar.make(relativeLayout,"You must first insert data",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveData(SharedPreferences sharedPreferences){

        if(username.getText().toString().length()!=0 &&  password.getText().toString().length()!=0) {

            //allow us to edit file
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            Log.d("debug","saving data...");
            editor.commit();
            insert=true;
            Snackbar.make(relativeLayout, "Save Data successfully", Snackbar.LENGTH_SHORT).show();
            return;
        }
        else
        {
            username.setError("mandatory");
            password.setError("mandatory");
        }

    }






}