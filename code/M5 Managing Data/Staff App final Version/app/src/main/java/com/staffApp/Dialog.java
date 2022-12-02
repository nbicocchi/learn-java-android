package com.staffApp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    private EditText editName, editPosition;
    /*private InsertDialogListener insertDialogListener;*/
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.insert_dialog,null);
        builder.setView(view)
                .setNegativeButton("cancel", (dialog, which) -> {

                }).setPositiveButton("Add", (dialog, which) -> {


                    String name=editName.getText().toString();
                    String position=editPosition.getText().toString();
                    if(name.length()!=0 && position.length()!=0) {
                        /*insertDialogListener.insertData(name, position);*/
                    }
                    else
                    {
                        editName.setError("Empty field");
                        editPosition.setError("Empty field");

                    }

                });
        editName=view.findViewById(R.id.add_name_dialog);
        editPosition=view.findViewById(R.id.add_position_dialog);
        return builder.create();

    }

/*

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            insertDialogListener= (AddDialog.InsertDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+"must implement InsertDialogListener");
        }
    }
*/

/*
    public interface  InsertDialogListener{

        void insertData(String username,String position);


    }*/
}
