package com.app_v1_1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app_v1_1.Watchers.AgeWatcher;
import com.app_v1_1.Watchers.DateWatcher;
import com.app_v1_1.Watchers.EmailWatcher;
import com.app_v1_1.Watchers.StringWatcher;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity  {



    private Button button;
    private FakeDb fakeDb=FakeDb.getInstance();
    private ArrayList<EditText> editable=new ArrayList<EditText>();
    private AgeWatcher aw;
    private StringWatcher sw;
    private DateWatcher dw;
    private TextView title;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        button=findViewById(R.id.next_button);
        title=findViewById(R.id.title);
        Typeface myfont=Typeface.createFromAsset(getAssets(),"font/poppins/Poppins-Black.otf");
        title.setTypeface(myfont);

        editable.add(0,(EditText) findViewById(R.id.editName));
        editable.add(1,(EditText)findViewById(R.id.editLastName));
        editable.add(2,(EditText)findViewById(R.id.editMail));
        editable.add(3,(EditText)findViewById(R.id.editDate));
        editable.add(4,(EditText)findViewById(R.id.editAge));
        editable.add(5,(EditText) findViewById(R.id.editPassword));


        //Adding textWatcher
        editable.get(0).addTextChangedListener(new StringWatcher(SignUpActivity.this,editable.get(0)));
        editable.get(1).addTextChangedListener(new StringWatcher(SignUpActivity.this,editable.get(1)));
        editable.get(2).addTextChangedListener(new EmailWatcher(SignUpActivity.this,editable.get(2)));
        editable.get(3).addTextChangedListener(new DateWatcher(SignUpActivity.this,editable.get(3)));
        editable.get(4).addTextChangedListener(new AgeWatcher(SignUpActivity.this, editable.get(4)));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(editable)) {


                   if( isUserAlreadyRegistered(editable.get(2).getText().toString())==false)
                    {
                        fakeDb.addUser(editable.get(2).getText().toString(), editable.get(5).getText().toString());
                        for (Map.Entry<String, String> entry : fakeDb.getUsers().entrySet()) {
                            System.out.println("Username = " + entry.getKey() + ", Password = " + entry.getValue());
                        }
                        Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                        i.putExtra("username", editable.get(2).getText().toString());
                        startActivity(i);
                    }
                    else
                    {
                       RelativeLayout rl=findViewById(R.id.singup_layout);
                        Snackbar.make(rl,"User already registred",Snackbar.LENGTH_SHORT).show();

                        //Toast.makeText(SignUpActivity2.this,"User already registry",Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
    }




    private  boolean isEmpty(ArrayList<EditText> editable) {

        boolean ris=true;
        for(EditText e: editable)
        {
            if(e.getText().toString().length()==0)
            {
                e.setError(getResources().getString(R.string.madatory));
                ris=false;
            }
        }
        if(ris)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    private boolean isUserAlreadyRegistered(String username) {



        for(String s: fakeDb.getUsers().keySet())
        {
            if(s.equals(username))
            {
                return true;
            }
        }


        return false;
    }


}