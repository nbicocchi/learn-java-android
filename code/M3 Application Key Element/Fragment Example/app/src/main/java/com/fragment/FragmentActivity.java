package com.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {

    Fragment1 f1=new Fragment1();
    Fragment2 f2=new Fragment2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //set default fragment
        Button bt1=findViewById(R.id.btnFragment1);
        Button bt2=findViewById(R.id.btnFragment2);


        bt1.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, f1).commit());


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, f2).commit();

            }

        });
}



}
