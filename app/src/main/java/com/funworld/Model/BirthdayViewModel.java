package com.funworld.Model;

import android.app.Application;

import com.funworld.Repository.BirthdayRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BirthdayViewModel extends AndroidViewModel {
private BirthdayRepository birthdayRepository;
private LiveData<List<Birthday>> allBirthday;
    public BirthdayViewModel(@NonNull Application application) {
        super(application);
        birthdayRepository= new BirthdayRepository(application);
        allBirthday= birthdayRepository.getAllBirthday();
    }

    public void Insert(Birthday birthday){
      birthdayRepository.Insert(birthday);
    }

    public void Update(Birthday birthday){
      birthdayRepository.Update(birthday);
    }

    public void Delete(Birthday birthday){
     birthdayRepository.Delete(birthday);
    }

    public void DeleteAll(){
        birthdayRepository.DeleteAll();
    }

    public LiveData<List<Birthday>>getAllBirthday(){
        return allBirthday;
    }
}
