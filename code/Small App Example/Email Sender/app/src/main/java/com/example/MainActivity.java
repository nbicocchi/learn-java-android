package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText rec,subject,message;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec=findViewById(R.id.textTo);
        subject=findViewById(R.id.textSubject);
        message=findViewById(R.id.textMessage);
        btn=findViewById(R.id.buttonSend);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    String[] receivers=String.valueOf(rec.getText()).split(" ");
                    intent.putExtra(Intent.EXTRA_EMAIL,receivers);
                    intent.putExtra(Intent.EXTRA_SUBJECT,String.valueOf(subject.getText()));
                    intent.putExtra(Intent.EXTRA_TEXT,message.getText());
                    //specify MIME type for Email
                    intent.setType("message/rfc822");
                    //Intent chooser=Intent.createChooser(intent,"Send Email");
                    //startActivity(chooser);
                    startActivity(intent);
                }
        });

    }
}