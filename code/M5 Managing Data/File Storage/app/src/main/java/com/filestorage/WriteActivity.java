package com.filestorage;

import static java.nio.file.StandardOpenOption.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.StandardOpenOption;

public class WriteActivity extends AppCompatActivity {



    Button submit,open;
    private  EditText username,position;
    private LinearLayout layout;
    private File file=null;
    boolean insert=false;
    private String path="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);
        username=findViewById(R.id.edit_name);
        open=findViewById(R.id.open_btn);
        position=findViewById(R.id.edit_position);
        submit=findViewById(R.id.submit_btn);
        layout=findViewById(R.id.write_layout);

        submit.setOnClickListener(v->{
            saveInternalData(username,position);
        });


        open.setOnClickListener(v -> {


                   Intent i = new Intent(WriteActivity.this, ReadActivity.class);
                  /* i.putExtra("Path", path);*/
                   startActivity(i);

        });
    }

    /**Saving on internal storage***/
    private boolean saveInternalData(EditText username, EditText position) {

        boolean insert=false;
        String us=username.getText().toString();
        String pos=position.getText().toString();
        if(us.length()!=0 && pos.length()!=0)
        {

            String data=us+" "+pos+"\n";
            File internalFile=getFilesDir();
            String filename="InternalStorage.txt";
            insert=writeData(internalFile,filename,data);


        }
        else{
            username.setError("mandatory");
            position.setError("mandatory");
        }

        return insert;
    }


    /**Saving on external storage***/
    private boolean saveExternalData(EditText username, EditText password) {

        boolean insert=false;
        String us=username.getText().toString();
        String pass=password.getText().toString();
        if(us.length()!=0 && pass.length()!=0)
        {
            String data=us+" "+pass+"\n";
            File externalFile=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            Log.d("Path",externalFile.toString());
            String filename="ExternalStorage.txt";
            insert=writeData(externalFile,filename,data);



        }
        else{
            username.setError("mandatory");
            password.setError("mandatory");
        }
        return insert;

    }


    private boolean writeData(File folderPath, String filename, String data) {


        FileOutputStream fileOutputStream=null;

        try {

            File file=new File(folderPath,filename);
            fileOutputStream=new FileOutputStream(file,true);
            fileOutputStream.write(data.getBytes());
            //olderPath.toString().replace("/0","")+"/"+filename
            setPath(folderPath+"/"+filename);

            String showPath=getPath().replace("/0","");
            Snackbar.make(layout, "Data saved Successfully into: " + showPath, Toast.LENGTH_SHORT).setTextColor( Color.GREEN).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                return true;

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return false;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



}