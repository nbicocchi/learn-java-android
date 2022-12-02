package com.staffApp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateDialog extends AppCompatDialogFragment {

    private EditText editName, editPosition;
    private UpdateDialogListener updateDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.update_dialog,null);
        builder.setView(view)
                .setNegativeButton("cancel", (dialog, which) -> {

                }).setPositiveButton("Add", (dialog, which) -> {


                    String name=editName.getText().toString();
                    String position=editPosition.getText().toString();
                    if(name.length()!=0 && position.length()!=0) {
                        updateDialogListener.updateData(name, position);
                    }
                    else
                    {
                        editName.setError("Empty field");
                        editPosition.setError("Empty field");

                    }

                });
        editName=view.findViewById(R.id.update_name_dialog);
        editPosition=view.findViewById(R.id.update_position_dialog);
        return builder.create();

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            updateDialogListener= (UpdateDialog.UpdateDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+"must implement InsertDialogListener");
        }
    }


    public EditText getEditName() {
        return editName;
    }

    public void setEditName(EditText editName) {
        this.editName = editName;
    }

    public EditText getEditPosition() {
        return editPosition;
    }

    public void setEditPosition(EditText editPosition) {
        this.editPosition = editPosition;
    }


    public interface  UpdateDialogListener{

        void updateData(String username,String position);
    }
}
