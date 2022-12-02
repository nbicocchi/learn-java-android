package com.staff;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Employee> element=new ArrayList<>();

    public RecycleViewAdapter(Context context){
        this.context=context;
    }
    
    public void setItems(ArrayList<Employee> subElement) {
        Log.d("oggetti settati",String.valueOf(subElement.size()));
        element.addAll(subElement);
        
    }

    public void clearItems(){
        element.clear();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder) holder;
        Employee employee=element.get(position);
        viewHolder.name.setText(employee.getName());
        viewHolder.position.setText(employee.getPosition());
        viewHolder.option.setOnClickListener(v->{

            PopupMenu popupMenu=new PopupMenu(context,viewHolder.option);
            popupMenu.inflate(R.menu.option_menu);

            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("EDIT",employee);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DataBaseAdapter dataBaseAdapter=new DataBaseAdapter();
                        dataBaseAdapter.remove(employee.getKey())
                                .addOnSuccessListener(suc-> {
                                    Toast.makeText(context,"Record Removed",Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                    element.remove(position);
                                }
                                )
                                .addOnFailureListener(er -> Toast.makeText(context, er.getMessage(), Toast.LENGTH_SHORT).show());
                        break;
                }
                return false;

            });
            popupMenu.show();
        });


    }

    @Override
    public int getItemCount() {
        return element.size();
    }
}
