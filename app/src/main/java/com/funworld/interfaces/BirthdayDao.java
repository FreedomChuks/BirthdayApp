package com.funworld.interfaces;

import com.funworld.Model.Birthday;
import com.funworld.util.Converters;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

@Dao
public interface BirthdayDao {
    @TypeConverters({Converters.class})
    @Insert
    void Insert(Birthday birthday);

    @Update
    void Update(Birthday birthday);

    @Delete
    void Delete(Birthday birthday);

    @Query("Delete From birthday_table")
     void DeleteAll();

    @Query("Select * from birthday_table")
    LiveData<List<Birthday>> getallData();

}
