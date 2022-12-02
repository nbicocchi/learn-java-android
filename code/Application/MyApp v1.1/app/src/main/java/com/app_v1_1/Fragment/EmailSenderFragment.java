package com.app_v1_1.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.app_v1_1.R;


public class EmailSenderFragment extends Fragment {


    EditText rec,subject,message,title;

    ImageView btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_email_sender, container, false);

        rec=view.findViewById(R.id.textTo);
        subject=view.findViewById(R.id.textSubject);
        message=view.findViewById(R.id.textMessage);
        btn=view.findViewById(R.id.btn_send);

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
                startActivity(intent);
            }
        });

        return  view;



    }
}