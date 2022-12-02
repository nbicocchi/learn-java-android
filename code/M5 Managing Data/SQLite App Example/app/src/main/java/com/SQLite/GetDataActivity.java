package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.SQLite.DataBase.DataBaseAdapter;

public class GetDataActivity extends AppCompatActivity {

    DataBaseAdapter sqlitehelper;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_layout);
        result=findViewById(R.id.textResult);
        sqlitehelper=new DataBaseAdapter(this);
        getUser();
    }

    private void getUser() {

        String data=sqlitehelper.getAllData();
        result.setText(data);


    }
}