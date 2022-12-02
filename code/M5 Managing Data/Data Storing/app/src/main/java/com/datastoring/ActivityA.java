package com.datastoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityA extends AppCompatActivity {

    EditText text;
    Button internalCache,externalCache,externalPrivate,externalPublic,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        text=findViewById(R.id.dataText);
        internalCache=findViewById(R.id.InternalCacheBtn);
        externalCache=findViewById(R.id.ExternalCacheBtn);
        externalPrivate=findViewById(R.id.ExternalPrivateBtn);
        externalPublic=findViewById(R.id.ExternalPublicBtn);
        next=findViewById(R.id.BtnNext);


        //set click Listener
        internalCache.setOnClickListener(v -> saveInternalCache());
        externalCache.setOnClickListener(v->saveExternalCache());
        externalPrivate.setOnClickListener(v->savePrivateExternalFile());
        externalPublic.setOnClickListener(v->savePublicExternalFile());
        next.setOnClickListener(v ->{ startActivity(new Intent(ActivityA.this,ActivityB.class));});


    }

    public void saveInternalCache()
    {
        File folder=getCacheDir();
        File myfile= new File(folder,"IntenalCache.txt");
        writeData(myfile,folder,text.getText().toString());


    }

    public void saveExternalCache()
    {
        File folder=getExternalCacheDir();
        File myfile= new File(folder,"ExternalCache.txt");
        writeData(myfile,folder,text.getText().toString());

    }

    public void savePrivateExternalFile()
    {
        //create a folder named "Data Storage in our device"
        File folder=getExternalFilesDir("Data Storage");
        File myfile= new File(folder,"PrivateExternal.txt");
        writeData(myfile,folder,text.getText().toString());

    }

    public void savePublicExternalFile()
    {
        //create a folder named "Data Storage in our device"
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File myfile= new File(folder,"PublicExternal.txt");
        writeData(myfile,folder,text.getText().toString());

    }


    private void writeData(File file,File path, String data){
        FileOutputStream fileOutputStream=null;
        try {

            fileOutputStream=new FileOutputStream(file);
            data+='\n';
            fileOutputStream.write(data.getBytes());
            View coordinator= findViewById(R.id.coordinator);
            Snackbar snackbar= Snackbar.make(coordinator,"data successfully saved:"+path.toString()+"/"+file.toString(),Snackbar.LENGTH_LONG);
            snackbar.setTextMaxLines(3);
            snackbar.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}