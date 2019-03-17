package com.example.test_android;

import java.io.Serializable;
import java.util.Date;

public class Worker implements Serializable{

    private String firstName;
    private String lastName;
    private int age;
    private Date birthday;
    private String specialty;

    public Worker(String fName, String lName, Date birthday, String specialty)
    {
        firstName = fName;
        lastName = lName;
        this.birthday = birthday;
        this.specialty = specialty;

        Date date = new Date();
        age = date.getYear() - birthday.getYear();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getSpecialty() {
        return specialty;
    }
}
