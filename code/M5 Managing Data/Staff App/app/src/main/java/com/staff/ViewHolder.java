package com.staff;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView name,position,option;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.textViewName);
        position=itemView.findViewById(R.id.textViewPosition);
        option=itemView.findViewById(R.id.option);

    }
}
