package com.ImageUploader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private FirebaseStorage firebaseStorage;
    private ValueEventListener dbListener;
    private DatabaseReference databaseReference;
    private List<Upload> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager set vertical orientation by default
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<Upload>();
        imageAdapter=new ImageAdapter(ImagesActivity.this,uploadList);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(ImagesActivity.this);
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        firebaseStorage=FirebaseStorage.getInstance();
        dbListener=databaseReference.addValueEventListener(new ValueEventListener() {
            /***
             * @onDataChange: Called when something change into Database ex: add operation
             * @param snapshot  list represent data of database
             * @getChildren will return a reference to the immediate node inside the database
             * ***/
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                uploadList.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    //retrieve value from database and convert into upload object
                    Upload upload=dataSnapshot.getValue(Upload.class);
                    //add id key
                    upload.setKey(dataSnapshot.getKey());
                    uploadList.add(upload);
                }

                imageAdapter.notifyDataSetChanged();

            }

            /***Called when we don't have the permission to read on Database***/
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ImagesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"item click"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClick(int position) {

        Toast.makeText(this,"edit click"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

        Upload deletedItem=uploadList.get(position);
        String key=deletedItem.getKey();
        StorageReference imageRef= firebaseStorage.getReferenceFromUrl(deletedItem.getImageURL());
        imageRef.delete().addOnSuccessListener(unused -> {
            databaseReference.child(key).removeValue();
            Toast.makeText(this,"Item deleted",Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(dbListener);

    }
}