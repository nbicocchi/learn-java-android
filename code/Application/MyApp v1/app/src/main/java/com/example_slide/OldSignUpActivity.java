/*
package com.example_slide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example_slide.Watcher.AgeWatcher;
import com.example_slide.Watcher.DateWatcher;
import com.example_slide.Watcher.StringWatcher;

import java.util.ArrayList;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity  {

    private AgeWatcher aw=new AgeWatcher(this);
    private StringWatcher sw=new StringWatcher(this);
    private DateWatcher dw=new DateWatcher(this);
    private EditText email,password;

    private Button button;
    private FakeDb fakeDb=FakeDb.getInstance();
    private ArrayList<View> views;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        //TextWatcher
        ArrayList<EditText> toCheck= new ArrayList<EditText>();
        toCheck.add(0,(EditText) findViewById(R.id.editName));
        toCheck.add(1,(EditText) findViewById(R.id.editLastName));
        toCheck.add(2,(EditText) findViewById(R.id.editAge));
        toCheck.add(3,(EditText) findViewById(R.id.editDate));
        toCheck.get(0).addTextChangedListener(sw);
        toCheck.get(1).addTextChangedListener(sw);
        toCheck.get(2).addTextChangedListener(aw);
        toCheck.get(3).addTextChangedListener(dw);
        email=findViewById(R.id.editMail);
        password=findViewById(R.id.editPassword);
        button=findViewById(R.id.next_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeDb.addUser(email.getText().toString(),password.getText().toString());
                for (Map.Entry<String,String> entry : fakeDb.getUsers().entrySet()) {
                    System.out.println("Username = " + entry.getKey() + ", Password = " + entry.getValue());
                }
                Intent i=new Intent(SignUpActivity.this, HomeActivity.class);
                i.putExtra("name",email.getText().toString());
                startActivity(i);

            }
        });









    }




}*/
