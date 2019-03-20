package com.example.test_android;

public class Specialty{

    private int specialty_id;
    private String name;

    public Specialty(int specialty_id, String name){
        this.specialty_id = specialty_id;
        this.name = name;
    }

    public int getSpecialty_id() {
        return specialty_id;
    }

    public String getName() {
        return name;
    }
}
