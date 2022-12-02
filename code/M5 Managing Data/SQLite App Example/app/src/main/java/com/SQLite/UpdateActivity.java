/*
package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SQLite.DataBase.DataBaseAdapter;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DataBaseAdapter sqlitehelper;
    EditText newPosition;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        sqlitehelper=new DataBaseAdapter(this);
        newName=findViewById(R.id.edit_name);
        newLastName=findViewById(R.id.edit_lastname);
        newPosition=findViewById(R.id.edit_position);
        update=findViewById(R.id.update_btnAct);
        update.setOnClickListener(v-> updateRecord());
    }

    private void updateRecord() {


        ArrayList<String> users=sqlitehelper.getUsers();
        if(users.contains())
        {
            sqlitehelper.updateData(newNam.getText().toString(),newUsername.getText().toString());
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this,oldUsername.getText().toString()+" not in database",Toast.LENGTH_SHORT).show();
        }
    }

}
*/
