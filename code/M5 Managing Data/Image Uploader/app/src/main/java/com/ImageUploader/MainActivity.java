package com.ImageUploader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;



public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask uploadTask;
    Button chooseButton,uploadButton;
    TextView showUpload;
    EditText editFileTextName;
    ImageView imageView;
    ProgressBar progressBar;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseButton=findViewById(R.id.button_choose_image);
        uploadButton=findViewById(R.id.button_upload);
        editFileTextName=findViewById(R.id.edit_text_file_name);
        imageView=findViewById(R.id.image_view);
        showUpload=findViewById(R.id.text_view_show_uploads);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));

        //we save image into folder named uploads
        storageReference= FirebaseStorage.getInstance().getReference("uploads");
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        chooseButton.setOnClickListener(v-> openFileChooser() );
        uploadButton.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                       if(uploadTask!=null && uploadTask.isInProgress())
                       {
                           Toast.makeText(MainActivity.this,"upload in Progress",Toast.LENGTH_SHORT).show();

                       }
                       else
                       {
                           uploadImage();
                       }
                   }
               });



        showUpload.setOnClickListener(v->
                showImage());
    }

    public void showImage() {
        Intent intent=new Intent(this,ImagesActivity.class);
        startActivity(intent);
    }


    private String getFileExtension( Uri uri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage() {

        if(imageUri!=null)
        {
            /***
             * In order to have unique name and don't override other image, we will upload image
             * naming with current time.
             * @child: add more info to string root "uploads/..." ***/
            StorageReference sr= storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

               uploadTask=sr.putFile(imageUri)
                       .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    /***
                     * @postDelay if image uploads with success we want after set progress bar to zero.
                     * but if we perform the reset immediately the user will not see the progress bar, so we have to introduce a delay with a Handler ***/
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },500);
                    Toast.makeText(MainActivity.this,"Upload successful",Toast.LENGTH_SHORT).show();

                    /**Create a entry into database***/
                    Task<Uri> task=taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageUri=uri;
                            Upload upload= new Upload(editFileTextName.getText().toString().trim(),imageUri.toString());
                            String uploadId=databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);
                        }
                    });


                }
                /**add a failure listener to let the user know if something goes wrong */
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0* snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });

        }
        else
        {
            Toast.makeText(this,"no file selected",Toast.LENGTH_SHORT).show();
        }

    }

    private void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            //Uri of image select
            imageUri =data.getData();
            imageView.setImageURI(imageUri);
        }

    }
}