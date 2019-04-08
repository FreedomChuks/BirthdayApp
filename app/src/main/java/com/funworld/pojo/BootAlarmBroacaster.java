package com.funworld.pojo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.funworld.util.AlarmHelper;

public class BootAlarmBroacaster extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Launch service to set alarms if ACTION_BOOT_COMPLETE is received
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            AlarmHelper.setAllNotificationAlarms(context);
        }
    }
}
