package com.staffApp.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
/**Model class**/
public class Employee  implements Serializable {

    @Exclude
    private String idEmployee;
    private String name,email,position,phone,idBoss;

    public Employee(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Employee(String name, String position, String email, String phone, String idBoss) {
        this.name = name;
        this.position = position;
        this.email=email;
        this.phone=phone;
        this.idBoss=idBoss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdBoss() {
        return idBoss;
    }

    public void setIdBoss(String idBoss) {
        idBoss = idBoss;
    }
}
