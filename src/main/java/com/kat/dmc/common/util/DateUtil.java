package com.kat.dmc.common.util;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DateUtil {
    public static Timestamp getCurrentDayTS(){
        LocalDate localDate = LocalDate.now();
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
