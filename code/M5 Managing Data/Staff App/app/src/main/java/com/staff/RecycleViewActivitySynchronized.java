package com.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class RecycleViewActivitySynchronized extends AppCompatActivity {

    SwipeRefreshLayout swipeRefleshLayout;
    RecyclerView recyclerView;
    DataBaseAdapter dataBaseAdapter;
    boolean isLoading = false;
    FirebaseRecyclerAdapter adapter;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_acitvity);
        swipeRefleshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        dataBaseAdapter = new DataBaseAdapter();
        FirebaseRecyclerOptions<Employee> option = new FirebaseRecyclerOptions.Builder<Employee>()
                .setQuery(dataBaseAdapter.get(), new SnapshotParser<Employee>() {
                    @NonNull
                    @Override
                    public Employee parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Employee employee = snapshot.getValue(Employee.class);
                        employee.setKey(snapshot.getKey());
                        return employee;
                    }
                }).build();
        adapter = new FirebaseRecyclerAdapter(option) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {

                ViewHolder viewHolder = (ViewHolder) holder;
                Employee employee = (Employee) model;
                viewHolder.name.setText(employee.getName());
                viewHolder.position.setText(employee.getPosition());
                viewHolder.option.setOnClickListener(v -> {

                    PopupMenu popupMenu = new PopupMenu(RecycleViewActivitySynchronized.this, viewHolder.option);
                    popupMenu.inflate(R.menu.option_menu);

                    popupMenu.setOnMenuItemClickListener(item ->
                    {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                Intent intent = new Intent(RecycleViewActivitySynchronized.this, MainActivity.class);
                                intent.putExtra("EDIT", employee);
                                startActivity(intent);
                                break;
                            case R.id.menu_remove:
                                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter();
                                dataBaseAdapter.remove(employee.getKey())
                                        .addOnSuccessListener(suc -> {
                                                    Toast.makeText(RecycleViewActivitySynchronized.this, "Record Removed", Toast.LENGTH_SHORT).show();
                                                }
                                        )
                                        .addOnFailureListener(er -> Toast.makeText(RecycleViewActivitySynchronized.this, er.getMessage(), Toast.LENGTH_SHORT).show());
                                break;
                        }
                        return false;

                    });
                    popupMenu.show();
                });

            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(RecycleViewActivitySynchronized.this).inflate(R.layout.layout_item, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                Toast.makeText(RecycleViewActivitySynchronized.this, "Data Changed", Toast.LENGTH_SHORT).show();
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

}