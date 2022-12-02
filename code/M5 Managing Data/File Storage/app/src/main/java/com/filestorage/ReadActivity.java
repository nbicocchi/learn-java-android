package com.filestorage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadActivity extends AppCompatActivity {


    /*private  final String INTERNAL_PATH=;*/
    Button backBtn,loadButton;
    TextView result;
    String path;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        result=findViewById(R.id.textResult);
        /*backBtn=findViewById(R.id.back_button);*/
        loadButton=findViewById(R.id.load_button);
        layout =findViewById(R.id.load_layout);
        /***Internal Storage path**/

        path=getFilesDir().toString()+"/InternalStorage.txt";
        /***External Storage path**/
        /*path=getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/ExternalStorage.txt";*/


       loadButton.setOnClickListener(v ->
        {
            /*Intent intent=getIntent();
            //String data=intent.getStringExtra("Path");*/
                load(path);

        });



       /* backBtn.setOnClickListener(v -> {
            Intent i=new Intent(ReadActivity.this, WriteActivity.class);
            startActivity(i);
        });

*/
    }

    public void load(String path)
    {
        FileInputStream file=null;
        try {

            file=new FileInputStream(path);
            InputStreamReader inputStreamReader=new InputStreamReader(file);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer=new StringBuffer();
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line+"\n");
            }
            bufferedReader.close();
            result.setText(stringBuffer.toString());

                String showPath=path.replace("/0","");
                Snackbar.make(layout,"Data loaded successfully from: "+showPath,Snackbar.LENGTH_SHORT).setTextColor( Color.GREEN).show();


        } catch (FileNotFoundException e) {
            //if file doesn't exists or we can't read beacuse we don't have the permission
            Snackbar.make(layout,"File not foud",Snackbar.LENGTH_SHORT).setTextColor(Color.RED).show();
            e.printStackTrace();
        } catch (IOException e) {
            //to handle read error
            e.printStackTrace();
        }


    }
}