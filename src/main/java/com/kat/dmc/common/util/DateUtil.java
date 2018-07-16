package com.kat.dmc.common.util;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtil {
    public static Timestamp getCurrentDayTS(){
        LocalDate localDate = LocalDate.now();
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
    public static String toString(Date input, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(input);
    }

    public static Date getNextDay(Date checkingDate) {
        DateTime dtOrg = new DateTime(checkingDate);
        DateTime dtPlusOne = dtOrg.plusDays(1);
        return dtPlusOne.toDate();
    }
    public static Date getPreviousDay(Date checkingDate) {
        DateTime dtOrg = new DateTime(checkingDate);
        DateTime dtPlusOne = dtOrg.plusDays(-1);
        return dtPlusOne.toDate();
    }
}
