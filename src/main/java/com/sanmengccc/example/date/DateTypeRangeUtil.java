package com.sanmengccc.example.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * description: 获取当前时间的一些操作 <br>
 * date: 2020/6/17 9:13 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class DateTypeRangeUtil {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static final String WEEK = "WEEK";

    public static final String MONTH = "MONTH";

    public static final String QUARTER = "QUARTER";

    public static final String YEAR = "YEAR";



    /**
     * 包含0点与24点时间
     * 获取某个日期所在的那一周的开始与结束时间
     * @param nowDate 具体日期
     * @param isFirst true 表示开始时间;false表示结束时间
     * @return
     */
    public static String getStartOrEndDayOfWeek(LocalDate nowDate, Boolean isFirst){
        nowDate = Optional.ofNullable(nowDate).orElse(LocalDate.now());
        DayOfWeek week = nowDate.getDayOfWeek();
        int value = week.getValue();
        if (isFirst) {
            return LocalDateTime
                    .of(nowDate.minusDays(value - 1), LocalTime.MIN)
                    .format(DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDateTime
                .of(nowDate.plusDays(7 - value), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 包含0点与24点时间
     * 获取某个日期所在的那一月的开始与结束时间
     * @param nowDate 具体日期
     * @param isFirst true 表示开始时间;false表示结束时间
     * @return
     */
    public static String getStartOrEndDayOfMonth(LocalDate nowDate, Boolean isFirst){
        nowDate = Optional.ofNullable(nowDate).orElse(LocalDate.now());
        Month month = nowDate.getMonth();
        int length = month.length(nowDate.isLeapYear());
        if (isFirst) {
            return LocalDateTime
                    .of(LocalDate.of(nowDate.getYear(), month, 1), LocalTime.MIN)
                    .format(DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDateTime
                .of(LocalDate.of(nowDate.getYear(), month, length), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 包含0点与24点时间
     * 获取某个日期所在的那一季度的开始与结束时间
     * @param nowDate 具体日期
     * @param isFirst true 表示开始时间;false表示结束时间
     * @return
     */
    public static String getStartOrEndDayOfQuarter(LocalDate nowDate, Boolean isFirst){
        nowDate = Optional.ofNullable(nowDate).orElse(LocalDate.now());
        Month month = nowDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            return LocalDateTime
                    .of(LocalDate.of(nowDate.getYear(), firstMonthOfQuarter, 1), LocalTime.MIN)
                    .format(DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDateTime
                .of(LocalDate.of(nowDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(nowDate.isLeapYear())), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 包含0点与24点时间
     * 获取某个日期所在的那一年度的开始与结束时间
     * @param nowDate 具体日期
     * @param isFirst true 表示开始时间;false表示结束时间
     * @return
     */
    public static String getStartOrEndDayOfYear(LocalDate nowDate, Boolean isFirst){
        nowDate = Optional.ofNullable(nowDate).orElse(LocalDate.now());
        if (isFirst) {
            return LocalDateTime
                    .of(LocalDate.of(nowDate.getYear(), Month.JANUARY, 1), LocalTime.MIN)
                    .format(DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDateTime
                .of(LocalDate.of(nowDate.getYear(), Month.DECEMBER, Month.DECEMBER.length(nowDate.isLeapYear())), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 根据类型获取
     * @param dateType
     * @param localDate
     * @param isFirst
     * @return
     */
    public static String getStartOrEndDayOfType(String dateType, LocalDate localDate, Boolean isFirst) {
        switch (dateType) {
            case WEEK:
                return getStartOrEndDayOfWeek(localDate, isFirst);
            case QUARTER:
                return getStartOrEndDayOfQuarter(localDate, isFirst);
            case MONTH:
                return getStartOrEndDayOfMonth(localDate, isFirst);
            case YEAR:
                return getStartOrEndDayOfYear(localDate, isFirst);
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(getStartOrEndDayOfWeek(LocalDate.now(), false));
        System.out.println(getStartOrEndDayOfWeek(LocalDate.now(), true));
        System.out.println(getStartOrEndDayOfWeek(null, true));
        System.out.println("-----------------------------------------------");
        System.out.println(getStartOrEndDayOfMonth(LocalDate.now(), false));
        System.out.println(getStartOrEndDayOfMonth(LocalDate.now(), true));
        System.out.println(getStartOrEndDayOfMonth(null, true));
        System.out.println("-----------------------------------------------");
        System.out.println(getStartOrEndDayOfQuarter(LocalDate.now(), false));
        System.out.println(getStartOrEndDayOfQuarter(LocalDate.now(), true));
        System.out.println(getStartOrEndDayOfQuarter(null, true));
        System.out.println("-----------------------------------------------");
        System.out.println(getStartOrEndDayOfYear(LocalDate.now(), false));
        System.out.println(getStartOrEndDayOfYear(LocalDate.now(), true));
        System.out.println(getStartOrEndDayOfYear(null, true));
    }

}
