package com.funworld.pojo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.funworld.Model.Birthday;


import java.util.Calendar;


import androidx.annotation.Nullable;


public class SetAlermSerivce extends Service {

    public  void setAlerm(Birthday birthday, Context context){
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(birthday.getCalendar().getTime());
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.YEAR,birthday.getCalendar().get(Calendar.YEAR));
        int id=birthday.getLastName().hashCode();
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceivers.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),id,intent,0);
        AlarmManager alarmManager =(AlarmManager)context.getApplicationContext().getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
