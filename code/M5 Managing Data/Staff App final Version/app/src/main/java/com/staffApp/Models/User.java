package com.staffApp.Models;

import java.io.Serializable;

public class User  implements Serializable {

    private  String name, age,email,password;

    public User() {

    }



    public User(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
