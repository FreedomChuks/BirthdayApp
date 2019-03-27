package com.funworld.Repository;


import android.app.Application;
import android.os.AsyncTask;

import com.funworld.Model.Birthday;
import com.funworld.interfaces.BirthdayDB;
import com.funworld.interfaces.BirthdayDao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

public class BirthdayRepository {
    private BirthdayDao birthdayDao;
    private LiveData<List<Birthday>> allBirthday;

    public BirthdayRepository(Application application){
        BirthdayDB db =BirthdayDB.getInstance(application);
        birthdayDao=db.birthdayDao();
        allBirthday=birthdayDao.getallData();
    }

    public void Insert(Birthday birthday){
    new InsertAsyncTask(birthdayDao).execute(birthday);
    }

    public void Update(Birthday birthday){
        new UpdateAsyncTask(birthdayDao).execute(birthday);
    }

    public void Delete(Birthday birthday){
        new DeleteAsyncTask(birthdayDao).execute(birthday);
    }

    public void DeleteAll(){
        new DeleteAllAsyncTask(birthdayDao).execute();
    }

    public LiveData<List<Birthday>> getAllBirthday(){
        return allBirthday;
    }

    public static class InsertAsyncTask extends AsyncTask<Birthday,Void,Void> {
        private  BirthdayDao birthdayDao;

        public InsertAsyncTask(BirthdayDao birthdayDao) {
            this.birthdayDao = birthdayDao;
        }

        @Override
        protected Void doInBackground(Birthday... birthdays) {
            birthdayDao.Insert(birthdays[0]);
            return null;
        }

    }

    public static class UpdateAsyncTask extends AsyncTask<Birthday,Void,Void> {
        private  BirthdayDao birthdayDao;

        public UpdateAsyncTask(BirthdayDao birthdayDao) {
            this.birthdayDao = birthdayDao;
        }

        @Override
        protected Void doInBackground(Birthday... birthdays) {
           birthdayDao.Update(birthdays[0]);
           return null;
        }

    }


    public static class DeleteAsyncTask extends AsyncTask<Birthday,Void,Void> {
        private  BirthdayDao birthdayDao;

        public DeleteAsyncTask(BirthdayDao birthdayDao) {
            this.birthdayDao = birthdayDao;
        }

        @Override
        protected Void doInBackground(Birthday... birthdays) {
            birthdayDao.Delete(birthdays[0]);
            return null;
        }

    }


    public static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        private  BirthdayDao birthdayDao;

        public DeleteAllAsyncTask(BirthdayDao birthdayDao) {
            this.birthdayDao = birthdayDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            birthdayDao.DeleteAll();
            return null;
        }
    }


}
