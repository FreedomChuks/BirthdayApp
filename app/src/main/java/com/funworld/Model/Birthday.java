package com.funworld.Model;

import com.funworld.util.Converters;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "birthday_table")
public class Birthday {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;
    @TypeConverters(Converters.class)
    private Date dob;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public Date getDob() {
        return dob;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Birthday (String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
