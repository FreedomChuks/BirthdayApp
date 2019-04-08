package com.funworld.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.funworld.Model.Birthday;
import com.funworld.pojo.AlarmReceivers;
import com.funworld.pojo.SetAlermSerivce;

import java.util.ArrayList;

public class AlarmHelper {
//reinitiat alarm on Bootstart
    public static void setAllNotificationAlarms(Context context) {
        Intent serviceIntent = new Intent(context, SetAlermSerivce.class);
        context.startService(serviceIntent);
    }

    public static void cancelAllAlarms(Context context, ArrayList<Birthday> birthdays) {
        for (Birthday b: birthdays) {
            cancleAlarm(context, b.getLastName().hashCode());
        }
    }

    public static void cancleAlarm(Context context,int id){
        //creating the recever intent
        Intent calcelalarm = new Intent(context, AlarmReceivers.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context.getApplicationContext(),id,calcelalarm,PendingIntent.FLAG_UPDATE_CURRENT);
        //cancel alerm
        AlarmManager alarmManager=(AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager!=null){
            alarmManager.cancel(pendingIntent);
        }

    }
}
