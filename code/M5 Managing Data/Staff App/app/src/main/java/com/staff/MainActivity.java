package com.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public final int MODIFIED=2;
    private  EditText editName,editPosition;
    private Button submit,update,remove,open;
    private String nameModified,positionModified;
    DataBaseAdapter dataBaseAdapter;
    Employee emp_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName=findViewById(R.id.edit_name);
        editPosition=findViewById(R.id.edit_position);
        open=findViewById(R.id.open_btn);
        submit=findViewById(R.id.submit_btn);
/*        update=findViewById(R.id.update_btn);
        remove=findViewById(R.id.remove_btn);*/

        dataBaseAdapter=new DataBaseAdapter();
        open.setOnClickListener(v-> {
            Intent intent=new Intent(MainActivity.this, RecycleViewActivity.class);
            startActivity(intent);
        });

       /* nameModified=getIntent().getStringExtra("EDIT_NAME");
        positionModified=getIntent().getStringExtra("EDIT_POSITION");*/

        emp_edit=(Employee) getIntent().getSerializableExtra("EDIT");

        if(emp_edit!=null)
        {
           submit.setText("Update");
           editName.setText(emp_edit.getName());
           editPosition.setText(emp_edit.getPosition());
           open.setVisibility(View.GONE);
        }
        else
        {
            submit.setText("Submit");
            open.setVisibility(View.VISIBLE);
        }


        submit.setOnClickListener(v-> {
            if(emp_edit==null) {

                addEmployee();
            }
            else
            {
                updateEmployee();
            }

        } );


        /*update.setOnClickListener(v->updateEmployee());
        remove.setOnClickListener(v-> removeEmployee());*/


    }

    /*private void removeEmployee() {

        dataBaseAdapter.remove("-NEvG6S4XE9A65SDsVv5")
                .addOnSuccessListener(suc-> Toast.makeText(this,"Record Removed",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());


    }*/



    private void addEmployee() {
      Employee employee=new Employee(editName.getText().toString(),editPosition.getText().toString());
          dataBaseAdapter.add(employee)
                  .addOnSuccessListener(suc -> Toast.makeText(this, "Record inserted", Toast.LENGTH_SHORT).show())
                  .addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());

    }

    private void updateEmployee() {

        HashMap<String,Object> hashMap; hashMap=new HashMap<>();
        hashMap.put("name",editName.getText().toString());
        hashMap.put("position",editPosition.getText().toString());
        dataBaseAdapter.update(emp_edit.getKey(),hashMap).addOnSuccessListener(suc-> {
            Toast.makeText(this,"Record Updated",Toast.LENGTH_SHORT).show();
                    this.setResult(MODIFIED);
                    finish();
            }).addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());

    }



}