package com.staffApp.Database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.staffApp.Models.Employee;

import java.util.HashMap;


public class DataBaseAdapter {

    private DatabaseReference database;

    public DataBaseAdapter(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        database=firebaseDatabase.getReference(Employee.class.getSimpleName());
    }


    public Task<Void> add(Employee employee)
    {

        return database.push().setValue(employee);
    }

    public  Task<Void> update(String key, HashMap<String,Object> hashMap)
    {
        /**return record passing key**/
        return  database.child(key).updateChildren(hashMap);
    }

    public  Task<Void> remove(String key)
    {
        /**return record passing key**/
        return  database.child(key).removeValue();
    }

    public Query get(String key)
    {
        if(key==null) {
            return  database.orderByKey().limitToFirst(8);
        }
            return database.orderByKey().startAfter(key).limitToFirst(8);


    }

    public Query get()
    {

        return database.orderByKey();


    }
}
