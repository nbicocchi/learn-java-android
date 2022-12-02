package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SQLite.DataBase.DataBaseAdapter;

public class MainActivity extends AppCompatActivity {


    DataBaseAdapter sqlitehelper;
    EditText name,position;
    Button addButton,getButton,deleteButton,updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        sqlitehelper=new DataBaseAdapter(this);
        name =findViewById(R.id.edit_name);
        position=findViewById(R.id.edit_position);
        addButton=findViewById(R.id.add_btn);
        getButton=findViewById(R.id.viewData_btn);
        deleteButton=findViewById(R.id.remove_btn);
        updateButton=findViewById(R.id.edit_btn);
        addButton.setOnClickListener(v-> addUser());
        getButton.setOnClickListener(v->{
            {

                Intent intent=new Intent(MainActivity.this,GetDataActivity.class);
                startActivity(intent);
            }
        });
        deleteButton.setOnClickListener(v->{

            int row=sqlitehelper.deleteData(name.getText().toString());
            if(row>0)
            {
                Toast.makeText(this,name.getText().toString()+" deleted successfully",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this,name.getText().toString()+" not in database",Toast.LENGTH_SHORT).show();

            }


        });

        updateButton.setOnClickListener(v->{
            int row=sqlitehelper.updateData(name.getText().toString(),position.getText().toString());
            if(row>0)
            {
                Toast.makeText(this,name.getText().toString()+" updated successfully",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this,name.getText().toString()+" not in database",Toast.LENGTH_SHORT).show();

            }

        });


    }


    public void addUser()
    {
            String firstName = name.getText().toString();
            String pos = position.getText().toString();
            if (firstName.length()!=0  && pos.length()!=0) {
                long id = sqlitehelper.insertData(firstName,pos);
                Log.d("value receieved", String.valueOf(id));
                if (id < 0) {
                    Toast.makeText(getApplicationContext(), "User insert failed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), " insert success", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "failed: fill all field", Toast.LENGTH_SHORT).show();
            }

    }
}
