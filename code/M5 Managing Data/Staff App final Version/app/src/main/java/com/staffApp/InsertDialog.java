package com.staffApp;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Models.Employee;

public class InsertDialog extends android.app.Dialog {

    private EditText editName,editPosition,editEmail,editPhone;
    private TextView addButton;
    private android.app.Dialog dialog;
    boolean addResult;
    DataBaseAdapter dataBaseAdapter;

    public boolean getAddResult() {
        return addResult;
    }

    private void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }

    public InsertDialog(@NonNull Context context) {
        super(context);
        this.dialog=new android.app.Dialog(getContext());
        dataBaseAdapter=new DataBaseAdapter();
        dialog.setContentView(R.layout.insert_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.curve_shape);
        editName=dialog.findViewById(R.id.add_name_dialog);
        editPosition=dialog.findViewById(R.id.add_position_dialog);
        editEmail=dialog.findViewById(R.id.add_email_dialog);
        editPhone=dialog.findViewById(R.id.add_phone_dialog);
        addButton=dialog.findViewById(R.id.dialog_add_btn);
        addButton.setOnClickListener(v->{ addOnClickListener();
        });
        dialog.show();

    }

    /***
     * add onClickListener to addbutton, when user try to insert new data
     */
    public void addOnClickListener() {


            addButton.setOnClickListener(v -> {

                String name=editName.getText().toString();
                String position=editPosition.getText().toString();
                if(name.length()!=0 && position.length()!=0) {
                    boolean ris=checkInput();
                    if(ris==true) {

                        dialog.dismiss();

                        insertData();


                    }
                    else
                    {
                        editName.setError("Invalid Input");
                        editPosition.setError("Invalid Input");
                    }
                }
                else
                {
                    editName.setError("Empty field");
                    editPosition.setError("Empty field");

                }



            });


    }

    private boolean checkInput() {
        return true;
    }


    public void insertData() {

        Employee employee=new Employee();
        employee.setName(editName.getText().toString());
        employee.setPosition(editPosition.getText().toString());
        employee.setEmail(editEmail.getText().toString());
        employee.setPhone(editPhone.getText().toString());
        dataBaseAdapter.add(employee).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Insert Data Successfully",Toast.LENGTH_SHORT);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Insert Failed",Toast.LENGTH_SHORT);
            }
        });


    }



}
