package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recycleViewAdapter;
    ArrayList<Employee> employees=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.recyclerview);
        employees.add(new Employee("Adam","CEO"));
        employees.add(new Employee("Margaret","HR"));
        employees.add(new Employee("David","Web Designer"));
        employees.add(new Employee("Margaret","Project Manager"));
        //...
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recycleViewAdapter=new RecyclerViewAdapter(this);
        recycleViewAdapter.setItems(employees);
        recyclerView.setAdapter(recycleViewAdapter);

    }

}