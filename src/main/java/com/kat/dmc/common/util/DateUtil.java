package com.kat.dmc.common.util;

import com.kat.dmc.common.constant.DateConst;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.ParseException;
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

    public static Date getStartDay4MonthYear(Integer month, Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear(month);
        dtOrg = dtOrg.withYear(year);
        return dtOrg.toDate();
    }

    public static Date getEndDay4MonthYear(Integer month, Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear(month + 1);
        dtOrg = dtOrg.withYear(year);
        dtOrg = dtOrg.plusDays(-1);
        return dtOrg.toDate();
    }

    public static Date getStartDay4QuaterYear(Integer quater, Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear((quater - 1) * 3 + 1);
        dtOrg = dtOrg.withYear(year);
        return dtOrg.toDate();
    }

    public static Date getEndDay4QuaterYear(Integer quater, Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear(quater * 3);
        dtOrg = dtOrg.withYear(year);
        return dtOrg.toDate();
    }
    public static Date getStartDay4Year(Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear(1);
        dtOrg = dtOrg.withYear(year);
        return dtOrg.toDate();
    }

    public static Date getEndDay4Year(Integer year) {
        DateTime dtOrg = new DateTime();
        dtOrg = dtOrg.withDayOfMonth(1);
        dtOrg = dtOrg.withMonthOfYear(12);
        dtOrg = dtOrg.withYear(year);
        return dtOrg.toDate();
    }

    public static String YYYYMMDD2DDMMYYYY(String YYYYMMDD){
        Date yyyymmdd;
        try {
            yyyymmdd = new SimpleDateFormat(DateConst.FORMAT_YYYYMMDD).parse(YYYYMMDD);
        } catch (ParseException e) {
            return "";
        }
        return new SimpleDateFormat(DateConst.FORMAT_DD_MM_YYYY).format(yyyymmdd);
    }
}
