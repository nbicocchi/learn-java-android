package com.example_slide;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private TextView welcome;
    private EditText email,password;
    private Button login,singup;
    private Intent i;
    private RelativeLayout rl;
    private FakeDb fakeDb;
    private HashMap<String, String> users=new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users.put("nicola.bicocchi@unimore.it","nicola");
        users.put("267705@studenti.unimore.it","stefano");
        fakeDb=FakeDb.create(users);
        setContentView(R.layout.login_layout);
        rl=findViewById(R.id.relative_layout);
        //giving custom font to TextView
        welcome=findViewById(R.id.Welcome);
        Typeface myfont=Typeface.createFromAsset(getAssets(),"font/poppins/Poppins-Black.otf");
        welcome.setTypeface(myfont);
        email=findViewById(R.id.email_edit);
        password=findViewById(R.id.pass_edit);
        checkBox=findViewById(R.id.checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                   Snackbar snackbar=Snackbar.make(rl,"remember user choice to save data",Snackbar.LENGTH_SHORT).setAction("Undo",new View.OnClickListener(){

                       @Override
                       public void onClick(View v) {
                           Snackbar.make(rl,"now the app will remember the credential",Snackbar.LENGTH_SHORT).setTextColor(Color.WHITE).setBackgroundTint(Color.BLUE).show();
                       }
                   });
                   snackbar.setActionTextColor(Color.YELLOW);
                   snackbar.setBackgroundTint(Color.BLUE);
                   snackbar.setTextColor(Color.WHITE);
                   snackbar.show();
                    //Log.d("Debug","Remember credential ");

                }

            }
        });

        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(fakeDb.verifyCredential(email.getText().toString(),password.getText().toString())) {
                    i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("username",email.getText().toString());
                    startActivity(i);

                }else
                {
                    Toast.makeText(LoginActivity.this,"Incorrect Credential",Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        singup=findViewById(R.id.singup);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });





    }
}
