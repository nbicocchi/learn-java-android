package com.example.listview;


import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] employee = {
            "Andrew peterson",
            "Bob Keen",
            "Carl Robb",
            "Elen Whitmore",
            "Bet Bush"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);

        final ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.list_item, R.id.textView, employee);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            String value = "Hi i'm " + adapter.getItem(position);
            /* Display the Toast */
            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
        });
    }
}