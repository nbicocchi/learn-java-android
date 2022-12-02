package com.SQLite.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseAdapter  {

    private SQLiteHelper sqLiteHelper;
    private Context context;

    public DataBaseAdapter(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
        this.context=context;
    }

    public long  insertData(String name, String position)
    {

        ContentValues contentValues=new ContentValues();
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        contentValues.put(SQLiteHelper.NAME,name);
        contentValues.put(SQLiteHelper.POSITION,position);
        long id=db.insert(sqLiteHelper.TABLE_NAME,null,contentValues);
        Log.d("value",String.valueOf(id));
        return id;

    }

   /* public Hasmap<String,String>  getAllData()
    {
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        String columns[]={sqLiteHelper.UID,sqLiteHelper.NAME,sqLiteHelper.POSITION};
        StringBuffer buffer=new StringBuffer();
        Cursor cursor = db.query(SQLiteHelper.TABLE_NAME, columns, null, null, null, null,null);
        int i=0;

        if(cursor==null)
        {
            return "no data was found";
        }
        String name,position;
        while(cursor.moveToNext()) {

            int id=cursor.getInt(0);
            name=cursor.getString(1);
            position=cursor.getString(2);
            buffer.append(id+" "+name+" "+" "+position+"\n");


        }
        return buffer.toString();

    }*/

    public int deleteData(String name)
    {
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        String[] whereArgs={name};
        String deleteQuery=SQLiteHelper.NAME+"=?";
        int num=db.delete(SQLiteHelper.TABLE_NAME,deleteQuery,whereArgs);
        return num;

    }

    public int  updateData(String name, String position)
    {
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        ArrayList<String> users=getUsers();
        if(users.contains(name))
        {
            String queryUpdate=sqLiteHelper.NAME+"=?";
            ContentValues contentValues=new ContentValues();
            contentValues.put(sqLiteHelper.NAME,name);
            contentValues.put(sqLiteHelper.POSITION,position);
            String whereArgs[]={name};
            int rowNum=db.update(SQLiteHelper.TABLE_NAME,contentValues,queryUpdate,whereArgs);
            return rowNum;
        }
        return -1;

    }

    public String getAllData(){
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        String columns[]={sqLiteHelper.UID,sqLiteHelper.NAME,sqLiteHelper.POSITION};
        /**1st way**/
        /*
        String whereCondition=columns[0]+"=\""+name+"\"";
        Cursor cursor=db.query(sqLiteHelper.TABLE_NAME,columns,whereCondition,null,null,null,null);
        */
        /***2nd way**/
        Cursor cursor=db.query(sqLiteHelper.TABLE_NAME,columns,null,null,null,null,null);
        Log.d("cursor",cursor.toString());
        StringBuffer stringBuffer=new StringBuffer();
        int index;
        if(cursor!=null) {

            while (cursor.moveToNext()) {

                /***
                 * we could also specify index row
                 * String name=cursor.getString(1)
                 * String position=cursor.getString(2)
                 * In this particularly  case all values are string so we can use a for to retrieve data.
                 * This is way better, because if database structure change nothing will happen to this piece of code,
                 * Also column returned may change depending of query we perform
                 */
                for (int i = 0; i < columns.length; ++i) {
                    index = cursor.getColumnIndex(columns[i]);
                    stringBuffer.append(cursor.getString(index) + " ");

                }
                stringBuffer.append("\n");

            }
        }
        else
        {
            Toast.makeText(context,"empty Database",Toast.LENGTH_SHORT).show();

        }
        return stringBuffer.toString();

    }

    public ArrayList<String> getUsers()
    {

        String columns[]={sqLiteHelper.NAME};
        SQLiteDatabase db=sqLiteHelper.getReadableDatabase();
        Cursor cursor=db.query(sqLiteHelper.TABLE_NAME,columns,null,null,null,null,null);
        ArrayList<String> users=new ArrayList<>();
        int index= cursor.getColumnIndex(sqLiteHelper.NAME);
        while (cursor.moveToNext()) {
            users.add(cursor.getString(index));
        }
        return  users;
    }

}
