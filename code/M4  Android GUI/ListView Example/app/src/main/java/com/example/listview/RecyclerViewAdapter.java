package com.example.listview;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Employee> element=new ArrayList<>();

    public RecyclerViewAdapter(Context context){
        this.context=context;
    }

    public void setItems(ArrayList<Employee> subElement) {

        element.addAll(subElement);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder) holder;
        Employee employee=element.get(position);
        viewHolder.name.setText(employee.getName());
        viewHolder.position.setText(employee.getPosition());
    }

    public ArrayList<Employee> getElement() {
        return element;
    }

    public void setElement(ArrayList<Employee> element) {
        this.element = element;
    }

    @Override
    public int getItemCount() {
        return element.size();
    }
}
