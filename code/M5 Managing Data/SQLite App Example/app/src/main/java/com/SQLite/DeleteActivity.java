/*
package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SQLite.DataBase.DataBaseAdapter;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    DataBaseAdapter sqlitehelper;
    Button delete,showData;
    EditText deleteUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        sqlitehelper=new DataBaseAdapter(this);
        delete=findViewById(R.id.delete_btn);
        deleteUsername=findViewById(R.id.edit_delete_username);
        showData=findViewById(R.id.show_button);
        showData.setOnClickListener(v->{
            Intent intent=new Intent(DeleteActivity.this,GetDataActivity.class);
            startActivity(intent);
        });
        delete.setOnClickListener(v-> delete());
    }




    private void delete() {
        sqlitehelper.getName();
        String deleteUsr=deleteUsername.getText().toString();
        if(deleteUsr.length()!=0) {
            ArrayList<String> users=sqlitehelper.getName();
            if(users.contains(deleteUsr))
            {
                sqlitehelper.deleteData(deleteUsername.getText().toString());
                Toast.makeText(this,deleteUsr+" deleted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,deleteUsr+" not in database",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
*/
