package com.funworld.Model;

import com.funworld.util.Converters;

import java.util.Calendar;
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
    private Calendar calendar;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Calendar getCalendar() {
        return calendar;
    }
    public Birthday(String firstName, String lastName, Calendar calendar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.calendar = calendar;
    }

    public void setId(int id) {
        this.id = id;
    }
}
