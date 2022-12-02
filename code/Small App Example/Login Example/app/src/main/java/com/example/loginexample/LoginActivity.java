package com.example.loginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.FakeDb;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        EditText text_user= findViewById(R.id.usernameText);
        EditText text_password= findViewById(R.id.passwordText);
        Button home=findViewById(R.id.loginButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FakeDb fakeDb=new FakeDb();

                String username=String.valueOf(text_user.getText());
                String password=String.valueOf(text_password.getText());

                if(username.equals(fakeDb.getUsername()) || username.equals(fakeDb.getEmail())  && password.equals(fakeDb.getPassword())) {

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                    intent.putExtra("username", fakeDb.getUsername());
                    startActivity(intent);
                }
            }
        });
    }



}