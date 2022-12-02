package com.datastoring;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class ActivityB extends AppCompatActivity {

    TextView text;
    Button internalCache,externalCache,externalPrivate,externalPublic,previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        text=findViewById(R.id.dataText);
        internalCache=findViewById(R.id.LoadInternalCacheBtn);
        externalCache=findViewById(R.id.LoadExternalCacheBtn);
        externalPrivate=findViewById(R.id.LoadExternalPrivateBtn);
        externalPublic=findViewById(R.id.LoadExternalPublicBtn);
        previous=findViewById(R.id.btnprevious);

        previous.setOnClickListener(v->{startActivity(new Intent(ActivityB.this,ActivityA.class));});
        internalCache.setOnClickListener(v -> loadInternalCache(text));
        externalCache.setOnClickListener(v->loadExternalCache(text));
        externalPrivate.setOnClickListener(v->loadPrivateExternalFile(text));
        externalPublic.setOnClickListener(v->loadPublicExternalFile(text));



    }

    public void loadInternalCache(TextView data){

        File folder=getCacheDir();
        File myfile=new File(folder,"IntenalCache.txt");
        readData(myfile,data);

    }
    public void loadExternalCache(TextView data){
        File folder=getExternalCacheDir();
        File myfile=new File(folder,"ExternalCache.txt");
        readData(myfile,data);

    }

    private void loadPrivateExternalFile(TextView data) {
        File folder=getExternalFilesDir("Data Storage");
        File myfile=new File(folder,"PrivateExternal.txt");
        readData(myfile,data);

    }

    private void loadPublicExternalFile(TextView data) {
        File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File myfile=new File(folder,"PublicExternal.txt");
        readData(myfile,data);

    }


    public void readData(File file, TextView data)
    {
        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader = null;
        try {

            fileInputStream=new FileInputStream(file);

                 inputStreamReader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(inputStreamReader);


            // or BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuffer stringBuffer = new StringBuffer();
            String buff;

            while((buff=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(buff);
            }
            if(stringBuffer!=null  && stringBuffer.length()!=0)
            {
                data.setText(stringBuffer.toString());
            }
            else
            {
                Toast toast=Toast.makeText(getApplicationContext(),"No data was found", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            if(inputStreamReader!=null && fileInputStream!=null && bufferedReader!=null) {
                try {
                    fileInputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
            {
                Toast.makeText(getApplicationContext(),"No data was found",Toast.LENGTH_SHORT).show();

            }


        }

    }

}