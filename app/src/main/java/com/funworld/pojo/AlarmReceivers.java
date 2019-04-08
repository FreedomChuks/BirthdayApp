package com.funworld.pojo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.funworld.MainActivity;
import com.funworld.R;

import androidx.core.app.NotificationCompat;


public class AlarmReceivers extends BroadcastReceiver {
    public static final String USER_NAME="uname";
    String name;
    public static final int NOTIFICATION_ID=1;
    public static final String NOTIFICATION_CHANNEL="com.funworld.pojo.channel";
    long[] vaibrator_pattern={0,100,100,100,100,100};
    NotificationManager notificationManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        name=(String)intent.getExtras().get(USER_NAME);
        notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        startNotification(context);
    }

    public void startNotification(Context context){
        // the notification takes you back to the app
     Intent intent  = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(context,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Build The Notofication
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_cake)
                .setContentTitle("Fun World Birthday Reminder")
                .setContentText("it "+ name +" Birthday Today")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setVibrate(vaibrator_pattern)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
                notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}
