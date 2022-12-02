package com.example_slide;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class FakeDb {


    private HashMap<String,String> users;

    private static FakeDb instance=null;

    private FakeDb(HashMap<String, String> users) {

        this.users=users;
    }

    public void setUsers(HashMap<String, String> users) {
        this.users = users;
    }

    public  static  FakeDb getInstance() {
        if(instance==null)
        {
            try {

                throw  new InvalidObjectException("FakeDb instance not created");
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }
        }

        return instance;

    }
    public static FakeDb create(HashMap<String, String> users)
    {
        if(instance!=null)
        {
            try {

                throw  new InvalidObjectException("FakeDb instance already created!");
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }

        }
        instance=new FakeDb(users);
                return instance;
    }


    public void addUser(String username, String password)
    {
        this.users.put(username,password);
    }

    public void deleteUser(String username, String password)
    {
        this.users.remove(username);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void  changePassword(String username, String password)
    {
        this.users.replace(username,password);

    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public boolean verifyCredential(String username, String password)
    {

        Iterator<Map.Entry<String,String>> entries=users.entrySet().iterator();
        while(entries.hasNext())
        {
            Map.Entry<String,String> entry=entries.next();
           // System.out.println(entry.getKey()+entry.getValue());
            if(entry.getKey().equals(username) && entry.getValue().equals(password))
            {
                return true;
            }

        }
        return  false;

    }







}