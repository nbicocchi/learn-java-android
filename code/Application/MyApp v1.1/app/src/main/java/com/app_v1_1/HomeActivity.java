package com.app_v1_1;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app_v1_1.Fragment.EmailSenderFragment;

public class HomeActivity extends AppCompatActivity {


        ImageButton mail;
        ImageButton home;
        TextView title;
        EmailSenderFragment fragmentEmailSender=new EmailSenderFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_layout);
        //change font Example
     /*   HomeFragment homeFragment=new HomeFragment(getIntent().getStringExtra("username"));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
        mail=findViewById(R.id.mail_button);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().remove(homeFragment);


                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,fragmentEmailSender).commit();
            }
        });

        home=findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            }
        });
*/

    }
}