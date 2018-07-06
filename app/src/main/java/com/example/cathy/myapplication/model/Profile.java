package com.example.cathy.myapplication.model;

public class Profile {
    private int id;
    private String name;
    private String dam;
    private String sire;
    private String age;
    private String gender;
    private String paddock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDam() {
        return dam;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPaddock() {
        return paddock;
    }

    public void setPaddock(String paddock) {
        this.paddock = paddock;
    }
}
