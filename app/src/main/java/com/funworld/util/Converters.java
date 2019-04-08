package com.funworld.util;

import android.telephony.PhoneNumberUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        if (value==null){
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(value*1000);
        return cal;
    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar calendar) {
        if (calendar==null){
            return null;
        }
        return calendar.getTimeInMillis()/1000;
    }
}
