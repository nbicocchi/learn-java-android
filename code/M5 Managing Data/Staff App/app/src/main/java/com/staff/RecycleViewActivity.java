package com.staff;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.firebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefleshLayout;
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;
    DataBaseAdapter dataBaseAdapter;
    boolean isLoading=false;
   // String key=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_acitvity);
        swipeRefleshLayout=findViewById(R.id.swipe);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recycleViewAdapter=new RecycleViewAdapter(this);
        recyclerView.setAdapter(recycleViewAdapter);
        dataBaseAdapter=new DataBaseAdapter();
        loadData();
/*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
                int itemNum=linearLayoutManager.getItemCount();
                int lastVisible=linearLayoutManager.findLastVisibleItemPosition();
                if(itemNum <lastVisible+3)
                {
                    if(!isLoading)
                    {
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });
*/
    }

    private void loadData() {
            swipeRefleshLayout.setRefreshing(true);

            dataBaseAdapter.get().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Employee> employeeArrayList=new ArrayList<>();
                        //return list of node
                        for(DataSnapshot ds:snapshot.getChildren())
                        {

                            Employee employee=ds.getValue(Employee.class);
                            employee.setKey(ds.getKey());
                            employeeArrayList.add(employee);
                           // key=ds.getKey();
                        }
                        Log.d("lista employee", String.valueOf(employeeArrayList.size()));
                        recycleViewAdapter.clearItems();

                        recycleViewAdapter.setItems(employeeArrayList);
                        recycleViewAdapter.notifyDataSetChanged();
                        isLoading=false;
                        swipeRefleshLayout.setRefreshing(false);
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    swipeRefleshLayout.setRefreshing(false);
                }
            });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("entrato","entrato in onResume ");
        loadData();
    }

}