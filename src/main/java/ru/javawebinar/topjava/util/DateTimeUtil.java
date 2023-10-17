package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static LocalDateTime startDateTime = LocalDateTime.MIN;
    public static LocalDateTime endDateTime = LocalDateTime.MAX;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

//    public static boolean isBetweenTimeHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
//        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
//    }

    public static boolean isBetweenDateAndTimeHalfOpen(LocalDate localDate, LocalTime localTime, LocalDateTime sdt, LocalDateTime edt) {
        return localDate.compareTo(sdt.toLocalDate()) >= 0
                && localDate.compareTo(edt.toLocalDate()) <= 0
                && localTime.compareTo(sdt.toLocalTime()) >= 0
                && localTime.compareTo(edt.toLocalTime()) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

